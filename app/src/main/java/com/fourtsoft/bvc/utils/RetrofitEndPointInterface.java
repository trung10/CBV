package com.fourtsoft.bvc.utils;

import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.model.VideoList;

import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RetrofitEndPointInterface {

    @GET("callbackgroundvideo/VideoJson.json")
    Call<VideoList> getVideoList();

    @Streaming
    @GET()
    Call<ResponseBody> getVideoITem(@Url String url);
}
