package com.seabreeze.life.common.api;

import com.seabreeze.life.entity.music.BannerListBean;
import com.seabreeze.life.entity.music.MusicBean;
import com.seabreeze.life.entity.music.MusicListBean;
import com.seabreeze.life.entity.music.TrackBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WyApiService {

//    @GET("mgmagazinelist/r/10/page/{id}/sign=2230926e0bb334c908c9f7fabdaf42014e1afb31c17cd4d53b52fcd3bc34d501&api_key=08b1e567157582019f7fe639c841c42a&timestrap=1488600156")
//    Observable<List<MusicListBean>> getMusicList(@Path("id") int id);

    ///app/v1/track/list_by_user?page=1&size=200&user_id=47962&category=2&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524541397
    @Headers("Authorization: wawa=bfabbeaf62642e2a029eb995d121a5cf")
    @GET("/app/v1/musician/listbyweb?timestamp=1524538141&api_key=0fcf845a413e11beb5606448eb8abbc4")
    Observable<MusicBean> getMusicList(@Query("page") int page, @Query("size") int size);

    ///app/v1/track/list_by_user?page=1&size=200&user_id=48109&category=2&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524542135


    //    wawa.fm   /app/v1/label/list_track?page=1&size=50&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097
    @GET("/app/v1/label/list_track?page=1&size=50&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097")
    Observable<ResponseBody> getLabelListTrack();

    //    wawa.fm	/app/v1/banner/list?api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097
    @Headers("Authorization: wawa=f6e65f8bce6b9b868cf119bae7ea7cae")
    @GET("/app/v1/banner/list?api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097")
    Observable<BannerListBean> getBannerList();

    //    wawa.fm	/app/v1/musician/list?category=3&page=1&size=10&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=152470709
    @Headers("Authorization: wawa=bfabbeaf62642e2a029eb995d121a5cf")
    @GET("/app/v1/musician/list?category=3&page=1&size=10&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=152470709")
    Observable<ResponseBody> getMusicianList();


    //    wawa.fm	/app/v1/album/list?category=1&page=1&size=1&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097
    @GET("/app/v1/album/list?category=1&page=1&size=1&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097")
    Observable<ResponseBody> getAlbumList();

    //    wawa.fm	/app/v1/track/recommend_list?page=1&size=10&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097
    @Headers("Authorization: wawa=bfabbeaf62642e2a029eb995d121a5cf")
    @GET("/app/v1/track/recommend_list?page=1&size=10&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097")
    Observable<ResponseBody> getTrackRecommendList();

    //    wawa.fm	/app/v1/doc/list?category=4&page=1&size=3&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097
    @GET("/app/v1/doc/list?category=4&page=1&size=3&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524707097")
    Observable<ResponseBody> getDocList();

    //wawa.fm	/app/v1/track/list_by_user?page=1&size=200&user_id=47962&category=2&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524810612
    //    //Authorization: wawa a9ca6c065cf246edb160c81dba17d129
    @Headers("Authorization: wawa=a9ca6c065cf246edb160c81dba17d129")
    @GET("/app/v1/track/list_by_user?size=200&category=2&api_key=0fcf845a413e11beb5606448eb8abbc4&timestamp=1524810612")
    Observable<List<TrackBean>> getTrack(@Query("page") int page, @Query("user_id") int user_id);
}
