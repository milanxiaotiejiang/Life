package com.seabreeze.life.entity.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.seabreeze.life.entity.video.data.ConsumptionBean;
import com.seabreeze.life.entity.video.data.CoverBean;
import com.seabreeze.life.entity.video.data.PlayInfoBean;
import com.seabreeze.life.entity.video.data.ProviderBean;
import com.seabreeze.life.entity.video.data.TagsBean;
import com.seabreeze.life.entity.video.data.WebUrlBean;

import java.util.ArrayList;
import java.util.List;

public class DataBean implements Parcelable {

    /**
     * dataType : VideoBeanForClient
     * id : 98446
     * title : 特条 |「死侍 2」贱贱的终极预告
     * description : 「死侍 2」最终预告释出，皮到新境界！不仅调侃了一波 DC，还直言这是「来自杀死金刚狼的电影公司」……预计 5 月会在北美贱贱地上映，月指活！
     * library : DAILY
     * tags : [{"id":504,"name":"笑cry","actionUrl":"eyepetizer://tag/504/?title=%E7%AC%91cry","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/3d3cb19d13cc16e8c3c2eddb12284fa3.jpeg?imageMogr2/quality/100","headerImage":"http://img.kaiyanapp.com/177986653f12273f6d55edafe856ffe3.jpeg?imageMogr2/quality/100","tagRecType":"NORMAL"},{"id":454,"name":"二十世纪福克斯","actionUrl":"eyepetizer://tag/454/?title=%E4%BA%8C%E5%8D%81%E4%B8%96%E7%BA%AA%E7%A6%8F%E5%85%8B%E6%96%AF","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/bb2aa5442496c07744efe09f42e4017e.jpeg?imageMogr2/quality/100","headerImage":"http://img.kaiyanapp.com/93baabbd393e8f7c1a4416b68ec0ed9d.jpeg?imageMogr2/quality/100","tagRecType":"NORMAL"},{"id":404,"name":"超级英雄","actionUrl":"eyepetizer://tag/404/?title=%E8%B6%85%E7%BA%A7%E8%8B%B1%E9%9B%84","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/54e03e1d8e91ab98b840124bf08e3445.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/54e03e1d8e91ab98b840124bf08e3445.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"},{"id":22,"name":"预告","actionUrl":"eyepetizer://tag/22/?title=%E9%A2%84%E5%91%8A","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/05383ae22a23cc6fcc008681e7ff5a1c.jpeg?imageMogr2/quality/60","headerImage":"http://img.kaiyanapp.com/05383ae22a23cc6fcc008681e7ff5a1c.jpeg?imageMogr2/quality/60","tagRecType":"NORMAL"}]
     * consumption : {"collectionCount":177,"shareCount":526,"replyCount":14}
     * resourceType : video
     * slogan : 来自那家杀死金刚狼的电影公司
     * provider : {"name":"YouTube","alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png"}
     * category : 预告
     * author : {"id":366,"icon":"http://img.kaiyanapp.com/c895728c49daadc8e5ef1570a005ab5e.jpeg?imageMogr2/quality/60","name":"二十世纪福克斯电影公司","description":"二十世纪福克斯电影公司（20th Century Fox Film Corporation）是美国最主要的电影、电视节目发行和制作公司之一，总部坐落在美国加州洛杉矶比佛利山庄西侧的世纪城。","link":"","latestReleaseTime":1524197570000,"videoNum":203,"adTrack":null,"follow":{"itemType":"author","itemId":366,"followed":false},"shield":{"itemType":"author","itemId":366,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true}
     * cover : {"feed":"http://img.kaiyanapp.com/d6b17cf04d3f9e55ff9410768f59d8b2.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/d6b17cf04d3f9e55ff9410768f59d8b2.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/744ef09931869d68653f9ef3ba7a4ab2.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/d6b17cf04d3f9e55ff9410768f59d8b2.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
     * playUrl : https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=default&source=aliyun
     * thumbPlayUrl : null
     * duration : 152
     * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=98446","forWeibo":"http://www.eyepetizer.net/detail.html?vid=98446"}
     * releaseTime : 1524197570000
     * playInfo : [{"height":480,"width":854,"urlList":[{"name":"aliyun","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=normal&source=aliyun","size":17385310},{"name":"qcloud","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=normal&source=qcloud","size":17385310},{"name":"ucloud","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=normal&source=ucloud","size":17385310}],"name":"标清","type":"normal","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=normal&source=aliyun"},{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=high&source=aliyun","size":28979250},{"name":"qcloud","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=high&source=qcloud","size":28979250},{"name":"ucloud","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=high&source=ucloud","size":28979250}],"name":"高清","type":"high","url":"https://baobab.kaiyanapp.com/api/v1/playUrl?vid=98446&editionType=high&source=aliyun"}]
     * campaign : null
     * waterMarks : null
     * adTrack : null
     * type : NORMAL
     * titlePgc : null
     * descriptionPgc : null
     * remark : X 特攻队全员亮相，
     * ifLimitVideo : false
     * idx : 0
     * shareAdTrack : null
     * favoriteAdTrack : null
     * webAdTrack : null
     * date : 1524186000000
     * promotion : null
     * label : null
     * labelList : []
     * descriptionEditor : 「死侍 2」最终预告释出，皮到新境界！不仅调侃了一波 DC，还直言这是「来自杀死金刚狼的电影公司」……预计 5 月会在北美贱贱地上映，月指活！
     * collected : false
     * played : false
     * subtitles : []
     * lastViewTime : null
     * playlists : null
     * src : null
     */

