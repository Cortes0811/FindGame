package com.example.findgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    Button btnIni;
    EditText txtiemail, txtpIPassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        mAuth = FirebaseAuth.getInstance();

        txtiemail = findViewById(R.id.txtiemail);
        txtpIPassword = findViewById(R.id.txtpIPassword);
        btnIni = findViewById(R.id.btnIni);

        btnIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = txtiemail.getText().toString().trim();
                String passUser = txtpIPassword.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty())
                {
                    Toast.makeText(InicioSesion.this, "Ingrear los datos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUser(emailUser, passUser);
                }
            }
        });
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    finish();
                    startActivity(new Intent(InicioSesion.this, Inicio.class));
                    Toast.makeText(InicioSesion.this, "Se inicio correctamente", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(InicioSesion.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InicioSesion.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void regresar(View view) {
        Intent miIntent = new Intent(InicioSesion.this,MainActivity.class);
        startActivity(miIntent);
    }
}