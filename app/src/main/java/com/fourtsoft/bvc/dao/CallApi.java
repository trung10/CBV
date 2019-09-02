package com.fourtsoft.bvc.dao;

import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.model.VideoList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/*
This interface for call all tasks of call network
* */
public interface CallApi {

    PublishSubject<VideoList> getVideoList();

    void downloadVideoItem(VideoItem videoItem);

}
