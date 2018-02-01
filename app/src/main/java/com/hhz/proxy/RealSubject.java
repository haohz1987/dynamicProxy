package com.hhz.proxy;

/**
 * Created by haohz on 2018/2/1.
 * 授权代理方操作
 */

public class RealSubject implements Subject {
    private String charge;
    @Override
    public void swipeCard() {
        LogT.w("刷卡*************");
    }

    @Override
    public void charge(String str) {
        charge=str;
        LogT.w("付款:"+charge);
    }
}
