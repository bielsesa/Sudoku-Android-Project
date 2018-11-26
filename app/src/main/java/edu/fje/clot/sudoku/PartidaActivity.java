package edu.fje.clot.sudoku;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PartidaActivity extends AppCompatActivity{

    private ArrayList<String> numsSudokuJoc;
    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private Button btOne, btTwo, btThree, btFour, btFive, btSix, btSeven, btEight, btNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        btOne = findViewById(R.id.btOne);
        btTwo = findViewById(R.id.btTwo);
        btThree = findViewById(R.id.btThree);
        btFour = findViewById(R.id.btFour);
        btFive = findViewById(R.id.btFive);
        btSix = findViewById(R.id.btSix);
        btSeven = findViewById(R.id.btSeven);
        btEight = findViewById(R.id.btEight);
        btNine = findViewById(R.id.btNine);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // A partir d'aquí s'agafa el sudoku de la base de dades.
        SudokuDbHelper sudoDButil = SudokuDbHelper.getInstance(this); // Better han getBaseContext()
        SQLiteDatabase db = sudoDButil.getWritableDatabase();

        String sudo = sudoDButil.obteSudokus();

        // A partir d'aquí es prepara el sudoku per ficar-lo al GridView.
        final String[] numsSudokuBase = sudo.split(",");
        numsSudokuJoc = new ArrayList<String>();
        Random rand = new Random();
        int numerosAEliminar = 25, randIndex;

        for (int i = 0; i < numsSudokuBase.length; i++) {
            numsSudokuJoc.add(numsSudokuBase[i]);
        }

        // I aquí es seleccionen els índex a esborrar amb un número aleatori.
        while (numerosAEliminar != 0) {
            randIndex = rand.nextInt(81);
            if (numerosAEliminar != 0) {
                if (numsSudokuJoc.get(randIndex) != " ") {
                    numsSudokuJoc.set(randIndex, " ");
                    numerosAEliminar--;
                }
            }

        }

        /*for (int i = 0; i < numsSudokuJoc.size(); i++) {

        }*/

        // Aquí s'obté el GridView del layout i es possa el seu adapter.
        gridView = findViewById(R.id.gridView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numsSudokuJoc);
        gridView.setAdapter(adapter);

        // A partir d'aquí s'ha d'implementar com fer que es puguin inserir números. Una idea és: botons abaix del sudoku.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                if (adapter.getItem(position).equals(" ")) {
                    Toast.makeText(getApplicationContext(),
                            ((TextView) v).getText() + "Casilla seleccionada!", Toast.LENGTH_SHORT).show();
                    btOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "1");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "2");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "3");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btFour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "4");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btFive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "5");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btSix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "6");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btSeven.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "7");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btEight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "8");
                            adapter.notifyDataSetChanged();
                        }
                    });
                    btNine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "9");
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

                if (numsSudokuJoc.equals(numsSudokuBase)) {

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
 /*
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
    }*/
}
