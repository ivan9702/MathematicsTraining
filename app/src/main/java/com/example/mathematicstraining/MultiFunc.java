package com.example.mathematicstraining;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;

import static com.example.mathematicstraining.MainActivity.HOMERUN_AWARE;

public class MultiFunc extends AppCompatActivity {

    int units=0, tens=0, hundreds=0;
    int units_Add=0, tens_Add=0, hundreds_Add=0;

    int[] intArrayA = new int[4];
    int[] intArrayB = new int[4];
    int[] intAnswer = new int[4];
    int counts=0,digit=01, edit_Id;
    String Questions;
    int choose=0, multiBasic=0, multiMedium=0, multiHigh=0;
    Boolean GotoAgain = true;
    //Boolean check=true;
    int errorCount=0;

    TextView tvTitle, tvQuestion1, tvQuestion2,tvQuestion3,tvQuestion4,tvTotalAward,getTvTotalHalfAward;
    public  ImageView[] IMGS0 = new ImageView[10];
    public  ImageView[] IMGS1 = new ImageView[10];
    public  ImageView[] IMGS2 = new ImageView[10];
    public  ImageView[] IMGS3 = new ImageView[10];

    public  EditText[] ETS0 = new EditText[10];
    public  EditText[] ETS3 = new EditText[10];

    public  ImageView[] IMGS4 = new ImageView[10];
    public  ImageView[] IMGS5 = new ImageView[10];
    public  ImageView[] IMGS6 = new ImageView[10];
    public  ImageView[] IMGS7 = new ImageView[10];

    public  EditText[] ETS4 = new EditText[10];
    public  EditText[] ETS7 = new EditText[10];
    public EditText etAnswer1, etAnswer2, etAnswer3, etAnswer4;

    public Button btnCheck, btnBack, btnScore;

    public ImageView ivAward1, ivAward2,ivAward3;
    CharSequence date_temp, s;
    int stars=0;
    boolean starHalf=false;
    String date;

    SharedPreferences sharedata;
    SharedPreferences.Editor editor;
    Calendar mCal;
    int errImgNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_func);

        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        sharedata = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata.edit();//获取Editor

        stars = sharedata.getInt("stars", 0);
        starHalf = sharedata.getBoolean("starHalf", false);
        errorCount = sharedata.getInt("errorCount", 0);

        multiBasic = sharedata.getInt("multiBasic", 0);
        multiMedium = sharedata.getInt("multiMedium", 0);
        multiHigh = sharedata.getInt("multiHigh", 0);

        date = sharedata.getString("date", "0");

        errImgNum = sharedata.getInt("errImgNum", 0);
        if(date.compareTo(date_temp.toString()) !=0)
        {
            multiHigh =0;
            multiMedium = 0;
            multiHigh = 0;

            editor.putInt("multiBasic",multiBasic );
            editor.putInt("multiMedium",multiMedium );
            editor.putInt("multiHigh",multiHigh );
            editor.commit();

        }
        Log.d("MultiFunc onCreate", "TODAY:"+ date_temp);
        Log.d("MultiFunc onCreate", "multiBasic:"+sharedata.getInt("multiBasic",0));
        Log.d("MultiFunc onCreate ", "multiMedium:"+sharedata.getInt("multiMedium",0));
        Log.d("MultiFunc onCreate", "multiHigh:"+sharedata.getInt("multiHigh",0));

        Log.d("MultiFunc onCreate", "stars:"+sharedata.getInt("stars",0));
        Log.d("MultiFunc onCreate", "starHalf:"+sharedata.getBoolean("starHalf",false));
        Log.d("MultiFunc onCreate", "starHalf:"+sharedata.getInt("errorCount",0));
        Log.d("MultiFunc onCreate", "date:"+ sharedata.getString("date", "0"));
        Log.d("MultiFunc onCreate", "errImgNum:"+ sharedata.getInt("errImgNum",0));


        tvQuestion1 = findViewById(R.id.tvQuestion1);
        tvQuestion2 = findViewById(R.id.tvQuestion2);
        tvQuestion3 = findViewById(R.id.tvQuestion3);
        tvQuestion4 = findViewById(R.id.tvQuestion4);

        tvTitle = findViewById(R.id.tvTitle);

        etAnswer1 = findViewById(R.id.etAnswer1);
        etAnswer2 = findViewById(R.id.etAnswer2);
        etAnswer3 = findViewById(R.id.etAnswer3);
        etAnswer4 = findViewById(R.id.etAnswer4);

        tvTotalAward = (TextView)findViewById(R.id.tvTotalAward);
        getTvTotalHalfAward = (TextView)findViewById(R.id.tvTotalHalfAward);

        tvTotalAward.setText(" x "+ stars);
        if(starHalf)
            getTvTotalHalfAward.setText((" x 1"));
        else
            getTvTotalHalfAward.setText((" x 0"));

        etAnswer1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    // Perform action on key press
                    do_imm_hidden(etAnswer1);
                    return true;
                }
                return false;
            }
        });
        etAnswer2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    // Perform action on key press
                    do_imm_hidden(etAnswer2);
                    return true;
                }
                return false;
            }
        });
        etAnswer3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    // Perform action on key press
                    do_imm_hidden(etAnswer3);
                    return true;
                }
                return false;
            }
        });
        etAnswer4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    // Perform action on key press
                    do_imm_hidden(etAnswer4);
                    return true;
                }
                return false;
            }
        });

        IMGS0[0] = (ImageView)findViewById(R.id.imageView00);
//        IMGS0[1] = (ImageView)findViewById(R.id.imageView01);
//        IMGS0[2] = (ImageView)findViewById(R.id.imageView02);
//        IMGS0[3] = (ImageView)findViewById(R.id.imageView03);
        IMGS0[4] = (ImageView)findViewById(R.id.imageView04);
        IMGS0[5] = (ImageView)findViewById(R.id.imageView05);
