package com.example.mathematicstraining;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import junit.framework.TestCase;

public class AwardStoreActivity extends AppCompatActivity {

    SharedPreferences sharedata0;
    SharedPreferences.Editor editor;

    TextView tvStars, tvHalfStar,tvOpDate;
    int stars;
    Boolean starHalf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_store);
        getSupportActionBar().hide();
        //設定隱藏狀態View cv = getWindow().getDecorView();
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().SYSTEM_UI_FLAG_FULLSCREEN);


        sharedata0 = getSharedPreferences("award", MODE_PRIVATE);

        tvStars =findViewById(R.id.tvStars);
        tvHalfStar=findViewById(R.id.tvHalfStar);
        tvOpDate=findViewById(R.id.tvOpDate);

        stars = sharedata0.getInt("stars",0);
        starHalf = sharedata0.getBoolean("starHalf",false);

        tvStars.setText(" x "+stars);
        if(starHalf)
            tvHalfStar.setText(" x 1");
        else
            tvHalfStar.setText(" x 0");


        Log.d("Main onResume", "stars:"+sharedata0.getInt("stars",0));
        Log.d("Main onResume", "starHalf:"+sharedata0.getBoolean("starHalf",false));



    }

    @Override
    protected void onResume() {
        super.onResume();
         sharedata0 = getSharedPreferences("award", MODE_PRIVATE);

        Log.d("Main onResume", "stars:"+sharedata0.getInt("stars",0));
        Log.d("Main onResume", "starHalf:"+sharedata0.getBoolean("starHalf",false));
        Log.d("Main onResume", "errorCount:"+sharedata0.getInt("errorCount",0));


        Log.d("Main onResume", "date:"+sharedata0.getString("date", "0"));

        if(sharedata0.getBoolean("starHalf",false) == false)
            tvHalfStar.setText(" x 0");
        else
            tvHalfStar.setText(" x 1");

        tvStars.setText(" x "+sharedata0.getInt("stars",0));



        tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));

    }
}
