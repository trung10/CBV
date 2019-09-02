
package com.fourtsoft.bvc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VideoList {

    @SerializedName("VideoItem")
    @Expose
    private List<VideoItem> videoItem = null;

    public List<VideoItem> getVideoItem() {
        return videoItem;
    }

    public void setVideoItem(List<VideoItem> videoItem) {
        this.videoItem = videoItem;
    }
}
