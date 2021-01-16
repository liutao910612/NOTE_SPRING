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
# 2.BeanFactory准备阶段
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

