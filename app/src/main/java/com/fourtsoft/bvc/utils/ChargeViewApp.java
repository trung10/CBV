package com.fourtsoft.bvc.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fourtsoft.bvc.R;

public class ChargeViewApp extends LinearLayoutCompat {

    private TextView tvTitle;
    private TextView tvPrice;
    private LinearLayout container;

    public ChargeViewApp(Context context) {
        super(context);
        init(context, null);
    }

    public ChargeViewApp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChargeViewApp(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet) {
        inflate(getContext(), R.layout.charge_item_unselected, this);
        tvTitle = findViewById(R.id.tv_title);
        tvPrice = findViewById(R.id.tv_price);
        container = findViewById(R.id.container);
    }


    public void setTitleText(String title, String price) {
        tvTitle.setText(title);
        tvPrice.setText(price);
        invalidate();
    }


    public void changeLayout(int type, String title, String price){
        if(type == 1){
            removeAllViewsInLayout();
            inflate(getContext(), R.layout.charge_item_selected, this);
            tvTitle = findViewById(R.id.tv_title);
            tvPrice = findViewById(R.id.tv_price);
            tvTitle.setText(title);
            tvPrice.setText(price);
            postInvalidateDelayed(100);
        }else {
            removeAllViewsInLayout();
            inflate(getContext(), R.layout.charge_item_unselected, this);
            tvTitle = findViewById(R.id.tv_title);
            tvPrice = findViewById(R.id.tv_price);
            tvTitle.setText(title);
            tvPrice.setText(price);
            invalidate();
            postInvalidate();
        }

    }
}
