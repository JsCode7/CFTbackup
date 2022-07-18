package com.jsdev.cftbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    int attempt = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attempt < 3) {
                    if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                        //correct
                        Toast.makeText(MainActivity.this, "LOGEADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        login();

                    } else
                        //incorrect
                        Toast.makeText(MainActivity.this, "LOGIN INCORRECTO, INTENTO NRO: " +attempt, Toast.LENGTH_SHORT).show();
                } else if (attempt == 3) {
                    Toast.makeText(MainActivity.this, "LIMITE DE LOGIN EXCEDIDO !!", Toast.LENGTH_SHORT).show();
                } else if (attempt > 3){
                    System.exit(0);
                }
                attempt++;
            }
        });

    }

    private void login() {
        Intent login = new Intent(this, mainmenu.class);
        startActivity(login);
    }

}