package com.seabreeze.life.entity.veer;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.seabreeze.life.entity.veer.data.CategoriesBean;
import com.seabreeze.life.entity.veer.data.TagsBean;
import com.seabreeze.life.entity.veer.data.ThumbnailBean;
import com.seabreeze.life.entity.veer.data.UrlsBean;
import com.seabreeze.life.entity.veer.data.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DataBean implements Parcelable {

    /**
     * uid : 153359
     * video_id : 153359
     * title : 【TonyVR】FPV, Running on Shanghai ZQDL Stadium, Traxxas X-Maxx 8S
     * original_title : 【TonyVR】FPV, Running on Shanghai ZQDL Stadium, Traxxas X-Maxx 8S
     * info : 考虑到Gopro Fusion没有镜头保护，X-Maxx这次在草地上只跑了半速，没敢全速抬头狂奔。看来Gopro Fusion的清晰度、色彩都非常出众，特别是相机自稳系统在车子高速行进、剧烈颠簸时仍然能保持水平和稳定，完全可以和那些三轴稳定云台媲美，这也是其他一些全景相机不具备的突出优点。
     * urls : {"default":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/source.mp4?sign=6f0f3e9982d34421cbcbedc746846fc9&t=5afcc600","high":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/720p/index.mp4?sign=2dec01dc70a0bcc0ccef36d839395f22&t=5afcc600","best":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/1080p/index.mp4?sign=c4fb9e189c848eb9422dbb5e02782f45&t=5afcc600"}
     * media : [{"size":170506451,"resolution":720,"url":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/720p/index.mp4?sign=2dec01dc70a0bcc0ccef36d839395f22&t=5afcc600","height":720,"width":1280,"video_bitrate":4529560},{"size":419132122,"resolution":1080,"url":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/1080p/index.mp4?sign=c4fb9e189c848eb9422dbb5e02782f45&t=5afcc600","height":1080,"width":1920,"video_bitrate":11336621},{"size":742238526,"resolution":1440,"url":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/1440p/index.mp4?sign=e471cc68d0009d3f557b39f27dd0a3f7&t=5afcc600","height":1440,"width":2560,"video_bitrate":20182855},{"size":1552382519,"resolution":2160,"url":"https://qvcdn.veervr.tv/hZhBjWXsWC__DCUge5mzdg/2160p/index.mp4?sign=a9b4ab7ed7eec00deca95cd16dcabe6c&t=5afcc600","height":2160,"width":3840,"video_bitrate":42363517}]
     * hls :
     * barrel :
     * player : https://qvcdn.veervr.tv/vr-player/index_18c60c69.html?sign=122f13c475f52e97ac8aa6432390a19f&t=5afcc600
     * thumbnail : {"url_normal":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png?sign=83793d208b6af6457eaef48a77b39fe1&t=5afcc600","url_normal_share_micro":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.micro?sign=5ab898f9a421827e197a79a447148d78&t=5afcc600","url_normal_share_medium":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.medium?sign=9990161a94f66353719513a27d0a6191&t=5afcc600","url_normal_share_medium_watermark":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.medium.cn?sign=cb3ac5cf7d5d0d79bdbc8431ef061399&t=5afcc600","url_normal_small":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-video.small?sign=857946546d669cd72202eb047c840710&t=5afcc600","url_normal_medium":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-video.medium?sign=2cb4d5b9f99866b18237d9e73a6d7a43&t=5afcc600","url_vr":"https://qcdn.veervr.tv/6aac80c6073f40f49d6d7044576cd7dd.png-video.vr.medium?sign=2614396c907222d4eabc68eb505a2dfe&t=5afcc600","url_vr_medium":"https://qcdn.veervr.tv/6aac80c6073f40f49d6d7044576cd7dd.png-video.vr.medium?sign=2614396c907222d4eabc68eb505a2dfe&t=5afcc600","url_content_medium":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-content.medium?sign=19a3dd40d18891332015462b05c1524b&t=5afcc600"}
     * thumbnail_resized : {"url_normal":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png?sign=83793d208b6af6457eaef48a77b39fe1&t=5afcc600","url_normal_share_micro":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.micro?sign=5ab898f9a421827e197a79a447148d78&t=5afcc600","url_normal_share_medium":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.medium?sign=9990161a94f66353719513a27d0a6191&t=5afcc600","url_normal_share_medium_watermark":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.medium.cn?sign=cb3ac5cf7d5d0d79bdbc8431ef061399&t=5afcc600","url_normal_small":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-video.small?sign=857946546d669cd72202eb047c840710&t=5afcc600","url_normal_medium":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-video.medium?sign=2cb4d5b9f99866b18237d9e73a6d7a43&t=5afcc600","url_vr":"https://qcdn.veervr.tv/6aac80c6073f40f49d6d7044576cd7dd.png-video.vr.medium?sign=2614396c907222d4eabc68eb505a2dfe&t=5afcc600","url_vr_medium":"https://qcdn.veervr.tv/6aac80c6073f40f49d6d7044576cd7dd.png-video.vr.medium?sign=2614396c907222d4eabc68eb505a2dfe&t=5afcc600","url_content_medium":"https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-content.medium?sign=19a3dd40d18891332015462b05c1524b&t=5afcc600"}
     * is_stereo : false
     * is_3d : false
     * stereo_type : null
     * fov : 360
     * duration : 292.25
     * like_count : 10
     * have_i_liked : false
     * comment_count : 0
     * view_count : 6460
     * share_count : 17
     * status : public
     * publish_status : published
     * privacy : public
     * processing_status : processed
     * display_status : processed
     * created_at : 2018-04-17 11:22:02.000000
     * time_delta_created_at : 503947.038961263
     * allow_download : true
     * allow_screenshot : true
     * start_at : null
     * end_at : null
     * timezone : Etc/UTC
     * featured : true
     * horizontal_degree : 360
     * vertical_degree : 180
     * categories : [{"uid":99999,"name":"Fake Category"}]
     * tags : [{"name":"运动"},{"name":"model"},{"name":"car"},{"name":"fusion"},{"name":"gopro"},{"name":"dog"},{"name":"rc"},{"name":"gopro"},{"name":"Fusion"},{"name":"GoPro"},{"name":"Dog"},{"name":"RC"},{"name":"Rc"},{"name":"Car"},{"name":"CAR"},{"name":"Model"},{"name":"FPV"}]
     * creator : {"name":"","thumbnail":"","info":""}
     * user : {"uid":1343755,"userid":"0054336456c81ce2","username":"TonyVision","profile":{"uid":1343318,"name":"大同视野 TonyVision","info":"Like travel and photography","avatar_url":"https://qcdn.veervr.tv/profile/avatar/1226e37e4fc64ee181d59b30f3136616.jpg-avatar.medium?sign=2c1f7487c262362997d19f896753621b&t=5afcc600","avatar_url_watermark":"https://qcdn.veervr.tv/profile/avatar/1226e37e4fc64ee181d59b30f3136616.jpg-share.medium.cn?sign=c545518af4f44f44cfdc8f34d667ce1e&t=5afcc600","avatar_url_small":"https://qcdn.veervr.tv/profile/avatar/1226e37e4fc64ee181d59b30f3136616.jpg-avatar.small?sign=e5a9d84a8d1ba2700b4c56d93d8942fe&t=5afcc600","avatar_url_large":"https://qcdn.veervr.tv/profile/avatar/1226e37e4fc64ee181d59b30f3136616.jpg-avatar.large?sign=a6bcc19581fd8f8ef728bcc969a539f2&t=5afcc600","background_url":"https://qcdn.veervr.tv/profile/background/ndXK6hbi9umce8lBjUbhBQ-background.medium?sign=691430d9d0d1479dd238baa408d2a077&t=5afcc600","like_viewable":true,"gender":1,"birthday":null,"location":null,"homepage":"","geolocation":"country_69","display_geolocation":"中国","category_sequence":null},"is_creator":false,"vip_days_left":0,"is_vip":false,"following_count":11,"follower_count":73,"have_i_followed":false,"beta_feature_permissions":{"experience":false},"third_party_accounts":{"facebook":{"name":"Tony  Yong"},"wechat":null,"twitter":null,"weibo":null,"google":null,"instagram":null},"viewable_settings":{"weibo":true,"twitter":true,"instagram":true},"is_new_register":false}
     */

    private int uid;
    private int video_id;
    private String title;
    private String original_title;
    private String info;
    private ThumbnailBean thumbnail;
    private String player;
    private boolean is_stereo;
    private boolean is_3d;
    private int fov;
    private double duration;
    private int like_count;
    private boolean have_i_liked;
    private int comment_count;
    private int view_count;
    private int share_count;
    private String status;
    private String publish_status;
    private String privacy;
    private String processing_status;
    private String display_status;
    private String created_at;
    private double time_delta_created_at;
    private boolean allow_download;
    private boolean allow_screenshot;
    private String timezone;
    private boolean featured;
    private int horizontal_degree;
    private int vertical_degree;
    private List<CategoriesBean> categories;
    private List<TagsBean> tags;
    private UserBean user;
    private UrlsBean urls;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ThumbnailBean getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailBean thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isIs_stereo() {
        return is_stereo;
    }

    public void setIs_stereo(boolean is_stereo) {
        this.is_stereo = is_stereo;
    }

    public boolean isIs_3d() {
        return is_3d;
    }

    public void setIs_3d(boolean is_3d) {
        this.is_3d = is_3d;
    }

    public int getFov() {
        return fov;
    }

    public void setFov(int fov) {
        this.fov = fov;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public boolean isHave_i_liked() {
        return have_i_liked;
    }

    public void setHave_i_liked(boolean have_i_liked) {
        this.have_i_liked = have_i_liked;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublish_status() {
        return publish_status;
    }

    public void setPublish_status(String publish_status) {
        this.publish_status = publish_status;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getProcessing_status() {
        return processing_status;
    }

    public void setProcessing_status(String processing_status) {
        this.processing_status = processing_status;
    }

    public String getDisplay_status() {
        return display_status;
    }

    public void setDisplay_status(String display_status) {
        this.display_status = display_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getTime_delta_created_at() {
        return time_delta_created_at;
    }

    public void setTime_delta_created_at(double time_delta_created_at) {
        this.time_delta_created_at = time_delta_created_at;
    }

    public boolean isAllow_download() {
        return allow_download;
    }

    public void setAllow_download(boolean allow_download) {
        this.allow_download = allow_download;
    }

    public boolean isAllow_screenshot() {
        return allow_screenshot;
    }

    public void setAllow_screenshot(boolean allow_screenshot) {
        this.allow_screenshot = allow_screenshot;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public int getHorizontal_degree() {
        return horizontal_degree;
    }

    public void setHorizontal_degree(int horizontal_degree) {
        this.horizontal_degree = horizontal_degree;
    }

    public int getVertical_degree() {
        return vertical_degree;
    }

    public void setVertical_degree(int vertical_degree) {
        this.vertical_degree = vertical_degree;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UrlsBean getUrls() {
        return urls;
    }

    public void setUrls(UrlsBean urls) {
        this.urls = urls;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeInt(this.video_id);
        dest.writeString(this.title);
        dest.writeString(this.original_title);
        dest.writeString(this.info);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeString(this.player);
        dest.writeByte(this.is_stereo ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_3d ? (byte) 1 : (byte) 0);
        dest.writeInt(this.fov);
        dest.writeDouble(this.duration);
        dest.writeInt(this.like_count);
        dest.writeByte(this.have_i_liked ? (byte) 1 : (byte) 0);
        dest.writeInt(this.comment_count);
        dest.writeInt(this.view_count);
        dest.writeInt(this.share_count);
        dest.writeString(this.status);
        dest.writeString(this.publish_status);
        dest.writeString(this.privacy);
        dest.writeString(this.processing_status);
        dest.writeString(this.display_status);
        dest.writeString(this.created_at);
        dest.writeDouble(this.time_delta_created_at);
        dest.writeByte(this.allow_download ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_screenshot ? (byte) 1 : (byte) 0);
        dest.writeString(this.timezone);
        dest.writeByte(this.featured ? (byte) 1 : (byte) 0);
        dest.writeInt(this.horizontal_degree);
        dest.writeInt(this.vertical_degree);
        dest.writeTypedList(this.categories);
        dest.writeTypedList(this.tags);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.urls, flags);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.uid = in.readInt();
        this.video_id = in.readInt();
        this.title = in.readString();
        this.original_title = in.readString();
        this.info = in.readString();
        this.thumbnail = in.readParcelable(ThumbnailBean.class.getClassLoader());
        this.player = in.readString();
        this.is_stereo = in.readByte() != 0;
        this.is_3d = in.readByte() != 0;
        this.fov = in.readInt();
        this.duration = in.readDouble();
        this.like_count = in.readInt();
        this.have_i_liked = in.readByte() != 0;
        this.comment_count = in.readInt();
        this.view_count = in.readInt();
        this.share_count = in.readInt();
        this.status = in.readString();
        this.publish_status = in.readString();
        this.privacy = in.readString();
        this.processing_status = in.readString();
        this.display_status = in.readString();
        this.created_at = in.readString();
        this.time_delta_created_at = in.readDouble();
        this.allow_download = in.readByte() != 0;
        this.allow_screenshot = in.readByte() != 0;
        this.timezone = in.readString();
        this.featured = in.readByte() != 0;
        this.horizontal_degree = in.readInt();
        this.vertical_degree = in.readInt();
        this.categories = in.createTypedArrayList(CategoriesBean.CREATOR);
        this.tags = in.createTypedArrayList(TagsBean.CREATOR);
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.urls = in.readParcelable(UrlsBean.class.getClassLoader());
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}
