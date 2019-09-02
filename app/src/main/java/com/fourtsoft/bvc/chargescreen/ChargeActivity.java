package com.fourtsoft.bvc.chargescreen;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseActivity;

import javax.inject.Inject;

public class ChargeActivity extends BaseActivity implements ChargeContract.View, IConnectChargeActivityFragment {

    @Inject
    ChargePresenter<ChargeContract.View> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
    }

    @Override
    protected Fragment getFragment() {
        return ChargeFragment.newInstance(this);
    }

    @Override
    public int setFragmentLayout() {
        return R.layout.activity_charge;
    }

    @Override
    protected Activity registerActivityComponent() {
        return this;
    }

    @Override
    protected void injectActivity() {
        getActivityComponent().inject(this);
    }


    @Override
    public void itemChargeClickListener() {

    }
}