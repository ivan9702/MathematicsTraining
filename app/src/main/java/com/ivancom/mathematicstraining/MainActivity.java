package com.ivancom.mathematicstraining;

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
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;



public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ID_READ_PERMISSION = 100;
    private static final int REQUEST_ID_WRITE_PERMISSION = 200;
    static final int HOMERUN_AWARE = 5;
    //Bitmap bitmap;
    //String path;

    private ImageView ivAdd,ivSub,ivMul,ivDiv,ivScreenimage;
    private TextView  tvAdd, tvSub, tvMul, tvDiv, tvStars,tvHalfStar,tvErrors;
    TextView tvOpDate;
    private String sharePath="no";
    Bitmap mbitmap;
    private MediaPlayer mediaPlayer;
    private static MediaPlayer mediaPlayer1;
    SharedPreferences sharedata0;
    SharedPreferences.Editor editor;

    Calendar mCal;
    //  TextView tvOpDate;
    CharSequence date_temp, s;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedata0 = getSharedPreferences("award", MODE_PRIVATE);
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

        //===========================================sound 1 =============================
        mediaPlayer=new MediaPlayer();

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
        String path = file.getPath()+"/recording.wav";
        Log.d("Main onCreate", "wav Path:"+path);
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
//=======================================sound 2 ===============================

        mediaPlayer1=new MediaPlayer();

        File file1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES);
        String path1 = file.getPath()+"/recording.wav";
        Log.d("Main onCreate", "wav1 Path:"+path1);
        try {
            mediaPlayer1.setDataSource(path1);
            mediaPlayer1.prepare();
            mediaPlayer1.setLooping(false);

        } catch (IOException e) {
            e.printStackTrace();
        }


        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "ADDITION !!!!", Toast.LENGTH_SHORT).show();

                if(!HomeRunCheck(sharedata0, false))
                {

                    Intent it = new Intent(MainActivity.this,Addition.class);
                    startActivity(it);
                }
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!HomeRunCheck(sharedata0, false)) {
                    //  Toast.makeText(MainActivity.this, "ADDITION !!!!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, Addition.class);
                    startActivity(it);
                }
            }
        });
        ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!HomeRunCheck(sharedata0,false)) {
                    //Toast.makeText(MainActivity.this, "SUBTRACTION !!!!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, Subtraction.class);
                    startActivity(it);
                }
            }
        });
        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!HomeRunCheck(sharedata0,false)) {
                    // Toast.makeText(MainActivity.this, "SUBTRACTION !!!!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, Subtraction.class);
                    startActivity(it);
                }
            }
        });

        ivMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!HomeRunCheck(sharedata0,false)) {
                    //Toast.makeText(MainActivity.this, "MULTIPLICATION !!!!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, Multiplication.class);
                    startActivity(it);
                }
            }
        });
        tvMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!HomeRunCheck(sharedata0,false)) {
                    //Toast.makeText(MainActivity.this, "MULTIPLICATION !!!!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, Multiplication.class);
                    startActivity(it);
                }
            }
        });
        ivDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Toast.makeText(MainActivity.this, "DIVISION !!!!", Toast.LENGTH_SHORT).show();
                //screenShot();
            }
        });
        tvDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                Toast.makeText(MainActivity.this, "DIVISION tvDiv", Toast.LENGTH_SHORT).show();

            }
        });

        tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        if( task.getResult() == null)
                            return;
                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        Log.i("MainActivity","token "+token);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedata0 = getSharedPreferences("award", MODE_PRIVATE);
        editor = sharedata0.edit();//获取Editor

        Log.d("Main onResume", "stars:"+sharedata0.getInt("stars",0));
        Log.d("Main onResume", "starHalf:"+sharedata0.getBoolean("starHalf",false));
        Log.d("Main onResume", "errorCount:"+sharedata0.getInt("errorCount",0));

        Log.d("Main onResume", "addBasic:"+sharedata0.getInt("addBasic",0));
        Log.d("Main onResume", "addMedium:"+sharedata0.getInt("addMedium",0));
        Log.d("Main onResume", "addHigh:"+sharedata0.getInt("addHigh",0));

        Log.d("Main onResume", "subtractionBasic:"+sharedata0.getInt("subtractionBasic",0));
        Log.d("Main onResume", "subtractionMedium:"+sharedata0.getInt("subtractionMedium",0));
        Log.d("Main onResume", "subtractionHigh:"+sharedata0.getInt("subtractionHigh",0));

        Log.d("Main onResume", "multiBasic:"+sharedata0.getInt("multiBasic",0));
        Log.d("Main onResume", "multiMedium:"+sharedata0.getInt("multiMedium",0));
        Log.d("Main onResume", "multiHigh:"+sharedata0.getInt("multiHigh",0));

        Log.d("Main onResume", "date:"+sharedata0.getString("date", "0"));

        Log.d("Main onResume", "errImgNum:"+sharedata0.getInt("errImgNum",0));
        Log.d("Main onResume", "homeRunFib2:"+sharedata0.getInt("homeRunFib2",1));
        Log.d("Main onResume", "homeRunContinue:"+  sharedata0.getInt("homeRunContinue", 0));
        Log.d("Main onResume", "Last homeRun Date:"+ sharedata0.getString("homeRunDate", "0"));


        mCal = Calendar.getInstance();
        s = DateFormat.format("yyyy/MM/dd ", mCal.getTime());    // kk:24小時制, hh:12小時制
        date_temp =  DateFormat.format("yyyyMMdd ", mCal.getTime());

        String date = sharedata0.getString("date", "0");


        if(date.compareTo(date_temp.toString()) !=0 )
        {
            int multiBasic =0;
            int multiMedium = 0;
            int multiHigh = 0;

            int subtractionBasic =0;
            int subtractionMedium = 0;
            int subtractionHigh=0;

            int addBasic =0;

            int addMedium = 0;
            int addHigh = 0;

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
            Log.d("Main onCreate", "CLEAR!! date:"+sharedata0.getString("date", "0"));
        }



        if(sharedata0.getBoolean("starHalf",false) == false)
            tvHalfStar.setText(" x 0");
        else
            tvHalfStar.setText(" x 1");

        tvStars.setText(" x "+sharedata0.getInt("stars",0));

        tvErrors.setText(" x "+sharedata0.getInt("errorCount",0)+"/3");

        String str = "上一次測試日期為：　"+sharedata0.getString("date", "0");

        String lastHomeRundate = sharedata0.getString("homeRunDate", "0");

        CharSequence date_current;

        mCal = Calendar.getInstance();

        mCal.add(Calendar.DATE, -1);


        date_current =  DateFormat.format("yyyyMMdd ", mCal.getTime());
        String temp = date_current.subSequence(0, 8).toString();
        Log.d("Main onResume", "Last homeRun Date:"+ lastHomeRundate);
        Log.d("Main onResume", "during current Date:"+ date_current);
        Log.d("Main onResume", "date_current.toString().compareTo(lastHomeRundate):"+ date_current.toString().compareTo(lastHomeRundate));
        Log.d("Main onResume", "lastHomeRundate.compareTo(temp):"+ lastHomeRundate.compareTo(temp));
        Log.d("Main onResume", "temp.compareTo(20190509):"+ temp.compareTo("20190509"));

        Log.d("Main onResume", "lastHomeRundate len"+ lastHomeRundate.length());
        Log.d("Main onResume", "date_current len"+ date_current.toString().length());
       if(temp.compareTo(lastHomeRundate) > 0)
        {

            Log.d("Main onResume", ">>>>>>>> 0  over time for HR Continue");
            editor.putInt("homeRunContinue", 0);
            editor.commit();
        }
        else
        {
            Log.d("Main onResume", "<<<<<<<< ======= 0");
            Log.d("Main onResume", "Keep Counting for HR Continue");
        }
