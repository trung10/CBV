package com.fourtsoft.bvc.dao;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fourtsoft.bvc.di.AppContext;
import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.model.VideoList;
import com.fourtsoft.bvc.utils.RetrofitEndPointInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApiHelper implements CallApi {
    private static final String TAG = CallApiHelper.class.getSimpleName();
    private String BASE_URL = "http://www.4tsoft.com";
    private Retrofit mRetrofit;
    private RetrofitEndPointInterface apiService;
    private Context context;

    @Inject
    CallApiHelper(@AppContext Context context) {
        this.context = context;
        Gson gson = new GsonBuilder()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService
                = mRetrofit.create(RetrofitEndPointInterface.class);
    }

    @Override
    public PublishSubject<VideoList> getVideoList() {
        final PublishSubject<VideoList> subject = PublishSubject.create();

        Call<VideoList> call = apiService.getVideoList();
        call.enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(@NonNull Call<VideoList> call, @NonNull Response<VideoList> response) {
                if (response.isSuccessful()) {
                    subject.onNext(response.body());
                } else {
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideoList> call, @NonNull Throwable t) {
                subject.onError(t);
            }
        });
        return subject;
    }

    @Override
    public void downloadVideoItem(VideoItem videoItem) {
//        Intent intent = new Intent(context, DownloadService.class);
//        intent.putExtra("url", videoItem.getVideoUrl());
//        context.startService(intent);
    }
}
