package com.example.hp.dc_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class UserSignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed_fname,ed_lname,ed_addr,ed_pin;
    private Button btn_usersgn;
    private ImageView iv_user;
    private TextView tv;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private ProgressDialog pd;
    private User user;
    private UUID id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        database = FirebaseDatabase.getInstance();
        iv_user = (ImageView) findViewById(R.id.img_user);
        databaseReference = database.getReference("User");
        ed_fname = (EditText) findViewById(R.id.fname);
        ed_lname = (EditText) findViewById(R.id.lname);
        ed_addr = (EditText) findViewById(R.id.addr);
        ed_pin = (EditText) findViewById(R.id.pin);
        tv = (TextView)findViewById(R.id.already);
        user = new User();
        pd = new ProgressDialog(this);
        btn_usersgn = (Button)findViewById(R.id.user_signup);
        btn_usersgn.setOnClickListener(this);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(UserSignupActivity.this,phoneotp.class);
                startActivity(i);
            }
        });
    }

    private boolean saveUserInfo() {

        if (TextUtils.isEmpty(ed_fname.getText().toString())) {
            Toast.makeText(UserSignupActivity.this, "Enter first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(ed_lname.getText().toString())) {
            Toast.makeText(UserSignupActivity.this, "Enter last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(ed_addr.getText().toString())) {
            Toast.makeText(UserSignupActivity.this, "Enter address", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(ed_pin.getText().toString())) {
            Toast.makeText(UserSignupActivity.this, "Enter pin", Toast.LENGTH_SHORT).show();
            return false;
        }
        pd.setMessage("Registering.Please wait...");
        user.setFname(ed_fname.getText().toString());
        user.setLname(ed_lname.getText().toString());
        user.setAddress(ed_addr.getText().toString());
        user.setPin(ed_pin.getText().toString());
        return true;
    }

    @Override
    public void onClick(View v) {
        if (saveUserInfo()) {
            id = id.randomUUID();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    databaseReference.child(String.valueOf(id)).setValue(user);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            finish();
            Intent intent = new Intent(UserSignupActivity.this, phoneotp.class);
            startActivity(intent);
        }
    }
}
