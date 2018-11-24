package edu.fje.clot.sudoku;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PartidaActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, android.widget.PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SudokuDbHelper sudoDButil = SudokuDbHelper.getInstance(this); // Better han getBaseContext()
        SQLiteDatabase db = sudoDButil.getWritableDatabase();

        String sudo = sudoDButil.obteSudokus();

        String[] numsSudokuBase = sudo.split(",");
        ArrayList<String> numsSudokuJoc = new ArrayList<String>();
        Random rand = new Random();
        int numerosAEliminar = 5, randIndex;

        for (int i = 0; i < numsSudokuBase.length; i++) {
            numsSudokuJoc.add(numsSudokuBase[i]);
        }

        while (numerosAEliminar != 0) {
            randIndex = rand.nextInt(81);
            if (numerosAEliminar != 0) {
                if (numsSudokuJoc.get(randIndex) != " ") {
                    numsSudokuJoc.set(randIndex, " ");
                    numerosAEliminar--;
                }
            }

        }

        for (int i = 0; i < numsSudokuJoc.size(); i++) {

        }

        GridView gridView = findViewById(R.id.gridView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numsSudokuJoc);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /*Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();*/
                if (adapter.getItem(position).equals(" ")) {
                    showPopup(parent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortir:
                finishAffinity();
                return true;
            case R.id.ajuda:
                Intent intentWeb = new Intent(PartidaActivity.this, WebAjudaActivity.class);
                startActivity(intentWeb);
                return true;
            case R.id.menu:
                Intent intentMenu = new Intent(PartidaActivity.this, MenuPrincipalActivity.class);
                startActivity(intentMenu);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPopup(View v) {
        android.widget.PopupMenu popup = new android.widget.PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.num1:
                Toast.makeText(this, "Número 1 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num2:
                Toast.makeText(this, "Número 2 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num3:
                Toast.makeText(this, "Número 3 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num4:
                Toast.makeText(this, "Número 4 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num5:
                Toast.makeText(this, "Número 5 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num6:
                Toast.makeText(this, "Número 6 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num7:
                Toast.makeText(this, "Número 7 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num8:
                Toast.makeText(this, "Número 8 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.num9:
                Toast.makeText(this, "Número 9 seleccionat", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

}
