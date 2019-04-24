package com.example.mathematicstraining;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Addition extends Activity {

    public Dialog dialog;
    private Button btnUnits, btnTens, btnHundreds;
    TextView tvTotalAward, tvTotalHalfAward,tvErrors;
    private int Choose = 1;
    SharedPreferences sharedata1;
    SharedPreferences.Editor editor;
    int stars,errorCount,  addBasic, addMedium, addHigh;
    Boolean starHalf;
    Calendar mCal;
  //  TextView tvOpDate;
    CharSequence date_temp, s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);


        btnUnits = findViewById(R.id.btnUnits);
        btnTens = findViewById(R.id.btnTens);
        btnHundreds = findViewById(R.id.btnHundreds);
        //tvOpDate = findViewById(R.id.tvOpDate);
        tvTotalAward= findViewById(R.id.tvTotalAward);
        tvTotalHalfAward= findViewById(R.id.tvTotalHalfAward);
        tvErrors= findViewById(R.id.tvErrors);

        sharedata1 = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata1.edit();//获取Editor

        stars = sharedata1.getInt("stars", 0);
        starHalf = sharedata1.getBoolean("starHalf", false);
        errorCount = sharedata1.getInt("errorCount", 0);

        addBasic = sharedata1.getInt("addBasic", 0);
        addMedium = sharedata1.getInt("addMedium", 0);
        addHigh = sharedata1.getInt("addHigh", 0);

        Log.d("Addition onCreate", "stars:"+sharedata1.getInt("stars",0));
        Log.d("Addition onCreate", "starHalf:"+sharedata1.getBoolean("starHalf",false));
        Log.d("Addition onCreate", "errorCount:"+sharedata1.getInt("errorCount",0));

        Log.d("Addition onCreate", "addBasic:"+sharedata1.getInt("addBasic",0));
        Log.d("Addition onCreate", "addMedium:"+sharedata1.getInt("addMedium",0));
        Log.d("Addition onCreate", "addHigh:"+sharedata1.getInt("addHigh",0));


        Log.d("Addition onCreate", "date:"+sharedata1.getString("date", "0"));


        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        String date = sharedata1.getString("date", "0");

        if(date.compareTo(date_temp.toString()) !=0 )
        {
            addBasic =0;
            addMedium = 0;
            addHigh = 0;

            editor.putInt("addBasic",addBasic );
            editor.putInt("addMedium",addMedium );
            editor.putInt("addHigh",addHigh );
            editor.commit();
            Log.d("Addition onCreate", "CLEAR!! date:"+sharedata1.getString("date", "0"));
        }

        if(addBasic == 2)
            btnUnits.setEnabled(false);
        else
            btnUnits.setEnabled(true);

        if(addMedium == 2)
            btnTens.setEnabled(false);
        else
            btnTens.setEnabled(true);
        if(addHigh == 2)
            btnHundreds.setEnabled(false);
        else
            btnHundreds.setEnabled(true);

        //tvOpDate.setText("上一次測試日期為：　"+date);

        tvTotalAward.setText("x "+ stars);
        if(starHalf)
            tvTotalHalfAward.setText("x 1");
        else
            tvTotalHalfAward.setText("x 0");

        tvErrors.setText("x "+errorCount+"/3");

        btnUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose = 1;
                Intent it = new Intent(Addition.this, AddFunc.class);
                it.putExtra("Choose", Choose);


                startActivity(it);

            }
        });
        btnTens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose = 2;
                Intent it = new Intent(Addition.this, AddFunc.class);
                it.putExtra("Choose", Choose);

                startActivity(it);
            }
        });
        btnHundreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose = 3;
                Intent it = new Intent(Addition.this, AddFunc.class);
                it.putExtra("Choose", Choose);

                startActivity(it);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedata1 = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata1.edit();//获取Editor

        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());


        stars = sharedata1.getInt("stars", 0);
        starHalf = sharedata1.getBoolean("starHalf", false);
        errorCount = sharedata1.getInt("errorCount", 0);

        addBasic = sharedata1.getInt("addBasic", 0);
        addMedium = sharedata1.getInt("addMedium", 0);
        addHigh = sharedata1.getInt("addHigh", 0);

        Log.d("Addition onResume", "stars:"+sharedata1.getInt("stars",0));
        Log.d("Addition onResume", "starHalf:"+sharedata1.getBoolean("starHalf",false));
        Log.d("Addition onResume", "errorCount:"+sharedata1.getInt("errorCount",0));

        Log.d("Addition onResume", "addBasic:"+sharedata1.getInt("addBasic",0));
        Log.d("Addition onResume", "addMedium:"+sharedata1.getInt("addMedium",0));
        Log.d("Addition onResume", "addHigh:"+sharedata1.getInt("addHigh",0));



        Log.d("Addition onResume", "date:"+sharedata1.getString("date", "0"));


        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        String date = sharedata1.getString("date", "0");

        if(date.compareTo(date_temp.toString()) !=0 )
        {
            addBasic =0;
            addMedium = 0;
            addHigh = 0;

            editor.putInt("addBasic",addBasic );
            editor.putInt("addMedium",addMedium );
            editor.putInt("addHigh",addHigh );
            editor.commit();
            Log.d("Addition onResume", "CLEAR!! date:"+sharedata1.getString("date", "0"));
        }

        if(addBasic == 2)
            btnUnits.setEnabled(false);
        else
            btnUnits.setEnabled(true);

        if(addMedium == 2)
            btnTens.setEnabled(false);
        else
            btnTens.setEnabled(true);
        if(addHigh == 2)
            btnHundreds.setEnabled(false);
        else
            btnHundreds.setEnabled(true);

       // tvOpDate.setText("上一次測試日期為：　"+date);
        tvTotalAward.setText("x "+ stars);
        if(starHalf)
            tvTotalHalfAward.setText("x 1");
        else
            tvTotalHalfAward.setText("x 0");

        tvErrors.setText("x "+errorCount+"/3");

    }

    public void backToAddition(View view) {
        super.onBackPressed();
    }

    public void gotoStore(View view) {
        Intent it = new Intent(Addition.this,AwardStoreActivity.class);
        startActivity(it);
    }
}
