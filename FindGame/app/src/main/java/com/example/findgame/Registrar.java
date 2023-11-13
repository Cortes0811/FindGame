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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Registrar extends AppCompatActivity {

    String Usuario="", Email="", Password="";

    FirebaseAuth mAuth;
    DatabaseReference mDataBase;
    EditText txteUsuario, txtemail, txtpPassword;
    Button btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        txteUsuario = findViewById(R.id.txteUsuario);
        txtemail = findViewById(R.id.txtemail);
        txtpPassword = findViewById(R.id.txtpPasword);
        btnRegistrar = findViewById(R.id.btnCrear);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario = txteUsuario.getText().toString();
                Email = txtemail.getText().toString();
                Password = txtpPassword.getText().toString();

                if (!Usuario.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {

                  if (Password.length() >= 6) {
                      registrarUsuario();


                  }
                  else {
                      Toast.makeText(Registrar.this,"El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                  }

                }
                else {
                    Toast.makeText(Registrar.this,"Debe completar los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void registrarUsuario() {
        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", Usuario);
                    map.put("email", Email);
                    map.put("password", Password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(Registrar.this, Generos.class));
                                finish();
                                Toast.makeText(Registrar.this, "Se registro correctamente el usuario ", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(Registrar.this, "No se pudieron crear los datos correctamente",Toast.LENGTH_SHORT);
                            }

                        }
                    });

                }
                else {
                    Toast.makeText(Registrar.this, "No se pudo Registrar este Usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void regresar(View view) {
        Intent miIntent = new Intent(Registrar.this,MainActivity.class);
        startActivity(miIntent);
    }
}