package com.example.mayreneraquel.draganddrop;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity2 extends AppCompatActivity {
    ImageView img1, img2, img3;
    TextView text1;
    Button btnreview;
    int respuestaCorrecta = -1;

    public int[] imglist = {
            R.drawable.tresr,
            R.drawable.planttree,
            R.drawable.avoidchemical,
            R.drawable.walk,
            R.drawable.savewater,
            R.drawable.saveenergy,
            R.drawable.cleanenergy,
            R.drawable.avoidbags,
            R.drawable.clasifygarbage,
            R.drawable.nothrowgarbage
    };

    public int[] noimglist = {
            R.drawable.tresr2,
            R.drawable.planttree2,
            R.drawable.avoidchemical2,
            R.drawable.walk2,
            R.drawable.savewater2,
            R.drawable.saveenergy2,
            R.drawable.cleanenergy2,
            R.drawable.avoidbags2,
            R.drawable.clasifygarbage2,
            R.drawable.nothrowgarbage2
    };

    public String[] fraseslist = {
            "Recycle, reuse, reduce",
            "Plant trees",
            "Avoid using chemicals",
            "Drive less, walk more",
            "Save water",
            "Save electric energy",
            "Use clean power",
            "Avoid buying plastic bags",
            "Clasify garbage",
            "Do not throw the garbage in the street"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        text1 = (TextView) findViewById(R.id.text);
        btnreview = (Button) findViewById(R.id.btnreview);

        img1.setOnLongClickListener(longClickListener);
        img2.setOnLongClickListener(longClickListener);
        img3.setOnDragListener(dragListener);

        preparar();

        btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisar();
            }
        });
    }

    public void revisar() {
        int respuestaUsuario = (int) img3.getTag();
        if (respuestaUsuario == respuestaCorrecta) {
            Toast.makeText(getApplicationContext(), "Good job! You got the answer right!", Toast.LENGTH_SHORT).show();
            preparar();
            img3.setImageResource(R.color.fucsia);
        }
        else {
            Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
            img3.setImageResource(R.color.fucsia);
        }
    }

    public void preparar() {
        //Imagenes al azar
        Random random = new Random();
        int aux = random.nextInt(imglist.length);
        int aux2 = random.nextInt(1);
        int imgSelec1 = imglist[aux];
        int imgSelec2 = noimglist[aux];
        if (aux2 == 0) {
            img1.setImageResource(imgSelec1);
            img1.setTag(imgSelec1);
            img2.setImageResource(imgSelec2);
            img2.setTag(imgSelec2);
            respuestaCorrecta = (int) img1.getTag();
        }
        else {
            img1.setImageResource(imgSelec2);
            img1.setTag(imgSelec2);
            img2.setImageResource(imgSelec1);
            img2.setTag(imgSelec1);
            respuestaCorrecta = (int) img2.getTag();
        }
        //frase
        String frase = "\n" + fraseslist[aux];
        text1.setText(frase);
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData d = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(d, shadowBuilder,v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();
            Drawable drawi = null;
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //img2.setImageResource(R.color.colorPrimaryDark);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.img1) {
                        drawi = img1.getDrawable();
                        //img1.setImageResource(android.R.color.transparent);
                        img3.setImageDrawable(drawi);
                        img3.setTag(img1.getTag());
                    }
                    if (view.getId() == R.id.img2) {
                        drawi = img2.getDrawable();
                        //img2.setImageResource(android.R.color.transparent);
                        img3.setImageDrawable(drawi);
                        img3.setTag(img2.getTag());
                    }
                    break;
            }
            return true;
        }
    };
}
