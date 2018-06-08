package com.seabreeze.life.entity.video.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TagsBean implements Parcelable {

    /**
     * id : 504
     * name : 笑cry
     * actionUrl : eyepetizer://tag/504/?title=%E7%AC%91cry
     * adTrack : null
     * desc : null
     * bgPicture : http://img.kaiyanapp.com/3d3cb19d13cc16e8c3c2eddb12284fa3.jpeg?imageMogr2/quality/100
     * headerImage : http://img.kaiyanapp.com/177986653f12273f6d55edafe856ffe3.jpeg?imageMogr2/quality/100
     * tagRecType : NORMAL
     */

    private int id;
    private String name;
    private String actionUrl;
    private String bgPicture;
    private String headerImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.actionUrl);
        dest.writeString(this.bgPicture);
        dest.writeString(this.headerImage);
    }

    public TagsBean() {
    }

    protected TagsBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.actionUrl = in.readString();
        this.bgPicture = in.readString();
        this.headerImage = in.readString();
    }

    public static final Creator<TagsBean> CREATOR = new Creator<TagsBean>() {
        @Override
        public TagsBean createFromParcel(Parcel source) {
            return new TagsBean(source);
        }

        @Override
        public TagsBean[] newArray(int size) {
            return new TagsBean[size];
        }
    };
}
