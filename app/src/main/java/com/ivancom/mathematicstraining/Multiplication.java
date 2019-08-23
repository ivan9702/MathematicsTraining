package com.ivancom.mathematicstraining;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class Multiplication extends Activity {

    public Dialog dialog;
    private Button btnUnits, btnTens, btnHundreds;
    TextView tvTotalAward, tvTotalHalfAward,tvErrors;
    private int Choose = 1;
    SharedPreferences sharedata1;
    SharedPreferences.Editor editor;
    int stars,errorCount,  multiBasic, multiMedium, multiHigh, subtractionBasic, subtractionMedium, subtractionHigh, addBasic, addMedium, addHigh;
    boolean starHalf;
    Calendar mCal;
    //  TextView tvOpDate;
    CharSequence date_temp, s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);


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

        multiBasic = sharedata1.getInt("multiBasic", 0);
        multiMedium = sharedata1.getInt("multiMedium", 0);
        multiHigh = sharedata1.getInt("multiHigh", 0);

        Log.d("Multiplication onCreate", "stars:"+sharedata1.getInt("stars",0));
        Log.d("Multiplication onCreate", "starHalf:"+sharedata1.getBoolean("starHalf",false));
        Log.d("Multiplication onCreate", "errorCount:"+sharedata1.getInt("errorCount",0));

        Log.d("Multiplication onCreate", "multiBasic:"+sharedata1.getInt("multiBasic",0));
        Log.d("Multiplication onCreate", "multiMedium:"+sharedata1.getInt("multiMedium",0));
        Log.d("Multiplication onCreate", "multiHigh:"+sharedata1.getInt("multiHigh",0));


        Log.d("Multiplication onCreate", "date:"+sharedata1.getString("date", "0"));


        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        String date = sharedata1.getString("date", "0");

        if(date.compareTo(date_temp.toString()) !=0 )
        {
            multiBasic =0;
            multiMedium = 0;
            multiHigh = 0;

            subtractionBasic =0;
            subtractionMedium = 0;
            subtractionHigh=0;

            addBasic =0;
            addMedium = 0;
            addHigh = 0;

            editor.putInt("addBasic",addBasic );
            editor.putInt("addMedium",addMedium );
            editor.putInt("addHigh",addHigh );

            editor.putInt("multiBasic",multiBasic );
            editor.putInt("multiMedium",multiMedium );
            editor.putInt("multiHigh",multiHigh );
            editor.putInt("subtractionBasic",subtractionBasic );
            editor.putInt("subtractionMedium",subtractionMedium );
            editor.putInt("subtractionHigh",subtractionHigh );

            editor.commit();
            Log.d("Multiplication onResume", "CLEAR!! date:"+sharedata1.getString("date", "0"));
        }

        if(multiBasic == 2)
            btnUnits.setEnabled(false);
        else
            btnUnits.setEnabled(true);

        if(multiMedium == 2)
            btnTens.setEnabled(false);
        else
            btnTens.setEnabled(true);
        if(multiHigh == 2)
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
                Intent it = new Intent(Multiplication.this, MultiFunc.class);
                it.putExtra("Choose", Choose);


                startActivity(it);

            }
        });
        btnTens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose = 2;
                Intent it = new Intent(Multiplication.this, MultiFunc2.class);
                it.putExtra("Choose", Choose);

                startActivity(it);
            }
        });
        btnHundreds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose = 3;
                Intent it = new Intent(Multiplication.this, MultiFunc2.class);
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

        multiBasic = sharedata1.getInt("multiBasic", 0);
        multiMedium = sharedata1.getInt("multiMedium", 0);
        multiHigh = sharedata1.getInt("multiHigh", 0);

        Log.d("Multiplication onResume", "stars:"+sharedata1.getInt("stars",0));
        Log.d("Multiplication onResume", "starHalf:"+sharedata1.getBoolean("starHalf",false));
        Log.d("Multiplication onResume", "errorCount:"+sharedata1.getInt("errorCount",0));

        Log.d("Multiplication onResume", "multiBasic:"+sharedata1.getInt("multiBasic",0));
        Log.d("Multiplication onResume", "multiMedium:"+sharedata1.getInt("multiMedium",0));
        Log.d("Multiplication onResume", "multiHigh:"+sharedata1.getInt("multiHigh",0));

        Log.d("Main onResume", "multiBasic:"+sharedata1.getInt("multiBasic",0));
        Log.d("Main onResume", "multiMedium:"+sharedata1.getInt("multiMedium",0));
        Log.d("Main onResume", "multiHigh:"+sharedata1.getInt("multiHigh",0));

        Log.d("Multiplication onResume", "date:"+sharedata1.getString("date", "0"));


        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        String date = sharedata1.getString("date", "0");

        if(date.compareTo(date_temp.toString()) !=0 )
        {
            multiBasic =0;
            multiMedium = 0;
            multiHigh = 0;

            subtractionBasic =0;
            subtractionMedium = 0;
            subtractionHigh=0;

            addBasic =0;
            addMedium = 0;
            addHigh = 0;

            editor.putInt("addBasic",addBasic );
            editor.putInt("addMedium",addMedium );
            editor.putInt("addHigh",addHigh );

            editor.putInt("multiBasic",multiBasic );
            editor.putInt("multiMedium",multiMedium );
            editor.putInt("multiHigh",multiHigh );
            editor.putInt("subtractionBasic",subtractionBasic );
            editor.putInt("subtractionMedium",subtractionMedium );
            editor.putInt("subtractionHigh",subtractionHigh );

            editor.commit();
            Log.d("Multiplication onResume", "CLEAR!! date:"+sharedata1.getString("date", "0"));
        }

        if(multiBasic == 2)
            btnUnits.setEnabled(false);
        else
            btnUnits.setEnabled(true);

        if(multiMedium == 2)
            btnTens.setEnabled(false);
        else
            btnTens.setEnabled(true);
        if(multiHigh == 2)
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
        Intent it = new Intent(Multiplication.this,AwardStoreActivity.class);
        startActivity(it);
    }
}
