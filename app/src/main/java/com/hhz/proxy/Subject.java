package com.hhz.proxy;

/**
 * Created by haohz on 2018/2/1.
 * 抽象操作类，授权代理方调用
 */

public interface Subject {
    /* 刷卡 */
    void swipeCard();
    /* 付款 */
    void charge(String str);

}