    private String dataType;
    private int id;
    private String title;
    private String description;
    private ConsumptionBean consumption;
    private ProviderBean provider;
    private String category;
    private CoverBean cover;
    private String playUrl;
    private int duration;
    private WebUrlBean webUrl;
    private long releaseTime;
    private String type;
    private int idx;
    private long date;
    private boolean collected;
    private boolean played;
    private List<TagsBean> tags;
    private List<PlayInfoBean> playInfo;
    private String coverForFeed;


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConsumptionBean getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionBean consumption) {
        this.consumption = consumption;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WebUrlBean getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrlBean webUrl) {
        this.webUrl = webUrl;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<PlayInfoBean> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfoBean> playInfo) {
        this.playInfo = playInfo;
    }

    public String getCoverForFeed() {
        return coverForFeed;
    }

    public void setCoverForFeed(String coverForFeed) {
        this.coverForFeed = coverForFeed;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.consumption, flags);
        dest.writeParcelable(this.provider, flags);
        dest.writeString(this.category);
        dest.writeParcelable(this.cover, flags);
        dest.writeString(this.playUrl);
        dest.writeInt(this.duration);
        dest.writeParcelable(this.webUrl, flags);
        dest.writeLong(this.releaseTime);
        dest.writeString(this.type);
        dest.writeInt(this.idx);
        dest.writeLong(this.date);
        dest.writeByte(this.collected ? (byte) 1 : (byte) 0);
        dest.writeByte(this.played ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.tags);
        dest.writeTypedList(this.playInfo);
        dest.writeString(this.coverForFeed);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.consumption = in.readParcelable(ConsumptionBean.class.getClassLoader());
        this.provider = in.readParcelable(ProviderBean.class.getClassLoader());
        this.category = in.readString();
        this.cover = in.readParcelable(CoverBean.class.getClassLoader());
        this.playUrl = in.readString();
        this.duration = in.readInt();
        this.webUrl = in.readParcelable(WebUrlBean.class.getClassLoader());
        this.releaseTime = in.readLong();
        this.type = in.readString();
        this.idx = in.readInt();
        this.date = in.readLong();
        this.collected = in.readByte() != 0;
        this.played = in.readByte() != 0;
        this.tags = in.createTypedArrayList(TagsBean.CREATOR);
        this.playInfo = in.createTypedArrayList(PlayInfoBean.CREATOR);
        this.coverForFeed = in.readString();
    }

    public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
