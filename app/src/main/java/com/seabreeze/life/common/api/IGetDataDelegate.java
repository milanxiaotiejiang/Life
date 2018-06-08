package com.seabreeze.life.common.api;

public interface IGetDataDelegate<T> {

    void getDataSuccess(T t);

    void getDataError(String errmsg);

}
