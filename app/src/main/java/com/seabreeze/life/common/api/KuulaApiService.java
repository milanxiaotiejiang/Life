package com.seabreeze.life.common.api;

import com.seabreeze.life.entity.kuula.KuulaBean;
import com.seabreeze.life.entity.kuula.KuulaListBean;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cuieney on 2017/6/8.
 */

public interface KuulaApiService {
//    @GET("?action=explore&tag=featured&limit=12&time=0&app=vr")
//    Observable<KuulaListBean> getKuulaList(@Query("offset") int offset);

    @GET("?action=explore&tag=featured&cid=0&&time=0")
    Observable<KuulaListBean> getKuulaList(@Query("offset") int offset);

//    @GET("?action=get&app=vr")
    @GET("?action=get")
    Observable<KuulaBean> getKuulaImage(@Query("id") String id);

    @GET("?action=comments")
    Observable<ResponseBody> getKuulaComments(@Query("id") String id);

}
