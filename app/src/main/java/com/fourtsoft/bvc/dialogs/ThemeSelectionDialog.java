package com.fourtsoft.bvc.dialogs;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.model.DownloadVideo;
import com.fourtsoft.bvc.model.VideoItem;
import com.fourtsoft.bvc.utils.DownloadUtil;
import com.fourtsoft.bvc.utils.FileUtils;

import java.io.File;

public class ThemeSelectionDialog extends DialogFragment {

    VideoItem mVideoItem;
    ThemeSelectListener mListener;
    Context mContext;
    VideoView mVideoBackground;

    ProgressBar progress;
    Button btnSelect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mDialogView = inflater.inflate(R.layout.dialog_theme_selection, container, false);
        mContext = mDialogView.getContext();

        mVideoBackground = mDialogView.findViewById(R.id.video_background);

        ImageButton btnClose = mDialogView.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onClose();
            }
        });

        btnSelect = mDialogView.findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSelectTheme(mVideoItem);
            }
        });

        TextView textView = mDialogView.findViewById(R.id.txtNumber);
        //todo set number change

        progress = mDialogView.findViewById(R.id.progress);

        //todo
        String termPath = Environment.getExternalStorageDirectory() + File.separator + "temp";

        File temp = new File(termPath);

        //check file exist
        boolean success = true;
        if (!temp.exists()) {
            success = temp.mkdirs();
        }

//        Log.e("Sss", FileUtils.getFileNameFromURL(mVideoItem.getVideoUrl()));
        String filePath = termPath + "/" + FileUtils.getFileNameFromURL(mVideoItem.getVideoUrl());
        if (success) {
            File file = new File(filePath);

            if (file.exists()) {
                btnSelect.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                loadVideoBackground();

            } else {
                btnSelect.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                DownloadUtil.downloadVideo(mContext, mVideoItem.getVideoUrl(), filePath, progress);

            }

        } else {
            // Do something else on failure

        }
        // have exist -> load on view

        // kiem tra thu muc temp -> neu chua co thi tao thu muc temp
        // neu da co thi kiem tra video co chua.
        // chua co thi tai ve luu trong temp



        return mDialogView;
    }

    public VideoItem getmVideoItem() {
        return mVideoItem;
    }

    public void setmVideoItem(VideoItem mVideoItem) {
        this.mVideoItem = mVideoItem;
    }

    public ThemeSelectListener getmListener() {
        return mListener;
    }

    public void setmListener(ThemeSelectListener mListener) {
        this.mListener = mListener;
    }

    private void loadVideoBackground() {
        // TODO thong: load string path ò video from ROOM  database
        // get data user setting at here, in this case, load data from ThemeSelected table in ROOM database.
        File file = new File(mContext.getCacheDir() + "/cbvVideo/", "default.mp4");
        String pathVideo = file.getAbsolutePath();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mVideoBackground.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        mVideoBackground.setLayoutParams(params);
        mVideoBackground.setVideoPath(pathVideo);
        mVideoBackground.requestFocus();
        mVideoBackground.start();
        mVideoBackground.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    public interface ThemeSelectListener {
        void onClose();

        void onSelectTheme(VideoItem videoItem);
    }
}