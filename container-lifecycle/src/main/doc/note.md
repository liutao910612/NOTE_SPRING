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
# 4.BeanFactory后置处理阶段
BeanFactory后置处理是再次对BeanFactory做一些定制和数据存储，可能是注册新的BeanFactory，也可能是调整BeanFactory的一些行为。在AbstractApplicationContext
中是通过如下两个方法来实现的   
```java
// Allows post-processing of the bean factory in context subclasses.
postProcessBeanFactory(beanFactory);

// Invoke factory processors registered as beans in the context.
invokeBeanFactoryPostProcessors(beanFactory);
```
postProcessBeanFactory是由子类来实现的，在我们扩展beanFactory后置处理的时候很少会选择这种方法，因为这需要通过继承来实现，而invokeBeanFactoryPostProcessors
这种方法类似与组合，我们更多选择这种方法来实现后置处理。   
**（1）postProcessBeanFactory**    
我们看AnnotationConfigServletWebServerApplicationContext#postProcessBeanFactory   
```java
@Override
protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
super.postProcessBeanFactory(beanFactory);
if (this.basePackages != null && this.basePackages.length > 0) {
    this.scanner.scan(this.basePackages);
}
if (!this.annotatedClasses.isEmpty()) {
    this.reader.register(ClassUtils.toClassArray(this.annotatedClasses));
```
这里在执行当前方法的操作之前首先调了父类的方法，这里通过扫描basePackages来生成一些bean，这其实就是注解实现Bean的逻辑入口，并且注册Config class。   
**（2）invokeBeanFactoryPostProcessors**    
```java
protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    //获取到所有的BeanFactoryPostProcessor并进行激活
    PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());

    // Detect a LoadTimeWeaver and prepare for weaving, if found in the meantime
    // (e.g. through an @Bean method registered by ConfigurationClassPostProcessor)
    if (beanFactory.getTempClassLoader() == null && beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
        beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
        beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
    }
}
```   
上面的激活BeanFactoryPostProcessor包含了BeanDefinitionRegistryPostProcessor 和BeanFactoryPostProcessor。     
# 5.BeanFactory注册BeanPostProcessor阶段
到目前为止，经过BeanFactory后置处理之后，我们基本可以认为BeanFactory的初始化完成了。接下来就是注册BeanPostProcessor，同样的BeanPostProcessor的注册和
BeanFactory的后置处理类似，都遵循PriorityOrdered , Ordered 和普通的PostProcessor的注册顺序。    
```java
protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
}
```   
同样，这里使用了PostProcessorRegistrationDelegate来进行BeanPostProcessor的注册。   
```java
public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

    //获取到所有的postProcessor的name
    String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

    // Register BeanPostProcessorChecker that logs an info message when
    // a bean is created during BeanPostProcessor instantiation, i.e. when
    // a bean is not eligible for getting processed by all BeanPostProcessors.
    int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
    beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

    // Separate between BeanPostProcessors that implement PriorityOrdered,
    // Ordered, and the rest.
    List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
    List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
    List<String> orderedPostProcessorNames = new ArrayList<>();
    List<String> nonOrderedPostProcessorNames = new ArrayList<>();
    for (String ppName : postProcessorNames) {
        if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
            BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
            priorityOrderedPostProcessors.add(pp);
            if (pp instanceof MergedBeanDefinitionPostProcessor) {
                internalPostProcessors.add(pp);
            }
        }
        else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
            orderedPostProcessorNames.add(ppName);
        }
        else {
            nonOrderedPostProcessorNames.add(ppName);
        }
    }

    // First, register the BeanPostProcessors that implement PriorityOrdered.
    sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
    registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

    // Next, register the BeanPostProcessors that implement Ordered.
    List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
    for (String ppName : orderedPostProcessorNames) {
        BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
        orderedPostProcessors.add(pp);
        if (pp instanceof MergedBeanDefinitionPostProcessor) {
            internalPostProcessors.add(pp);
        }
    }
    sortPostProcessors(orderedPostProcessors, beanFactory);
    registerBeanPostProcessors(beanFactory, orderedPostProcessors);

    // Now, register all regular BeanPostProcessors.
    List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
    for (String ppName : nonOrderedPostProcessorNames) {
        BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
        nonOrderedPostProcessors.add(pp);
        if (pp instanceof MergedBeanDefinitionPostProcessor) {
            internalPostProcessors.add(pp);
        }
    }
    registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

    // Finally, re-register all internal BeanPostProcessors.
    sortPostProcessors(internalPostProcessors, beanFactory);
    registerBeanPostProcessors(beanFactory, internalPostProcessors);

    // Re-register post-processor for detecting inner beans as ApplicationListeners,
    // moving it to the end of the processor chain (for picking up proxies etc).
    beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
}
```   
从上面的源码我们可以很容易地看出来，这里将BeanPostProcessor分成了三个部分：   
+ priorityOrderedPostProcessors
+ orderedPostProcessors
+ nonOrderedPostProcessors   
在获取到这三个部分之后并分别对每个部分进行排序，以此来保证BeanPostProcessor处理的先后顺序。所以在我们实现自己的PostProcessor的时候我们可以利用这一点
来确保自己的PostProcessor的执行顺序。
# 6.初始化内建Bean:MessageSource
初始化MessageSource的逻辑在这里就比较简单了，我们先来看一看initMessageSource的具体实现：   
```java
protected void initMessageSource() {
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    if (beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME)) {
        this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
        // Make MessageSource aware of parent MessageSource.
        if (this.parent != null && this.messageSource instanceof HierarchicalMessageSource) {
            HierarchicalMessageSource hms = (HierarchicalMessageSource) this.messageSource;
            if (hms.getParentMessageSource() == null) {
                // Only set parent context as parent MessageSource if no parent MessageSource
                // registered already.
                hms.setParentMessageSource(getInternalParentMessageSource());
            }
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Using MessageSource [" + this.messageSource + "]");
        }
    }
    else {
        // Use empty MessageSource to be able to accept getMessage calls.
        DelegatingMessageSource dms = new DelegatingMessageSource();
        dms.setParentMessageSource(getInternalParentMessageSource());
        this.messageSource = dms;
        beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
        if (logger.isTraceEnabled()) {
            logger.trace("No '" + MESSAGE_SOURCE_BEAN_NAME + "' bean, using [" + this.messageSource + "]");
        }
    }
}
``` 
从上面的源码我们可以看出，这里的逻辑就是将messageSource注册为单例bean，并且设置他的双亲委派关系。   
# 7.初始化内建Bean:Spring事件广播器
内建事件广播器的实现也比较简单，调用的是方法**initApplicationEventMulticaster**,这里判断如果不存在就new一个SimpleApplicationEventMulticaster。
# 8.Spring应用上下文刷新阶段
Spring应用上下文刷新阶段**onRefresh**，字面意思是当应用上下文刷新的时候，有点类似事件。这个方法在AbstractApplicationContext里面的实现是一个空方法，
子类可以通过实现这个方法来对做一些自己的操作，当然这里就可以利用到前面的步骤中已经初始化好的Bean。
# 9.Spring事件监听器注册阶段
Spring事件监听器注册阶段是通过**registerListeners**方法来进行实现的，这个方法里面包含下面三个步骤：   
+ 添加当前应用上下文所关联的ApplicationListener对象（集合）
+ 添加BeanFactory所注册ApplicationListener Beans
+ 广播早期Spring事件   
由于只要ApplicationContext被实例化后，就可以往里面添加ApplicationListener,但是这个时候我们的事件广播器并没有实例化好，
所以在需要在注册事件监听器的时候首先获取
当前应用上下文中关联的事件监听器来进行注册。   
这里针对早期Spring事件的产生,如果我们实现了ApplicationEventPublisherAware，获取到了ApplicationEventPublisher，并广播事件，
这个时候如果广播器没有初始化完成，就会将事件进行缓存，所以这个时候就需要发布早期事件的一个操作。
# 10.BeanFactory初始化完成阶段
# 11.Spring应用上下文刷新完成阶段
Spring应用上下文刷新完成阶段是定义在AbstractApplicationContext#finishRefresh方法里面的，这个方法里面分为以下几个小步骤：   
1. 清除ResourceLoader缓存-clearResourceCaches()@since5.0
2. 初始化LifecycleProcessor对象-initLifecycleProcessor()
3. 调用LifecycleProcessor#onRefresh()方法
4. 发布Spring应用上下文刷新事件-ContextRefreshedEvent
5. 向MBeanServer托管Live Beans   
# 12.Spring应用上下文启动阶段
Spring应用上下文启动阶段，需要手动去调用start方法。刷新可以认为是一种广义的启动，start是指狭义的启动。这是Spring扩展的生命周期，这个生命周期的作用有以下两种：
+ 启动LifecycleProcessor   
依赖查找Lifecycle Beans   
启动Lifecycle Beans , 调用每个Bean的start方法来完成启动。   
+ 发布Spring应用上下文已启动事件-ContextStartedEvent

# 13.Spring应用上下文停止阶段
Spring应用上下文停止阶段，和Spring应用上下文启动阶段类似，需要手动调用stop()方法。这个阶段完成的事情就和start方法是
对应的。   
+ 停止LifecycleProcessor   
依赖查找Lifecycle Beans   
停止Lifecycle Beans , 调用每个Bean的stop方法来完成启动。   
+ 发布Spring应用上下文停止事件-ContextStoppedEvent

# 14.Spring应用上下文关闭阶段
Spring应用上下文的关闭是通过调用AbstractApplicationContext#close方法来完成的，这个阶段主要完成以下内容：    
+ 状态标识：active(false)、closed(true)
+ Live Beans JMX撤销托管   
LiveBeansView.unregisterApplicationContext   
+ 发布Spring应用上下文已关闭事件-ContextClosedEvent
+ 关闭LifecycleProcessor   
依赖查找Lifecycle Beans   
停止Lifecycle Beans   
+ 销毁Spring Beans
+ 关闭Bean Factory
+ 回调onClose()   
+ 注册Shutdown Hook线程（如果曾注册）
