package com.example.mathematicstraining;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView ivAdd,ivSub,ivMul,ivDiv;
    private TextView  tvAdd, tvSub, tvMul, tvDiv, tvStars,tvHalfStar,tvErrors;
    TextView tvOpDate;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedata0 = getSharedPreferences("award", MODE_PRIVATE);
        //SharedPreferences.Editor sharedata = getSharedPreferences("award", 0).edit();

        ivAdd = findViewById(R.id.ivAdd);
        ivSub = findViewById(R.id.ivSub);
        ivMul = findViewById(R.id.ivMul);
        ivDiv = findViewById(R.id.ivDiv);
        tvAdd = findViewById(R.id.tvAdd);
        tvSub = findViewById(R.id.tvSub);
        tvMul = findViewById(R.id.tvMul);
        tvDiv = findViewById(R.id.tvDiv);
        tvStars = findViewById(R.id.tvStars);
        tvHalfStar = findViewById(R.id.tvHalfStar);
        tvErrors = findViewById(R.id.tvErrors);

        tvOpDate = findViewById(R.id.tvOpDate);

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "ADDITION !!!!", Toast.LENGTH_SHORT).show();

                Intent it = new Intent(MainActivity.this,Addition.class);
                startActivity(it);
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(MainActivity.this, "ADDITION !!!!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this,Addition.class);
                startActivity(it);
            }
        });
        ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "SUBTRACTION !!!!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this,Subtraction.class);
                startActivity(it);
            }
        });
        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "SUBTRACTION !!!!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this,Subtraction.class);
                startActivity(it);
            }
        });

        ivMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "MULTIPLICATION !!!!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this,Multiplication.class);
                startActivity(it);
            }
        });
        tvMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "MULTIPLICATION !!!!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this,Multiplication.class);
                startActivity(it);
            }
        });
        ivDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "DIVISION !!!!", Toast.LENGTH_SHORT).show();
            }
        });
        tvDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "DIVISION !!!!", Toast.LENGTH_SHORT).show();
            }
        });

        tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));
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

        if(sharedata0.getBoolean("starHalf",false) == false)
            tvHalfStar.setText(" x 0");
        else
            tvHalfStar.setText(" x 1");

        tvStars.setText(" x "+sharedata0.getInt("stars",0));

        tvErrors.setText(" x "+sharedata0.getInt("errorCount",0)+"/3");

        tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));

    }

    public void gotoStore(View view) {
        Intent it = new Intent(MainActivity.this,AwardStoreActivity.class);
        startActivity(it);
    }
}
