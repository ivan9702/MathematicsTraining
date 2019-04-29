package com.example.mathematicstraining;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    EditText tvTotalAward, tvTotalHalfAward, tvErrors;
    EditText etCars, etShield, etTV, etToy, etPark;

    SharedPreferences sharedata;
    SharedPreferences.Editor editor;

    Button btnAddBasic, btnAddMedium, btnAddHigh;
    Button btnSubtrBasic, btnSubtrMedium, btnSubtrHigh;
    Button btnMultiBasic, btnMultiMedium, btnMultiHigh;
    Button btnDivBasic, btnDivMedium,btnDivHigh;
    Button btnCommit, btnCancel;

    int AddBasic, AddMedium, AddHigh;
    int SubtrBasic, SubtrMedium, SubtrHigh;
    int  MultiBasic, MultiMedium, MultiHigh;


    int Cars, Shield, TV, Toy, Park;
    int stars, halfstars, error;

    Boolean Entry_Cars = true;
    Boolean Entry_Shield = true;
    Boolean Entry_TV = true;
    Boolean Entry_Toy = true;
    Boolean Entry_Park = true;

    Boolean Entry_stars = true;
    Boolean Entry_halfstar = true;
    Boolean Entry_error = true;
 //   private MediaPlayer mediaPlayer;

    int btnAddBasicCount=0, btnAddMediumCount=0, btnAddHighCount=0, btnSubtrBasicCount=0, btnSubtrMediumCount=0, btnSubtrHighCount=0, btnMultiBasicCount=0, btnMultiMediumCount=0, btnMultiHighCount=0;
    Calendar mCal;
    //  TextView tvOpDate;
    CharSequence date_temp, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedata = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata.edit();//获取Editor

        Log.d("Setting onCreate", "stars:"+sharedata.getInt("stars",0));
        Log.d("Setting onCreate", "starHalf:"+sharedata.getBoolean("starHalf",false));
        Log.d("Setting onCreate", "errorCount:"+sharedata.getInt("errorCount",0));

        tvTotalAward = findViewById(R.id.tvTotalAward);
        tvTotalHalfAward = findViewById(R.id.tvTotalHalfAward);
        tvErrors = findViewById(R.id.tvErrors);

        etCars = findViewById(R.id.etCars);
        etShield = findViewById(R.id.etShield);
        etTV = findViewById(R.id.etTV);
        etToy = findViewById(R.id.etToy);
        etPark = findViewById(R.id.etPark);

        btnAddBasic = findViewById(R.id.btnAddBasic);
        btnAddMedium = findViewById(R.id.btnAddMedium);
        btnAddHigh = findViewById(R.id.btnAddHigh);

        btnSubtrBasic = findViewById(R.id.btnSubtrBasic);
        btnSubtrMedium = findViewById(R.id.btnSubtrMedium);
        btnSubtrHigh = findViewById(R.id.btnSubtrHigh);

        btnMultiBasic = findViewById(R.id.btnMultiBasic);
        btnMultiMedium = findViewById(R.id.btnMultiMedium);
        btnMultiHigh = findViewById(R.id.btnMultiHigh);

        btnDivBasic = findViewById(R.id.btnDivBasic);
        btnDivMedium = findViewById(R.id.btnDivMedium);
        btnDivHigh = findViewById(R.id.btnDivHigh);

        btnCommit = findViewById(R.id.btnCommit);
        btnCancel = findViewById(R.id.btnCancel);


        etCars.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_Cars) {
                    etCars.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_Cars = false;
            }
        });

        etShield.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_Shield) {
                    etShield.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_Shield = false;
            }
        });

        etTV.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_TV) {
                    etTV.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_TV = false;
            }
        });

        etToy.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_Toy) {
                    etToy.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_Toy = false;
            }
        });

        etPark.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_Park) {
                    etPark.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_Park = false;
            }
        });

        tvTotalAward.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_stars) {
                    tvTotalAward.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_stars = false;
            }
        });

        tvTotalHalfAward.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_halfstar) {
                    tvTotalHalfAward.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_halfstar = false;
            }
        });

        tvErrors.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Entry_error) {
                    tvErrors.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                Entry_error = false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedata = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata.edit();//获取Editor

        Log.d("Setting onResume", "stars:"+sharedata.getInt("stars",0));
        Log.d("Setting onResume", "starHalf:"+sharedata.getBoolean("starHalf",false));
        Log.d("Setting onResume", "errorCount:"+sharedata.getInt("errorCount",0));

        Log.d("Setting onResume", "addBasic:"+sharedata.getInt("addBasic",0));
        Log.d("Setting onResume", "addMedium:"+sharedata.getInt("addMedium",0));
        Log.d("Setting onResume", "addHigh:"+sharedata.getInt("addHigh",0));

        Log.d("Setting onResume", "subtractionBasic:"+sharedata.getInt("subtractionBasic",0));
        Log.d("Setting onResume", "subtractionMedium:"+sharedata.getInt("subtractionMedium",0));
        Log.d("Setting onResume", "subtractionHigh:"+sharedata.getInt("subtractionHigh",0));

        Log.d("Setting onResume", "multiBasic:"+sharedata.getInt("multiBasic",0));
        Log.d("Setting onResume", "multiMedium:"+sharedata.getInt("multiMedium",0));
        Log.d("Setting onResume", "multiHigh:"+sharedata.getInt("multiHigh",0));

        Log.d("Setting onResume", "date:"+sharedata.getString("date", "0"));

        Log.d("Setting onResume", "errImgNum:"+sharedata.getInt("errImgNum",0));

        Log.d("Setting onResume", "CarNum:"+sharedata.getInt("CarNum",0));
        Log.d("Setting onResume", "ShieldNum:"+sharedata.getInt("ShieldNum",0));
        Log.d("Setting onResume", "TVNum:"+sharedata.getInt("TVNum",0));

        Log.d("Setting onResume", "ToyNum:"+sharedata.getInt("ToyNum", 0));

        Log.d("Setting onResume", "ParkNum:"+sharedata.getInt("ParkNum",0));

//==========================================================================================================
//              Get Data from SharePreference
//==========================================================================================================

        AddBasic = Integer.valueOf(sharedata.getInt("addBasic",0));
        AddMedium = Integer.valueOf(sharedata.getInt("addMedium",0));
        AddHigh = Integer.valueOf(sharedata.getInt("addHigh",0));

        SubtrBasic = Integer.valueOf(sharedata.getInt("subtractionBasic",0));
        SubtrMedium = Integer.valueOf(sharedata.getInt("subtractionMedium",0));
        SubtrHigh = Integer.valueOf(sharedata.getInt("subtractionHigh",0));

        MultiBasic = Integer.valueOf(sharedata.getInt("multiBasic",0));
        MultiMedium = Integer.valueOf(sharedata.getInt("multiMedium",0));
        MultiHigh = Integer.valueOf(sharedata.getInt("multiHigh",0));


        Cars= sharedata.getInt("CarNum",0);
        Shield = sharedata.getInt("ShieldNum",0);
        TV = sharedata.getInt("TVNum",0);
        Toy = sharedata.getInt("ToyNum", 0);
        Park = sharedata.getInt("ParkNum",0);

        stars = sharedata.getInt("stars",0);
        halfstars = (sharedata.getBoolean("starHalf",false)? 1:0);
        error = sharedata.getInt("errorCount",0);

         //=====================================================================================================
        //                      Show SharedPreferences Data
        //=====================================================================================================


        if(sharedata.getBoolean("starHalf",false) == false)
            tvTotalHalfAward.setText("0");
        else
            tvTotalHalfAward.setText("1");

        tvTotalAward.setText(Integer.toString(sharedata.getInt("stars",0)));

        tvErrors.setText(Integer.toString(sharedata.getInt("errorCount",0)));

        String Str = btnAddBasic.getText().toString()+Integer.valueOf(sharedata.getInt("addBasic",0));
        btnAddBasic.setText(Str);
        Str = btnAddMedium.getText().toString()+Integer.valueOf(sharedata.getInt("addMedium",0));
        btnAddMedium.setText(Str);
        Str = btnAddHigh.getText().toString()+Integer.valueOf(sharedata.getInt("addHigh",0));
        btnAddHigh.setText(Str);

        Str = btnSubtrBasic.getText().toString()+Integer.valueOf(sharedata.getInt("subtractionBasic",0));
        btnSubtrBasic.setText(Str);
        Str = btnSubtrMedium.getText().toString()+Integer.valueOf(sharedata.getInt("subtractionMedium",0));
        btnSubtrMedium.setText(Str);
        Str = btnSubtrHigh.getText().toString()+Integer.valueOf(sharedata.getInt("subtractionHigh",0));
        btnSubtrHigh.setText(Str);

        Str = btnMultiBasic.getText().toString()+Integer.valueOf(sharedata.getInt("multiBasic",0));
        btnMultiBasic.setText(Str);
        Str = btnMultiMedium.getText().toString()+Integer.valueOf(sharedata.getInt("multiMedium",0));
        btnMultiMedium.setText(Str);
        Str = btnMultiHigh.getText().toString()+Integer.valueOf(sharedata.getInt("multiHigh",0));
        btnMultiHigh.setText(Str);

//
//        Str = etCars.getText().toString()+Integer.valueOf(Cars);
//        etCars.setText(Str);
//        Str = etShield.getText().toString()+Integer.valueOf(Shield);
//        etShield.setText(Str);
//        Str = etTV.getText().toString()+Integer.valueOf(TV);
//        etTV.setText(Str);
//        Str = etToy.getText().toString()+Integer.valueOf(Toy);
//        etToy.setText(Str);
//        Str = etPark.getText().toString()+Integer.valueOf(Park);
//        etPark.setText(Str);
        etCars.setText(Integer.toString(Cars));
        etShield.setText(Integer.toString(Shield));
        etTV.setText(Integer.toString(TV));
        etToy.setText(Integer.toString(Toy));
        etPark.setText(Integer.toString(Park));
    }

    public void ButtonClick(View view) {

        int id= view.getId();
        String Str;
        int len;
        switch(id)
        {
            case R.id.btnCommit:
                commitFunc();
                Log.d("BTN", "Commit");
                break;

            case R.id.btnCancel:
                Log.d("BTN", "Cancel");
                cancelFunc();
                break;

            case R.id.btnAddBasic:
                Log.d("BTN", "AddBasic");
                if(btnAddBasicCount == 0)
                {
                    AddBasic = 0;
                    btnAddBasicCount++;
                }
                else if(btnAddBasicCount==1)
                {
                    AddBasic = 1;
                    btnAddBasicCount++;
                }
                else{
                    AddBasic=2;
                    btnAddBasicCount=0;
                }
                len=btnAddBasic.getText().toString().length();
                Str = btnAddBasic.getText().toString().substring(0, len-1);
                Log.d("BTN", "AddBasic: "+Str);
                Str = Str+Integer.toString(AddBasic);
                btnAddBasic.setText(Str);
                btnAddBasic.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnAddMedium:
                Log.d("BTN", "AddMedium");

                if(btnAddMediumCount == 0)
                {
                    AddMedium = 0;
                    btnAddMediumCount++;
                }
                else if(btnAddBasicCount==1)
                {
                    AddMedium = 1;
                    btnAddMediumCount++;
                }
                else{
                    AddMedium=2;
                    btnAddMediumCount=0;
                }
                len=btnAddMedium.getText().toString().length();
                Str = btnAddMedium.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(AddMedium);
                btnAddMedium.setText(Str);
                btnAddMedium.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnAddHigh:
                Log.d("BTN", "AddHigh");

                if(btnAddHighCount == 0)
                {
                    AddHigh = 0;
                    btnAddHighCount++;
                }
                else if(btnAddHighCount==1)
                {
                    AddHigh = 1;
                    btnAddHighCount++;
                }
                else{
                    AddHigh=2;
                    btnAddHighCount=0;
                }
                len=btnAddHigh.getText().toString().length();
                Str = btnAddHigh.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(AddHigh);
                btnAddHigh.setText(Str);
                btnAddHigh.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnSubtrBasic:
                Log.d("BTN", "SubtrBasic");

                if(btnSubtrBasicCount ==0)
                {
                    SubtrBasic = 0;
                    btnSubtrBasicCount++;
                }
                else if(btnSubtrBasicCount ==1)
                {
                    SubtrBasic = 1;
                    btnSubtrBasicCount++;
                }
                else
                {
                    SubtrBasic = 2;
                    btnSubtrBasicCount=0;
                }
                len=btnSubtrBasic.getText().toString().length();
                Str = btnSubtrBasic.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(SubtrBasic);
                btnSubtrBasic.setText(Str);
                btnSubtrBasic.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnSubtrMedium:
                Log.d("BTN", "SubtrMedium");

                if(btnSubtrMediumCount ==0)
                {
                    SubtrMedium=0;
                    btnSubtrMediumCount++;
                }
                else if(btnSubtrMediumCount==1)
                {
                    SubtrMedium=1;
                    btnSubtrMediumCount++;
                }
                else{
                    SubtrMedium=2;
                    btnSubtrMediumCount=0;
                }
                len=btnSubtrMedium.getText().toString().length();
                Str = btnSubtrMedium.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(SubtrMedium);
                btnSubtrMedium.setText(Str);
                btnSubtrMedium.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.btnSubtrHigh:
                Log.d("BTN", "SubtrHigh");

                if(btnSubtrHighCount ==0)
                {
                    SubtrHigh=0;
                    btnSubtrHighCount++;
                }
                else if(btnSubtrHighCount==1)
                {
                    SubtrHigh=1;
                    btnSubtrHighCount++;
                }
                else{
                    SubtrHigh=2;
                    btnSubtrHighCount=0;
                }
                len=btnSubtrHigh.getText().toString().length();
                Str = btnSubtrHigh.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(SubtrHigh);
                btnSubtrHigh.setText(Str);
                btnSubtrHigh.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnMultiBasic:
                Log.d("BTN", "btnMultiBasic");

                if(btnMultiBasicCount ==0)
                {
                    MultiBasic=0;
                    btnMultiBasicCount++;
                }
                else if(btnMultiBasicCount==1)
                {
                    MultiBasic=1;
                    btnMultiBasicCount++;
                }
                else{
                    MultiBasic=2;
                    btnMultiBasicCount=0;
                }
                len=btnMultiBasic.getText().toString().length();
                Str = btnMultiBasic.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(MultiBasic);
                btnMultiBasic.setText(Str);
                btnMultiBasic.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnMultiMedium:
                Log.d("BTN", "btnMultiMedium");

                if(btnMultiMediumCount ==0)
                {
                    MultiMedium=0;
                    btnMultiMediumCount++;
                }
                else if(btnMultiMediumCount==1)
                {
                    MultiMedium=1;
                    btnMultiMediumCount++;
                }
                else{
                    MultiMedium=2;
                    btnMultiMediumCount=0;
                }
                len=btnMultiMedium.getText().toString().length();
                Str = btnMultiMedium.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(MultiMedium);
                btnMultiMedium.setText(Str);
                btnMultiMedium.setTextColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btnMultiHigh:
                Log.d("BTN", "btnMultiHigh");

                if(btnMultiHighCount ==0)
                {
                    MultiHigh=0;
                    btnMultiHighCount++;
                }
                else if(btnMultiHighCount==1)
                {
                    MultiHigh=1;
                    btnMultiHighCount++;
                }
                else{
                    MultiHigh=2;
                    btnMultiHighCount=0;
                }
                len=btnMultiHigh.getText().toString().length();
                Str = btnMultiHigh.getText().toString().substring(0, len-1);
                Str = Str+Integer.toString(MultiHigh);
                btnMultiHigh.setText(Str);
                btnMultiHigh.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }


    }

    void commitFunc() {
        sharedata = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata.edit();//获取Editor

        //
        editor.putInt("addBasic", AddBasic);
        editor.putInt("addMedium", AddMedium);
        editor.putInt("addHigh", AddHigh);

        editor.putInt("subtractionBasic", SubtrBasic);
        editor.putInt("subtractionMedium", SubtrMedium);
        editor.putInt("subtractionHigh", SubtrHigh);

        editor.putInt("multiBasic", MultiBasic);
        editor.putInt("multiMedium", MultiMedium);
        editor.putInt("multiHigh", MultiHigh);


        Cars = Integer.valueOf(etCars.getText().toString());
        Shield = Integer.valueOf(etShield.getText().toString());
        TV = Integer.valueOf(etTV.getText().toString());
        Toy = Integer.valueOf(etToy.getText().toString());
        Park = Integer.valueOf(etPark.getText().toString());

        stars = Integer.valueOf(tvTotalAward.getText().toString());
        halfstars = Integer.valueOf(tvTotalHalfAward.getText().toString());
        error = Integer.valueOf(tvErrors.getText().toString());


        editor.putInt("CarNum", Cars);
        editor.putInt("ShieldNum", Shield);
        editor.putInt("TVNum", TV);
        editor.putInt("ToyNum", Toy);
        editor.putInt("ParkNum", Park);

        editor.putInt("stars", stars);
        if(halfstars == 0)
            editor.putBoolean("starHalf", false);
        else
            editor.putBoolean("starHalf", true);

        editor.putInt("errorCount", error);

        editor.commit();
        cancelFunc();
    }

    void cancelFunc()
    {
        super.onBackPressed();
    }

    public void Forward(View view) {
        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        //String date = sharedata.getString("date", "0");

        String date_FF=date_temp.toString().substring(0, 7);
        Log.d("Setting", "data_FF:"+ date_FF);
        int d_ff = Integer.valueOf(date_temp.toString().substring(7,8));
        Log.d("Setting", "int:" + d_ff+" str:"+date_temp.toString().substring(7,8));

        d_ff--;
        String date = date_FF.toString()+Integer.toString(d_ff);
        Log.d("Setting", "int:" + d_ff+" str:"+date.toString());
        editor.putString("date",date.toString());
        editor.commit();

    }
}
