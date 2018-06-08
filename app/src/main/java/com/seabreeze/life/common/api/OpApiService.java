package com.seabreeze.life.common.api;

import com.seabreeze.life.entity.essay.EssayDetailBean;
import com.seabreeze.life.entity.essay.EssayListBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpApiService {

    //    @GET("channel/reading/more/{id}?platform=ios&uuid=850717CA-9EF7-429E-92B3-6B88B479A476&user_id=&version=v4.2.1")
//    Observable<EssayListBean> getEssayList(@Path("id") int id);
    @GET("channel/reading/more/{id}")
    Observable<EssayListBean> getEssayList(@Path("id") int id);


    @GET("essay/{id}?platform=ios&uuid=850717CA-9EF7-429E-92B3-6B88B479A476&user_id=&version=v4.2.1&source=channel_reading")
    Observable<EssayDetailBean> getEssayDetail(@Path("id") int id, @Query("source_id") int sourceId);
}
