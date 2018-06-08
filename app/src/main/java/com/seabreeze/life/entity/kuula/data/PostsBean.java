package com.seabreeze.life.entity.kuula.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PostsBean implements Parcelable {

    /**
     * id : 7lzQG
     * uuid : 5adb-9676-05bf-a123
     * description : 720 degree panorama - 2 360s in one
     * <p>
     * late autumn and early winter at Llyn Idwal in North Wales, UK - both in one panorama
     * <p>
     * #wales #720 #2in1 #UK #gimp #mathmap
     * cover : 01
     * privacy : public
     * views : 1359
     * tiny : 2
     * featured : 0
     * created : 1524340596
     * comments : 1
     * likes : 4
     * user : {"id":"1855","name":"kronpano","displayname":"kronpano","picture":"1","type":"0"}
     */

    private String id;
    private String uuid;
    private String description;
    private String cover;
    private String privacy;
    private int views;
    private String tiny;
    private String featured;
    private String created;
    private int comments;
    private int likes;
    private UserBean user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getTiny() {
        return tiny;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.uuid);
        dest.writeString(this.description);
        dest.writeString(this.cover);
        dest.writeString(this.privacy);
        dest.writeInt(this.views);
        dest.writeString(this.tiny);
        dest.writeString(this.featured);
        dest.writeString(this.created);
        dest.writeInt(this.comments);
        dest.writeInt(this.likes);
        dest.writeParcelable(this.user, flags);
    }

    public PostsBean() {
    }

    protected PostsBean(Parcel in) {
        this.id = in.readString();
        this.uuid = in.readString();
        this.description = in.readString();
        this.cover = in.readString();
        this.privacy = in.readString();
        this.views = in.readInt();
        this.tiny = in.readString();
        this.featured = in.readString();
        this.created = in.readString();
        this.comments = in.readInt();
        this.likes = in.readInt();
        this.user = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PostsBean> CREATOR = new Parcelable.Creator<PostsBean>() {
        @Override
        public PostsBean createFromParcel(Parcel source) {
            return new PostsBean(source);
        }

        @Override
        public PostsBean[] newArray(int size) {
            return new PostsBean[size];
        }
    };
}
