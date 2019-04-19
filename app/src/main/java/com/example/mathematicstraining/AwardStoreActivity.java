package com.example.mathematicstraining;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AwardStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_store);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedata0 = getSharedPreferences("award", MODE_PRIVATE);

        Log.d("Main onResume", "stars:"+sharedata0.getInt("stars",0));
        Log.d("Main onResume", "starHalf:"+sharedata0.getBoolean("starHalf",false));
        Log.d("Main onResume", "errorCount:"+sharedata0.getInt("errorCount",0));

        Log.d("Main onResume", "addBasic:"+sharedata0.getInt("addBasic",0));
        Log.d("Main onResume", "addMedium:"+sharedata0.getInt("addMedium",0));
        Log.d("Main onResume", "addHigh:"+sharedata0.getInt("addHigh",0));

        Log.d("Main onResume", "subtractionBasic:"+sharedata0.getInt("subtractionBasic",0));
        Log.d("Main onResume", "subtractionMedium:"+sharedata0.getInt("subtractionMedium",0));
        Log.d("Main onResume", "subtractionHigh:"+sharedata0.getInt("subtractionHigh",0));

        Log.d("Main onResume", "multiBasic:"+sharedata0.getInt("MultiBasic",0));
        Log.d("Main onResume", "multiMedium:"+sharedata0.getInt("multiMedium",0));
        Log.d("Main onResume", "multiHigh:"+sharedata0.getInt("multiHigh",0));

        Log.d("Main onResume", "date:"+sharedata0.getString("date", "0"));
//
//        if(sharedata0.getBoolean("starHalf",false) == false)
//            tvHalfStar.setText(" x 0");
//        else
//            tvHalfStar.setText(" x 1");
//
//        tvStars.setText(" x "+sharedata0.getInt("stars",0));
//
//        tvErrors.setText(" x "+sharedata0.getInt("errorCount",0)+"/3");
//
//        tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));

    }
}
