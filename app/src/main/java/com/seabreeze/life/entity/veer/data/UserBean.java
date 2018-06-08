package com.seabreeze.life.entity.veer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

    /**
     * uid : 1494574
     * userid : 01dae4f0ef175b64
     * username : Ricco_Label
     * profile : {"uid":1494134,"name":"Ricco Label","info":"The Tokyo-based cinematic music label/production","avatar_url":"https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-avatar.medium?sign=fe3a5b30ed4516e7ffb90ef221a9d770&t=5afcc600","avatar_url_watermark":"https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-share.medium.cn?sign=2cc3d388bec620cd63a7b121f71fd2c3&t=5afcc600","avatar_url_small":"https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-avatar.small?sign=c7dc8fe9364f50a8658bdaf0d53efc5c&t=5afcc600","avatar_url_large":"https://qcdn.veervr.tv/profile/avatar/6a346ac3972640c28464283b85faf166.jpg-avatar.large?sign=037d08d7af32e19ada7e658ca941882d&t=5afcc600","background_url":"","like_viewable":true,"gender":null,"birthday":null,"location":null,"homepage":"http://www.riccolabel.com","geolocation":"country_80","display_geolocation":"日本","category_sequence":null}
     * is_creator : false
     * vip_days_left : 0
     * is_vip : false
     * following_count : 1
     * follower_count : 9
     * have_i_followed : false
     * beta_feature_permissions : {"experience":false}
     * third_party_accounts : {"facebook":null,"wechat":null,"twitter":null,"weibo":null,"google":null,"instagram":null}
     * viewable_settings : {"weibo":true,"twitter":true,"instagram":true}
     * is_new_register : false
     */

    private int uid;
    private String userid;
    private String username;
    private  ProfileBean profile;
    private boolean is_creator;
    private int vip_days_left;
    private boolean is_vip;
    private int following_count;
    private int follower_count;
    private boolean have_i_followed;
    private boolean is_new_register;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public boolean isIs_creator() {
        return is_creator;
    }

    public void setIs_creator(boolean is_creator) {
        this.is_creator = is_creator;
    }

    public int getVip_days_left() {
        return vip_days_left;
    }

    public void setVip_days_left(int vip_days_left) {
        this.vip_days_left = vip_days_left;
    }

    public boolean isIs_vip() {
        return is_vip;
    }

    public void setIs_vip(boolean is_vip) {
        this.is_vip = is_vip;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public boolean isHave_i_followed() {
        return have_i_followed;
    }

    public void setHave_i_followed(boolean have_i_followed) {
        this.have_i_followed = have_i_followed;
    }

    public boolean isIs_new_register() {
        return is_new_register;
    }

    public void setIs_new_register(boolean is_new_register) {
        this.is_new_register = is_new_register;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.userid);
        dest.writeString(this.username);
        dest.writeParcelable(this.profile, flags);
        dest.writeByte(this.is_creator ? (byte) 1 : (byte) 0);
        dest.writeInt(this.vip_days_left);
        dest.writeByte(this.is_vip ? (byte) 1 : (byte) 0);
        dest.writeInt(this.following_count);
        dest.writeInt(this.follower_count);
        dest.writeByte(this.have_i_followed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_new_register ? (byte) 1 : (byte) 0);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.uid = in.readInt();
        this.userid = in.readString();
        this.username = in.readString();
        this.profile = in.readParcelable(ProfileBean.class.getClassLoader());
        this.is_creator = in.readByte() != 0;
        this.vip_days_left = in.readInt();
        this.is_vip = in.readByte() != 0;
        this.following_count = in.readInt();
        this.follower_count = in.readInt();
        this.have_i_followed = in.readByte() != 0;
        this.is_new_register = in.readByte() != 0;
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
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
