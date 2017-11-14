package com.marcosdefreitas.maakereview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Remove title bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_login);

        btnEntrar = (Button)findViewById(R.id.btn_entrar_login);
        btnEntrar.setOnClickListener(login.this);

    }

    @Override
    public void onClick(View v) {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Mudando..", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(login.this, relativeLayout.class));
            }
        });
    }

}
