package com.example.mayreneraquel.draganddrop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            public void onClick(View v) {
                openGameActivity2();
            }
        });
    }

    /**
     * Metodo para crear el menu.
     * @param menu layout con el menu.
     * @return true si se crea correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    /**
     * Metodo para realizar una accion al seleccionar items del menu.
     * @param item una opcion del menu.
     * @return true si realiza la accion correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.menu_Ins:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
                builder.setTitle(getText(R.string.tituloIns));
                builder.setMessage(getText(R.string.instrucciones));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
                AlertDialog dialog = builder.show();
                // Must call show() prior to fetching text view
                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
        }
        return true;
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
