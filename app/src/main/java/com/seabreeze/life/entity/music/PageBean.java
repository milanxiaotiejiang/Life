package com.seabreeze.life.entity.music;

import android.os.Parcel;
import android.os.Parcelable;

public class PageBean implements Parcelable {

    /**
     * page : 1
     * total_count : 505
     * total_page : 26
     */

    private int page;
    private int total_count;
    private int total_page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.total_count);
        dest.writeInt(this.total_page);
    }

    public PageBean() {
    }

    protected PageBean(Parcel in) {
        this.page = in.readInt();
        this.total_count = in.readInt();
        this.total_page = in.readInt();
    }

    public static final Parcelable.Creator<PageBean> CREATOR = new Parcelable.Creator<PageBean>() {
        @Override
        public PageBean createFromParcel(Parcel source) {
            return new PageBean(source);
        }

        @Override
        public PageBean[] newArray(int size) {
            return new PageBean[size];
        }
    };
}
