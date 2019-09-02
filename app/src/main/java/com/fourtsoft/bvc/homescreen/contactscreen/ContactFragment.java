package com.fourtsoft.bvc.homescreen.contactscreen;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseFragment;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.model.Contact;
import com.fourtsoft.bvc.utils.Constant;
import com.fourtsoft.bvc.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends BaseFragment
        implements ContactContract.View {

    @Inject
    ContactPresenter<ContactContract.View> mPresenter;

    private RecyclerView mContactList;


    public static ContactFragment newInstance() {
        LogUtils.setLogSequenceMethod();
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.setLogSequenceMethod();

        DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build().inject(this);
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LogUtils.setLogSequenceMethod();
        super.onViewCreated(view, savedInstanceState);

        mContactList = view.findViewById(R.id.contact_list);
        mPresenter.onAttach(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                mPresenter.requestPermissionContact();
            } else {
                mPresenter.loadContactList();
            }
        } else {
            mPresenter.loadContactList();
        }

    }

    @Override
    public void showListContact(List<Contact> contacts) {
        mContactList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mContactList.setAdapter(ListContactAdapter.getInstance(contacts, new ContactFragmentListener() {
            @Override
            public void callClick(Contact contact) {

            }

            @Override
            public void detailContactClick(Contact contact) {
                mPresenter.detailContact(contact.getId());
            }
        }));
    }

    @Override
    public void requestRequest() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, Constant.REQUEST_CODE_READ_CONTACTS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDettach();
    }
}
