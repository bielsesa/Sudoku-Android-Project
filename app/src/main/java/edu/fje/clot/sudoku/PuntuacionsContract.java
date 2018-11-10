package edu.fje.clot.sudoku;

import android.provider.BaseColumns;

/**
 * Classe Utilitat que defineix el contacte envers la base de dades
 *
 * @author biel serrano
 * @version 1.0 09/11/2018
 *
 */
public final class PuntuacionsContract{

    public PuntuacionsContract() {
    }

    /**
     * Classe Interna que declara una taula de Puntuacions
     *
     * @author biel serrano
     * @version 1.0 09/11/2018
     *
     */

    static abstract class TaulaPuntuacions implements BaseColumns {
        static final String NOM_TAULA = "puntuacions";
        static final String COLUMNA_NOM = "nom";
        static final String COLUMNA_PUNTS = "punts";
        static final String COLUMNA_NULL = "null";
    }
}
