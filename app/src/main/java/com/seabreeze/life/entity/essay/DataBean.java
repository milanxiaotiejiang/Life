package com.seabreeze.life.entity.essay;

import android.os.Parcel;
import android.os.Parcelable;

import com.seabreeze.life.entity.essay.data.AuthorBean;
import com.seabreeze.life.entity.essay.data.TagListBean;

import java.util.List;

public class DataBean implements Parcelable {

    /**
     * id : 12808
     * category : 1
     * display_category : 6
     * item_id : 2696
     * title : 没有赏味期限，只有来日方长
     * forward : 爱一个人的时候，才会学会理解和包容。愿意为对方削去身上的棱角，然后打磨成圆。
     * img_url : http://image.wufazhuce.com/Fo59plBlnS9WkK9dkR-y6IkOAMN-
     * like_count : 5421
     * post_date : 2017-07-31 06:00:00
     * last_update_date : 2017-07-30 17:24:15
     * author : {"user_id":"8203444","user_name":"周源远","desc":"知名作家、GQ China创意总监。曾出版《我就乐意这样寂寞了》、《住在岛上》。新书《就像星星被浪费在夜空里》正在预售。","wb_name":"@源远","is_settled":"0","settled_type":"0","summary":"知名作家、GQ China创意总监。","fans_total":"618","web_url":"http://image.wufazhuce.com/FqN0qOUuwzBp95IV_VQf1O91cFaW"}
     * video_url :
     * audio_url :
     * audio_platform : 2
     * start_video :
     * has_reading : 0
     * volume : 0
     * pic_info :
     * words_info :
     * subtitle :
     * number : 0
     * serial_id : 0
     * serial_list : []
     * movie_story_id : 0
     * ad_id : 0
     * ad_type : 0
     * ad_pvurl :
     * ad_linkurl :
     * ad_makettime :
     * ad_closetime :
     * ad_share_cnt :
     * ad_pvurl_vendor :
     * content_id : 2696
     * content_type : 1
     * content_bgcolor : #FFFFFF
     * share_url : http://m.wufazhuce.com/article/2696
     * share_info : {"url":"http://m.wufazhuce.com/article/2696","image":"http://image.wufazhuce.com/Fo59plBlnS9WkK9dkR-y6IkOAMN-","title":"没有赏味期限，只有来日方长 作者/周源远","content":"世界上最高的山，是永远登不到顶峰的你的心。"}
     * share_list : {"wx":{"title":"STORY | 没有赏味期限，只有来日方长","desc":"文/周源远 世界上最高的山，是永远登不到顶峰的你的心。","link":"http://m.wufazhuce.com/article/2696?channel=singlemessage","imgUrl":"http://image.wufazhuce.com/ONE_logo_120_square.png","audio":""},"wx_timeline":{"title":"STORY | 没有赏味期限，只有来日方长","desc":"文/周源远 世界上最高的山，是永远登不到顶峰的你的心。","link":"http://m.wufazhuce.com/article/2696?channel=timeline","imgUrl":"http://image.wufazhuce.com/ONE_logo_120_square.png","audio":""},"weibo":{"title":"ONE一个《STORY | 没有赏味期限，只有来日方长》 文/周源远： 世界上最高的山，是永远登不到顶峰的你的心。 阅读全文：http://m.wufazhuce.com/article/2696?channel=weibo 下载ONE一个APP:http://weibo.com/p/100404157874","desc":"","link":"http://m.wufazhuce.com/article/2696?channel=weibo","imgUrl":"","audio":""},"qq":{"title":"没有赏味期限，只有来日方长","desc":"世界上最高的山，是永远登不到顶峰的你的心。","link":"http://m.wufazhuce.com/article/2696?channel=qq","imgUrl":"http://image.wufazhuce.com/ONE_logo_120_square.png","audio":""}}
     * tag_list : [{"id":"27","title":"STORY"}]
     */

    private int id;
    private String category;
    private int display_category;
    private int item_id;
    private String title;
    private String forward;
    private String img_url;
    private int like_count;
    private String post_date;
    private String last_update_date;
    private AuthorBean author;
    private String video_url;
    private String audio_url;
    private int audio_platform;
    private String start_video;
    private int volume;
    private String pic_info;
    private String words_info;
    private String subtitle;
    private int number;
    private int serial_id;
    private int movie_story_id;
    private int ad_id;
    private int ad_type;
    private String ad_pvurl;
    private String ad_linkurl;
    private String ad_makettime;
    private String ad_closetime;
    private String ad_share_cnt;
    private String ad_pvurl_vendor;
    private String content_id;
    private String content_type;
    private String content_bgcolor;
    private String share_url;
    private List<TagListBean> tag_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDisplay_category() {
        return display_category;
    }

