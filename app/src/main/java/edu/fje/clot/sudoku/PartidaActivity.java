package edu.fje.clot.sudoku;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

    final Context context = this;
    String[] numsSudokuBase;
    private ArrayList<String> numsSudokuJoc;
    private ArrayList<String> numsSudokuSolucio;
    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private Button btOne, btTwo, btThree, btFour, btFive, btSix, btSeven, btEight, btNine;
    private static long tempsIniciPartida;
    private static long tempsFinalPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tempsIniciPartida = System.currentTimeMillis();
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
        numsSudokuBase = sudo.split(",");
        numsSudokuJoc = new ArrayList<>();
        numsSudokuSolucio = new ArrayList<>();
        Random rand = new Random();
        int numerosAEliminar = 1, randIndex;

        for (int i = 0; i < numsSudokuBase.length; i++) {
            numsSudokuJoc.add(numsSudokuBase[i]);
            numsSudokuSolucio.add(numsSudokuBase[i]);
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
                            checkSudokuCompletion();
                        }
                    });
                    btTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "2");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "3");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btFour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "4");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btFive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "5");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btSix.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "6");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btSeven.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "7");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btEight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "8");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    btNine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            numsSudokuJoc.remove(position);
                            numsSudokuJoc.add(position, "9");
                            adapter.notifyDataSetChanged();
                            checkSudokuCompletion();
                        }
                    });
                    //checkSudokuCompletion();
                }
            }
        });
        //checkSudokuCompletion();
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

    public void checkSudokuCompletion() {
        if (numsSudokuJoc.equals(numsSudokuSolucio)) {

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // Títol del dialog
            alertDialogBuilder.setTitle("HAS GUANYAT!");

            // Missatge del dialog i botons
            alertDialogBuilder
                    .setMessage("Prem OK per sortir.")
                    .setCancelable(false)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // si es prem aquest botó, es tanca
                            // l'activitat actual (la partida)
                            PartidaActivity.this.finish();
                        }
                    });
            // Crea el dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // Mostra el dialog
            alertDialog.show();
            tempsFinalPartida = System.currentTimeMillis(); // Temps en la que s'acaba la partida
            long puntuacio = (50000 - (tempsFinalPartida - tempsIniciPartida)) / 100; //El 50000 és una puntuació base
            System.out.println("Puntuació: " + puntuacio);
        }
    }
}
