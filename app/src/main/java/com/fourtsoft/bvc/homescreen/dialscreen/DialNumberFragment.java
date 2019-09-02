package com.fourtsoft.bvc.homescreen.dialscreen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fourtsoft.bvc.CallBackgroundVideoApplication;
import com.fourtsoft.bvc.R;
import com.fourtsoft.bvc.base.BaseFragment;
import com.fourtsoft.bvc.di.component.DaggerActivityComponent;
import com.fourtsoft.bvc.di.module.ActivityModule;
import com.fourtsoft.bvc.utils.NumberUtils;

import javax.inject.Inject;

public class DialNumberFragment extends BaseFragment implements DialNumberContract.View, View.OnClickListener, View.OnTouchListener {

    private View rootView;
    private TextView txtTitle;
    private EditText txtNumber;
    private ImageButton btnCall;
    private CountDownTimer mTimer;

    @Inject
    DialNumberPresenter<DialNumberContract.View> presenter;

    public static DialNumberFragment newInstance() {
        DialNumberFragment fragment = new DialNumberFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DaggerActivityComponent.builder().applicationComponent(
                CallBackgroundVideoApplication.getmApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .build().inject(this);
        rootView = inflater.inflate(R.layout.fragment_dial_number, container, false);
        initView();

        return rootView;
    }

    private void initView() {
        txtTitle = rootView.findViewById(R.id.titleDialNumber);
        txtNumber = rootView.findViewById(R.id.txtNumber);

        ImageButton btnBackSpase = rootView.findViewById(R.id.btnBackSpase);
        btnBackSpase.setOnTouchListener(this);

        btnCall = rootView.findViewById(R.id.btnCall);

        Button btn1 = rootView.findViewById(R.id.btn1);

        Button btn2 = rootView.findViewById(R.id.btn2);

        Button btn3 = rootView.findViewById(R.id.btn3);

        Button btn4 = rootView.findViewById(R.id.btn4);

        Button btn5 = rootView.findViewById(R.id.btn5);

        Button btn6 = rootView.findViewById(R.id.btn6);

        Button btn7 = rootView.findViewById(R.id.btn7);

        Button btn8 = rootView.findViewById(R.id.btn8);

        Button btn9 = rootView.findViewById(R.id.btn9);

        Button btn0 = rootView.findViewById(R.id.btn0);
        btn0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "+"));
                return true;
            }
        });

        setOnClick(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnCall, btnBackSpase);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttach(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "0"));

                break;

            case R.id.btn1:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "1"));

                break;

            case R.id.btn2:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "2"));

                break;

            case R.id.btn3:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "3"));

                break;

            case R.id.btn4:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "4"));

                break;

            case R.id.btn5:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "5"));

                break;

            case R.id.btn6:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "6"));

                break;

            case R.id.btn7:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "7"));

                break;

            case R.id.btn8:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "8"));

                break;

            case R.id.btn9:
                txtNumber.setText(NumberUtils.addLastNumber(txtNumber.getText().toString(), "9"));
                break;

            case R.id.btnCall:
                //todo
                break;

            case R.id.btnBackSpase:
                txtNumber.setText(NumberUtils.backSpase(txtNumber.getText().toString()));

                break;
        }
    }

    private void setOnClick(View... btns) {
        for (View btn : btns) {
            btn.setOnClickListener(this);
        }
    }

    //Set long click button delete
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:

                //delete letter
                deleteChar();

                break;

            case MotionEvent.ACTION_MOVE:
                /*--- no action required ---*/
                break;

            case MotionEvent.ACTION_UP:

                mTimer.cancel();
                break;
        }
        return false;
    }


    @Override
    public void deleteChar() {
        // set the desired interval. I'm gonna use 200ms before last char deletion.
        mTimer = new CountDownTimer(9999999, 200) {
            long time = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                time += 200;
                if (txtNumber.getText().toString().length() > 0 && time < 500) {
                    String input = txtNumber.getText().toString();
                    input = input.substring(0, input.length() - 1);
                    txtNumber.setText(input);

                } else if (txtNumber.getText().toString().length() > 0) {
                    String input = txtNumber.getText().toString();

                    if (input.length() >= 3) {
                        input = input.substring(0, input.length() - 3);

                    } else if (input.length() == 2) {
                        input = input.substring(0, input.length() - 2);

                    } else if (input.length() == 1) {
                        input = input.substring(0, input.length() - 1);
                    }
                    txtNumber.setText(input);
                }
            }

            @Override
            public void onFinish() {

            }
        };
        mTimer.start();
    }
}