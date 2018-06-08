package com.seabreeze.life.entity.video.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ConsumptionBean implements Parcelable {

    /**
     * collectionCount : 177
     * shareCount : 526
     * replyCount : 14
     */

    private int collectionCount;
    private int shareCount;
    private int replyCount;

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.collectionCount);
        dest.writeInt(this.shareCount);
        dest.writeInt(this.replyCount);
    }

    public ConsumptionBean() {
    }

    protected ConsumptionBean(Parcel in) {
        this.collectionCount = in.readInt();
        this.shareCount = in.readInt();
        this.replyCount = in.readInt();
    }

    public static final Parcelable.Creator<ConsumptionBean> CREATOR = new Parcelable.Creator<ConsumptionBean>() {
        @Override
        public ConsumptionBean createFromParcel(Parcel source) {
            return new ConsumptionBean(source);
        }

        @Override
        public ConsumptionBean[] newArray(int size) {
            return new ConsumptionBean[size];
        }
    };
}
