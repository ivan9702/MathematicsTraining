package com.example.mathematicstraining;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

public class AwardStoreActivity extends AppCompatActivity {

    SharedPreferences sharedata0;
    SharedPreferences.Editor editor;

    TextView tvStars, tvHalfStar,tvOpDate;
    int stars;
    Boolean starHalf;

    ImageView ivCar, ivShield, ivTV, ivToy, ivPark;
    TextView tvCarTicket, tvShieldTicket, tvTVTicket, tvToyTicket, tvParkTicket;

    int Cars,Shield,TV,Toy,Park;
    private static final int PRICE_CAR = 50;
    private static final int PRICE_SHIELD = 200;
    private static final int PRICE_TV = 50;
    private static final int PRICE_TOY = 200;
    private static final int PRICE_PARK = 100;

    int selection=0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_store);
        getSupportActionBar().hide();
        //設定隱藏狀態View cv = getWindow().getDecorView();
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().SYSTEM_UI_FLAG_FULLSCREEN);

        ivCar = findViewById(R.id.ivCar);
        ivShield = findViewById(R.id.ivShield);
        ivToy = findViewById(R.id.ivToy);
        ivTV = findViewById(R.id.ivTV);
        ivPark = findViewById(R.id.ivPark);

        tvCarTicket = findViewById(R.id.tvCarTicket);
        tvShieldTicket = findViewById(R.id.tvShieldTicket);
        tvTVTicket = findViewById(R.id.tvTVTicket);
        tvToyTicket = findViewById(R.id.tvToyTicket);
        tvParkTicket = findViewById(R.id.tvParkTicket);

        sharedata0 = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata0.edit();//获取Editor

        tvStars =findViewById(R.id.tvStars);
        tvHalfStar=findViewById(R.id.tvHalfStar);
        tvOpDate=findViewById(R.id.tvOpDate);

        stars = sharedata0.getInt("stars",0);
        starHalf = sharedata0.getBoolean("starHalf",false);

