package com.fourtsoft.bvc.chargescreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseFragment;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.model.ImageSource;
import com.fourtsoft.bvc.utils.ChargeViewApp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import thuannv.pageslider.InfinitePagerAdapter;
import thuannv.pageslider.PageSliderView;

public class ChargeFragment extends BaseFragment implements ChargeFragmentContact.View, View.OnClickListener {

    private ChargeViewApp threeDays;
    private ChargeViewApp oneMonth;
    private ChargeViewApp oneYear;

    @Inject
    ChargeFragmentContact.Presenter<ChargeFragmentContact.View> mPresenter;

    private static IConnectChargeActivityFragment mListener;

    public static ChargeFragment newInstance(IConnectChargeActivityFragment listener) {
        mListener = listener;
        ChargeFragment fragment = new ChargeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        DaggerActivityComponent
                .builder()
                .applicationComponent(CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build()
                .inject(this);
        return inflater.inflate(R.layout.fragment_charge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);
        threeDays = view.findViewById(R.id.item_container_three_day);
        oneMonth = view.findViewById(R.id.item_container_one_mouth);
        oneYear = view.findViewById(R.id.item_container_one_year);
        FloatingActionButton btnBuy = view.findViewById(R.id.btn_buy);
        PageSliderView pageSliderView = view.findViewById(R.id.image_slider);


        setOnclick(threeDays, oneMonth, oneYear, btnBuy);

        threeDays.changeLayout(1, "3 DAYS TRIAL", "$9.99");
        oneMonth.setTitleText("1 MONTH", "$29.99");
        oneYear.setTitleText("1 YEAR", "$49.99");

        List<ImageSource> list = new ArrayList<>();
        list.add(ImageSource.fromResource(R.drawable.slide_01));
        list.add(ImageSource.fromResource(R.drawable.slide_02));
        list.add(ImageSource.fromResource(R.drawable.slide_03));

        ChargeImageSliderAdapter adapter = new ChargeImageSliderAdapter(list);
        pageSliderView.setAdapter(InfinitePagerAdapter.wrap(adapter));

        mPresenter.getContact();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_container_three_day:
                threeDays.changeLayout(1, "3 DAYS TRIAL", "$9.99");
                oneMonth.changeLayout(0, "1 MONTH", "$29.99");
                oneYear.changeLayout(0, "1 YEAR", "$49.99");
                break;
            case R.id.item_container_one_mouth:
                threeDays.changeLayout(0, "3 DAYS TRIAL", "$9.99");
                oneMonth.changeLayout(1, "1 MONTH", "$29.99");
                oneYear.changeLayout(0, "1 YEAR", "$49.99");
                break;
            case R.id.item_container_one_year:
                threeDays.changeLayout(0, "3 DAYS TRIAL", "$9.99");
                oneMonth.changeLayout(0, "1 MONTH", "$29.99");
                oneYear.changeLayout(1, "1 YEAR", "$49.99");
                break;
        }
    }

    private void setOnclick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }
}
