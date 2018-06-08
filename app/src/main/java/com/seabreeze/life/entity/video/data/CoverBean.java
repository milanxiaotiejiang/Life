package com.seabreeze.life.entity.video.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CoverBean implements Parcelable {

    /**
     * feed : http://img.kaiyanapp.com/d6b17cf04d3f9e55ff9410768f59d8b2.jpeg?imageMogr2/quality/60/format/jpg
     * detail : http://img.kaiyanapp.com/d6b17cf04d3f9e55ff9410768f59d8b2.jpeg?imageMogr2/quality/60/format/jpg
     * blurred : http://img.kaiyanapp.com/744ef09931869d68653f9ef3ba7a4ab2.jpeg?imageMogr2/quality/60/format/jpg
     * sharing : null
     * homepage : http://img.kaiyanapp.com/d6b17cf04d3f9e55ff9410768f59d8b2.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
     */

    private String feed;
    private String detail;
    private String blurred;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.feed);
        dest.writeString(this.detail);
        dest.writeString(this.blurred);
    }

    public CoverBean() {
    }

    protected CoverBean(Parcel in) {
        this.feed = in.readString();
        this.detail = in.readString();
        this.blurred = in.readString();
    }

    public static final Parcelable.Creator<CoverBean> CREATOR = new Parcelable.Creator<CoverBean>() {
        @Override
        public CoverBean createFromParcel(Parcel source) {
            return new CoverBean(source);
        }

        @Override
        public CoverBean[] newArray(int size) {
            return new CoverBean[size];
        }
    };
}
