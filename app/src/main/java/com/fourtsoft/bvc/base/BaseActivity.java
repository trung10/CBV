package com.fourtsoft.bvc.base;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.di.component.ActivityComponent;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;

import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setFragmentLayout());
        setView(getFragment());

        mActivityComponent = DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(registerActivityComponent()))
                .build();
        injectActivity();
    }

    protected ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected abstract Fragment getFragment();

    public abstract int setFragmentLayout();

    protected abstract Activity registerActivityComponent();

    protected abstract void injectActivity();

    // in per activity, layout must have a FrameLayout with name "contentLayout"
    public void setView(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentLayoutContainer, fragment);
        ft.commit();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showDataEmpty() {

    }

    @Override
    public void setData(BaseModel baseModel) {

    }

    @Override
    public boolean isHaveInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return Objects.requireNonNull(connectivityManager).getActiveNetworkInfo() != null;
    }
}
