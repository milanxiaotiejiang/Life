package com.seabreeze.life.entity.kuula.data;

import android.os.Parcel;
import android.os.Parcelable;

public class PageBean implements Parcelable {

    /**
     * total : 1178
     * size : 60
     * index : 0
     * start : 0
     * end : 60
     * hasPrev : false
     * hasNext : true
     * nextIndex : 1
     */

    private int total;
    private int size;
    private int index;
    private int start;
    private int end;
    private boolean hasPrev;
    private boolean hasNext;
    private int nextIndex;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeInt(this.size);
        dest.writeInt(this.index);
        dest.writeInt(this.start);
        dest.writeInt(this.end);
        dest.writeByte(this.hasPrev ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasNext ? (byte) 1 : (byte) 0);
        dest.writeInt(this.nextIndex);
    }

    public PageBean() {
    }

    protected PageBean(Parcel in) {
        this.total = in.readInt();
        this.size = in.readInt();
        this.index = in.readInt();
        this.start = in.readInt();
        this.end = in.readInt();
        this.hasPrev = in.readByte() != 0;
        this.hasNext = in.readByte() != 0;
        this.nextIndex = in.readInt();
    }

    public static final Parcelable.Creator<PageBean> CREATOR = new Parcelable.Creator<PageBean>() {
        @Override
        public PageBean createFromParcel(Parcel source) {
            return new PageBean(source);
        }

        @Override
        public PageBean[] newArray(int size) {
            return new PageBean[size];
        }
    };
}
