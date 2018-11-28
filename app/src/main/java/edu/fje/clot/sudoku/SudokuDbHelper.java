package edu.fje.clot.sudoku;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static java.lang.System.exit;

/**
 * Classe utilitat per a la manipulació de la base de dades de puntuacions.
 *
 * @author Biel Serrano
 * @version 1.0 09/11/2018
 *
 */

public class SudokuDbHelper extends SQLiteOpenHelper {

    private static SudokuDbHelper sInstance;

    // Si canviem l'esquema hem de canviar la versió a un nombre superior.
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Sudoku";

    // TAULA PUNTUACIONS
    private static final String SQL_CREACIO_TAULA_PUNTUACIONS = "CREATE TABLE "
            + SudokusContract.TaulaPuntuacions.NOM_TAULA + " ("
            + SudokusContract.TaulaPuntuacions._ID + " INTEGER PRIMARY KEY,"
            + SudokusContract.TaulaPuntuacions.COLUMNA_NOM + " TEXT,"
            + SudokusContract.TaulaPuntuacions.COLUMNA_PUNTS + " INTEGER )";

    public static final String SQL_ESBORRAT_TAULA_PUNTUACIONS = "DROP TABLE IF EXISTS "
            + SudokusContract.TaulaPuntuacions.NOM_TAULA;

    // TAULA SUDOKUS
    public static final String SQL_CREACIO_TAULA_SUDOKUS = "CREATE TABLE "
            + SudokusContract.TaulaSudokus.NOM_TAULA + " ("
            + SudokusContract.TaulaSudokus._ID + " INTEGER PRIMARY KEY,"
            + SudokusContract.TaulaSudokus.COLUMNA_ARRAY_SUDOKU + " TEXT )";

    public static final String SQL_ESBORRAT_TAULA_SUDOKUS = "DROP TABLE IF EXISTS "
            + SudokusContract.TaulaPuntuacions.NOM_TAULA;

    public static synchronized SudokuDbHelper getInstance(Context context) {
        // Utilitza el context de l'aplicació, el que assegura
        // que no es filtri accidentalment un context d'una Activity.
        if (sInstance == null) {
            sInstance = new SudokuDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * El Constructor ha de ser privat per prevenir instanciació directa.
     * S'ha de fer una trucada al mètode estàtic "getInstance()" en el seu lloc.
     *
     * @author Biel Serrano
     * @version 1.0 09/11/2018
     */
    private SudokuDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREACIO_TAULA_PUNTUACIONS);
        db.execSQL(SQL_CREACIO_TAULA_SUDOKUS);

        // Inserció sudokus
        ContentValues sudoku1 = new ContentValues(), sudoku2 = new ContentValues(), sudoku3 = new ContentValues();
        sudoku1.put(SudokusContract.TaulaSudokus.COLUMNA_ARRAY_SUDOKU,
                "4,3,5,2,6,9,7,8,1,6,8,2,5,7,1,4,9,3,1,9,7,8,3,4,5,6,2,8,2,6,1,9,5,3,4,7,3,7,4,6,8,2,9,1,5,9,5,1,7,4,3,6,2,8,5,1,9,3,2,6,8,7,4,2,4,8,9,5,7,1,3,6,7,6,3,4,1,8,2,5,9");
        /*sudoku2.put(SudokusContract.TaulaSudokus.COLUMNA_ARRAY_SUDOKU,
                "1,5,2,4,8,9,3,7,6,7,3,9,2,5,6,8,4,1,4,6,8,3,7,1,2,9,5,3,8,7,1,2,4,6,5,9,5,9,1,7,6,3,4,2,8,2,4,6,8,9,5,7,1,3,9,1,4,6,3,7,5,8,2,6,2,5,9,4,8,1,3,7,8,7,3,5,1,2,9,6,4");
        sudoku3.put(SudokusContract.TaulaSudokus.COLUMNA_ARRAY_SUDOKU,
                "5,6,8,3,1,9,4,7,2,7,9,1,4,5,2,3,6,8,2,3,4,6,7,8,5,9,1,6,1,9,5,8,4,7,2,3,3,2,5,7,9,1,6,8,4,4,8,7,2,3,6,9,1,5,1,4,3,8,6,7,2,5,9,8,5,6,9,2,3,1,4,7,9,7,2,1,4,5,8,3,6");
*/
        db.insert(SudokusContract.TaulaSudokus.NOM_TAULA, null, sudoku1);
        /*db.insert(SudokusContract.TaulaSudokus.NOM_TAULA, null, sudoku2);
        db.insert(SudokusContract.TaulaSudokus.NOM_TAULA, null, sudoku3);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_ESBORRAT_TAULA_PUNTUACIONS);
        db.execSQL(SQL_ESBORRAT_TAULA_SUDOKUS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    /**
     * Mètode per inserir una puntuació nova a la BD.
     * @param p puntuació del jugador
     * @author Biel Serrano
     * @version 1.0 12/11/2018
     */
    public void afegeixPuntuacio(Puntuacio p) {
        // Crea i/o obre la BD per escriure.
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SudokusContract.TaulaPuntuacions.COLUMNA_NOM, Puntuacio.nomJugador);
            values.put(SudokusContract.TaulaPuntuacions.COLUMNA_PUNTS, Puntuacio.punts);

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(SudokusContract.TaulaPuntuacions.NOM_TAULA, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error a l'hora d'intentar afegir una nova puntuació a la base de dades");
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Mètode per recollir totes les puntuacions de la BD.
     * @return llista de puntuacions actuals
     * @author Biel Serrano
     * @version 1.0 12/11/2018
     */
    public List<Puntuacio> obteTotesLesPuntuacions() {
        List<Puntuacio> puntuacions = new ArrayList<>();

        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        SudokusContract.TaulaPuntuacions.NOM_TAULA);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Puntuacio novaPuntuacio = new Puntuacio();
                    novaPuntuacio.nomJugador = cursor.getString(cursor.getColumnIndex(SudokusContract.TaulaPuntuacions.COLUMNA_NOM));
                    novaPuntuacio.punts = cursor.getInt(cursor.getColumnIndex(SudokusContract.TaulaPuntuacions.COLUMNA_PUNTS));
                    puntuacions.add(novaPuntuacio);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error a l'hora d'intentar agafar la informació de la base de dades");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return puntuacions;
    }

    /**
     * Mètode per obtenir els Sudokus de la BD.
     * @return llista de sudokus
     * @author Biel Serrano
     * @version 1.0 20/11/2018
     */
    public String obteSudokus() {
        //Random rand = new Random();
        String sudoku = "";
        //ArrayList<String> sudokus = null;

        String SUDOKU_SELECT_QUERY = "SELECT * FROM " + SudokusContract.TaulaSudokus.NOM_TAULA + " LIMIT 1";
        //String SUDOKU_SELECT_QUERY = "SELECT * FROM " + SudokusContract.TaulaSudokus.NOM_TAULA;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SUDOKU_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    sudoku = cursor.getString(cursor.getColumnIndex(SudokusContract.TaulaSudokus.COLUMNA_ARRAY_SUDOKU));
                    //sudokus.add(cursor.getString(cursor.getColumnIndex(SudokusContract.TaulaSudokus.COLUMNA_ARRAY_SUDOKU)));
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error a l'hora d'intentar agafar la informació de la base de dades");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        /*if (sudokus != null) {
            sudoku = sudokus.get(rand.nextInt(3));
        } else {
            System.out.println("S'ha produït un error a l'hora d'intentar obtenir les dades: no existeixen.");
        }*/
        return sudoku;
    }
}
