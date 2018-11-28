package edu.fje.clot.sudoku;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
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
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PartidaActivity extends AppCompatActivity{

    private final Context context = this;
    private String[] numsSudokuBase;
    private ArrayList<String> numsSudokuJoc;
    private ArrayList<String> numsSudokuSolucio;
    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private Button btOne, btTwo, btThree, btFour, btFive, btSix, btSeven, btEight, btNine;
    private static long tempsIniciPartida;
    private SudokuDbHelper sudoDButil;
    private long puntuacio;

    // Calendari
    private ContentResolver contentResolver;
    private Set<String> calendaris = new HashSet<>();
    private List<String> events = new ArrayList<>();

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
        // Calendari
        contentResolver = getContentResolver();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // A partir d'aquí s'agafa el sudoku de la base de dades.
        sudoDButil = SudokuDbHelper.getInstance(this); // Better han getBaseContext()


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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numsSudokuJoc);
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

    // Mètode propi per comprovar que s'ha completat el Sudoku. S'ha de cridar cada cop que es prem
    // un dels botons dels números.
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
            calculateAndSaveScore();
        }
    }

    // Mètode propi per calcular i guardar la puntuació de l'usuari. Conté una puntuació máxima de 5000
    // i es van restant punts segons el temps que ha trigat l'usuari en completar el Sudoku.
    public void calculateAndSaveScore() {
        long tempsFinalPartida = System.currentTimeMillis();
        puntuacio =  5000 - (tempsFinalPartida - tempsIniciPartida) / 100; // Es resta el temps inicial
                                                                           // al temps final per saber
                                                                           // quant de temps ha passat.
        Puntuacio p = new Puntuacio();
        p.nomJugador = "Solaire"; // Nom genèric de l'usuari. Encara no he pogut posar un EditText al
                                  // diàlog per guardar el nom.
        p.punts = (int) puntuacio;
        System.out.println("Puntuació: " + puntuacio); // Debug per comprovar el càlcul de la puntuació
        sudoDButil.afegeixPuntuacio(p);
        afegirEvent();
    }

    // Mètode que serveix per afegir un event al calendari
    private void afegirEvent() {

        ContentValues esdeveniment = new ContentValues();
        esdeveniment.put(CalendarContract.Events.CALENDAR_ID, 1); // Tipus de calendari
        esdeveniment.put(CalendarContract.Events.TITLE, "HAS GUANYAT AL JOC DEL SUDOKU! Tens una " +
                "puntuació de " + puntuacio);
        esdeveniment.put(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis());
        esdeveniment.put(CalendarContract.Events.DTEND, Calendar.getInstance().getTimeInMillis());
        esdeveniment.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Madrid");
        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, esdeveniment);

        // La URI conté el contentProvider i retorna el id del event creat
        int id = Integer.parseInt(uri.getLastPathSegment());
        Toast.makeText(getApplicationContext(), "S'ha afegit la teva puntuació al calendari!",
                Toast.LENGTH_SHORT).show();
        obtenirCalendaris();
    }

    // Mètode que recupera tots els calendaris disponibles al dispositiu
    private void obtenirCalendaris() {
        //la URI dels calendaris és content://com.android.calendar/calendars
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String[] projeccio = {CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.CALENDAR_COLOR,
                CalendarContract.Calendars.VISIBLE};
        Cursor cursor = contentResolver.query(uri, projeccio, null, null, null);

        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String nomIntern = cursor.getString(0);
                    String nomMostrat = cursor.getString(1);
                    String color = cursor.getString(cursor.getColumnIndex(
                            CalendarContract.Calendars.CALENDAR_COLOR));
                    Boolean seleccionat = !cursor.getString(3).equals("0");
                    calendaris.add(nomMostrat);
                }
            }
        } catch (AssertionError ex) {
        }
    }
}
