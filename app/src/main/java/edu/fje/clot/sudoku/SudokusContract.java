package edu.fje.clot.sudoku;

import android.provider.BaseColumns;

/**
 * Classe utilitat que defineix el contacte amb la base de dades.
 * Conte la informacio de les taules.
 */
public final class SudokusContract{

    public SudokusContract() {
    }

    /**
     * Classe interna que declara la taula de sudokus del joc.
     *
     * @author Biel Serrano
     * @version 1.0 15/11/2018
     *
     */

    static abstract class TaulaSudokus implements BaseColumns {
        static final String NOM_TAULA = "sudokus";
        static final String COLUMNA_ARRAY_SUDOKU = "array_sudoku";
        static final String COLUMNA_NULL = "null";
    }

    /**
     * Classe interna que declara la taula de puntuacions del joc.
     *
     * @author Biel Serrano
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

