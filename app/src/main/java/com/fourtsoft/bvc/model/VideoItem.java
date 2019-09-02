
package com.fourtsoft.bvc.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoItem {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "status=" + status +
                ", videoUrl='" + videoUrl + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}