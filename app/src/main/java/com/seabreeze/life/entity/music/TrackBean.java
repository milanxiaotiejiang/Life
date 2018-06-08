package com.seabreeze.life.entity.music;

import android.os.Parcel;
import android.os.Parcelable;

public class TrackBean implements Parcelable {


    /**
     * sid : 9427
     * id : 18040000138
     * singer : 索林
     * songname : I’m  Not  Okay
     * file128 : http://up.wawa.fm/21,0276003f7d8494
     * file192 : http://up.wawa.fm/21,0275ffaf0870b4
     * file320 : http://up.wawa.fm/21,02758a04ff16ce
     * res_cover : http://up.wawa.fm/15,02758bf9b6a165
     * cut_cover : http://up.wawa.fm/15,02758bf9b6a165?width=500
     * time : 174
     * create_user : 47887
     * lyrics : 作曲：索林Sorling
     作词：索林Sorling
     混音：Abner_火伴
     Prod by：Beatz Era
     Don't go I'm sorry
     But I know 不能挽回
     已经尽力在习惯了没有你的夜
     转身之后身边却少了熟悉的脸
     沙发上的外衣已经淡了你的味
     夜里熬夜再也没人填饱你的胃
     走后你要注意
     记得早点休息
     联系断了吧
     免得勾起彼此回忆
     放心去追你想过的生活Im Okay
     没有关系分手拥抱这次我给
     不会再去借酒消愁 Im Okay
     收拾好的皮箱脾气还是那么干脆
     这个结果已经懒得争论谁错谁对
     没有绝对
     走后放心家务我会
     躺在床上心又开始下坠
     失眠的夜谁在陪你入睡
     和你养的猫它又在想你
     生病找药发现有些费力
     不过这都没有关系
     生活像是一场游戏
     少了谁都一样可以
     感情像是拉不住的手刹
     让人无处躲
     太多的事物大多的矛盾都差个如果
     如果可以重新选择又有多少人会
     像是重来的心早已经被你撕碎
     放心去追你想过的生活Im Okay
     没有关系分手拥抱这次我给
     不会再去借酒消愁 Im Okay
     收拾好的皮箱脾气还是那么干脆
     这个结果已经懒得争论谁错谁对
     没有绝对走后放心家务我会
     躺在床上心又开始下坠
     失眠的夜谁在陪你入睡

     * status : 1
     * from_user : 47887
     * note :
     * is_recommend : -1
     * label_ids : 38
     * download_on : 1
     * cmcc_code :
     * cucc_code :
     * ctcc_code :
     * reviews :
     * pay_on : 1
     * create_at : 2018-04-25T13:32:00+08:00
     * update_at : 2018-04-27T13:33:54+08:00
     * review_at : 2018-04-25T15:09:09+08:00
     * review_remark :
     * vol_number : 0
     * play_count : 113
     * like_count : 0
     * download_count : 3
     * share_count : 2
     * view_count : 0
     * recommend_at : 0001-01-01T00:00:00Z
     * base_count : 0
     * Flag : 0
     * comment_count : 0
     */

    private int sid;
    private long id;
    private String singer;
    private String songname;
    private String file128;
    private String file192;
    private String file320;
    private String res_cover;
    private String cut_cover;
    private int time;
    private int create_user;
    private String lyrics;
    private int status;
    private int from_user;
    private String note;
    private int is_recommend;
    private String label_ids;
    private int download_on;
    private String cmcc_code;
    private String cucc_code;
    private String ctcc_code;
    private String reviews;
    private int pay_on;
    private String create_at;
    private String update_at;
    private String review_at;
    private String review_remark;
    private int vol_number;
    private int play_count;
    private int like_count;
    private int download_count;
    private int share_count;
    private int view_count;
    private String recommend_at;
    private int base_count;
    private int Flag;
    private int comment_count;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getFile128() {
        return file128;
    }

    public void setFile128(String file128) {
        this.file128 = file128;
    }

    public String getFile192() {
        return file192;
    }

    public void setFile192(String file192) {
        this.file192 = file192;
    }

    public String getFile320() {
        return file320;
    }

