package com.cliknfix.technicianReview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.congratulations.CongratulationsActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechReviewActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_technician_name)
    TextView tvTechName;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_review);
        ButterKnife.bind(this);
        init();
    }

    public void onSubmitClicked(View view) {
        startActivity(new Intent(this, CongratulationsActivity.class));
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvTechName.setTypeface(Utility.typeFaceForBoldText(this));
        btnSubmit.setTypeface(Utility.typeFaceForBoldText(this));
    }
}
