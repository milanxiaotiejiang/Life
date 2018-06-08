package com.seabreeze.life.entity.essay.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TagListBean implements Parcelable {
    /**
     * id : 27
     * title : STORY
     */

    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    public TagListBean() {
    }

    protected TagListBean(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<TagListBean> CREATOR = new Parcelable.Creator<TagListBean>() {
        @Override
        public TagListBean createFromParcel(Parcel source) {
            return new TagListBean(source);
        }

        @Override
        public TagListBean[] newArray(int size) {
            return new TagListBean[size];
        }
    };
}