    public void setDisplay_category(int display_category) {
        this.display_category = display_category;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public int getAudio_platform() {
        return audio_platform;
    }

    public void setAudio_platform(int audio_platform) {
        this.audio_platform = audio_platform;
    }

    public String getStart_video() {
        return start_video;
    }

    public void setStart_video(String start_video) {
        this.start_video = start_video;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getPic_info() {
        return pic_info;
    }

    public void setPic_info(String pic_info) {
        this.pic_info = pic_info;
    }

    public String getWords_info() {
        return words_info;
    }

    public void setWords_info(String words_info) {
        this.words_info = words_info;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(int serial_id) {
        this.serial_id = serial_id;
    }

    public int getMovie_story_id() {
        return movie_story_id;
    }

    public void setMovie_story_id(int movie_story_id) {
        this.movie_story_id = movie_story_id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int getAd_type() {
        return ad_type;
    }

    public void setAd_type(int ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_pvurl() {
        return ad_pvurl;
    }

    public void setAd_pvurl(String ad_pvurl) {
        this.ad_pvurl = ad_pvurl;
    }

    public String getAd_linkurl() {
        return ad_linkurl;
    }

    public void setAd_linkurl(String ad_linkurl) {
        this.ad_linkurl = ad_linkurl;
    }

    public String getAd_makettime() {
        return ad_makettime;
    }

    public void setAd_makettime(String ad_makettime) {
        this.ad_makettime = ad_makettime;
    }

    public String getAd_closetime() {
        return ad_closetime;
    }

    public void setAd_closetime(String ad_closetime) {
        this.ad_closetime = ad_closetime;
    }

    public String getAd_share_cnt() {
        return ad_share_cnt;
    }

    public void setAd_share_cnt(String ad_share_cnt) {
        this.ad_share_cnt = ad_share_cnt;
    }

    public String getAd_pvurl_vendor() {
        return ad_pvurl_vendor;
    }

    public void setAd_pvurl_vendor(String ad_pvurl_vendor) {
        this.ad_pvurl_vendor = ad_pvurl_vendor;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_bgcolor() {
        return content_bgcolor;
    }

    public void setContent_bgcolor(String content_bgcolor) {
        this.content_bgcolor = content_bgcolor;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public List<TagListBean> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagListBean> tag_list) {
        this.tag_list = tag_list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.category);
        dest.writeInt(this.display_category);
        dest.writeInt(this.item_id);
        dest.writeString(this.title);
        dest.writeString(this.forward);
        dest.writeString(this.img_url);
        dest.writeInt(this.like_count);
        dest.writeString(this.post_date);
        dest.writeString(this.last_update_date);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.video_url);
        dest.writeString(this.audio_url);
        dest.writeInt(this.audio_platform);
        dest.writeString(this.start_video);
        dest.writeInt(this.volume);
        dest.writeString(this.pic_info);
        dest.writeString(this.words_info);
        dest.writeString(this.subtitle);
        dest.writeInt(this.number);
        dest.writeInt(this.serial_id);
        dest.writeInt(this.movie_story_id);
        dest.writeInt(this.ad_id);
        dest.writeInt(this.ad_type);
        dest.writeString(this.ad_pvurl);
        dest.writeString(this.ad_linkurl);
        dest.writeString(this.ad_makettime);
        dest.writeString(this.ad_closetime);
        dest.writeString(this.ad_share_cnt);
        dest.writeString(this.ad_pvurl_vendor);
        dest.writeString(this.content_id);
        dest.writeString(this.content_type);
        dest.writeString(this.content_bgcolor);
        dest.writeString(this.share_url);
        dest.writeTypedList(this.tag_list);
    }

    public DataBean() {
    }

    protected DataBean(Parcel in) {
        this.id = in.readInt();
        this.category = in.readString();
        this.display_category = in.readInt();
        this.item_id = in.readInt();
        this.title = in.readString();
        this.forward = in.readString();
        this.img_url = in.readString();
        this.like_count = in.readInt();
        this.post_date = in.readString();
        this.last_update_date = in.readString();
        this.author = in.readParcelable(AuthorBean.class.getClassLoader());
        this.video_url = in.readString();
        this.audio_url = in.readString();
        this.audio_platform = in.readInt();
        this.start_video = in.readString();
        this.volume = in.readInt();
        this.pic_info = in.readString();
        this.words_info = in.readString();
        this.subtitle = in.readString();
        this.number = in.readInt();
        this.serial_id = in.readInt();
        this.movie_story_id = in.readInt();
        this.ad_id = in.readInt();
        this.ad_type = in.readInt();
        this.ad_pvurl = in.readString();
        this.ad_linkurl = in.readString();
        this.ad_makettime = in.readString();
        this.ad_closetime = in.readString();
        this.ad_share_cnt = in.readString();
        this.ad_pvurl_vendor = in.readString();
        this.content_id = in.readString();
        this.content_type = in.readString();
        this.content_bgcolor = in.readString();
        this.share_url = in.readString();
        this.tag_list = in.createTypedArrayList(TagListBean.CREATOR);
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

    @Override
    public String toString() {
        return "DataBean{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", display_category=" + display_category +
                ", item_id='" + item_id + '\'' +
                ", title='" + title + '\'' +
                ", forward='" + forward + '\'' +
                ", img_url='" + img_url + '\'' +
                ", like_count=" + like_count +
                ", post_date='" + post_date + '\'' +
                ", last_update_date='" + last_update_date + '\'' +
                ", video_url='" + video_url + '\'' +
                ", audio_url='" + audio_url + '\'' +
                ", audio_platform=" + audio_platform +
                ", start_video='" + start_video + '\'' +
                ", volume=" + volume +
                ", pic_info='" + pic_info + '\'' +
                ", words_info='" + words_info + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", number=" + number +
                ", serial_id=" + serial_id +
                ", movie_story_id=" + movie_story_id +
                ", ad_id=" + ad_id +
                ", ad_type=" + ad_type +
                ", ad_pvurl='" + ad_pvurl + '\'' +
                ", ad_linkurl='" + ad_linkurl + '\'' +
                ", ad_makettime='" + ad_makettime + '\'' +
                ", ad_closetime='" + ad_closetime + '\'' +
                ", ad_share_cnt='" + ad_share_cnt + '\'' +
                ", ad_pvurl_vendor='" + ad_pvurl_vendor + '\'' +
                ", content_id='" + content_id + '\'' +
                ", content_type='" + content_type + '\'' +
                ", content_bgcolor='" + content_bgcolor + '\'' +
                ", share_url='" + share_url + '\'' +
                '}';
    }
}
