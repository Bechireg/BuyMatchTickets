package com.example.buymatchtickets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class Inscription extends AppCompatActivity {
    EditText Nom, Prenom, Email, Mdp, Tel;
    Button Btn;
    TextView Cnx;
    FirebaseAuth Auth;
    ProgressBar ProgressBar;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

            Nom = findViewById(R.id.nom);
            Prenom = findViewById(R.id.prenom);
            Email = findViewById(R.id.mail);
            Mdp = findViewById(R.id.password);
            Tel = findViewById(R.id.tel);
            Btn = findViewById(R.id.btn);
            Cnx = findViewById(R.id.cnx);
            Nom = findViewById(R.id.nom);
            ProgressBar = findViewById(R.id.progressBar);
            Auth = FirebaseAuth.getInstance();


            Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = Email.getText().toString().trim();
                    String mdp = Mdp.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        Email.setError("Email Required!!!");
                        return;
                    }
                    if (TextUtils.isEmpty(mdp)) {
                        Mdp.setError("Password Required!!!");
                        return;
                    }
                    if (mdp.length() < 8) {
                        Mdp.setError("Password Too Short Must Be More Than 8 Characters");
                        return;
                    }
                    ProgressBar.setVisibility(View.VISIBLE);

                    Auth.createUserWithEmailAndPassword(email, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        String userID;

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Inscription.this, "Utilisateur Crée", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(Inscription.this, "Erreur L'ors De La Création D'utilisateur!", Toast.LENGTH_SHORT).show();
                                ProgressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });

            Cnx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Connexion.class));
                }
            });
    }
}
