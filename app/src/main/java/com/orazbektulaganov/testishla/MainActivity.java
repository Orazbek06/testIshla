package com.orazbektulaganov.testishla;

import static java.lang.System.exit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button orgkompButton;
    Button webButton;
    Button algoButton;
    Button progButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orgkompButton = findViewById(R.id.orgkomButton);
        webButton = findViewById(R.id.webButton);
        algoButton = findViewById(R.id.algoButton);
        progButton = findViewById(R.id.progButton);

        orgkompButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://testcheckerapi.herokuapp.com/ok";
                Intent intent = new Intent(getApplicationContext(), TestSolvingActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("currentId",0);
                startActivity(intent);
            }
        });
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://testcheckerapi.herokuapp.com/web";
                Intent intent = new Intent(getApplicationContext(), TestSolvingActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("currentId",0);
                startActivity(intent);
            }
        });

        algoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://testcheckerapi.herokuapp.com/algo";
                Intent intent = new Intent(getApplicationContext(), TestSolvingActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("currentId",0);
                startActivity(intent);
            }
        });

        progButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://testcheckerapi.herokuapp.com/proginjinir";
                Intent intent = new Intent(getApplicationContext(), TestSolvingActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("currentId",0);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}