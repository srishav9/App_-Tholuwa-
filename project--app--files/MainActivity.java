package com.example.hp.dc_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    static int SPLASH_TIME_OUT = 4000;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(!isConnected(this)){ Toast.makeText(this, "NO INTERNET CONNECTION!!", Toast.LENGTH_LONG).show();
                finish();
        }
        else{
            setContentView(R.layout.activity_main);
        }
        super.onCreate(savedInstanceState);
        tv1 =(TextView)findViewById(R.id.tv_splash2);

    }

    protected  void onStart()
    {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            //splash activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,home2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }, SPLASH_TIME_OUT);

        }
        else{
            //splash activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, UserSignupActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

    public Boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if(netinfo != null && netinfo.isConnectedOrConnecting())
        {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi !=null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        }
        return  false;
    }

    /*public AlertDialog.Builder buildDialog(Context c)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to be connected to the internet. Press OK to exit");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return builder;
    }*/
}