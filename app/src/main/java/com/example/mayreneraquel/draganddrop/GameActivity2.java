package com.example.mayreneraquel.draganddrop;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import java.util.HashSet;

public class GameActivity2 extends AppCompatActivity {
    ImageView img1, img2, img3;
    TextView text1;
    Button btnreview;
    int respuestaCorrecta;
    int respuestaUsuario;
    int todasUsadas;
    public HashSet<Integer> randomUsadas;

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
        todasUsadas = 0;
        randomUsadas = new HashSet<Integer>();
        respuestaCorrecta = -1;
        respuestaUsuario = -1;

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
        respuestaUsuario = (int) img3.getTag();
        if (img3.getTag() != img1.getTag() && img3.getTag() != img2.getTag()) {
            Toast.makeText(getApplicationContext(), "Please select an image!", Toast.LENGTH_SHORT).show();
        }
        else {
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
    }

    public void preparar() {
        Random random = new Random();

        if (todasUsadas < imglist.length-1) {
            int aux = random.nextInt(imglist.length);
            int aux2 = random.nextInt(2);

            if (randomUsadas.contains(aux)) { //Para que las imagenes no se repitan
                while (randomUsadas.contains(aux)) {
                    aux = random.nextInt(imglist.length);
                }
            }
            randomUsadas.add(aux);
            todasUsadas++;

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
            String frase = "\n" + fraseslist[aux];
            text1.setText(frase);
        }
        else {
            img1.setImageResource(R.color.verde);
            img2.setImageResource(R.color.verde);
            img3.setImageResource(R.color.fucsia);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("All right, you guessed all the phrases!")
                    .setTitle("CONGRATULATIONS!")
                    .setCancelable(false);
            builder.setPositiveButton("Try Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            todasUsadas = 0;
                            randomUsadas.clear();
                            respuestaCorrecta = -1;
                            respuestaUsuario = -1;
                            preparar();
                        }
                    });
            builder.setNegativeButton("Return to main menu",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            builder.show();
        }
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
