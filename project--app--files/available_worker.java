package com.example.hp.dc_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import com.bumptech.glide.Glide;
//import com.bumptech.glide.annotation.GlideModule;
//import com.bumptech.glide.module.AppGlideModule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class available_worker extends AppCompatActivity implements Serializable {
    private ListView lv;
    private String phone="empty";
    private String key,work,get_worker1,get_worker2,get_worker3,get_worker4,get_worker5,get_worker6;
    private FirebaseDatabase database;
    private DatabaseReference mref;
    private ImageView iv;
    private ArrayList<String> list,idWorker;
    private ArrayAdapter<String> adapter;
    private Electrician elec = new Electrician();
    private Plumber plumber = new Plumber();
    private Driver driver = new Driver();
    private Painter painter = new Painter();
    private TextView tv;
    private User u=new User();
    private Welder welder = new Welder();
    private Mechanist mechanist = new Mechanist();

    private List<workers> imgList;
    private MyAdapter myAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_worker);
        lv = (ListView) findViewById(R.id.listView_workeravailable);
        iv= (ImageView) findViewById(R.id.usericon);
        tv=(TextView) findViewById(R.id.tv_available);
       list = new ArrayList<>();
       imgList = new ArrayList<>();
       idWorker=new ArrayList<>();

      //MyAdapter = new ArrayAdapter<String>(this, R.layout.list_of_worker, R.id.employeename,list);
        myAdapter = new MyAdapter(this, R.layout.list_of_worker, imgList);

        //ListAdapter adapter = new SimpleAdapter(this, list, R.layout.list_of_worker, new int[]{R.id.usericon,R.id.employeename});

        database = FirebaseDatabase.getInstance();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(available_worker.this,WorkerProfile.class);
                i.putExtra("id",idWorker.get(position));
                i.putExtra("work",work);
                startActivity(i);
            }
        });


        Intent i = getIntent();

        get_worker1 = i.getStringExtra("Electrician");
        get_worker2 = i.getStringExtra("Plumber");
        get_worker3 = i.getStringExtra("Driver");
        get_worker4 = i.getStringExtra("Painter");
        get_worker5 = i.getStringExtra("Mechanist");
        get_worker6 = i.getStringExtra("Welder");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading..");
        progressDialog.show();
        if ("elec".equals(get_worker1))
            work="Electrician";
            cf = "Electrician.Class";
        else if ("weld".equals(get_worker6))
            work = "Welder";

        if ("elec".equals(get_worker1)) {
            work="Electrician";
            mref = database.getReference(work);
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        //make arrays empty here.
                        elec = ds.getValue(Electrician.class);
                        key = ds.getKey();
                        String url = elec.getFilePhoto();
                        if(elec.getEnable().equals("1")) {
                           //String eName="Name: " + elec.getFname().toString() + " " + elec.getLname().toString() + "\n" + "Address: " + elec.getAddress().toString());
                            workers img = new workers();
                            img.setTitle("Name: " + elec.getFname().toString() + " " + elec.getLname().toString() + "\n" + "Address: " + elec.getAddress().toString());
                            img.setImage(url);
                            imgList.add(img);
                            phone = elec.getPhone();
                            idWorker.add(key);
                        }
                    }
                    lv.setAdapter(myAdapter);
                    if(phone.equals("empty")){
                        tv.setText("\"No Workers Available!\"");

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else if ("weld".equals(get_worker6)) {
              work="Welder";
              mref = database.getReference(work);
              mref.addValueEventListener(new ValueEventListener() {
                @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    welder=ds.getValue(Welder.class);
                    key = ds.getKey();
                    if(welder.getEnable().equals("1")) {
                        list.add("Name: " + welder.getFname().toString() + " " + welder.getLname().toString() + "\n" + "Address: " + welder.getAddress().toString());
                        phone = welder.getPhone();
                        idWorker.add(key);
                    }
                }
                    lv.setAdapter(adapter);
                    if(phone.equals("empty")){
                         tv.setText("\"No Workers Available!\"");
                    }
            }

            @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

            }
             });
        }
        else if ("plum".equals(get_worker2)) {
            work="Plumber";
            mref = database.getReference(work);
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        plumber=ds.getValue(Plumber.class);
                        key = ds.getKey();
                        if(plumber.getEnable().equals("1")) {
                            list.add("Name: " + plumber.getFname().toString() + " " + plumber.getLname().toString() + "\n" + "Address: " + plumber.getAddress().toString());
                            phone = plumber.getPhone();
                            idWorker.add(key);

                        }
                    }
                    lv.setAdapter(adapter);
                    if(phone.equals("empty")){ tv.setText("\"No Workers Available!\"");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if ("paint".equals(get_worker4)) {
            work="Painter";
            mref = database.getReference("Painter");
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        painter=ds.getValue(Painter.class);
                        key = ds.getKey();
                        if(painter.getEnable().equals("1")) {
                            list.add("Name: " + painter.getFname().toString() + " " + painter.getLname().toString() + "\n" + "Address: " + painter.getAddress().toString());
                            phone = painter.getPhone();
                            idWorker.add(key);
                        }
                    }
                    lv.setAdapter(adapter);
                    if(phone.equals("empty")){ tv.setText("\"No Workers Available!\"");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if ("mecha".equals(get_worker5)) {
            work="Mechanist";
            mref = database.getReference("Mechanist");
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        mechanist=ds.getValue(Mechanist.class);
                        key = ds.getKey();
                        if(mechanist.getEnable().equals("1")) {
                            list.add("Name: " + mechanist.getFname().toString() + " " + mechanist.getLname().toString() + "\n" + "Addresss: " + mechanist.getAddress().toString());
                            phone = mechanist.getPhone();
                            idWorker.add(key);
                        }
                    }
                    lv.setAdapter(adapter);
                    if(phone.equals("empty")){ tv.setText("\"No Workers Available!\"");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if ("drive".equals(get_worker3)) {
            work="Driver";
            mref = database.getReference("Driver");
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        driver=ds.getValue(Driver.class);
                        key = ds.getKey();
                        if(driver.getEnable().equals("1")) {
                            list.add("Name: " + driver.getFname().toString() + " " + driver.getLname().toString() + "\n" + "Address: " + driver.getAddress().toString());
                            phone = driver.getPhone();
                            idWorker.add(key);
                        }
                    }
                    lv.setAdapter(adapter);
                    if(phone.equals("empty")){ tv.setText("\"No Workers Available!\"");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

}