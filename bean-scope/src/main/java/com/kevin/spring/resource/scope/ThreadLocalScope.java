package com.kevin.spring.resource.scope;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Kevin
 * @Date:Created in 21:13 2020/12/15
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local";
    private final NamedThreadLocal<Map<String,Object>> threadLocal = new NamedThreadLocal("thread-local-scope"){
        public Map<String,Object> initialValue(){
            return new HashMap<>();
        }
    };


    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        //非空
        Map<String,Object> context = getContext();
        Object obj = context.get(name);
        if(obj == null){
            obj = objectFactory.getObject();
            context.put(name,obj);
        }
        return obj;
    }

    @NotNull
    private Map<String, Object> getContext() {
        return threadLocal.get();
    }

    @Override
    public Object remove(String name) {
        Map<String,Object> context = getContext();
        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //TODO
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String,Object> context = getContext();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
