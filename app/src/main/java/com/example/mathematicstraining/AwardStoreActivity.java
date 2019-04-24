package com.example.mathematicstraining;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.TestCase;

public class AwardStoreActivity extends AppCompatActivity {

    SharedPreferences sharedata0;
    SharedPreferences.Editor editor;

    TextView tvStars, tvHalfStar,tvOpDate;
    int stars;
    Boolean starHalf;

    ImageView ivCar, ivShield, ivTV, ivToy, ivPark;
    TextView tvCarTicket, tvShieldTicket, tvTVTicket, tvToyTicket, tvParkTicket;
    private static final int PRICE_CAR = 50;
    private static final int PRICE_SHIELD = 200;
    private static final int PRICE_TV = 50;
    private static final int PRICE_TOY = 200;
    private static final int PRICE_PARK = 100;

    int selection=0;
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


        Log.d("AwardStore onCreate", "stars:"+sharedata0.getInt("stars",0));
        Log.d("AwardStore onCreate", "starHalf:"+sharedata0.getBoolean("starHalf",false));



    }

    @Override
    protected void onResume() {
        super.onResume();
         sharedata0 = getSharedPreferences("award", MODE_PRIVATE);

        Log.d("AwardStore onResume", "stars:"+sharedata0.getInt("stars",0));
        Log.d("AwardStore onResume", "starHalf:"+sharedata0.getBoolean("starHalf",false));
        Log.d("AwardStore onResume", "errorCount:"+sharedata0.getInt("errorCount",0));


        Log.d("AwardStore onResume", "date:"+sharedata0.getString("date", "0"));

        if(sharedata0.getBoolean("starHalf",false) == false)
            tvHalfStar.setText(" x 0");
        else
            tvHalfStar.setText(" x 1");

        tvStars.setText(" x "+sharedata0.getInt("stars",0));



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
        int len, num;
        String str;
        switch(selection)
        {
            case 1:
                len = tvCarTicket.getText().length();
                str = tvCarTicket.getText().toString().substring(0,4);
                

                break;
        }

    }

    public void CancelTicket(View view) {
    }
}
