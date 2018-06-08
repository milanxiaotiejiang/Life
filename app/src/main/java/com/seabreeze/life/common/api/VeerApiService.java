package com.seabreeze.life.common.api;

import com.seabreeze.life.entity.veer.VeerListBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by cuieney on 2017/6/6.
 */

public interface VeerApiService {

    //    @GET("overall_featured_videos")
    @GET("videos/featured_by_page")
    Observable<VeerListBean> getVeer(@Query("page") int page);


    @GET("categories/{id}")
    Observable<ResponseBody> getCatagory(@Path("id") int id, @Query("order") String order, @Query("page") int page);


}
