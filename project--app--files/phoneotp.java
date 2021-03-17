package com.example.hp.dc_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phoneotp extends AppCompatActivity {
    private EditText ed1,ed2;
    private ProgressBar pb;
    private String phno;
    private Button btn1,btn2;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    private String verificationcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneotp);

        ed1 = (EditText)findViewById(R.id.editPhone);
        ed2 = (EditText)findViewById(R.id.validatePhone);
        ed2.setEnabled(false);
        btn1 = (Button)findViewById(R.id.btnCont);
        btn2 = (Button)findViewById(R.id.signin);
        btn2.setEnabled(false);
        pb = (ProgressBar)findViewById(R.id.progressbar);
        pb.setVisibility(View.INVISIBLE);
        auth = FirebaseAuth.getInstance();
        // Intent i = getIntent();
        // phno = i.getStringExtra("number");
        mcallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                // super.onCodeSent(s, forceResendingToken);
                verificationcode = s;
                //  pb.setVisibility(View.VISIBLE);
                Toast.makeText(phoneotp.this, "Code sent to the number", Toast.LENGTH_SHORT).show();
            }
        };

    }
    public void send_sms(View v)
    {   String phn =ed1.getText().toString();
        if(TextUtils.isEmpty(phn))
        {
            Toast.makeText(this, "Enter OTP first", Toast.LENGTH_SHORT).show();

        }
        else {
            ed2.setEnabled(true);
            btn2.setEnabled(true);
            pb.setVisibility(View.VISIBLE);
            String number=ed1.getText().toString();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" +number ,60, TimeUnit.SECONDS,this,mcallback
            );
        }

    }

    public void signinwithphone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(phoneotp.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(phoneotp.this,home2.class);
                    startActivity(i);
                }
                else {
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(phoneotp.this, "Invalid code entered", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
    }

    public void verify(View v)
    {
        String phone = ed2.getText().toString();
        verifyPhoneNumber(verificationcode,phone);
    }

    public void verifyPhoneNumber(String verificationcode, String phone) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcode,phone);
        signinwithphone(credential);
    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        ed2.setEnabled(false);
        btn2.setEnabled(false);
    }
    */
}
