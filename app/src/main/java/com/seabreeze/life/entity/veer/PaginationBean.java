package com.seabreeze.life.entity.veer;

import android.os.Parcel;
import android.os.Parcelable;

public class PaginationBean implements Parcelable {

    /**
     * current_page : 1
     * total_pages : 398
     * total_count : 3971
     * size : 13
     */

    private int current_page;
    private int total_pages;
    private int total_count;
    private int size;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.current_page);
        dest.writeInt(this.total_pages);
        dest.writeInt(this.total_count);
        dest.writeInt(this.size);
    }

    public PaginationBean() {
    }

    protected PaginationBean(Parcel in) {
        this.current_page = in.readInt();
        this.total_pages = in.readInt();
        this.total_count = in.readInt();
        this.size = in.readInt();
    }

    public static final Parcelable.Creator<PaginationBean> CREATOR = new Parcelable.Creator<PaginationBean>() {
        @Override
        public PaginationBean createFromParcel(Parcel source) {
            return new PaginationBean(source);
        }

        @Override
        public PaginationBean[] newArray(int size) {
            return new PaginationBean[size];
        }
    };
}
