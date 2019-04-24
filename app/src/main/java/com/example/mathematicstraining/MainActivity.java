package com.example.mathematicstraining;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;



public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;

    //Bitmap bitmap;
    //String path;

    private ImageView ivAdd,ivSub,ivMul,ivDiv,ivScreenimage;
    private TextView  tvAdd, tvSub, tvMul, tvDiv, tvStars,tvHalfStar,tvErrors;
    TextView tvOpDate;
    private String sharePath="no";
    Bitmap mbitmap;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedata0 = getSharedPreferences("award", MODE_PRIVATE);
        //SharedPreferences.Editor sharedata = getSharedPreferences("award", 0).edit();

        ivScreenimage = findViewById(R.id.ivScreenImage);
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

        Log.d("Main onCreate", "stars:"+sharedata0.getInt("stars",0));
        askPermissionAndWriteFile();
        //askPermissionAndReadFile();
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
                //screenShot();
            }
        });
        tvDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "DIVISION tvDiv", Toast.LENGTH_SHORT).show();

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

        Log.d("Main onResume", "errImgNum:"+sharedata0.getInt("errImgNum",0));

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

    private void askPermissionAndWriteFile() {
        boolean canWrite = this.askPermission(REQUEST_ID_WRITE_PERMISSION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //
        if (canWrite) {
           // Toast.makeText(getApplicationContext(), "PERMISSION_GRANTED0000", Toast.LENGTH_SHORT).show();
           // FileUtil.getInstance().storeBitmap(bitmap, path);
        }
    }

    private void askPermissionAndReadFile() {
        boolean canRead = this.askPermission(REQUEST_ID_READ_PERMISSION,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        //
        if (canRead) {
            //this.readFile();
        }
    }

    // With Android Level >= 23, you have to ask the user
    // for permission with device (For example read/write data on the device).
    private boolean askPermission(int requestId, String permissionName) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Check if we have permission
            int permission = ActivityCompat.checkSelfPermission(this, permissionName);


            if (permission != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{permissionName},
                        requestId
                );
                return false;
            }
        }
        return true;
    }


    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        // Note: If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0) {
            switch (requestCode) {
                case REQUEST_ID_READ_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //readFile();
                    }
                }
                case REQUEST_ID_WRITE_PERMISSION: {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                       // Toast.makeText(getApplicationContext(), "PERMISSION_GRANTED11111", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Cancelled!", Toast.LENGTH_SHORT).show();
        }
    }

    public static  void screenShot(Activity activity)
    {

        String path;
        Bitmap bitmap;

        SimpleDateFormat s = new SimpleDateFormat("MMddhhmmss");
        String format = s.format(new Date());

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        path = file.getPath();
        Log.d(">>>", "Pictures getPath path: " + path);

        File dir = new File(path+ "/MyFolder");

        if(dir.exists() && dir.isDirectory()) {
            Log.d(">>>", "MyFolder path0: " + dir.getPath());
            path =  dir.getPath();
        }
        else{
            dir.mkdirs();
            Log.d(">>>", "MyFolder path1: " + dir.getPath());
            path =  dir.getPath();
        }

        bitmap =ScreenshotUtil.getInstance().takeScreenshotForScreen(activity); // Take ScreenshotUtil
        path += "/"+format+"test.png";
        //Toast.makeText(MainActivity.this, path, Toast.LENGTH_SHORT).show();
        Log.d(">>>", "Pictures Folder path: " + path);

        FileUtil.getInstance().storeBitmap(bitmap, path);
    }

    public void settingScore(View view) {
// get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.text_inpu_password, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                        Log.d("Main Dialog", "Password: "+editText.getText());
                        if(editText.getText().toString().equals("asdfghjk"))
                        {
                            Log.d("Main Dialog", "GOTO SETTING... ");




                            Intent it = new Intent(MainActivity.this,SettingActivity.class);
                            startActivity(it);

                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
}
