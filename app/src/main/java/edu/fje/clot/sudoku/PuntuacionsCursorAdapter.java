package edu.fje.clot.sudoku;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PuntuacionsCursorAdapter extends CursorAdapter {
    public PuntuacionsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.puntuacio_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvNomJugador = (TextView) view.findViewById(R.id.tvNomJugador);
        TextView tvPunts = (TextView) view.findViewById(R.id.tvPunts);
        // Extract properties from cursor
        String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
        int punts = cursor.getInt(cursor.getColumnIndexOrThrow("punts"));
        // Populate fields with extracted properties
        tvNomJugador.setText(nom);
        tvPunts.setText(String.valueOf(punts));
    }
}
