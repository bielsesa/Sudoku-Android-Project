package edu.fje.clot.sudoku;

import android.provider.BaseColumns;

/**
 * Classe utilitat que defineix el contacte amb la base de dades.
 * Conté la informació de les taules.
 *
 * @author biel serrano
 * @version 1.0 09/11/2018
 *
 */
public final class PuntuacionsContract{

    public PuntuacionsContract() {
    }

    /**
     * Classe interna que declara la taula de puntuacions del joc.
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
