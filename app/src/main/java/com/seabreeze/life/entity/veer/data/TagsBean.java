package com.seabreeze.life.entity.veer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TagsBean implements Parcelable {

    /**
     * name : music
     */

    private String name;

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
        dest.writeString(this.name);
    }

    public TagsBean() {
    }

    protected TagsBean(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<TagsBean> CREATOR = new Parcelable.Creator<TagsBean>() {
        @Override
        public TagsBean createFromParcel(Parcel source) {
            return new TagsBean(source);
        }

        @Override
        public TagsBean[] newArray(int size) {
            return new TagsBean[size];
        }
    };
}
