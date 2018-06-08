package com.seabreeze.life.entity.music;

import android.os.Parcel;
import android.os.Parcelable;

import com.seabreeze.life.entity.music.data.LabelsBean;

import java.util.List;

public class DataBean implements Parcelable {

    /**
     * id : 47962
     * nickname : 陆地钢琴师MT1990
     * headimg : http://up.wawa.fm/15,025b523f6fc4a0
     * sign : 超级好听的钢琴音乐，一个努力实现梦想的钢琴师。
     * user_type : 3
     * parent : 0
     * description : 爵士/融合钢琴家；作曲家。研毕于上海音乐学院，致力于变得像海上钢琴师那么厉害。作品不但将古典钢琴与new age/hiphop/jazz结合，还创造性地与EDM/HOUSE等重电音风格融合。2018年5.18-6.10，全国巡演期待见面！
     * label_ids : 48,53,44
     * labels : [{"id":44,"category":1,"label_zh":"电子","label_en":"Electronic","color":"","res_cover":"http://up.wawa.fm/19,e56179fa2c98","cut_cover":"http://up.wawa.fm/19,e56179fa2c98?width=500","status":1,"create_at":"2017-04-24T14:10:41+08:00","update_at":"2017-08-25T16:40:41+08:00","Flag":0},{"id":48,"category":1,"label_zh":"世界音乐","label_en":"World Music","color":"","res_cover":"http://up.wawa.fm/17,e5503cd68a86","cut_cover":"http://up.wawa.fm/17,e5503cd68a86?width=500","status":1,"create_at":"2017-04-24T14:14:04+08:00","update_at":"2017-08-25T16:40:01+08:00","Flag":0},{"id":53,"category":1,"label_zh":"器乐","label_en":"Instrumental","color":"","res_cover":"http://up.wawa.fm/21,e54f0c1b1406","cut_cover":"http://up.wawa.fm/21,e54f0c1b1406?width=500","status":1,"create_at":"2017-07-20T14:32:32+08:00","update_at":"2017-08-25T16:39:49+08:00","Flag":0}]
     * play_count : 1124
     * track_count : 3
     * o_play_count : 0
     */

    private int id;
    private String nickname;
    private String headimg;
    private String sign;
    private int user_type;
    private int parent;
    private String description;
    private String label_ids;
    private int play_count;
    private int track_count;
    private int o_play_count;
    private List<LabelsBean> labels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel_ids() {
        return label_ids;
    }

    public void setLabel_ids(String label_ids) {
        this.label_ids = label_ids;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public int getTrack_count() {
        return track_count;
    }

    public void setTrack_count(int track_count) {
        this.track_count = track_count;
    }

    public int getO_play_count() {
        return o_play_count;
    }

    public void setO_play_count(int o_play_count) {
        this.o_play_count = o_play_count;
    }

    public List<LabelsBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels) {
        this.labels = labels;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nickname);
        dest.writeString(this.headimg);
        dest.writeString(this.sign);
        dest.writeInt(this.user_type);
        dest.writeInt(this.parent);
        dest.writeString(this.description);
        dest.writeString(this.label_ids);
        dest.writeInt(this.play_count);
        dest.writeInt(this.track_count);
        dest.writeInt(this.o_play_count);
        dest.writeTypedList(this.labels);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.id = in.readInt();
        this.nickname = in.readString();
        this.headimg = in.readString();
        this.sign = in.readString();
        this.user_type = in.readInt();
        this.parent = in.readInt();
        this.description = in.readString();
        this.label_ids = in.readString();
        this.play_count = in.readInt();
        this.track_count = in.readInt();
        this.o_play_count = in.readInt();
        this.labels = in.createTypedArrayList(LabelsBean.CREATOR);
    }

    public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}
