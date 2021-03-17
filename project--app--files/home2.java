package com.example.hp.dc_project;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class home2 extends AppCompatActivity{
    private ImageView btn1,btn2,btn3,btn4,btn5,btn6;
    private String elec,plum,weld,paint,drive,mecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        btn1 = (ImageView)findViewById(R.id.btn_elec);
        btn2 = (ImageView) findViewById(R.id.btn_plumber);
        btn3 = (ImageView) findViewById(R.id.btn_driver);
        btn4 = (ImageView) findViewById(R.id.btn_painter);
        btn5 = (ImageView) findViewById(R.id.btn_welder);
        btn6 = (ImageView) findViewById(R.id.btn_mechnist);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home2.this,available_worker.class);
                i.putExtra("Electrician","elec");
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home2.this,available_worker.class);
                i.putExtra("Plumber","plum");
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home2.this,available_worker.class);
                i.putExtra("Driver","drive");
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home2.this,available_worker.class);
                i.putExtra("Painter","paint");
                startActivity(i);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home2.this,available_worker.class);
                i.putExtra("Welder","weld");
                startActivity(i);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home2.this,available_worker.class);
                i.putExtra("Mechanist","mecha");
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_id: {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(home2.this, UserSignupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            }
            case R.id.id_about: {
                Intent intent = new Intent(home2.this, AboutUs.class);
                startActivity(intent);
            }
        }
        return true;
    }
}
