package com.hhz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by haohz on 2018/2/1.
 * 动态代理
 */

public class StaticProxyHandler implements InvocationHandler{
    /* 要代理的对象-授权代理方Subject */
    private Object subject;
    /* 构造方法，给要代理的对象赋初值 */
    public StaticProxyHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogT.w("代理前的操作,操作方法名："+method.getName());
        if(method.getName().equals("swipeCard")){
            method.invoke(subject,args);
            LogT.w("代理swipeCard操作");
        }
        /* 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用 */
        else{
            method.invoke(subject,args);
            LogT.w("代理其他操作");
        }

        LogT.w("代理后的操作");
        return null;
    }
}
