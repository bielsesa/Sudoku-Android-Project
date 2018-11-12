package edu.fje.clot.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Activitat per inicialitzar la pàgina web d'ajuda.
 * @author Biel Serrano
 * @version 1.0 09/11/2018
 */

public class WebAjudaActivity extends AppCompatActivity {

    WebView wvAjuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ajuda);

        wvAjuda = (WebView) findViewById(R.id.wvAjuda);
        wvAjuda.setWebViewClient(new WebViewClient());
        wvAjuda.getSettings().setBuiltInZoomControls(true); //Aixo pq es pugui fer zoom amb els dits
        wvAjuda.getSettings().setDisplayZoomControls(false); //Aixo pq no es mostri una lupa de zoom (queda una mica lletg xd)
        wvAjuda.loadUrl("file:///android_asset/web/index.html");
    }

    @Override
    public void onBackPressed() {
        if (wvAjuda.canGoBack()) {
            wvAjuda.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

