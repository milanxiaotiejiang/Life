package com.seabreeze.life.entity.veer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class UrlsBean implements Parcelable {


    private String high;
    private String best;

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.high);
        dest.writeString(this.best);
    }

    public UrlsBean() {
    }

    protected UrlsBean(Parcel in) {
        this.high = in.readString();
        this.best = in.readString();
    }

    public static final Parcelable.Creator<UrlsBean> CREATOR = new Parcelable.Creator<UrlsBean>() {
        @Override
        public UrlsBean createFromParcel(Parcel source) {
            return new UrlsBean(source);
        }

        @Override
        public UrlsBean[] newArray(int size) {
            return new UrlsBean[size];
        }
    };
}
