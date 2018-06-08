package com.seabreeze.life.entity.essay.data;

import android.os.Parcel;
import android.os.Parcelable;

public class AuthorBean implements Parcelable {

    /**
     * user_id : 8203444
     * user_name : 周源远
     * desc : 知名作家、GQ China创意总监。曾出版《我就乐意这样寂寞了》、《住在岛上》。新书《就像星星被浪费在夜空里》正在预售。
     * wb_name : @源远
     * is_settled : 0
     * settled_type : 0
     * summary : 知名作家、GQ China创意总监。
     * fans_total : 618
     * web_url : http://image.wufazhuce.com/FqN0qOUuwzBp95IV_VQf1O91cFaW
     */

    private String user_id;
    private String user_name;
    private String desc;
    private String wb_name;
    private String is_settled;
    private String settled_type;
    private String summary;
    private String fans_total;
    private String web_url;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWb_name() {
        return wb_name;
    }

    public void setWb_name(String wb_name) {
        this.wb_name = wb_name;
    }

    public String getIs_settled() {
        return is_settled;
    }

    public void setIs_settled(String is_settled) {
        this.is_settled = is_settled;
    }

    public String getSettled_type() {
        return settled_type;
    }

    public void setSettled_type(String settled_type) {
        this.settled_type = settled_type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFans_total() {
        return fans_total;
    }

    public void setFans_total(String fans_total) {
        this.fans_total = fans_total;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.user_id);
        dest.writeString(this.user_name);
        dest.writeString(this.desc);
        dest.writeString(this.wb_name);
        dest.writeString(this.is_settled);
        dest.writeString(this.settled_type);
        dest.writeString(this.summary);
        dest.writeString(this.fans_total);
        dest.writeString(this.web_url);
    }

    public AuthorBean() {
    }

    protected AuthorBean(Parcel in) {
        this.user_id = in.readString();
        this.user_name = in.readString();
        this.desc = in.readString();
        this.wb_name = in.readString();
        this.is_settled = in.readString();
        this.settled_type = in.readString();
        this.summary = in.readString();
        this.fans_total = in.readString();
        this.web_url = in.readString();
    }

    public static final Parcelable.Creator<AuthorBean> CREATOR = new Parcelable.Creator<AuthorBean>() {
        @Override
        public AuthorBean createFromParcel(Parcel source) {
            return new AuthorBean(source);
        }

        @Override
        public AuthorBean[] newArray(int size) {
            return new AuthorBean[size];
        }
    };
}
