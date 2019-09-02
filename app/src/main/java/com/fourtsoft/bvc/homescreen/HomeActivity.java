package com.fourtsoft.bvc.homescreen;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseActivity;

import com.fourtsoft.bvc.dao.RepositoryHelper;
import com.fourtsoft.bvc.dao.RepositoryHelper_Factory;
import com.fourtsoft.bvc.homescreen.contactscreen.ContactFragment;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.utils.Constant;
import com.fourtsoft.bvc.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends BaseActivity implements HomeContract.View {
    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;

    private final String TAG = this.getClass().getSimpleName();
    @Inject
    HomeContract.Presenter<HomeContract.View> mPresenter;

    @Override
    protected Activity registerActivityComponent() {
        return this;
    }

    @Override
    public void injectActivity() {
        getActivityComponent().inject(this);
    }

    @Override
    protected Fragment getFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    public int setFragmentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.setLogSequenceMethod();
        Log.e("onDestroy", "onDestroy");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.setLogSequenceMethod();
        Log.e("onDestroy", "onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.setLogSequenceMethod();
        mPresenter.onAttach(this);
//        File outFile = new File(Environment.getExternalStorageDirectory() + "/cbvVideo/", "test.mp4");
//        File file = new File(Environment.getExternalStorageDirectory() + "/cbvVideo");
//        if(!file.exists()){
//            file.mkdirs();
//        }
//        String filePath = outFile.getAbsolutePath();
//        DownloadUtil.downloadVideo(this,"http://www.4tsoft.com/callbackgroundvideo/default.mp4",outFile.getAbsolutePath(),new ProgressBar(this));

        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Constant.REQUEST_PHONE_CALL);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LogUtils.setLogSequenceMethod();
        switch (requestCode) {
            case Constant.REQUEST_CODE_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // in this case, the list of contact is hide
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment nowFragmet = ContactFragment.newInstance();
                    ft.replace(R.id.fragmentContentLayout, nowFragmet, TAG);
                    ft.commit();
                    // call to replace fragment again
                } else {
                    mPresenter.getSettingPermissionContacts();
                }
                break;

            case Constant.REQUEST_PHONE_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.getSettingPermissionCall(true);
                } else {
                    mPresenter.getSettingPermissionCall(false);
                }
                break;
        }
    }
}