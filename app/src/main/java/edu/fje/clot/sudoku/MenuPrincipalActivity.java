package edu.fje.clot.sudoku;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Activitat del menú principal des d'on es pot començar el joc i es pot veure la llista d'altres
 * jugades, amb la seva puntuació i el nom del jugador.
 *
 * @author Pau Desumvila
 * @version 1.0 09/11/2018
 */

public class MenuPrincipalActivity extends AppCompatActivity {

    Button btJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Creació o accés a la base de dades.
        // Si la bd ja existeix, no farà res.
        SudokuDbHelper puntuacions = SudokuDbHelper.getInstance(this);
        SQLiteDatabase db = puntuacions.getWritableDatabase();

        // Cursor per agafar les dades de la bd (s'agafen 3 puntuacions, les més altes)
        Cursor cursorPuntuacions = db.rawQuery("select * from puntuacions ORDER BY punts DESC LIMIT 3", null);

        // Creació del ListView per mostrar les puntuacions
        ListView lvPuntuacions = findViewById(R.id.lvPuntuacions);
        // Setup del CursorAdapter per les puntuacions
        PuntuacionsCursorAdapter puntuacionsAdapter = new PuntuacionsCursorAdapter(this, cursorPuntuacions);
        // I a continuació es connecta l'adaptador amb el ListView
        lvPuntuacions.setAdapter(puntuacionsAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btJugar = (Button)findViewById(R.id.btJugar);
        btJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalActivity.this, PartidaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortir:
                finishAffinity();
                return true;
            case R.id.ajuda:
                Intent intent = new Intent(MenuPrincipalActivity.this, WebAjudaActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
