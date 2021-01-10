package com.kevin.spring.event;

import com.kevin.base.utils.PrintUtil;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性Spring事件传播示例
 *
 * @Author:Kevin
 * @Date:Created in 15:46 2021/1/10
 */
public class HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);

        currentContext.setParent(parentContext);

        parentContext.refresh();
        currentContext.refresh();

        currentContext.close();
        parentContext.close();

    }

    static class MyListener implements ApplicationListener<ApplicationEvent>{

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            PrintUtil.print("Listened Spring application event : " + event);
        }
    }
}
