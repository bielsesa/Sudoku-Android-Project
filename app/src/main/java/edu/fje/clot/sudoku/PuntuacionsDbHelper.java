package edu.fje.clot.sudoku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe utilitat per a manipulació de la BD
 *
 * @author biel serrano
 * @version 1.0 09/11/2018
 *
 */

public class PuntuacionsDbHelper extends SQLiteOpenHelper {

    private static final String TIPUS_TEXT = " TEXT";
    private static final String TIPUS_ENTER = " INT";
    private static final String SEPARADOR_COMA = ",";
    private static final String SQL_CREACIO_TAULA = "CREATE TABLE "
            + PuntuacionsContract.TaulaPuntuacions.NOM_TAULA + " ("
            + PuntuacionsContract.TaulaPuntuacions._ID + " INTEGER PRIMARY KEY,"
            + PuntuacionsContract.TaulaPuntuacions.COLUMNA_NOM
            + TIPUS_TEXT + SEPARADOR_COMA
            + PuntuacionsContract.TaulaPuntuacions.COLUMNA_PUNTS + TIPUS_ENTER + " )";

    private static final String SQL_ESBORRAT_TAULA = "DROP TABLE IF EXISTS "
            + PuntuacionsContract.TaulaPuntuacions.NOM_TAULA;

    // Si canviem l'esquema hem de canviar la versió
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Puntuacions.db";

    public PuntuacionsDbHelper(Context context) {
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
}
