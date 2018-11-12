package edu.fje.clot.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Classe utilitat per a la manipulació de la base de dades de puntuacions.
 *
 * @author biel serrano
 * @version 1.0 09/11/2018
 *
 */

public class PuntuacionsDbHelper extends SQLiteOpenHelper {

    private static PuntuacionsDbHelper sInstance;

    // Si canviem l'esquema hem de canviar la versió a un nombre superior.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Puntuacions.db";

    // Declaració de constants per als tipus de dades i el codi SQL de creació i esborrat de la taula.
    private static final String TIPUS_TEXT = " TEXT";
    private static final String TIPUS_ENTER = " INT";
    private static final String SEPARADOR_COMA = ",";

    private static final String SQL_CREACIO_TAULA = "CREATE TABLE "
            + PuntuacionsContract.TaulaPuntuacions.NOM_TAULA + " ("
            + PuntuacionsContract.TaulaPuntuacions._ID + " INTEGER PRIMARY KEY,"
            + PuntuacionsContract.TaulaPuntuacions.COLUMNA_NOM + TIPUS_TEXT + SEPARADOR_COMA
            + PuntuacionsContract.TaulaPuntuacions.COLUMNA_PUNTS + TIPUS_ENTER + " )";

    private static final String SQL_ESBORRAT_TAULA = "DROP TABLE IF EXISTS "
            + PuntuacionsContract.TaulaPuntuacions.NOM_TAULA;

    public static synchronized PuntuacionsDbHelper getInstance(Context context) {
        // Utilitza el context de l'aplicació, el que assegura
        // que no es filtri accidentalment un context d'una Activity.
        if (sInstance == null) {
            sInstance = new PuntuacionsDbHelper(context.getApplicationContext());
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
    private PuntuacionsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREACIO_TAULA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_ESBORRAT_TAULA);
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
            values.put(PuntuacionsContract.TaulaPuntuacions.COLUMNA_NOM, Puntuacio.nomJugador);
            values.put(PuntuacionsContract.TaulaPuntuacions.COLUMNA_PUNTS, Puntuacio.punts);

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(PuntuacionsContract.TaulaPuntuacions.NOM_TAULA, null, values);
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
    public List<Puntuacio> getAllScores() {
        List<Puntuacio> puntuacions = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        PuntuacionsContract.TaulaPuntuacions.NOM_TAULA);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Puntuacio novaPuntuacio = new Puntuacio();
                    novaPuntuacio.nomJugador = cursor.getString(cursor.getColumnIndex(PuntuacionsContract.TaulaPuntuacions.COLUMNA_NOM));
                    novaPuntuacio.punts = cursor.getInt(cursor.getColumnIndex(PuntuacionsContract.TaulaPuntuacions.COLUMNA_PUNTS));
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
     * Mètode per eliminar totes les dades de la taula de puntuacions.
     * @author Biel Serrano
     * @version 1.0 12/11/2018
     *
     */
    public void deleteAllPostsAndUsers() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(PuntuacionsContract.TaulaPuntuacions.NOM_TAULA, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();
        }
    }
}
