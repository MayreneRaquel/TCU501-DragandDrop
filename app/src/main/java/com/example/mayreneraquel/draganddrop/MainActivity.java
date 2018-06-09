package com.example.mayreneraquel.draganddrop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity1();
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity2();
            }
        });
    }

    public void openGameActivity1() {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void openGameActivity2() {
        Intent i = new Intent(this, GameActivity2.class);
        startActivity(i);
    }

}
