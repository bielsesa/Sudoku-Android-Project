package edu.fje.clot.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PartidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


}
