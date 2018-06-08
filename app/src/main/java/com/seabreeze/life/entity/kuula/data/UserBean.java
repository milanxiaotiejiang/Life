package com.seabreeze.life.entity.kuula.data;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

    /**
     * id : 1855
     * name : kronpano
     * displayname : kronpano
     * picture : 1
     * type : 0
     */

    private String id;
    private String name;
    private String displayname;
    private String picture;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.displayname);
        dest.writeString(this.picture);
        dest.writeString(this.type);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.displayname = in.readString();
        this.picture = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