//        IMGS0[6] = (ImageView)findViewById(R.id.imageView06);
//        IMGS0[7] = (ImageView)findViewById(R.id.imageView07);
//        IMGS0[8] = (ImageView)findViewById(R.id.imageView08);
        IMGS0[9] = (ImageView)findViewById(R.id.imageView09);

        ///////////////  HOUSE @Line0    //////////////////
        ETS0[1] = (EditText)findViewById(R.id.imageView01);
        ETS0[2] = (EditText)findViewById(R.id.imageView02);
        ETS0[3] = (EditText)findViewById(R.id.imageView03);
        ETS0[6] = (EditText)findViewById(R.id.imageView06);
        ETS0[7] = (EditText)findViewById(R.id.imageView07);
        ETS0[8] = (EditText)findViewById(R.id.imageView08);


        IMGS1[0] = (ImageView)findViewById(R.id.imageView10);
        IMGS1[1] = (ImageView)findViewById(R.id.imageView11);
        IMGS1[2] = (ImageView)findViewById(R.id.imageView12);
        IMGS1[3] = (ImageView)findViewById(R.id.imageView13);
        IMGS1[4] = (ImageView)findViewById(R.id.imageView14);
        IMGS1[5] = (ImageView)findViewById(R.id.imageView15);
        IMGS1[6] = (ImageView)findViewById(R.id.imageView16);
        IMGS1[7] = (ImageView)findViewById(R.id.imageView17);
        IMGS1[8] = (ImageView)findViewById(R.id.imageView18);
        IMGS1[9] = (ImageView)findViewById(R.id.imageView19);

        IMGS2[0] = (ImageView)findViewById(R.id.imageView20);
        IMGS2[1] = (ImageView)findViewById(R.id.imageView21);
        IMGS2[2] = (ImageView)findViewById(R.id.imageView22);
        IMGS2[3] = (ImageView)findViewById(R.id.imageView23);
        IMGS2[4] = (ImageView)findViewById(R.id.imageView24);
        IMGS2[5] = (ImageView)findViewById(R.id.imageView25);
        IMGS2[6] = (ImageView)findViewById(R.id.imageView26);
        IMGS2[7] = (ImageView)findViewById(R.id.imageView27);
        IMGS2[8] = (ImageView)findViewById(R.id.imageView28);
        IMGS2[9] = (ImageView)findViewById(R.id.imageView29);


        ///////  Locker @Line3    //////////////////
        IMGS3[0] = (ImageView)findViewById(R.id.imageView30);
        ETS3[1] = (EditText)findViewById(R.id.imageView31);
        ETS3[2] = (EditText)findViewById(R.id.imageView32);
        ETS3[3] = (EditText)findViewById(R.id.imageView33);
        ETS3[4] = (EditText)findViewById(R.id.imageView34);

        IMGS3[5] = (ImageView)findViewById(R.id.imageView35);
        ETS3[6] = (EditText)findViewById(R.id.imageView36);
        ETS3[7] = (EditText)findViewById(R.id.imageView37);
        ETS3[8] = (EditText)findViewById(R.id.imageView38);
        ETS3[9] = (EditText)findViewById(R.id.imageView39);



        IMGS4[0] = (ImageView)findViewById(R.id.imageView40);
//        IMGS4[1] = (ImageView)findViewById(R.id.imageView41);
//        IMGS4[2] = (ImageView)findViewById(R.id.imageView42);
//        IMGS4[3] = (ImageView)findViewById(R.id.imageView43);
        IMGS4[4] = (ImageView)findViewById(R.id.imageView44);
        IMGS4[5] = (ImageView)findViewById(R.id.imageView45);
//        IMGS4[6] = (ImageView)findViewById(R.id.imageView46);
//        IMGS4[7] = (ImageView)findViewById(R.id.imageView47);
//        IMGS4[8] = (ImageView)findViewById(R.id.imageView48);
        IMGS4[9] = (ImageView)findViewById(R.id.imageView49);

        ///////////////  HOUSE @Line4    //////////////////
        ETS4[1] = (EditText)findViewById(R.id.imageView41);
        ETS4[2] = (EditText)findViewById(R.id.imageView42);
        ETS4[3] = (EditText)findViewById(R.id.imageView43);
        ETS4[6] = (EditText)findViewById(R.id.imageView46);
        ETS4[7] = (EditText)findViewById(R.id.imageView47);
        ETS4[8] = (EditText)findViewById(R.id.imageView48);

        IMGS5[0] = (ImageView)findViewById(R.id.imageView50);
        IMGS5[1] = (ImageView)findViewById(R.id.imageView51);
        IMGS5[2] = (ImageView)findViewById(R.id.imageView52);
        IMGS5[3] = (ImageView)findViewById(R.id.imageView53);
        IMGS5[4] = (ImageView)findViewById(R.id.imageView54);
        IMGS5[5] = (ImageView)findViewById(R.id.imageView55);
        IMGS5[6] = (ImageView)findViewById(R.id.imageView56);
        IMGS5[7] = (ImageView)findViewById(R.id.imageView57);
        IMGS5[8] = (ImageView)findViewById(R.id.imageView58);
        IMGS5[9] = (ImageView)findViewById(R.id.imageView59);

        IMGS6[0] = (ImageView)findViewById(R.id.imageView60);
        IMGS6[1] = (ImageView)findViewById(R.id.imageView61);
        IMGS6[2] = (ImageView)findViewById(R.id.imageView62);
        IMGS6[3] = (ImageView)findViewById(R.id.imageView63);
        IMGS6[4] = (ImageView)findViewById(R.id.imageView64);
        IMGS6[5] = (ImageView)findViewById(R.id.imageView65);
        IMGS6[6] = (ImageView)findViewById(R.id.imageView66);
        IMGS6[7] = (ImageView)findViewById(R.id.imageView67);
        IMGS6[8] = (ImageView)findViewById(R.id.imageView68);
        IMGS6[9] = (ImageView)findViewById(R.id.imageView69);


        // Locker @line7
        IMGS7[0] = (ImageView)findViewById(R.id.imageView70);
        ETS7[1] = (EditText)findViewById(R.id.imageView71);
        ETS7[2] = (EditText)findViewById(R.id.imageView72);
        ETS7[3] = (EditText)findViewById(R.id.imageView73);
        ETS7[4] = (EditText)findViewById(R.id.imageView74);

        IMGS7[5] = (ImageView)findViewById(R.id.imageView75);
        ETS7[6] = (EditText)findViewById(R.id.imageView76);
        ETS7[7] = (EditText)findViewById(R.id.imageView77);
        ETS7[8] = (EditText)findViewById(R.id.imageView78);
        ETS7[9] = (EditText)findViewById(R.id.imageView79);


        btnCheck = findViewById(R.id.btnCheck);
        btnBack = findViewById(R.id.btnScore);
        btnScore = findViewById(R.id.btnScore);

        btnCheck.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);

        String Questions;
        Intent intent = getIntent();
        choose = intent.getIntExtra("Choose", 1);

        ivAward1 = (ImageView)findViewById(R.id.ivAward1);
        ivAward2 = (ImageView)findViewById(R.id.ivAward2);
        ivAward3 = (ImageView)findViewById(R.id.ivAward3);

        switch (choose) {
            case 1:

                ivAward1.setImageResource(R.drawable.goalfalstars);

                break;
            case 2:
                ivAward1.setImageResource(R.drawable.goalstars);

                break;
            case 3:
                ivAward1.setImageResource(R.drawable.goalstars);
                ivAward2.setImageResource(R.drawable.goalstars);

                break;
            default:
                break;
        }


        Again2Test();


