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

public class RegistActivity extends AppCompatActivity {

    TextView login;
    EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    Button regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.masuk);
        regist = findViewById(R.id.button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);

        regist.setOnClickListener(view -> {
            registrasi();
        });

        login.setOnClickListener(view -> {
            startActivity( new Intent(RegistActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });
    }

    private void registrasi() {
        String user = inputEmail.getText().toString().trim();
        String pass = inputPassword.getText().toString().trim();
        if (user.isEmpty()) {
            Toast.makeText(RegistActivity.this, "Lengkapkan Forum Registrasi", Toast.LENGTH_LONG).show();
        }
        if (pass.isEmpty ()){
            Toast.makeText(RegistActivity.this, "Lengkapkan Forum Registrasi", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistActivity.this, "Registrasi berhasil", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                finish();
                            } else {
                                Toast.makeText(RegistActivity.this, "Lengkapkan Forum Registrasi", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
