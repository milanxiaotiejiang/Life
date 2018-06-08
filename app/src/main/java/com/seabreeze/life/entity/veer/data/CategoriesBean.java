package com.seabreeze.life.entity.veer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoriesBean implements Parcelable {

    /**
     * uid : 99999
     * name : Fake Category
     */

    private int uid;
    private String name;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.name);
    }

    public CategoriesBean() {
    }

    protected CategoriesBean(Parcel in) {
        this.uid = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CategoriesBean> CREATOR = new Parcelable.Creator<CategoriesBean>() {
        @Override
        public CategoriesBean createFromParcel(Parcel source) {
            return new CategoriesBean(source);
        }

        @Override
        public CategoriesBean[] newArray(int size) {
            return new CategoriesBean[size];
        }
    };
}