//        ETS3[1].addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.d("TEST", "xxxxxxxx");
//               // ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//               // ETS3[1].setText(null);
//                Log.d("TEST", "yyyyyyyyyyy");
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//               // ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
//                Log.d("TEST", "zzzzzzzzzzzz");
//                ETS3[1].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤
//
//                ETS3[2].setBackground(getResources().getDrawable(R.drawable.white));
//                ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
//            }
//        });

        for(counts =0;counts<=9; counts++) {
            if((counts !=0) && (counts !=5))
            ETS3[counts].addTextChangedListener(new TextWatcher() {
                int l=0;////////记录字符串被删除字符之前，字符串的长度


                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    Log.d("TEST", counts +"   >which one be press....in Line 3");
                    //ETS3[digit].setBackground(getResources().getDrawable(R.drawable.white));
                    if(ETS3[counts].getText().toString().isEmpty())
                    {
                        Log.d("TEST", counts +"   not show ...so EDIT IS EMPTY");
                    }else
                    {
                        Log.d("TEST", ETS3[counts].getText().toString() +"   BEFORE");
                    }

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

//                    ETS3[counts].setText(null); if(!ETS3[counts].getText().toString().isEmpty()) {
//                                            digit = Integer.getInteger(ETS3[counts].getText().toString());
//                                            Log.d("TEST", digit + "   >get answer....");
//                    }
                    if(ETS3[counts].getText().toString().isEmpty())
                    {
                        Log.d("TEST", counts +"   EDIT EMPTY");
                    }else
                    {
                        Log.d("TEST", ETS3[counts].getText().toString() +"   >>>ONTextChanged");
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {
                    // ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
                   // Log.d("TEST", "zzzzzzzzzzzz");

                   // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤
                   // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤






                   if(!ETS3[counts].getText().toString().isEmpty())
                        digit = Integer.valueOf(ETS3[counts].getText().toString().trim());
                    Log.d("TEST", digit+" >> change to integer");
                   //ETS3[2].setBackground(getResources().getDrawable(R.drawable.white));


                    ETS3[counts].setBackground(getResources().getDrawable(GetResourceIDfromInt(digit)));

                    InputMethodManager imm = (InputMethodManager)  getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow( ETS3[counts].getWindowToken(), 0);



                    ETS3[counts].setFocusable(false);

                    ETS3[counts].setFocusableInTouchMode(false);

                    do_imm_hidden(etAnswer1);
                    do_imm_hidden(etAnswer2);
                    do_imm_hidden(etAnswer3);
                    do_imm_hidden(etAnswer4);

                    for(int i =0;i<=9; i++) {
                        if ((i != 0) && (i != 4) && (i != 5) && (i != 9)) {
                            ETS0[i].setFocusable(false);
                            ETS0[i].setFocusableInTouchMode(false);
                            ETS4[i].setFocusable(false);
                            ETS4[i].setFocusableInTouchMode(false);
                        }

                   }
                    if(!ETS3[counts].getText().toString().isEmpty())
                        ETS3[counts].setText(null);
                }
            });

        }

        for(counts =0;counts<=9; counts++) {
            if ((counts != 0) && (counts != 5))
                ETS7[counts].addTextChangedListener(new TextWatcher() {
//                    int l = 0;////////记录字符串被删除字符之前，字符串的长度
//                    int location = 0;//记录光标的位置

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        Log.d("TEST", counts + "   >>>which one be press....");
                        //ETS3[digit].setBackground(getResources().getDrawable(R.drawable.white));
                        if (ETS7[counts].getText().toString().isEmpty()) {
                            Log.d("TEST", counts + "   EDIT EMPTY");
                        } else {
                            Log.d("TEST", ETS7[counts].getText().toString() + "   BEFORE");
                        }

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

//                    ETS3[counts].setText(null); if(!ETS3[counts].getText().toString().isEmpty()) {
//                                            digit = Integer.getInteger(ETS3[counts].getText().toString());
//                                            Log.d("TEST", digit + "   >get answer....");
//                    }
                        if (ETS7[counts].getText().toString().isEmpty()) {
                            Log.d("TEST", counts + "   EDIT EMPTY");
                        } else {
                            Log.d("TEST", ETS7[counts].getText().toString() + "   ON");
                        }

                        Log.d("TEST", "yyyyyyyyyyy");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
                        Log.d("TEST", "zzzzzzzzzzzz");

                        // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤
                        // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤


                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ETS7[counts].getWindowToken(), 0);


                        if (!ETS7[counts].getText().toString().isEmpty())
                            digit = Integer.valueOf(ETS7[counts].getText().toString().trim());
                        Log.d("TEST", digit + " zzzzzzzzzzzz");
                        //ETS3[2].setBackground(getResources().getDrawable(R.drawable.white));
                        ETS7[counts].setBackground(getResources().getDrawable(GetResourceIDfromInt(digit)));

                        ETS7[counts].setFocusable(false);

                        ETS7[counts].setFocusableInTouchMode(false);

                        do_imm_hidden(etAnswer1);
                        do_imm_hidden(etAnswer2);
                        do_imm_hidden(etAnswer3);
                        do_imm_hidden(etAnswer4);

                        for(int i =0;i<=9; i++) {
                            if ((i != 0) && (i != 4) && (i != 5) && (i != 9)) {
                                ETS0[i].setFocusable(false);
                                ETS0[i].setFocusableInTouchMode(false);
                                ETS4[i].setFocusable(false);
                                ETS4[i].setFocusableInTouchMode(false);
                            }

                        }
                        if (!ETS7[counts].getText().toString().isEmpty())
                            ETS7[counts].setText(null);
                    }
                });
        }
        for(counts =0;counts<=9; counts++) {
            if((counts!=0) && (counts !=4) && (counts !=5) && (counts !=9))
                ETS0[counts].addTextChangedListener(new TextWatcher() {


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        Log.d("TEST", counts + "   >>>which one be press....");
                        //ETS3[digit].setBackground(getResources().getDrawable(R.drawable.white));
                        if (ETS0[counts].getText().toString().isEmpty()) {
                            Log.d("TEST", counts + "   EDIT EMPTY");
                        } else {
                            Log.d("TEST", ETS0[counts].getText().toString() + "   BEFORE");
                        }

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

//                    ETS3[counts].setText(null); if(!ETS3[counts].getText().toString().isEmpty()) {
//                                            digit = Integer.getInteger(ETS3[counts].getText().toString());
//                                            Log.d("TEST", digit + "   >get answer....");
//                    }
                        if (ETS0[counts].getText().toString().isEmpty()) {
                            Log.d("TEST", counts + "   EDIT EMPTY");
                        } else {
                            Log.d("TEST", ETS0[counts].getText().toString() + "   ON");
                        }

                        Log.d("TEST", "yyyyyyyyyyy");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
                        Log.d("TEST", "zzzzzzzzzzzz");

                        // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤
                        // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤


                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ETS7[counts].getWindowToken(), 0);


                        if (!ETS0[counts].getText().toString().isEmpty())
                            digit = Integer.valueOf(ETS0[counts].getText().toString().trim());
                        Log.d("TEST", digit + " zzzzzzzzzzzz");
                        //ETS3[2].setBackground(getResources().getDrawable(R.drawable.white));
                        ETS0[counts].setBackground(getResources().getDrawable(GetCarryResourceIDFromInt(digit)));

                        ETS0[counts].setFocusable(false);

                        ETS0[counts].setFocusableInTouchMode(false);

                        do_imm_hidden(etAnswer1);
                        do_imm_hidden(etAnswer2);
                        do_imm_hidden(etAnswer3);
                        do_imm_hidden(etAnswer4);

                        for(int i =0;i<=9; i++) {
                            if ((i != 0) && (i != 4) && (i != 5) && (i != 9)) {
                                ETS0[i].setFocusable(false);
                                ETS0[i].setFocusableInTouchMode(false);
                                ETS4[i].setFocusable(false);
                                ETS4[i].setFocusableInTouchMode(false);
                            }

                        }
                        if (!ETS0[counts].getText().toString().isEmpty())
                            ETS0[counts].setText(null);


                    }
                });
        }

        for(counts =0;counts<=9; counts++) {
            if((counts!=0) && (counts !=4) && (counts !=5) && (counts !=9))
                ETS4[counts].addTextChangedListener(new TextWatcher() {


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        Log.d("TEST", counts + "   >>>which one be press....");
                        //ETS3[digit].setBackground(getResources().getDrawable(R.drawable.white));
                        if (ETS4[counts].getText().toString().isEmpty()) {
                            Log.d("TEST", counts + "   EDIT EMPTY");
                        } else {
                            Log.d("TEST", ETS4[counts].getText().toString() + "   BEFORE");
                        }

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

//                    ETS3[counts].setText(null); if(!ETS3[counts].getText().toString().isEmpty()) {
//                                            digit = Integer.getInteger(ETS3[counts].getText().toString());
//                                            Log.d("TEST", digit + "   >get answer....");
//                    }
                        if (ETS4[counts].getText().toString().isEmpty()) {
                            Log.d("TEST", counts + "   EDIT EMPTY");
                        } else {
                            Log.d("TEST", ETS4[counts].getText().toString() + "   ON");
                        }

                        Log.d("TEST", "yyyyyyyyyyy");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // ETS3[1].setBackground(getResources().getDrawable(R.drawable.white));
                        Log.d("TEST", "zzzzzzzzzzzz");

                        // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤
                        // ETS3[counts].setInputType(InputType.TYPE_NULL); // 關閉軟鍵盤


                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(ETS4[counts].getWindowToken(), 0);


                        if (!ETS4[counts].getText().toString().isEmpty())
                            digit = Integer.valueOf(ETS4[counts].getText().toString().trim());
                        Log.d("TEST", digit + " zzzzzzzzzzzz");
                        //ETS3[2].setBackground(getResources().getDrawable(R.drawable.white));
                        ETS4[counts].setBackground(getResources().getDrawable(GetCarryResourceIDFromInt(digit)));


                        ETS4[counts].clearFocus();
                        ETS4[counts].setFocusable(false);


                        ETS4[counts].setFocusableInTouchMode(false);
                        do_imm_hidden(etAnswer1);
                        do_imm_hidden(etAnswer2);
                        do_imm_hidden(etAnswer3);
                        do_imm_hidden(etAnswer4);
                        for(int i =0;i<=9; i++) {
                            if ((i != 0) && (i != 4) && (i != 5) && (i != 9)) {
                                ETS0[i].setFocusable(false);
                                ETS0[i].setFocusableInTouchMode(false);
                                ETS4[i].setFocusable(false);
                                ETS4[i].setFocusableInTouchMode(false);
                            }

                        }
                        if (!ETS4[counts].getText().toString().isEmpty())
                            ETS4[counts].setText(null);

                    }
                });
        }
    }


    void setLayoutDigits(int Num1, int Num2, int Cal, int level)
    {
        if(level == 1) {
            units = Num1;
            units_Add = Num2;

        }else if(level == 2)
        {
            units = Num1%10;
            tens = Num1/10;

            units_Add = Num2%10;
            tens_Add = Num2/10;

        }else {
            hundreds = Num1/100;
            int temp = Num1%100;
            units = temp%10;
            tens = temp/10;

            hundreds_Add = Num2/100;
            temp = Num2%100;
            units_Add = temp%10;
            tens_Add = temp/10;
        }

        units = GetResourceIDfromInt(units);
        units_Add = GetResourceIDfromInt(units_Add);

        tens = GetResourceIDfromInt(tens);
        tens_Add = GetResourceIDfromInt(tens_Add);

        hundreds_Add = GetResourceIDfromInt(hundreds_Add);
        hundreds = GetResourceIDfromInt(hundreds);
        if(level>=1 && level<=3) {
            if(Cal==1){
                IMGS1[4].setImageResource(units);
                IMGS2[4].setImageResource(units_Add);
            }else if(Cal==2){
                IMGS1[9].setImageResource(units);
                IMGS2[9].setImageResource(units_Add);
            }else if(Cal==3){
                IMGS5[4].setImageResource(units);
                IMGS6[4].setImageResource(units_Add);
            }else{
                IMGS5[9].setImageResource(units);
                IMGS6[9].setImageResource(units_Add);
            }

        }
        if(level>=2 && level<=3) {
            if(Cal==1){
                IMGS1[3].setImageResource(tens);
                IMGS2[3].setImageResource(tens_Add);
            }else if(Cal==2){
                IMGS1[8].setImageResource(tens);
                IMGS2[8].setImageResource(tens_Add);
            }else if(Cal==3){
                IMGS5[3].setImageResource(tens);
                IMGS6[3].setImageResource(tens_Add);
            }else{
                IMGS5[8].setImageResource(tens);
                IMGS6[8].setImageResource(tens_Add);
            }
        }
        if(level>=3 && level<=3) {
            if(Cal==1){
                IMGS1[2].setImageResource(hundreds);
                IMGS2[2].setImageResource(hundreds_Add);
            }else if(Cal==2){
                IMGS1[7].setImageResource(hundreds);
                IMGS2[7].setImageResource(hundreds_Add);
            }else if(Cal==3){
                IMGS5[2].setImageResource(hundreds);
                IMGS6[2].setImageResource(hundreds_Add);
            }else{
                IMGS5[7].setImageResource(hundreds);
                IMGS6[7].setImageResource(hundreds_Add);
            }
        }
    }

    void clearDigits()
    {
        for(int i=0; i<=9; i++){
            if((i>=1 && i<=3) ||(i>=6 && i<=8) ) {
                ETS0[i].setBackgroundResource(R.drawable.house_zero);
                ETS4[i].setBackgroundResource(R.drawable.house_zero);
            }
            else
            {
                IMGS0[i].setImageResource(0);
                IMGS4[i].setImageResource(0);
            }
            IMGS1[i].setImageResource(0);
            IMGS2[i].setImageResource(0);
            //IMGS3[i].setImageResource(0);
            // IMGS4[i].setImageResource(0);
            IMGS5[i].setImageResource(0);
            IMGS6[i].setImageResource(0);
            //IMGS7[i].setImageResource(0);
            if((i>=1 && i<=4) ||(i>=6 && i<=9))
            {
//                ETS3[i].setBackground(android.R.drawable.ic_lock_lock);
//                ETS7[i].setBackground(android.R.drawable.ic_lock_lock);
                ETS3[i].setBackground(getResources().getDrawable(R.drawable.locker));
                ETS7[i].setBackground(getResources().getDrawable(R.drawable.locker));
            }

        }
        IMGS2[0].setImageResource(R.drawable.multiplication);
        IMGS2[5].setImageResource(R.drawable.multiplication);

        IMGS6[0].setImageResource(R.drawable.multiplication);
        IMGS6[5].setImageResource(R.drawable.multiplication);

        etAnswer1.setText(null);
        etAnswer2.setText(null);
        etAnswer3.setText(null);
        etAnswer4.setText(null);

        etAnswer1.setBackground(getResources().getDrawable(R.color.colorGray));
        etAnswer2.setBackground(getResources().getDrawable(R.color.colorGray));
        etAnswer3.setBackground(getResources().getDrawable(R.color.colorGray));
        etAnswer4.setBackground(getResources().getDrawable(R.color.colorGray));

        etAnswer1.setEnabled(true);
        etAnswer2.setEnabled(true);
        etAnswer3.setEnabled(true);
        etAnswer4.setEnabled(true);

    }

    int GetResourceIDfromInt(int num)
    {
        int Id=0;
        switch(num)
        {
            case 0:
                Id=R.drawable.zero;
                break;
            case 1:
                Id=R.drawable.one;
                break;
            case 2:
                Id=R.drawable.two;
                break;
            case 3:
                Id=R.drawable.three;
                break;
            case 4:
                Id=R.drawable.four;
                break;
            case 5:
                Id=R.drawable.five;
                break;
            case 6:
                Id=R.drawable.six;
                break;
            case 7:
                Id=R.drawable.seven;
                break;
            case 8:
                Id=R.drawable.eight;
                break;
            case 9:
                Id=R.drawable.nine;
                break;

            default:
                Id=R.drawable.zero;
                break;
        }
        return  Id;
    }

    int GetCarryResourceIDFromInt(int num)
    {
        int Id=0;
        switch(num)
        {
            case 0:
                Id=R.drawable.house_zero;
                break;
            case 1:
                Id=R.drawable.house_one;
                break;
            case 2:
                Id=R.drawable.house_two;
                break;
            case 3:
                Id=R.drawable.house_three;
                break;
            case 4:
                Id=R.drawable.house_four;
                break;
            case 5:
                Id=R.drawable.house_five;
                break;
            case 6:
                Id=R.drawable.house_six;
                break;
            case 7:
                Id=R.drawable.house_seven;
                break;
            case 8:
                Id=R.drawable.house_eight;
                break;
            case 9:
                Id=R.drawable.house_nine;
                break;

            default:
                Id=R.drawable.house_zero;
                break;
        }
        return  Id;
    }

    public void getNumber(View view) {
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null)
        {
            view.requestFocus();
            imm.showSoftInput(view,0);
        }
    }

    public void getAnswer(View view) {
        int Id = view.getId();
        int whichOne =0;
        Log.d("TEST", "getAnswer: Line 3 on click");

        for(int i=0; i<=9;i++)
        {
            if((i!=0) && (i !=5))
            {
                if(Id == ETS3[i].getId())
                {
                    whichOne = i;
                    Log.d("TEST", "getAnswer "+whichOne+" gggggg");
                }
            }
        }
        //for (int i=0; i<=9;i++) {
        //Log.d("TEST", whichOne+" gggggg");
            ETS3[whichOne].setFocusable(true);

            ETS3[whichOne].setFocusableInTouchMode(true);

            ETS3[whichOne].requestFocus();

            ETS3[whichOne].requestFocusFromTouch();
            InputMethodManager inputManager =

                    (InputMethodManager) ETS3[whichOne].getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.showSoftInput(ETS3[whichOne], 0);

            edit_Id = Id;
            counts = whichOne;
        Log.d("TEST", "set counts = "+counts+"  ggggggg"  );

            if (Id == ETS3[whichOne].getId() && !ETS3[whichOne].getText().toString().isEmpty()) {
                //Toast.makeText(AddFunc.this,"TEST",Toast.LENGTH_SHORT).show();
                Log.d("TEST", "eeeeeeee" + ETS3[whichOne].getText().toString().trim());
                //  ETS3[1].setBackground(getResources().getDrawable(GetResourceIDfromInt(Integer.getInteger(ETS3[1].getText().toString()))));
            }
        ETS3[whichOne].setBackground(null);

        //}
    }
    public void getAnswer1(View view) {
        int Id = view.getId();
        int whichOne =0;
        Log.d("TEST", "gggggg");

        for(int i=0; i<=9;i++)
        {
            if((i!=0) && (i !=5))
            {
                if(Id == ETS7[i].getId())
                {
                    whichOne = i;
                    Log.d("TEST", whichOne+" gggggg");
                }
            }
        }
        //for (int i=0; i<=9;i++) {
        Log.d("TEST", whichOne+" 7777777");
        ETS7[whichOne].setFocusable(true);

        ETS7[whichOne].setFocusableInTouchMode(true);

        ETS7[whichOne].requestFocus();

        ETS7[whichOne].requestFocusFromTouch();
        InputMethodManager inputManager =

                (InputMethodManager) ETS7[whichOne].getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(ETS7[whichOne], 0);

        edit_Id = Id;
        counts = whichOne;
        if (Id == ETS7[whichOne].getId() && !ETS7[1].getText().toString().isEmpty()) {
            //Toast.makeText(AddFunc.this,"TEST",Toast.LENGTH_SHORT).show();
            Log.d("TEST", "eeeeeeee" + ETS7[1].getText().toString().trim());
            //  ETS3[1].setBackground(getResources().getDrawable(GetResourceIDfromInt(Integer.getInteger(ETS3[1].getText().toString()))));
        }
        //}
        ETS7[whichOne].setBackground(null);
    }

    public void CheckAnswer(View view) {
        Log.d("TEST", "CheckAnswer..........");
        GotoAgain=true;
        if(btnCheck.getText().toString() == "我還要再算")
        {
//               Again2Test();
//
//           btnCheck.setText("我算好了");
//Log.d("TEST", "我還要再算..........");
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return;
        }

        if(etAnswer1.getText().toString().isEmpty())
        {
            etAnswer1.setBackground(getResources().getDrawable(R.drawable.p150));
            GotoAgain=false;
        }
        if(etAnswer2.getText().toString().isEmpty())
        {
            etAnswer2.setBackground(getResources().getDrawable(R.drawable.p150));
            GotoAgain=false;
        }
        if(etAnswer3.getText().toString().isEmpty())
        {
            etAnswer3.setBackground(getResources().getDrawable(R.drawable.p150));
            GotoAgain=false;
        }
        if(etAnswer4.getText().toString().isEmpty())
        {
            etAnswer4.setBackground(getResources().getDrawable(R.drawable.p150));
            GotoAgain=false;
        }

        if(GotoAgain == false) {
            Toast.makeText(MultiFunc.this, "Please Check Your Answer In RED Fields Carefully", Toast.LENGTH_SHORT).show();


        }else
            do_check();

        return;
    }

    void do_check()
    {

        AlertDialog alertdialog;
        if(Integer.valueOf(etAnswer1.getText().toString()) == intAnswer[0]){
            etAnswer1.setBackground(getResources().getDrawable(R.drawable.good));
        }else {
            etAnswer1.setBackground(getResources().getDrawable(R.drawable.nogood));
            errorCount++;
            GotoAgain = false;
        }

        if(Integer.valueOf(etAnswer2.getText().toString()) == intAnswer[1]){
            etAnswer2.setBackground(getResources().getDrawable(R.drawable.good));
        }else {
            etAnswer2.setBackground(getResources().getDrawable(R.drawable.nogood));
            errorCount++;
            GotoAgain=false;
        }

        if(Integer.valueOf(etAnswer3.getText().toString()) == intAnswer[2]){
            etAnswer3.setBackground(getResources().getDrawable(R.drawable.good));
        }else {
            etAnswer3.setBackground(getResources().getDrawable(R.drawable.nogood));
            errorCount++;
            GotoAgain=false;
        }

        if(Integer.valueOf(etAnswer4.getText().toString()) == intAnswer[3]){
            etAnswer4.setBackground(getResources().getDrawable(R.drawable.good));
        }else {
            etAnswer4.setBackground(getResources().getDrawable(R.drawable.nogood));
            errorCount++;
            GotoAgain=false;
        }

        if(GotoAgain==false)
        {

            MainActivity.screenShot(MultiFunc.this);
            if(errorCount<3) {
                new AlertDialog.Builder(this)
                        .setTitle("ERROR !!!")
                        .setIcon(R.drawable.star1)

                        .setMessage("請再確認答案一次．．．      ("+errorCount+"/3")
                        .setPositiveButton("好的"
                                , new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                    }
                                })
                        .create()
                        .show();

                editor.putInt("errorCount", errorCount);
            }
            else
            {


                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setIcon(R.drawable.star1)
                        .setTitle("WARNING !!")
                        .setMessage("答錯題目已有 "+errorCount+ " 題, 將扣掉 "+ errorCount/3 + " 顆星星")
                        .setPositiveButton("確定", null)
                        .create();
                dialog.show();

                try {
                    Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
                 mAlert.setAccessible(true);
                Object mController = mAlert.get(dialog);Field mMessage = mController.getClass().getDeclaredField("mMessageView");
                mMessage.setAccessible(true);
                TextView mMessageView = (TextView) mMessage.get(mController);
                mMessageView.setTextColor(Color.BLACK);//message样式修改成红色 // title 同理
                Field mTitle = mController.getClass().getDeclaredField("mTitle");
                mMessage.setAccessible(true);
                TextView mTitleView = (TextView) mMessage.get(mController);
                mMessageView.setTextColor(Color.RED);//title样式修改成``色
                } catch (IllegalAccessException e) {e.printStackTrace();
                } catch (NoSuchFieldException e) {e.printStackTrace(); }

                int lose = errorCount/3;
                errorCount %= 3;
                stars-=lose;

                editor.putInt("stars", stars);
                editor.putInt("errorCount", errorCount);
                Log.d("TEST", "LOST "+lose+ "STARs"+"\n"+"errorCount = "+ errorCount);
            }
        }
        else
        {
             /*
          new AlertDialog.Builder(this)
                    .setTitle("AWARD GET !!")
                    .setIcon(R.drawable.goalstars)
                    .setMessage("你好厲害ㄟ　恭喜你得到這次的目標獎勵")
                    .setPositiveButton("確認"
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(AddFunc.this,"獎勵加到總成績了喔"
                                            ,Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            })
                    .create()
                    .show();
                */
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("AWARD GET !!");
            builder.setIcon(R.drawable.goalstars);
            builder.setMessage("你好厲害ㄟ　恭喜你得到這次的目標獎勵");
            builder.setPositiveButton("確認"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MultiFunc.this,"獎勵加到總成績了喔"
                                    ,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
            builder.create();
            alertdialog  = builder.show();

            switch (choose) {
                case 1:

                    if(starHalf == false)
                    {
                        editor.putBoolean("starHalf",true );
                        starHalf=true;
                    }
                    else
                    {
                        stars++;
                        starHalf = false;
                        editor.putBoolean("starHalf",false );
                    }
                    multiBasic++;
                    editor.putInt("stars", stars);
                    editor.putInt("multiBasic",multiBasic );
                    editor.commit();
                    Log.d("TEST", "do check GOTO again...1 multiBasic:"+multiBasic);
                    if(multiBasic == 2) {
                        if(MainActivity.HomeRunCheck(sharedata, true))
                        {
                            alertdialog.dismiss();
                            stars = sharedata.getInt("stars", 0);
                            int homeRunContinue= sharedata.getInt("homeRunContinue", 0);
                            stars+=HOMERUN_AWARE;
                            String Msg=" 已完成今日題目，得到額外獎勵 "+HOMERUN_AWARE+" 顆星星";
                            if(homeRunContinue >1)
                            {
                                Msg+="\n 而且連續HomeRun"+homeRunContinue+" 天, 可以再得到額外獎勵"+MainActivity.fibonacci(sharedata.getInt("homeRunContinue", 0) + 1)+" 顆星星";
                            }
                            Log.d("TEST", "HomeRun Check OK");
                            AlertDialog dialog = new AlertDialog.Builder(this)
                                    .setIcon(R.drawable.star1)
                                    .setTitle("EXCELLENT !!")
                                    .setMessage(Msg)
                                    .setPositiveButton("確定", null)
                                    .create();
                            dialog.show();
                            editor.putInt("stars", stars);
                            editor.commit();
                        }
                        else{
                            Log.d("TEST", "HomeRun Check !oK");
                        }
                        btnCheck.setText("完成了");
                        btnCheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backToAddition( v);
                            }
                        });

                    }
                    else
                        btnCheck.setText("我還要再算");
                    break;
                case 2:

                    stars++;
                    multiMedium++;
                    editor.putInt("stars", stars);
                    editor.putInt("multiMedium",multiMedium );
                    editor.commit();
                    Log.d("TEST", "choose...2 multiMedium:"+multiMedium);
                    if(multiMedium == 2)
                    {
                        if(MainActivity.HomeRunCheck(sharedata, true))
                        {
                            alertdialog.dismiss();
                            stars = sharedata.getInt("stars", 0);
                            int homeRunContinue= sharedata.getInt("homeRunContinue", 0);
                            stars+=HOMERUN_AWARE;
                            String Msg=" 已完成今日題目，得到額外獎勵 "+HOMERUN_AWARE+" 顆星星";
                            if(homeRunContinue >1)
                            {
                                Msg+="\n 而且連續HomeRun"+homeRunContinue+" 天, 可以再得到額外獎勵"+MainActivity.fibonacci(sharedata.getInt("homeRunContinue", 0) + 1)+" 顆星星";
                            }
                            Log.d("TEST", "HomeRun Check OK");
                            AlertDialog dialog = new AlertDialog.Builder(this)
                                    .setIcon(R.drawable.star1)
                                    .setTitle("EXCELLENT !!")
                                    .setMessage(Msg)
                                    .setPositiveButton("確定", null)
                                    .create();
                            dialog.show();
                            editor.putInt("stars", stars);
                            editor.commit();
                        }
                        else{
                            Log.d("TEST", "HomeRun Check !oK");
                        }
                        btnCheck.setText("完成了");
                        btnCheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backToAddition( v);
                            }
                        });
                    }

                    else
                        btnCheck.setText("我還要再算");
                    break;
                case 3:

                    stars+=2;
                    multiHigh++;
                    editor.putInt("stars", stars);
                    editor.putInt("multiHigh",multiHigh);
                    editor.commit();
                    Log.d("TEST", "choose...2 multiHigh:"+multiHigh);
                    if(multiHigh == 2)
                    {
                        if(MainActivity.HomeRunCheck(sharedata, true))
                        {
                            alertdialog.dismiss();
                            stars = sharedata.getInt("stars", 0);
                            int homeRunContinue= sharedata.getInt("homeRunContinue", 0);
                            stars+=HOMERUN_AWARE;
                            String Msg=" 已完成今日題目，得到額外獎勵 "+HOMERUN_AWARE+" 顆星星";
                            if(homeRunContinue >1)
                            {
                                Msg+="\n 而且連續HomeRun"+homeRunContinue+" 天, 可以再得到額外獎勵"+MainActivity.fibonacci(sharedata.getInt("homeRunContinue", 0) + 1)+" 顆星星";
                            }
                            Log.d("TEST", "HomeRun Check OK");
                            AlertDialog dialog = new AlertDialog.Builder(this)
                                    .setIcon(R.drawable.star1)
                                    .setTitle("EXCELLENT !!")
                                    .setMessage(Msg)
                                    .setPositiveButton("確定", null)
                                    .create();
                            dialog.show();
                            editor.putInt("stars", stars);
                            editor.commit();
                        }
                        else{
                            Log.d("TEST", "HomeRun Check !oK");
                        }
                        btnCheck.setText("完成了");
                        btnCheck.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backToAddition( v);
                            }
                        });
                    }
                    else
                        btnCheck.setText("我還要再算");
                    break;
                default:
                    break;
            }

            etAnswer1.setEnabled(false);
            etAnswer2.setEnabled(false);
            etAnswer3.setEnabled(false);
            etAnswer4.setEnabled(false);
            //**　ＮＯＴ　ＹＥＴ．．
        }
        tvTotalAward.setText(" x "+ stars);
        if(starHalf)
            getTvTotalHalfAward.setText((" x 1"));
        else
            getTvTotalHalfAward.setText((" x 0"));
        editor.putString("date",date_temp.toString());
        editor.commit();
    }

    public void getCarry(View view) {
        int Id = view.getId();
        int whichOne =0;
        Log.d("TEST", "getCarry: Line 0 on click");

        for(int i=0; i<=9;i++)
        {
            if((i!=0) && (i !=4) && (i !=5) && (i !=9))
            {
                if(Id == ETS0[i].getId())
                {
                    whichOne = i;
                    Log.d("TEST", "getCarry Line 0"+whichOne+" gggggg");
                }
            }
        }
        //for (int i=0; i<=9;i++) {
        Log.d("TEST", whichOne+" 7777777");
        ETS0[whichOne].setFocusable(true);

        ETS0[whichOne].setFocusableInTouchMode(true);

        ETS0[whichOne].requestFocus();

        ETS0[whichOne].requestFocusFromTouch();
        InputMethodManager inputManager =

                (InputMethodManager) ETS0[whichOne].getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(ETS0[whichOne], 0);

        edit_Id = Id;
        counts = whichOne;
        if (Id == ETS0[whichOne].getId() && !ETS0[whichOne].getText().toString().isEmpty()) {
            //Toast.makeText(AddFunc.this,"TEST",Toast.LENGTH_SHORT).show();
            Log.d("TEST", "eeeeeeee" + ETS0[whichOne].getText().toString().trim());
            //  ETS3[1].setBackground(getResources().getDrawable(GetResourceIDfromInt(Integer.getInteger(ETS3[1].getText().toString()))));
        }
        //}
        ETS0[whichOne].setBackground(null);
    }

    public void getCarry1(View view) {
        int Id = view.getId();
        int whichOne =0;
        Log.d("TEST", "gggggg");

        for(int i=0; i<=9;i++)
        {
            if((i!=0) && (i !=4) && (i !=5) && (i !=9))
            {
                if(Id == ETS4[i].getId())
                {
                    whichOne = i;
                    Log.d("TEST", whichOne+" gggggg");
                }
            }
        }
        //for (int i=0; i<=9;i++) {
        Log.d("TEST", whichOne+" 7777777");
        ETS4[whichOne].setFocusable(true);

        ETS4[whichOne].setFocusableInTouchMode(true);

        ETS4[whichOne].requestFocus();

        ETS4[whichOne].requestFocusFromTouch();
        InputMethodManager inputManager =(InputMethodManager) ETS4[whichOne].getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(ETS4[whichOne], 0);

        edit_Id = Id;
        counts = whichOne;
        if (Id == ETS4[whichOne].getId() && !ETS4[whichOne].getText().toString().isEmpty()) {
            //Toast.makeText(AddFunc.this,"TEST",Toast.LENGTH_SHORT).show();
            Log.d("TEST", "eeeeeeee" + ETS4[whichOne].getText().toString().trim());
            //  ETS3[1].setBackground(getResources().getDrawable(GetResourceIDfromInt(Integer.getInteger(ETS3[1].getText().toString()))));
        }
        //}
        ETS4[whichOne].setBackground(null);
    }

    public void EnterAnswer(View view) {
        int Id = view.getId();
        int whichOne =0;
        Log.d("TEST", "enter answer....");

        if(etAnswer1.getId() == Id) {
            do_imm_show(etAnswer1);
        }else if(etAnswer2.getId() == Id) {
                do_imm_show(etAnswer2);
        }else if(etAnswer3.getId() == Id) {
            do_imm_show(etAnswer3);
        }else if(etAnswer4.getId() == Id) {
            do_imm_show(etAnswer4);
        }
    }

    void do_imm_show(EditText edit)
    {
        edit.setBackground(null);
        edit.setFocusable(true);

        edit.setFocusableInTouchMode(true);

        edit.requestFocus();

        edit.requestFocusFromTouch();
        InputMethodManager inputManager =(InputMethodManager) edit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(edit, 0);
    }

    void do_imm_hidden(EditText edit)
    {

        Log.d("TEST", digit + "do_imm_hidden ...");
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);

        edit.setFocusable(false);
        edit.setFocusableInTouchMode(false);

    }


    public void backToAddition(View view) {
        super.onBackPressed();
    }

    void Again2Test()
    {
        for(int i=0;i<4;i++) {
            switch (choose) {
                case 1:

                    intArrayA[i] = (int) (Math.random() * 10);
                    intArrayB[i] = (int) (Math.random() * 10);

                    break;
                case 2:
                    do {
                        intArrayA[i] = (int) (Math.random() * 100);
                        intArrayB[i] = (int) (Math.random() * 100);
                    }while ( intArrayA[i]<=9 || intArrayB[i]<=9 );
                    break;
                case 3:
                    do {
                        intArrayA[i] = (int) (Math.random() * 1000);
                        intArrayB[i] = (int) (Math.random() * 1000);
                    }while( intArrayA[i]<=99 ||  intArrayB[i]<=99);
                    break;
                default:
                    break;
            }
        }

        switch (choose) {
            case 1:
                Log.d("TEST", "Again2Test... multiBasic:"+multiBasic);

                if(multiBasic==0) {
                    tvTitle.setText("初級乘法 --ROUND 1--   　　　　　  " + s);
                }
                else
                {
                    tvTitle.setText("初級乘法 --ROUND 2--   　　　　　  " + s);
                }

                break;
            case 2:

                if(multiMedium==0) {
                    tvTitle.setText("中級乘法 --ROUND 1--   　　　　　  " + s);
                }
                else
                {
                    tvTitle.setText("中級乘法 --ROUND 2--   　　　　　  " + s);
                }
                break;
            case 3:

                if(multiHigh==0) {
                    tvTitle.setText("高級乘法 --ROUND 1--   　　　　　  " + s);
                }
                else
                {
                    tvTitle.setText("高級乘法 --ROUND 2--   　　　　　  " + s);
                }
                break;
            default:
                break;
        }
        Questions = String.valueOf( intArrayA[0])+" ｘ "+String.valueOf(intArrayB[0])+ " = ";
        tvQuestion1.setText(Questions);
        Questions = String.valueOf(intArrayA[1])+" ｘ "+String.valueOf(intArrayB[1])+ " = ";
        tvQuestion2.setText(Questions);
        Questions = String.valueOf(intArrayA[2])+" ｘ "+String.valueOf(intArrayB[2])+ " = ";
        tvQuestion3.setText(Questions);
        Questions = String.valueOf(intArrayA[3])+" ｘ "+String.valueOf(intArrayB[3])+ " = ";
        tvQuestion4.setText(Questions);

        clearDigits();


        for(int i =1;i<=4; i++)
        {
            intAnswer[i-1] = intArrayA[i-1]*intArrayB[i-1];
            setLayoutDigits(intArrayA[i-1], intArrayB[i-1], i, choose);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        sharedata = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata.edit();//获取Editor

        stars = sharedata.getInt("stars", 0);
        starHalf = sharedata.getBoolean("starHalf", false);
        errorCount = sharedata.getInt("errorCount", 0);

        multiBasic = sharedata.getInt("multiBasic", 0);
        multiMedium = sharedata.getInt("multiMedium", 0);
        multiHigh = sharedata.getInt("multiHigh", 0);//errImgNum

        date = sharedata.getString("date", "0");
        errImgNum = sharedata.getInt("errImgNum", 0);

        if(date.compareTo(date_temp.toString()) !=0)
        {
            multiBasic =0;
            multiMedium = 0;
            multiHigh = 0;

            editor.putInt("multiBasic",multiBasic );
            editor.putInt("multiMedium",multiMedium );
            editor.putInt("multiHigh",multiHigh );
            editor.commit();

        }
        Log.d("MultiFunc onCreate", "TODAY:"+ date_temp);
        Log.d("MultiFunc onResume", "multiBasic:"+sharedata.getInt("multiBasic",0));
        Log.d("MultiFunc onResume ", "multiMedium:"+sharedata.getInt("multiMedium",0));
        Log.d("MultiFunc onResume", "multiHigh:"+sharedata.getInt("multiHigh",0));

        Log.d("MultiFunc onResume", "stars:"+sharedata.getInt("stars",0));
        Log.d("MultiFunc onResume", "starHalf:"+sharedata.getBoolean("starHalf",false));
        Log.d("MultiFunc onResume", "starHalf:"+sharedata.getInt("errorCount",0));
        Log.d("MultiFunc onResume", "date:"+ sharedata.getString("date", "0"));
        Log.d("MultiFunc onCreate", "errImgNum:"+ sharedata.getInt("errImgNum",0));

    }

    public void gotoStore(View view) {
        Intent it = new Intent(MultiFunc.this,AwardStoreActivity.class);
        startActivity(it);
    }


}
