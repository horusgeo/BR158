package horusgeo.br158;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MapaActivity extends AppCompatActivity {
    WebView webview;
    Button btnAcMapa;
    FloatingActionButton floatingReturn;
    FloatingActionButton floatingLocation;
    FloatingActionButton floatingRegua;
    FloatingActionButton fabReguaNew;
    FloatingActionButton fabReguaCancel;
    FloatingActionButton floatingPin;
    FloatingActionButton floatingDesenho;
    FloatingActionButton fabPointsCancel;
    FloatingActionButton fabPointsNew;
    FloatingActionButton fabPointsOk;

    String id;
    Boolean criarProp = true;
    addNewRegisterActivity cadastro;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        webview = (WebView) findViewById(R.id.webview);
        assert webview != null;
        webview.loadUrl("file:///android_asset/www/map.html");

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url){
                populateMap();
            }
        });

        webview.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        webview.addJavascriptInterface(new WebAppInterface(this), "Android");

        /* ----------------------------------------------------------------------------------*/
        /* Retorno*/
        floatingReturn = (FloatingActionButton)findViewById(R.id.floatingReturn);

        floatingReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MapaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /* ----------------------------------------------------------------------------------*/
        /* Location*/
        floatingLocation = (FloatingActionButton)findViewById(R.id.floatingLocation);
        floatingLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:findLocation()");
            }
        });
        /* ----------------------------------------------------------------------------------*/
        /* PIN*/
        floatingPin = (FloatingActionButton) findViewById(R.id.floatingPin);
        floatingPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingReturn.setVisibility(View.INVISIBLE);
                floatingReturn.setClickable(true);
                fabReguaCancel.setVisibility(View.VISIBLE);
                fabReguaCancel.setClickable(true);
                fabReguaNew.setVisibility(View.INVISIBLE);
                fabReguaNew.setClickable(false);
                webview.loadUrl("javascript:startPin()");
            }
        });
        /* ----------------------------------------------------------------------------------*/
        /* REGUA*/
        floatingRegua = (FloatingActionButton) findViewById(R.id.floatingRegua);
        floatingRegua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingReturn.setVisibility(View.INVISIBLE);
                floatingReturn.setClickable(false);
                fabReguaNew.setVisibility(View.VISIBLE);
                fabReguaNew.setClickable(true);
                fabReguaCancel.setVisibility(View.VISIBLE);
                fabReguaCancel.setClickable(true);
                clickRegua(true);
            }
        });

        fabReguaNew = (FloatingActionButton) findViewById(R.id.fabReguaNew);
        fabReguaNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:clickPinRegua()");
            }
        });

        fabReguaCancel = (FloatingActionButton) findViewById(R.id.fabReguaCancel);
        fabReguaCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingReturn.setVisibility(View.VISIBLE);
                floatingReturn.setClickable(false);
                fabReguaNew.setVisibility(View.INVISIBLE);
                fabReguaNew.setClickable(false);
                fabReguaCancel.setVisibility(View.INVISIBLE);
                fabReguaCancel.setClickable(false);
                clickRegua(false);
            }
        });
        /* ----------------------------------------------------------------------------------*/
        /*Pontos Propriedade*/
        floatingDesenho = (FloatingActionButton) findViewById(R.id.floatingDesenho);
        floatingDesenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                floatingReturn.setVisibility(View.INVISIBLE);
                floatingReturn.setClickable(false);
                floatingDesenho.setVisibility(View.INVISIBLE);
                floatingDesenho.setClickable(false);
                floatingRegua.setVisibility(View.INVISIBLE);
                floatingRegua.setClickable(true);
                floatingPin.setVisibility(View.INVISIBLE);
                floatingPin.setClickable(false);

                fabPointsNew.setVisibility(View.VISIBLE);
                fabPointsNew.setClickable(true);

                fabPointsCancel.setVisibility(View.VISIBLE);
                fabPointsCancel.setClickable(true);

                fabPointsOk.setVisibility(View.VISIBLE);
                fabPointsOk.setClickable(true);
                fabReguaCancel.setVisibility(View.VISIBLE);
                fabReguaCancel.setClickable(true);

            }
        });

        fabPointsOk = (FloatingActionButton) findViewById(R.id.fabPointsOk);
        fabPointsOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:createProperty(" + 123 + ", '" + "ABC" + "', '" + "Aceito" + "')");
                /*webview.loadUrl("javascript:createProperty(" + cadastro.get_id_prop() + ", '" + cadastro.get_nome_proprietario() + "', '" + tipo + "')");*/
            }
        });

        fabPointsCancel = (FloatingActionButton) findViewById(R.id.fabPointsCancel);
        fabPointsCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingReturn.setVisibility(View.VISIBLE);
                floatingReturn.setClickable(true);
                floatingDesenho.setVisibility(View.VISIBLE);
                floatingDesenho.setClickable(true);
                floatingPin.setVisibility(View.VISIBLE);
                floatingPin.setClickable(true);
                floatingRegua.setVisibility(View.VISIBLE);
                floatingReturn.setClickable(true);

                fabPointsNew.setVisibility(View.INVISIBLE);
                fabPointsNew.setClickable(false);
                fabReguaCancel.setVisibility(View.INVISIBLE);
                fabReguaCancel.setClickable(false);
                fabReguaNew.setVisibility(View.INVISIBLE);
                fabReguaNew.setClickable(false);

                fabPointsCancel.setVisibility(View.INVISIBLE);
                fabPointsCancel.setClickable(false);
                fabPointsOk.setVisibility(View.INVISIBLE);
                fabPointsOk.setClickable(false);

                webview.loadUrl("javascript:clearPoints()");

            }
        });
        fabPointsNew = (FloatingActionButton)findViewById((R.id.fabPointsNew));
        fabPointsNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.loadUrl("javascript:clickPoints()");

            }
        });
    }

    private void clickRegua(Boolean which){
        if(which)
            webview.loadUrl("javascript:clickRegua()");
        else
            webview.loadUrl("javascript:closeRegua()");
    }

    public void populateMap(){
        webview.loadUrl("javascript:loadImg('/storage/extSdCard/www')");
        webview.loadUrl("javascript:loadImg('/storage/E84C-FF83/www')");
        webview.loadUrl("javascript:loadImg('/storage/3363-3334/www')");
        webview.loadUrl("javascript:loadImg('/storage/3731-3938/www')");
        //webview.loadUrl("javascript:loadKml()");

        if(criarProp)
            webview.loadUrl("javascript:startCentralPin()");
    }

    private class WebAppInterface {
        public WebAppInterface(MapaActivity mapaActivity) {
        }
    }
}