//
//        Log.d("Main onResume", "Last homeRun Date:"+ Integer.valueOf(lastHomeRundate.toString()));
//        Log.d("Main onResume", "current Date:"+ Integer.valueOf(date_current.toString()));
//        if(Integer.valueOf(date_current.toString()) - Integer.valueOf(lastHomeRundate.toString())>2)
//        {
//            Log.d("Main onResume", "Reset HR Continue...");
//            editor.putInt("homeRunContinue", 0);
//            editor.commit();
//
//        }


        if(sharedata0.getInt("homeRunContinue",0) <=1) {
            str += "\n目前練續HomeRun天數為" + sharedata0.getInt("homeRunContinue",0);
           if( sharedata0.getInt("homeRunContinue",0)==0)
                str += "     下次連續獎勵為 " + 0+ ",顆星";
           else if(sharedata0.getInt("homeRunContinue",0)==1)
               str += "     下次連續獎勵為 " + 2+ ",顆星";


        }else{
            str += "\n目前練續HomeRun天數為" + sharedata0.getInt("homeRunContinue", 0);
            str += "     下一次連續獎勵為" + fibonacci(sharedata0.getInt("homeRunContinue", 0) + 2) + "顆星";
        }
       // tvOpDate.setText("上一次測試日期為：　"+sharedata0.getString("date", "0"));
        tvOpDate.setText(str);

        /////////////////////


    }

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
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

    public static Boolean HomeRunCheck(SharedPreferences sharedata, Boolean HRContinue)
    {
        SharedPreferences.Editor editor;
        editor = sharedata.edit();//获取Editor

        Log.d("Main onResume", "stars:"+sharedata.getInt("stars", 0));
        Log.d("Main onResume", "addBasic:"+sharedata.getInt("addBasic",0));
        Log.d("Main onResume", "addMedium:"+sharedata.getInt("addMedium",0));
        Log.d("Main onResume", "addHigh:"+sharedata.getInt("addHigh",0));

        Log.d("Main onResume", "subtractionBasic:"+sharedata.getInt("subtractionBasic",0));
        Log.d("Main onResume", "subtractionMedium:"+sharedata.getInt("subtractionMedium",0));
        Log.d("Main onResume", "subtractionHigh:"+sharedata.getInt("subtractionHigh",0));

        Log.d("Main onResume", "multiBasic:"+sharedata.getInt("multiBasic",0));
        Log.d("Main onResume", "multiMedium:"+sharedata.getInt("multiMedium",0));
        Log.d("Main onResume", "multiHigh:"+sharedata.getInt("multiHigh",0));

        Log.d("Main onResume", "last homeRunDate:"+sharedata.getString("homeRunDate","0"));
       // Log.d("Main onResume", "homeRunFib1:"+sharedata.getInt("homeRunFib1",1));
        Log.d("Main onResume", "homeRunFib2:"+sharedata.getInt("homeRunFib2",1));
        Log.d("Main onResume", "homeRunContinue:"+  sharedata.getInt("homeRunContinue", 0));
        if((sharedata.getInt("addBasic",0)==2) &&  (sharedata.getInt("addMedium",0)==2) &&  (sharedata.getInt("addHigh",0)==2) &&  (sharedata.getInt("subtractionBasic",0)==2) &&  (sharedata.getInt("subtractionMedium",0)==2) &&  (sharedata.getInt("subtractionHigh",0)==2) &&  (sharedata.getInt("multiBasic",0)==2) &&  (sharedata.getInt("multiMedium",0)==2) &&  (sharedata.getInt("multiHigh",0)==2))
        {
            mediaPlayer1.start();

            if(HRContinue) {
                HomeRunCount(sharedata);
            }
            CharSequence date;
            editor = sharedata.edit();//获取Editor
            Calendar mCal;
            mCal = Calendar.getInstance();
            //for test
           // mCal.add(Calendar.DATE, -1);
            date =  DateFormat.format("yyyyMMdd ", mCal.getTime());
            Log.d("Main onResume", "current HomeRun date:"+date.toString());
            editor.putString("homeRunDate",date.toString() );
            editor.commit();
            return true;
        }
        return  false;
    }

    public  static int HomeRunCount(SharedPreferences sharedata)
    {
        int homeRunContinue;
        int  awardStars=0;
        SharedPreferences.Editor editor;
       editor = sharedata.edit();//获取Editor
        Calendar mCal;
        //  TextView tvOpDate;
        CharSequence date_current;
        editor = sharedata.edit();//获取Editor

        mCal = Calendar.getInstance();

        mCal.add(Calendar.DATE, -1);

        date_current =  DateFormat.format("yyyyMMdd ", mCal.getTime());
        String temp = date_current.subSequence(0, 8).toString();

        String date = sharedata.getString("homeRunDate", "0");

        // test
        Log.d("Main onResume", "HomeRun date:"+date_current.toString());
        Log.d("Main onResume", "Last HomeRun date:"+ date);
        Log.d("Main onResume", "stars:"+ sharedata.getInt("stars", 0));

        if(temp.compareTo(date) <= 0) {
       // if(date.compareTo(date_current.toString()) ==0 ){
         //  int Fib1 =  sharedata.getInt("homeRunFib1", 1);
           //int Fib2 = sharedata.getInt("homeRunFib2", 1);
            int Fib2;
            Log.d("Main onResume", "------homeRunContinue:"+ sharedata.getInt("homeRunContinue", 0));
           homeRunContinue =  sharedata.getInt("homeRunContinue", 0);
           homeRunContinue++;
            Log.d("Main onResume", "++++++homeRunContinue:"+ homeRunContinue);
           //Fib1 = fibonacci(homeRunContinue);
           Fib2 = fibonacci(homeRunContinue+1);
           //awardStars = Fib1 + Fib2;
            awardStars=Fib2;
           // Log.d("Main onResume", "awardStars:"+ awardStars+"  Fib1:"+sharedata.getInt("homeRunFib1", 1)+"   Fib2:"+sharedata.getInt("homeRunFib2", 2) + " homeRunContinue: "+homeRunContinue);
            Log.d("Main onResume", "awardStars:"+ awardStars+"   Fib2:"+sharedata.getInt("homeRunFib2", 2) + " homeRunContinue: "+homeRunContinue);

            editor.putInt("stars", +sharedata.getInt("stars", 0)+awardStars);

            //editor.putInt("homeRunFib1", Fib1);
            editor.putInt("homeRunFib2", Fib2);
            editor.putInt("homeRunContinue", homeRunContinue);

        }
        else
        {
         //   editor.putInt("homeRunFib1", 1);
            editor.putInt("homeRunFib2", 1);
            editor.putInt("homeRunContinue", 1);

        }
        editor.commit();
        //Log.d("Main onResume", "homeRunFib1:"+  sharedata.getInt("homeRunFib1", 1));
        Log.d("Main onResume", "homeRunFib2:"+sharedata.getInt("homeRunFib2", 2));
        Log.d("Main onResume", "awardStars:"+ awardStars);
        Log.d("Main onResume", "stars:"+ sharedata.getInt("stars", 0));
        Log.d("Main onResume", "homeRunContinue:"+ sharedata.getInt("homeRunContinue", 0));
        return  awardStars;
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer=null;//回收资源
        }

        if(mediaPlayer1!=null&&mediaPlayer1.isPlaying()){
            mediaPlayer1.stop();
            mediaPlayer1.reset();
            mediaPlayer1=null;//回收资源
        }

        super.onDestroy();
    }

}
