package com.seabreeze.life.mvp.view;


public interface SplashView {

    /**
     * 跳转到主界面
     */
    void navToHome();


    /**
     * 跳转到登录界面
     */
    void navToLogin();

    /**
     * 是否已有用户登录
     */
    boolean isUserLogin();

}
