package com.fourtsoft.bvc.homescreen.historyscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseFragment;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.dialogs.NotificationDialog;
import com.fourtsoft.bvc.model.History;
import com.fourtsoft.bvc.utils.ViewUtils;

import java.util.List;

import javax.inject.Inject;

public class HistoryFragment extends BaseFragment implements HistoryContract.View, HistoryListener {

    private RecyclerView mListRecycler;
    private ListHistoryAdapter mAdapter;
    private List<History> mList;

    @Inject
    public HistoryContract.Presenter<HistoryContract.View> mPresenter;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build()
                .inject(this);

        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListRecycler = view.findViewById(R.id.list_history);
        mPresenter.onAttach(this);
        mPresenter.getListHistory();

        ImageButton btnDeleteAll = view.findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.size() > 0) {
                    ViewUtils.showDialogMessage((AppCompatActivity) getActivity(), "Delete all?", ViewUtils.DIALOG_DELETE, new NotificationDialog.NotificationDialogListener() {
                        @Override
                        public void onClickOk() {
                        }

                        @Override
                        public void onClickDelete() {
                            removeList();
                        }
                    });
                }
            }
        });


    }

    @Override
    public void showListHistory(List<History> history) {
        mList = history;
        mAdapter = ListHistoryAdapter.getInstance(getActivity(), history, this);
        mListRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mListRecycler.setAdapter(mAdapter);
    }

    @Override
    public void removeList() {
        int size = mList.size();
        mPresenter.deleteAll();
        mList.clear();
        mAdapter.notifyItemRangeRemoved(0, size);
    }

    @Override
    public void onClickHistory() {
        //todo click a item history
    }

    /**
     * @param position When click delete on dialog
     */
    @Override
    public void onLongClickHistory(int position) {
        Log.e("ssss", mList.get(position).getId() + "");
        removeAt(position);
    }

    public void removeAt(int position) {
        mPresenter.deleteHistory(mList.get(position));
        mList.remove(position);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, mList.size());
    }

}