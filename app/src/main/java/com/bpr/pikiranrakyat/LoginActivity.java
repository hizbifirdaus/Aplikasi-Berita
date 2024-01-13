package com.bpr.pikiranrakyat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    TextView Regist;
    EditText inputEmail, inputPassword;
    Button Masuk;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Regist = findViewById(R.id.regist);
        Masuk =findViewById(R.id.buttonMasuk);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);

        Regist.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegistActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        });

        Masuk.setOnClickListener(view -> {
            cekLogin();
        });

    }

    private void cekLogin() {
        String user = inputEmail.getText().toString().trim();
        String pass = inputPassword.getText().toString().trim();
        if (user.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Masukkan Username dan Password yang benar", Toast.LENGTH_LONG).show();
        }
        if (pass.isEmpty ()){
            Toast.makeText(LoginActivity.this, "Masukkan Username dan Password yang benar", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, FeedActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Masukkan Username dan Password yang benar", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

}