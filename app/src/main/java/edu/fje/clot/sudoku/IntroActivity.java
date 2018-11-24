package edu.fje.clot.sudoku;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Activitat principal des d'on comença l'aplicació.
 *
 * @author Pau Desumvila
 * @version 1.0 09/11/2018
 *
 */

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
