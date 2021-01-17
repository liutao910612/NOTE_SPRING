# 概述
Spring容器的生命周期开始是经过org.springframework.context.support.AbstractApplicationContext.refresh来实现的，
通过调用这个方法后,Spring容器就处于了工作状态，当然在这里面会有一系列的操作，接下来我们针对每个步骤会进行详细的讨论。
# 1.Spring应用上下文准备阶段
准备阶段是通过 **AbstractApplicationContext#prepareRefresh()** 方法来实现的，这这里面会做一下一些事情：<br/>
**（1）启动时间-startupDate**   
设置启动时间为当前时间。   
**（2）状态标识-closed(false),active(true)**  
针对容器的状态进行设置，就涉及到了容器的关闭状态（closed），用于表示容器是否关闭，以及容器的
激活状态（active），用于表示容器是否激活。针对关闭状态。这两个状态在我们调用close()方法的时候会被分别置为true和false。   
**（3）初始化PropertySources-initPropertySources()**   
这里我们首先来看下源码：  
```java
protected void initPropertySources() {
		// For subclasses: do nothing by default.
	}
```
通过上面的源码我们可以看见在AbstractApplicationContext类里面这里是没有做任何操作的，这里其实相当与给子类预留了一个初始化PropertySource
的方法，通过查看其子类StaticWebApplicationContext#initPropertySources，可以发现这里面其实就是在向Environment
中添加需要的PropertySource内容。   
**（4）检验Environment中的必要属性**   
查看源码如下：   
```java
getEnvironment().validateRequiredProperties();
```
这最终会调用到AbstractEnvironment.validateRequiredProperties方法，而方法里面又会调用到AbstractPropertyResolver.validateRequiredProperties，
查看AbstractEnvironment的继承关系，AbstractEnvironment -> ConfigurableEnvironment -> ConfigurablePropertyResolver,同时AbstractPropertyResolver -> ConfigurablePropertyResolver
,很容易发现，这里使用了一个简单的装饰者模式。   
**（5）初始化事件监听器集合**   
**（6）初始化Spring早期事件集合**  
# 2.BeanFactory创建阶段
BeanFactory的创建阶段是调用 **AbstractApplicationContext#obtainFreshBeanFactory()** 方法来实现的。这里具体包含了刷新Spring应用上下文底层BeanFactory（refreshBeanFactory）和获取
Spring应用上下文底层BeanFactory（getBeanFactory）两个操作。   
这两个操作其实用到了 **模板方法设计模式**，因为在AbstractApplicationContext里面这两个操作的方法都为抽象方法，具体实现被放到了其子类里面。   
现在我们仅仅关注AbstractRefreshableApplicationContext这个子类就行了。    
**（1）刷新Spring应用上下文底层BeanFactory-refreshBeanFactory**
```java
    @Override
	protected final void refreshBeanFactory() throws BeansException {
		
        //如果BeanFactory已经存在，则销毁并且关闭BeanFactory
        if (hasBeanFactory()) {
			destroyBeans();
			closeBeanFactory();
		}
		try {
            
            //创建BeanFactory
			DefaultListableBeanFactory beanFactory = createBeanFactory();
			
			//设置BeanFactory Id
			beanFactory.setSerializationId(getId());
			
			//自定义BeanFactory的属性
			customizeBeanFactory(beanFactory);
			
			//加载BeanDefinition
			loadBeanDefinitions(beanFactory);
			synchronized (this.beanFactoryMonitor) {
			    
			    //关联BeanFactory到应用上下文
				this.beanFactory = beanFactory;
			}
		}
		catch (IOException ex) {
			throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
		}
	}
```
从上面的源码我们可以看出，在Spring应用上下文中关联的BeanFactory的具体实现类为DefaultListableBeanFactory。   
针对源码中的销毁或者关闭BeanFactory其实最终其实都是调的BeanFactory自己的方法来实现的。   
这里自定义BeanFactory属性，是对是否允许循坏依赖和Bean重复定义来进行设置的。   
加载BeanDefinition,这里就涉及到了不同的实现了，当然具体实现还是由AbstractRefreshableApplicationContext的子类来完成。
这里就有XML的实现（AbstractXmlApplicationContext），注解的实现（AnnotationConfigWebApplicationContext）等。
# 3.BeanFactory准备阶段
前面我们已经拥有了BeanFactory实例，现在我们就需要针对该实例来做一些操作，以达到Spring框架的方便使用，这里就是BeanFactory
的准备阶段。准备阶段使用过调 **AbstractApplicationContext#prepareBeanFactory(ConfigurableListableFactory)** 方法来实现的。
```java
protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    //1关联ClassLoader.
    beanFactory.setBeanClassLoader(getClassLoader());
    
    //2设置Bean表达式处理器
    beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader()));
    
    //3添加PropertyEditorRegistrar实现
    beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));
    
    //4添加Aware回调接口BeanPostProcessor实现
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    
    //5忽略Aware回调接口作为依赖注入接口
    beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
    beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
    beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
    beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
    beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
    beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);
    
    //5注入ResolvableDependency对象
    beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
    beanFactory.registerResolvableDependency(ResourceLoader.class, this);
    beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
    beanFactory.registerResolvableDependency(ApplicationContext.class, this);
    
    //6注入ApplicationListenerDetector对象
    beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));
    
    //7注入LoadTimeWeaverAwareProcessor对象
    if (beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
        beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
        // Set a temporary ClassLoader for type matching.
        beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
    }
    
    //8注入单例对象
    if (!beanFactory.containsLocalBean(ENVIRONMENT_BEAN_NAME)) {
        beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
    }
    if (!beanFactory.containsLocalBean(SYSTEM_PROPERTIES_BEAN_NAME)) {
        beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
    }
    if (!beanFactory.containsLocalBean(SYSTEM_ENVIRONMENT_BEAN_NAME)) {
        beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
    }
}
```
这里为什么要设置ClassLoader呢？由于读取XML配置文件的Bean定义的时候，我们仅仅获取到的是类的全名信息，那么我们如何通过反射来创建具体的对象呢？这个时候就需要用到
ClassLoader。   
设置Bean的表达式处理器，针对SpringEL表达式的处理。    
在这里对Aware接口的注入进行了忽略，其实很明显如果我们注入Aware接口，没有任何意义，因为我们只关注Aware回调的对象。    
针对ApplicationListenerDetector的注入，这个类实现了DestructionAwareBeanPostProcessor和MergedBeanDefinitionPostProcessor，其实是在Bean销毁和
BeanDefinition merge的时候来对类型是ApplicationListener来进行处理。    
总结上面的内容，我们可以发现这个阶段就是做一些准备来帮助我们进行依赖查找和注入，以及将一些辅助Bean添加到上下文里面。   


