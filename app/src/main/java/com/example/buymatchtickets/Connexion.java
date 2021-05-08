package com.example.buymatchtickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

@SuppressWarnings("ALL")
public class Connexion extends AppCompatActivity {
    EditText Email, Password;
    Button Btn;
    TextView Inscrit;
    ProgressBar ProgressBar;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        Email = findViewById(R.id.mail);
        Password = findViewById(R.id.password);
        Btn = findViewById(R.id.btn);
        Inscrit = findViewById(R.id.inscrit);
        Auth = FirebaseAuth.getInstance();
        ProgressBar = findViewById(R.id.progressBar);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = Email.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    Email.setError("Email Required!!!");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Password.setError("Password Required!!!");
                    return;
                }
                if (pass.length() < 8) {
                    Password.setError("Password Too Short Must Be More Than 8 Characters");
                    return;
                }
                ProgressBar.setVisibility(View.VISIBLE);


                Auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    String userID;

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Connexion.this, "Connecté!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Connexion.this, "Email Ou Mot de pass Ne Correspond Pas!", Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });

        Inscrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Inscription.class));
            }
        });
    }
}