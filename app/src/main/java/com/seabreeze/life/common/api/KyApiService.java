package com.seabreeze.life.common.api;

import com.seabreeze.life.entity.video.VideoListBean;
import com.seabreeze.life.entity.video.VideoListBean2;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KyApiService {

    @GET("api/v4/tabs/selected")
    Observable<VideoListBean> getVideoList(@Query("date") String date);

    //    baobab.kaiyanapp.com	/api/v1/video/related/64607?num=10
    @GET("api/v1/video/related/{id}")
    Observable<VideoListBean2> getVideoRelated(@Path("id") int id, @Query("num") int num);
}
