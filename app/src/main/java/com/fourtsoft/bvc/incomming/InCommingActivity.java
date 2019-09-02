package com.fourtsoft.bvc.incomming;

import android.content.Context;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import com.fourtsoft.bvc.R;

import java.io.File;
import java.lang.reflect.Method;


public class InCommingActivity extends AppCompatActivity implements View.OnClickListener {

    private final int TIME_DELAY_ACCEPT_CALLING = 100;
    private final int TIME_DELAY_REJECT_CALLING = 300;

    private VideoView videoBackground;
    private ImageView btnReject;
    private ImageView btnAccepted;
    private View view;
    private WindowManager windowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_in_comming, null);

        showPhoneScreen();
    }

    private void initView(View view) {
        videoBackground = view.findViewById(R.id.video_background);

        btnAccepted = view.findViewById(R.id.btn_accept);
        btnAccepted.setOnClickListener(this);

        btnReject = view.findViewById(R.id.btn_reject);
        btnReject.setOnClickListener(this);
    }

    private void disconnectCall() {
        try {
            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);

            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acceptInComingCalling() {
        removeView(TIME_DELAY_ACCEPT_CALLING);
    }

    private void rejectInComingCalling() {
        disconnectCall();
        removeView(TIME_DELAY_REJECT_CALLING);
    }

    private void removeView(int timeDelay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                windowManager.removeViewImmediate(view);
                finish();
            }
        }, timeDelay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accept:
                acceptInComingCalling();
                btnAccepted.setEnabled(false);
                break;

            case R.id.btn_reject:
                rejectInComingCalling();
                btnReject.setEnabled(false);
                break;
        }
    }

    private void loadVideoBackground() {
        // TODO thong: load string path ò video from ROOM  database
        // get data user setting at here, in this case, load data from ThemeSelected table in ROOM database.
        File file = new File(getCacheDir() + "/cbvVideo/", "default.mp4");
        String pathVideo = file.getAbsolutePath();


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) videoBackground.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        videoBackground.setLayoutParams(params);
        videoBackground.setVideoPath(pathVideo);
        videoBackground.requestFocus();
        videoBackground.start();
        videoBackground.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    private void showPhoneScreen() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSPARENT);
        /*
         * is have permission draw over app
         * */
        params.gravity = Gravity.CENTER | Gravity.TOP;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(view, params);
        initView(view);

        loadVideoBackground();
    }
}