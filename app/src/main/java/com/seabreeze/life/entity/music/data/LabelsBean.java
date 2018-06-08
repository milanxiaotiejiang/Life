package com.seabreeze.life.entity.music.data;

import android.os.Parcel;
import android.os.Parcelable;

public class LabelsBean implements Parcelable {

    /**
     * id : 44
     * category : 1
     * label_zh : 电子
     * label_en : Electronic
     * color :
     * res_cover : http://up.wawa.fm/19,e56179fa2c98
     * cut_cover : http://up.wawa.fm/19,e56179fa2c98?width=500
     * status : 1
     * create_at : 2017-04-24T14:10:41+08:00
     * update_at : 2017-08-25T16:40:41+08:00
     * Flag : 0
     */

    private int id;
    private int category;
    private String label_zh;
    private String label_en;
    private String color;
    private String res_cover;
    private String cut_cover;
    private int status;
    private String create_at;
    private String update_at;
    private int Flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getLabel_zh() {
        return label_zh;
    }

    public void setLabel_zh(String label_zh) {
        this.label_zh = label_zh;
    }

    public String getLabel_en() {
        return label_en;
    }

    public void setLabel_en(String label_en) {
        this.label_en = label_en;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRes_cover() {
        return res_cover;
    }

    public void setRes_cover(String res_cover) {
        this.res_cover = res_cover;
    }

    public String getCut_cover() {
        return cut_cover;
    }

    public void setCut_cover(String cut_cover) {
        this.cut_cover = cut_cover;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int Flag) {
        this.Flag = Flag;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.category);
        dest.writeString(this.label_zh);
        dest.writeString(this.label_en);
        dest.writeString(this.color);
        dest.writeString(this.res_cover);
        dest.writeString(this.cut_cover);
        dest.writeInt(this.status);
        dest.writeString(this.create_at);
        dest.writeString(this.update_at);
        dest.writeInt(this.Flag);
    }

    public LabelsBean() {
    }

    protected LabelsBean(Parcel in) {
        this.id = in.readInt();
        this.category = in.readInt();
        this.label_zh = in.readString();
        this.label_en = in.readString();
        this.color = in.readString();
        this.res_cover = in.readString();
        this.cut_cover = in.readString();
        this.status = in.readInt();
        this.create_at = in.readString();
        this.update_at = in.readString();
        this.Flag = in.readInt();
    }

    public static final Parcelable.Creator<LabelsBean> CREATOR = new Parcelable.Creator<LabelsBean>() {
        @Override
        public LabelsBean createFromParcel(Parcel source) {
            return new LabelsBean(source);
        }

        @Override
        public LabelsBean[] newArray(int size) {
            return new LabelsBean[size];
        }
    };
}
