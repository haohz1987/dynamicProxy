package com.hhz.proxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    private Subject subject;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        /* 要代理的真是对象 */
        RealSubject realSubject = new RealSubject();
        /* 要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的 */
        InvocationHandler handler = new StaticProxyHandler(realSubject);
        /**
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象
         * 来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象
         * 所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         * */
        subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),handler);
        LogT.w("代理对象："+realSubject.getClass().getName());
        findViewById(R.id.btn_static_proxy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject.swipeCard();
                tv.setText("代理刷卡");
            }
        });
        findViewById(R.id.btn_dynamic_proxy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogT.w("*****************************");
                subject.charge("刷卡金额：￥100");
                tv.setText("代理缴费");
            }
        });
    }
}
