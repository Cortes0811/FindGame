package com.example.findgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void iniciosesion(View view) {
        Intent miIntent = new Intent(MainActivity.this,InicioSesion.class);
        startActivity(miIntent);
        }
    public void registrar(View view) {
        Intent miIntent = new Intent(MainActivity.this,Registrar.class);
        startActivity(miIntent);
    }

}