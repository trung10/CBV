package com.fourtsoft.bvc.termscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.fourtsoft.bvc.R;

public class TermActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RelativeLayout relativeTerm1;
    private LinearLayout lnTermOfUseContext1;

    private RelativeLayout relativeTerm2;
    private LinearLayout lnTermOfUseContext2;

    private RelativeLayout relativeTerm3;
    private LinearLayout lnTermOfUseContext3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        relativeTerm1 = findViewById(R.id.rlTermOfUse1);
        lnTermOfUseContext1 = findViewById(R.id.lnTermOfUseContext1);
        relativeTerm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lnTermOfUseContext1.getVisibility() == View.GONE) {
                    lnTermOfUseContext1.setVisibility(View.VISIBLE);
                } else {
                    lnTermOfUseContext1.setVisibility(View.GONE);
                }
            }
        });

        relativeTerm2 = findViewById(R.id.rlTermOfUse2);
        lnTermOfUseContext2 = findViewById(R.id.lnTermOfUseContext2);
        relativeTerm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lnTermOfUseContext2.getVisibility() == View.GONE) {
                    lnTermOfUseContext2.setVisibility(View.VISIBLE);
                } else {
                    lnTermOfUseContext2.setVisibility(View.GONE);
                }
            }
        });

        relativeTerm3 = findViewById(R.id.rlTermOfUse3);
        lnTermOfUseContext3 = findViewById(R.id.lnTermOfUseContext3);
        relativeTerm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lnTermOfUseContext3.getVisibility() == View.GONE) {
                    lnTermOfUseContext3.setVisibility(View.VISIBLE);
                } else {
                    lnTermOfUseContext3.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
}
