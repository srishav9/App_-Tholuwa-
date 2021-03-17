package com.example.hp.dc_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;

public class WorkerProfile extends AppCompatActivity {
    ImageView iv;
    private FirebaseDatabase database;
    private DatabaseReference mref;
    TextView ed_fname,ed_adress,ed_phone,ed_pin;
    Button btn_call;
    private String phone;
    Electrician e = new Electrician();
    Mechanist m = new Mechanist();
    Painter pa = new Painter();
    Plumber pl = new Plumber();
    Driver d = new Driver();
    Welder w = new Welder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);
        iv = (ImageView) findViewById(R.id.profileIV);
        ed_fname = (TextView) findViewById(R.id.edit_fname);
        ed_adress = (TextView) findViewById(R.id.address);
        ed_phone = (TextView) findViewById(R.id.edit_phone);
        ed_pin = (TextView) findViewById(R.id.edit_pin);

        database = FirebaseDatabase.getInstance();
        final String id = getIntent().getStringExtra("id");

        final String work =getIntent().getStringExtra("work");
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", phone, null));
                startActivity(phoneIntent);
            }
        });
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.usericon);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        iv.setImageDrawable(roundedBitmapDrawable);

        mref = database.getReference(work);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    switch (work){
                        case "Driver":{
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (id.equals(ds.getKey())) {
                                    d = ds.getValue(Driver.class);
                                    phone=d.getPhone();
                                    ed_fname.setText(d.getFname().toString() + " " + d.getLname().toString());
                                    ed_adress.setText(d.getAddress().toString());
                                    ed_phone.setText(d.getPhone().toString());
                                    ed_pin.setText(d.getPin().toString());
                                }
                            }
                            break;
                        }
                        case "Electrician": {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if(id.equals(ds.getKey())){
                                    e = ds.getValue(Electrician.class);
                                    phone=e.getPhone();
                                    String url = e.getFilePhoto();
                                    ed_fname.setText(e.getFname().toString() + " " + e.getLname().toString());
                                    ed_adress.setText(e.getAddress().toString());
                                    ed_phone.setText(e.getPhone().toString());
                                    ed_pin.setText(e.getPin().toString());
                                    Picasso.with(WorkerProfile.this).load(url).resize(300, 300).into(iv);

                                }
                            }
                            break;
                        }
                        case "Mechanist":{
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (id.equals(ds.getKey())) {
                                    m = ds.getValue(Mechanist.class);
                                    phone = m.getPhone();
                                    ed_fname.setText(m.getFname().toString() + " " + m.getLname().toString());
                                    ed_adress.setText(m.getAddress().toString());
                                    ed_phone.setText(m.getPhone().toString());
                                    ed_pin.setText(m.getPin().toString());
                                }
                            }
                            break;
                        }
                        case "Painter":{
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (id.equals(ds.getKey())) {
                                    pa = ds.getValue(Painter.class);
                                    phone=pa.getPhone();
                                    ed_fname.setText(pa.getFname().toString() + " " + pa.getLname().toString());
                                    ed_adress.setText(pa.getAddress().toString());
                                    ed_phone.setText(pa.getPhone().toString());
                                    ed_pin.setText(pa.getPin().toString());
                                }
                            }
                            break;
                        }
                        case "Plumber":{
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (id.equals(ds.getKey())) {
                                    pl = ds.getValue(Plumber.class);
                                    phone=pl.getPhone();
                                    ed_fname.setText(pl.getFname().toString() + " " + pl.getLname().toString());
                                    ed_adress.setText(pl.getAddress().toString());
                                    ed_phone.setText(pl.getPhone().toString());
                                    ed_pin.setText(pl.getPin().toString());
                                }
                            }
                            break;
                        }
                        case "Welder":{
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (id.equals(ds.getKey())) {
                                    w = ds.getValue(Welder.class);
                                    phone=w.getPhone();
                                    ed_fname.setText(w.getFname().toString() + " " + w.getLname().toString());
                                    ed_adress.setText(w.getAddress().toString());
                                    ed_phone.setText(w.getPhone().toString());
                                    ed_pin.setText(w.getPin().toString());
                                }
                            }
                            break;
                        }
                        default:
                            break;
                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
