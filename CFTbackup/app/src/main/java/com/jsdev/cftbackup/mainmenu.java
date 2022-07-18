package com.jsdev.cftbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void siguiente(View view) {
        Intent siguiente = new Intent(this, alumno.class);
        startActivity(siguiente);

    }

    public void siguientep(View view) {
        Intent siguientep = new Intent(this, profesor.class);
        startActivity(siguientep);

    }

    public void siguientec(View view) {
        Intent siguientec = new Intent(this, curso.class);
        startActivity(siguientec);

    }
}