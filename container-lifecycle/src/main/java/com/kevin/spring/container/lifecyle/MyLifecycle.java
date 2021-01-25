package com.kevin.spring.container.lifecyle;

import org.springframework.context.Lifecycle;

/**
 * Customized {@link Lifecycle}
 *
 * @Author:Kevin
 * @Date:Created in 20:44 2021/1/25
 */
public class MyLifecycle implements Lifecycle {
    private boolean running;

    @Override
    public void start() {
        running = true;
        System.out.println("MyLifecycle start...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("MyLifecycle stop...");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
