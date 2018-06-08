package com.seabreeze.life.entity.veer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileBean implements Parcelable {

    /**
     * uid : 1494134
     * name : Ricco Label
     * info : The Tokyo-based cinematic music label/production
     * avatar_url : https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-avatar.medium?sign=fe3a5b30ed4516e7ffb90ef221a9d770&t=5afcc600
     * avatar_url_watermark : https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-share.medium.cn?sign=2cc3d388bec620cd63a7b121f71fd2c3&t=5afcc600
     * avatar_url_small : https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-avatar.small?sign=c7dc8fe9364f50a8658bdaf0d53efc5c&t=5afcc600
     * avatar_url_large : https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-avatar.large?sign=037d08d7af32e19ada7e658ca941882d&t=5afcc600
     * background_url :
     * like_viewable : true
     * gender : null
     * birthday : null
     * location : null
     * homepage : http://www.riccolabel.com
     * geolocation : country_80
     * display_geolocation : 日本
     * category_sequence : null
     */

    private int uid;
    private String name;
    private String info;
    private String avatar_url;
    private String background_url;
    private boolean like_viewable;
    private String homepage;
    private String geolocation;
    private String display_geolocation;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getBackground_url() {
        return background_url;
    }

    public void setBackground_url(String background_url) {
        this.background_url = background_url;
    }

    public boolean isLike_viewable() {
        return like_viewable;
    }

    public void setLike_viewable(boolean like_viewable) {
        this.like_viewable = like_viewable;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getDisplay_geolocation() {
        return display_geolocation;
    }

    public void setDisplay_geolocation(String display_geolocation) {
        this.display_geolocation = display_geolocation;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.name);
        dest.writeString(this.info);
        dest.writeString(this.avatar_url);
        dest.writeString(this.background_url);
        dest.writeByte(this.like_viewable ? (byte) 1 : (byte) 0);
        dest.writeString(this.homepage);
        dest.writeString(this.geolocation);
        dest.writeString(this.display_geolocation);
    }

    public ProfileBean() {
    }

    protected ProfileBean(Parcel in) {
        this.uid = in.readInt();
        this.name = in.readString();
        this.info = in.readString();
        this.avatar_url = in.readString();
        this.background_url = in.readString();
        this.like_viewable = in.readByte() != 0;
        this.homepage = in.readString();
        this.geolocation = in.readString();
        this.display_geolocation = in.readString();
    }

    public static final Parcelable.Creator<ProfileBean> CREATOR = new Parcelable.Creator<ProfileBean>() {
        @Override
        public ProfileBean createFromParcel(Parcel source) {
            return new ProfileBean(source);
        }

        @Override
        public ProfileBean[] newArray(int size) {
            return new ProfileBean[size];
        }
    };
}