//        tvStars.setText(" x "+stars);
//        if(starHalf)
//            tvHalfStar.setText("x 1");
//        else
//            tvHalfStar.setText("x 0");

        mediaPlayer=new MediaPlayer();

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
        String path = file.getPath()+"/buy.wav";
        Log.d("Main onCreate", "wav Path:"+path);
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("AwardStore onCreate", "stars:"+sharedata0.getInt("stars",0));
        Log.d("AwardStore onCreate", "starHalf:"+sharedata0.getBoolean("starHalf",false));



    }

    @Override
    protected void onResume() {
        super.onResume();
         sharedata0 = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata0.edit();//获取Editor

        Log.d("AwardStore onResume", "stars:"+sharedata0.getInt("stars",0));
        Log.d("AwardStore onResume", "starHalf:"+sharedata0.getBoolean("starHalf",false));
        Log.d("AwardStore onResume", "errorCount:"+sharedata0.getInt("errorCount",0));

        stars = sharedata0.getInt("stars",0);
        starHalf = sharedata0.getBoolean("starHalf",false);

        Cars= sharedata0.getInt("CarNum",0);
        Shield = sharedata0.getInt("ShieldNum",0);
        TV = sharedata0.getInt("TVNum",0);
        Toy = sharedata0.getInt("ToyNum", 0);
        Park = sharedata0.getInt("ParkNum",0);

        Log.d("AwardStore onResume", "date:"+sharedata0.getString("date", "0"));

        if(sharedata0.getBoolean("starHalf",false) == false)
            tvHalfStar.setText("x 0");
        else
            tvHalfStar.setText("x 1");

        tvStars.setText(" x "+sharedata0.getInt("stars",0));

        String str = tvCarTicket.getText().toString().substring(0,3);
        tvCarTicket.setText(str+Integer.toString(Cars));
        tvShieldTicket.setText(str+Integer.toString(Shield));
        tvTVTicket.setText(str+Integer.toString(TV));
        tvToyTicket.setText(str+Integer.toString(Toy));
        tvParkTicket.setText(str+Integer.toString(Park));

        tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));

    }

    void CleanSelect()
    {
        ivCar.setImageResource(R.drawable.i8);
        ivShield.setImageResource(R.drawable.changes);
        ivTV.setImageResource(R.drawable.tv);
        ivToy.setImageResource(R.drawable.toy);
        ivPark.setImageResource(R.drawable.park);
    }

    public void IconSelect(View view) {
        int id = view.getId();
        Log.d("AwardStore onResume", "IconSelect:");
        switch (id)
        {
            case R.id.ivCar:
                CleanSelect();
                selection=1;
                ivCar.setImageResource(R.drawable.i8_sel);
                break;
            case R.id.ivShield:
                CleanSelect();
                selection=2;
                ivShield.setImageResource(R.drawable.changes_sel);
                break;
            case R.id.ivTV:
                CleanSelect();
                selection=3;
                ivTV.setImageResource(R.drawable.tv_sel);
                break;
            case R.id.ivToy:
                CleanSelect();
                selection=4;
                ivToy.setImageResource(R.drawable.toy_sel);
                break;
            case R.id.ivPark:
                CleanSelect();
                selection=5;
                ivPark.setImageResource(R.drawable.park_sel);
                break;
        }
    }

    public void BuyTicket(View view) {

        String str;
        switch(selection)
        {
            case 1:
                if(stars >=PRICE_CAR) {
                    Cars++;
                    stars-=PRICE_CAR;
                    Log.d("AwardStore", "stars:"+stars+"  Car:"+Cars);
                    str = tvCarTicket.getText().toString().substring(0, 3);
                    tvCarTicket.setText(str + Integer.toString(Cars));
                    tvCarTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                    str = tvStars.getText().toString().substring(0,2);
                    tvStars.setText(str+Integer.toString(stars));
                    tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                    //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                    editor.putInt("CarNum", Cars);
                    editor.putInt("stars",stars);
                }
                else
                {
                    mediaPlayer.start();
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setIcon(R.drawable.star)
                            .setTitle("WARNING !!")
                            .setMessage("還沒足夠的的星星獎勵喔～　趕快加油吧")
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
                break;

            case 2:
                if(stars >=PRICE_SHIELD) {
                    Shield++;
                    stars-=PRICE_SHIELD;
                    str = tvShieldTicket.getText().toString().substring(0, 3);
                    tvShieldTicket.setText(str + Integer.toString(Shield));
                    tvShieldTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                    str = tvStars.getText().toString().substring(0,2);
                    tvStars.setText(str+Integer.toString(stars));
                    tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                    editor.putInt("stars", stars);
                    //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                    editor.putInt("ShieldNum", Shield);

                }
                else
                {
                    mediaPlayer.start();
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setIcon(R.drawable.star)
                            .setTitle("WARNING !!")
                            .setMessage("還沒足夠的的星星獎勵喔～　趕快加油吧")
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
                break;
            case 3:
                if(stars >=PRICE_TV) {
                    TV++;
                    stars-=PRICE_TV;
                    str = tvTVTicket.getText().toString().substring(0, 3);
                    tvTVTicket.setText(str + Integer.toString(TV));
                    tvTVTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                    str = tvStars.getText().toString().substring(0,2);
                    tvStars.setText(str+Integer.toString(stars));
                    tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                    editor.putInt("stars", stars);
                    //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                    editor.putInt("TVNum", TV);
                }
                else
                {
                    mediaPlayer.start();
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setIcon(R.drawable.star)
                            .setTitle("WARNING !!")
                            .setMessage("還沒足夠的的星星獎勵喔～　趕快加油吧")
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
                break;
            case 4:
                if(stars >=PRICE_TOY) {
                    Toy++;
                    stars-= PRICE_TOY;
                    str = tvToyTicket.getText().toString().substring(0, 3);
                    tvToyTicket.setText(str + Integer.toString(Toy));
                    tvToyTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                    str = tvStars.getText().toString().substring(0,2);
                    tvStars.setText(str+Integer.toString(stars));
                    tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                    editor.putInt("stars", stars);
                    //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                    editor.putInt("ToyNum", Toy);
                }
                else
                {
                    mediaPlayer.start();
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setIcon(R.drawable.star)
                            .setTitle("WARNING !!")
                            .setMessage("還沒足夠的的星星獎勵喔～　趕快加油吧")
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
                break;
            case 5:
                if(stars >=PRICE_PARK) {
                    Park++;
                    stars-=PRICE_PARK;
                    str = tvParkTicket.getText().toString().substring(0, 3);
                    tvParkTicket.setText(str + Integer.toString(Park));
                    tvParkTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                    str = tvStars.getText().toString().substring(0,2);
                    tvStars.setText(str+Integer.toString(stars));
                    tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                    editor.putInt("stars", stars);
                    //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                    editor.putInt("ParkNum", Cars);
                }
                else
                {
                    mediaPlayer.start();
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setIcon(R.drawable.star)
                            .setTitle("WARNING !!")
                            .setMessage("還沒足夠的的星星獎勵喔～　趕快加油吧")
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                }
                break;
            default:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setIcon(R.drawable.star)
                        .setTitle("WARNING !!")
                        .setMessage("還沒選好購買的禮券喔～")
                        .setPositiveButton("確定", null)
                        .create();
                dialog.show();
                break;
        }
        editor.commit();
    }

    public void CancelTicket(View view) {
        String str;
        switch(selection)
        {
            case 1:
                    if(Cars>0) {
                        Cars--;
                        stars += PRICE_CAR;
                        Log.d("AwardStore", "stars:" + stars + "  Car:" + Cars);
                        str = tvCarTicket.getText().toString().substring(0, 3);
                        tvCarTicket.setText(str + Integer.toString(Cars));
                        tvCarTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                        str = tvStars.getText().toString().substring(0, 2);
                        tvStars.setText(str + Integer.toString(stars));
                        tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                        //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                        editor.putInt("CarNum", Cars);
                        editor.putInt("stars", stars);
                    }else
                    {
                        AlertDialog dialog = new AlertDialog.Builder(this)
                                .setIcon(R.drawable.star)
                                .setTitle("WARNING !!")
                                .setMessage("禮券已經用完了喔～")
                                .setPositiveButton("確定", null)
                                .create();
                        dialog.show();
                    }
                break;

            case 2:
                    if(Shield>0) {
                        Shield--;
                        stars += PRICE_SHIELD;
                        str = tvShieldTicket.getText().toString().substring(0, 3);
                        tvShieldTicket.setText(str + Integer.toString(Shield));
                        tvShieldTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                        str = tvStars.getText().toString().substring(0, 2);
                        tvStars.setText(str + Integer.toString(stars));
                        tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                        editor.putInt("stars", stars);
                        //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                        editor.putInt("ShieldNum", Shield);
                    }else
                    {
                        AlertDialog dialog = new AlertDialog.Builder(this)
                                .setIcon(R.drawable.star)
                                .setTitle("WARNING !!")
                                .setMessage("禮券已經用完了喔～")
                                .setPositiveButton("確定", null)
                                .create();
                        dialog.show();
                    }
                    break;
            case 3:
                    if(TV>0) {
                        TV--;
                        stars += PRICE_TV;
                        str = tvTVTicket.getText().toString().substring(0, 3);
                        tvTVTicket.setText(str + Integer.toString(TV));
                        tvTVTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                        str = tvStars.getText().toString().substring(0, 2);
                        tvStars.setText(str + Integer.toString(stars));
                        tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                        editor.putInt("stars", stars);
                        //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                        editor.putInt("TVNum", TV);
                    }else{
                        AlertDialog dialog = new AlertDialog.Builder(this)
                                .setIcon(R.drawable.star)
                                .setTitle("WARNING !!")
                                .setMessage("禮券已經用完了喔～")
                                .setPositiveButton("確定", null)
                                .create();
                        dialog.show();
                    }
                    break;
            case 4:
                    if(Toy>0) {
                        Toy--;
                        stars += PRICE_TOY;
                        str = tvToyTicket.getText().toString().substring(0, 3);
                        tvToyTicket.setText(str + Integer.toString(Toy));
                        tvToyTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                        str = tvStars.getText().toString().substring(0, 2);
                        tvStars.setText(str + Integer.toString(stars));
                        tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                        editor.putInt("stars", stars);
                        //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                        editor.putInt("ToyNum", Toy);
                    }else
                    {
                        AlertDialog dialog = new AlertDialog.Builder(this)
                                .setIcon(R.drawable.star)
                                .setTitle("WARNING !!")
                                .setMessage("禮券已經用完了喔～")
                                .setPositiveButton("確定", null)
                                .create();
                        dialog.show();
                    }
                break;
            case 5:
                if(Park>0) {
                    Park--;
                    stars += PRICE_PARK;
                    str = tvParkTicket.getText().toString().substring(0, 3);
                    tvParkTicket.setText(str + Integer.toString(Park));
                    tvParkTicket.setTextColor(getResources().getColor(R.color.colorAccent));

                    str = tvStars.getText().toString().substring(0, 2);
                    tvStars.setText(str + Integer.toString(stars));
                    tvStars.setTextColor(getResources().getColor(R.color.colorAccent));

                    editor.putInt("stars", stars);
                    //Log.d("AwardStore", "str:"+str+"  num:"+strNum);
                    editor.putInt("ParkNum", Cars);
                }else{
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setIcon(R.drawable.star)
                            .setTitle("WARNING !!")
                            .setMessage("禮券已經用完了喔～")
                            .setPositiveButton("確定", null)
                            .create();
                    dialog.show();
                    }
                break;
            default:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setIcon(R.drawable.star)
                        .setTitle("WARNING !!")
                        .setMessage("還沒選好退回的禮券喔～")
                        .setPositiveButton("確定", null)
                        .create();
                dialog.show();
                break;
        }
        editor.commit();

    }

    public void gottoBack(View view) {

            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer=null;//回收资源
        }
        super.onDestroy();
    }
}
