package com.fourtsoft.bvc.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DownloadVideo implements Parcelable {

    public DownloadVideo() {

    }

    private int progress;
    private int currentFileSize;
    private int totalFileSize;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(progress);
        dest.writeInt(currentFileSize);
        dest.writeInt(totalFileSize);
    }

    private DownloadVideo(Parcel in) {

        progress = in.readInt();
        currentFileSize = in.readInt();
        totalFileSize = in.readInt();
    }

    public static final Parcelable.Creator<DownloadVideo> CREATOR = new Parcelable.Creator<DownloadVideo>() {
        public DownloadVideo createFromParcel(Parcel in) {
            return new DownloadVideo(in);
        }

        public DownloadVideo[] newArray(int size) {
            return new DownloadVideo[size];
        }
    };
}