    public void setFile320(String file320) {
        this.file320 = file320;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCreate_user() {
        return create_user;
    }

    public void setCreate_user(int create_user) {
        this.create_user = create_user;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFrom_user() {
        return from_user;
    }

    public void setFrom_user(int from_user) {
        this.from_user = from_user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(int is_recommend) {
        this.is_recommend = is_recommend;
    }

    public String getLabel_ids() {
        return label_ids;
    }

    public void setLabel_ids(String label_ids) {
        this.label_ids = label_ids;
    }

    public int getDownload_on() {
        return download_on;
    }

    public void setDownload_on(int download_on) {
        this.download_on = download_on;
    }

    public String getCmcc_code() {
        return cmcc_code;
    }

    public void setCmcc_code(String cmcc_code) {
        this.cmcc_code = cmcc_code;
    }

    public String getCucc_code() {
        return cucc_code;
    }

    public void setCucc_code(String cucc_code) {
        this.cucc_code = cucc_code;
    }

    public String getCtcc_code() {
        return ctcc_code;
    }

    public void setCtcc_code(String ctcc_code) {
        this.ctcc_code = ctcc_code;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public int getPay_on() {
        return pay_on;
    }

    public void setPay_on(int pay_on) {
        this.pay_on = pay_on;
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

    public String getReview_at() {
        return review_at;
    }

    public void setReview_at(String review_at) {
        this.review_at = review_at;
    }

    public String getReview_remark() {
        return review_remark;
    }

    public void setReview_remark(String review_remark) {
        this.review_remark = review_remark;
    }

    public int getVol_number() {
        return vol_number;
    }

    public void setVol_number(int vol_number) {
        this.vol_number = vol_number;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getRecommend_at() {
        return recommend_at;
    }

    public void setRecommend_at(String recommend_at) {
        this.recommend_at = recommend_at;
    }

    public int getBase_count() {
        return base_count;
    }

    public void setBase_count(int base_count) {
        this.base_count = base_count;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int Flag) {
        this.Flag = Flag;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sid);
        dest.writeLong(this.id);
        dest.writeString(this.singer);
        dest.writeString(this.songname);
        dest.writeString(this.file128);
        dest.writeString(this.file192);
        dest.writeString(this.file320);
        dest.writeString(this.res_cover);
        dest.writeString(this.cut_cover);
        dest.writeInt(this.time);
        dest.writeInt(this.create_user);
        dest.writeString(this.lyrics);
        dest.writeInt(this.status);
        dest.writeInt(this.from_user);
        dest.writeString(this.note);
        dest.writeInt(this.is_recommend);
        dest.writeString(this.label_ids);
        dest.writeInt(this.download_on);
        dest.writeString(this.cmcc_code);
        dest.writeString(this.cucc_code);
        dest.writeString(this.ctcc_code);
        dest.writeString(this.reviews);
        dest.writeInt(this.pay_on);
        dest.writeString(this.create_at);
        dest.writeString(this.update_at);
        dest.writeString(this.review_at);
        dest.writeString(this.review_remark);
        dest.writeInt(this.vol_number);
        dest.writeInt(this.play_count);
        dest.writeInt(this.like_count);
        dest.writeInt(this.download_count);
        dest.writeInt(this.share_count);
        dest.writeInt(this.view_count);
        dest.writeString(this.recommend_at);
        dest.writeInt(this.base_count);
        dest.writeInt(this.Flag);
        dest.writeInt(this.comment_count);
    }

    public TrackBean() {
    }

    protected TrackBean(Parcel in) {
        this.sid = in.readInt();
        this.id = in.readLong();
        this.singer = in.readString();
        this.songname = in.readString();
        this.file128 = in.readString();
        this.file192 = in.readString();
        this.file320 = in.readString();
        this.res_cover = in.readString();
        this.cut_cover = in.readString();
        this.time = in.readInt();
        this.create_user = in.readInt();
        this.lyrics = in.readString();
        this.status = in.readInt();
        this.from_user = in.readInt();
        this.note = in.readString();
        this.is_recommend = in.readInt();
        this.label_ids = in.readString();
        this.download_on = in.readInt();
        this.cmcc_code = in.readString();
        this.cucc_code = in.readString();
        this.ctcc_code = in.readString();
        this.reviews = in.readString();
        this.pay_on = in.readInt();
        this.create_at = in.readString();
        this.update_at = in.readString();
        this.review_at = in.readString();
        this.review_remark = in.readString();
        this.vol_number = in.readInt();
        this.play_count = in.readInt();
        this.like_count = in.readInt();
        this.download_count = in.readInt();
        this.share_count = in.readInt();
        this.view_count = in.readInt();
        this.recommend_at = in.readString();
        this.base_count = in.readInt();
        this.Flag = in.readInt();
        this.comment_count = in.readInt();
    }

    public static final Parcelable.Creator<TrackBean> CREATOR = new Parcelable.Creator<TrackBean>() {
        @Override
        public TrackBean createFromParcel(Parcel source) {
            return new TrackBean(source);
        }

        @Override
        public TrackBean[] newArray(int size) {
            return new TrackBean[size];
        }
    };
}
