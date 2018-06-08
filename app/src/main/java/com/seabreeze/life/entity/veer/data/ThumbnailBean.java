package com.seabreeze.life.entity.veer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ThumbnailBean implements Parcelable {
    /**
     * url_normal : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png?sign=83793d208b6af6457eaef48a77b39fe1&t=5afcc600
     * url_normal_share_micro : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.micro?sign=5ab898f9a421827e197a79a447148d78&t=5afcc600
     * url_normal_share_medium : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.medium?sign=9990161a94f66353719513a27d0a6191&t=5afcc600
     * url_normal_share_medium_watermark : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-share.medium.cn?sign=cb3ac5cf7d5d0d79bdbc8431ef061399&t=5afcc600
     * url_normal_small : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-video.small?sign=857946546d669cd72202eb047c840710&t=5afcc600
     * url_normal_medium : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-video.medium?sign=2cb4d5b9f99866b18237d9e73a6d7a43&t=5afcc600
     * url_vr : https://qcdn.veervr.tv/6aac80c6073f40f49d6d7044576cd7dd.png-video.vr.medium?sign=2614396c907222d4eabc68eb505a2dfe&t=5afcc600
     * url_vr_medium : https://qcdn.veervr.tv/6aac80c6073f40f49d6d7044576cd7dd.png-video.vr.medium?sign=2614396c907222d4eabc68eb505a2dfe&t=5afcc600
     * url_content_medium : https://qcdn.veervr.tv/52549bc9dfb140a69e5bb7098e786140.png-content.medium?sign=19a3dd40d18891332015462b05c1524b&t=5afcc600
     */

    private String url_normal;
    private String url_normal_share_micro;
    private String url_normal_share_medium;
    private String url_normal_share_medium_watermark;
    private String url_normal_small;
    private String url_normal_medium;
    private String url_vr;
    private String url_vr_medium;
    private String url_content_medium;

    public String getUrl_normal() {
        return url_normal;
    }

    public void setUrl_normal(String url_normal) {
        this.url_normal = url_normal;
    }

    public String getUrl_normal_share_micro() {
        return url_normal_share_micro;
    }

    public void setUrl_normal_share_micro(String url_normal_share_micro) {
        this.url_normal_share_micro = url_normal_share_micro;
    }

    public String getUrl_normal_share_medium() {
        return url_normal_share_medium;
    }

    public void setUrl_normal_share_medium(String url_normal_share_medium) {
        this.url_normal_share_medium = url_normal_share_medium;
    }

    public String getUrl_normal_share_medium_watermark() {
        return url_normal_share_medium_watermark;
    }

    public void setUrl_normal_share_medium_watermark(String url_normal_share_medium_watermark) {
        this.url_normal_share_medium_watermark = url_normal_share_medium_watermark;
    }

    public String getUrl_normal_small() {
        return url_normal_small;
    }

    public void setUrl_normal_small(String url_normal_small) {
        this.url_normal_small = url_normal_small;
    }

    public String getUrl_normal_medium() {
        return url_normal_medium;
    }

    public void setUrl_normal_medium(String url_normal_medium) {
        this.url_normal_medium = url_normal_medium;
    }

    public String getUrl_vr() {
        return url_vr;
    }

    public void setUrl_vr(String url_vr) {
        this.url_vr = url_vr;
    }

    public String getUrl_vr_medium() {
        return url_vr_medium;
    }

    public void setUrl_vr_medium(String url_vr_medium) {
        this.url_vr_medium = url_vr_medium;
    }

    public String getUrl_content_medium() {
        return url_content_medium;
    }

    public void setUrl_content_medium(String url_content_medium) {
        this.url_content_medium = url_content_medium;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url_normal);
        dest.writeString(this.url_normal_share_micro);
        dest.writeString(this.url_normal_share_medium);
        dest.writeString(this.url_normal_share_medium_watermark);
        dest.writeString(this.url_normal_small);
        dest.writeString(this.url_normal_medium);
        dest.writeString(this.url_vr);
        dest.writeString(this.url_vr_medium);
        dest.writeString(this.url_content_medium);
    }

    public ThumbnailBean() {
    }

    protected ThumbnailBean(Parcel in) {
        this.url_normal = in.readString();
        this.url_normal_share_micro = in.readString();
        this.url_normal_share_medium = in.readString();
        this.url_normal_share_medium_watermark = in.readString();
        this.url_normal_small = in.readString();
        this.url_normal_medium = in.readString();
        this.url_vr = in.readString();
        this.url_vr_medium = in.readString();
        this.url_content_medium = in.readString();
    }

    public static final Parcelable.Creator<ThumbnailBean> CREATOR = new Parcelable.Creator<ThumbnailBean>() {
        @Override
        public ThumbnailBean createFromParcel(Parcel source) {
            return new ThumbnailBean(source);
        }

        @Override
        public ThumbnailBean[] newArray(int size) {
            return new ThumbnailBean[size];
        }
    };
}
