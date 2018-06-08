package com.seabreeze.life.entity.music;

import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
public class TracksBean implements Parcelable {

    /**
     * id : 24379
     * resourcecode : 20180119050027
     * songer : 竹内まりや
     * songname : プラスティック・ラブ
     * songalbum : null
     * filename : http://cdn.wawa.fm/group1/M00/01/EB/CvrcZ1phs6aABeUkAFHYfyS9ceo177.mp3
     * songphoto : http://cdn.wawa.fm/group1/M00/01/EB/CvrcZ1phs3GAMRIQAADnPiNoFwE780.jpg
     * remarks : 205
     * del_flag : 0
     * create_by : null
     * create_date : 2018-01-19 05:00:27
     * update_by : null
     * update_date : 2018-01-19 05:00:27
     * filename192 : http://cdn.wawa.fm/group1/M00/01/EB/CvrcZ1phs52AW2GzAHXSS_WUlK8567.mp3
     * filename320 : http://cdn.wawa.fm/group1/M00/01/EB/CvrcZ1phs5WAR7uFAL3F4sqIlLw411.mp3
     * time : 1516352427
     * thumbnail_url : http://cdn.wawa.fm/group1/M00/01/EB/CvrcZ1phs3GAMRIQAADnPiNoFwE780_150X150.jpg
     * fsize : 0
     * ggid : null
     * ypid : null
     * mtype : 2
     * uid : 301
     * lyrics : null
     * status : 1
     * flag : 0
     * note : null
     * create_time : 1516352427
     * update_time : null
     * publish_time : 1516352427
     * vol_id : 369
     * musician_id : null
     * banner_id : null
     * fee_tag : 0
     * cmcc_mid : null
     */

    private String id;
    private String resourcecode;
    private String songer;
    private String songname;
    private String filename;
    private String songphoto;
    private String remarks;
    private String del_flag;
    private String create_date;
    private String update_date;
    private String filename192;
    private String filename320;
    private String time;
    private String thumbnail_url;
    private String fsize;
    private String mtype;
    private String uid;
    private String status;
    private String flag;
    private String create_time;
    private String publish_time;
    private String vol_id;
    private String fee_tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourcecode() {
        return resourcecode;
    }

    public void setResourcecode(String resourcecode) {
        this.resourcecode = resourcecode;
    }

    public String getSonger() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer = songer;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSongphoto() {
        return songphoto;
    }

    public void setSongphoto(String songphoto) {
        this.songphoto = songphoto;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getFilename192() {
        return filename192;
    }

    public void setFilename192(String filename192) {
        this.filename192 = filename192;
    }

    public String getFilename320() {
        return filename320;
    }

    public void setFilename320(String filename320) {
        this.filename320 = filename320;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getFsize() {
        return fsize;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getVol_id() {
        return vol_id;
    }

    public void setVol_id(String vol_id) {
        this.vol_id = vol_id;
    }

    public String getFee_tag() {
        return fee_tag;
    }

    public void setFee_tag(String fee_tag) {
        this.fee_tag = fee_tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.resourcecode);
        dest.writeString(this.songer);
        dest.writeString(this.songname);
        dest.writeString(this.filename);
        dest.writeString(this.songphoto);
        dest.writeString(this.remarks);
        dest.writeString(this.del_flag);
        dest.writeString(this.create_date);
        dest.writeString(this.update_date);
        dest.writeString(this.filename192);
        dest.writeString(this.filename320);
        dest.writeString(this.time);
        dest.writeString(this.thumbnail_url);
        dest.writeString(this.fsize);
        dest.writeString(this.mtype);
        dest.writeString(this.uid);
        dest.writeString(this.status);
        dest.writeString(this.flag);
        dest.writeString(this.create_time);
        dest.writeString(this.publish_time);
        dest.writeString(this.vol_id);
        dest.writeString(this.fee_tag);
    }

    public TracksBean() {
    }

    protected TracksBean(Parcel in) {
        this.id = in.readString();
        this.resourcecode = in.readString();
        this.songer = in.readString();
        this.songname = in.readString();
        this.filename = in.readString();
        this.songphoto = in.readString();
        this.remarks = in.readString();
        this.del_flag = in.readString();
        this.create_date = in.readString();
        this.update_date = in.readString();
        this.filename192 = in.readString();
        this.filename320 = in.readString();
        this.time = in.readString();
        this.thumbnail_url = in.readString();
        this.fsize = in.readString();
        this.mtype = in.readString();
        this.uid = in.readString();
        this.status = in.readString();
        this.flag = in.readString();
        this.create_time = in.readString();
        this.publish_time = in.readString();
        this.vol_id = in.readString();
        this.fee_tag = in.readString();
    }

    public static final Parcelable.Creator<TracksBean> CREATOR = new Parcelable.Creator<TracksBean>() {
        @Override
        public TracksBean createFromParcel(Parcel source) {
            return new TracksBean(source);
        }

        @Override
        public TracksBean[] newArray(int size) {
            return new TracksBean[size];
        }
    };
}
