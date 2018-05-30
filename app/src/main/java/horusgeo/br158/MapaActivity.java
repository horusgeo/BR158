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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

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

        floatingReturn = (FloatingActionButton)findViewById(R.id.floatingReturn);

        floatingReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MapaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        floatingLocation = (FloatingActionButton)findViewById(R.id.floatingLocation);
        floatingLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("javascript:findLocation()");
            }
        });
    }

    public void populateMap(){
        webview.loadUrl("javascript:loadImg('/storage/extSdCard/www')");
        //webview.loadUrl("javascript:loadImg('/storage/E84C-FF83/www')");
        //webview.loadUrl("javascript:loadImg('/storage/3363-3334/www')");
        //webview.loadUrl("javascript:loadImg('/storage/3731-3938/www')");
        //webview.loadUrl("javascript:loadKml()");
    }

    private class WebAppInterface {
        public WebAppInterface(MapaActivity mapaActivity) {
        }
    }
}



