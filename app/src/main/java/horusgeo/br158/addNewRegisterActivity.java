package horusgeo.br158;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class addNewRegisterActivity extends AppCompatActivity {

    Button registerButton;
    Button empButton;
    Button propButton;
    Button fotoButton;
    Button relatorioButton;
    Button saveButton;
    Button regLatLng;

    DBProprietario dbProprietario;
    DBEmpresa dbEmpresa;
    DBStatus dbStatus;
    DBLatLng dbLatLng;

    TextView nomeText;
    TextView latText;
    TextView lngText;

    Integer id;

    LocationManager locationManager;
    LocationListener locationListener;
    Double lat = 0.0;
    Double lng = 0.0;

    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_register);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        tipo = intent.getStringExtra("tipo");
        id = Integer.parseInt(intent.getStringExtra("id"));

        dbProprietario = new DBProprietario(this, null, null, 1);
        dbEmpresa = new DBEmpresa(this, null, null, 1);
        dbStatus = new DBStatus(this, null, null, 1);
        dbLatLng = new DBLatLng(this, null, null, 1);

        nomeText = (TextView) findViewById(R.id.textNomeProp);
        latText = (TextView) findViewById(R.id.textLat);
        lngText = (TextView) findViewById(R.id.textLng);

        registerButton = (Button) findViewById(R.id.buttonRegister);
        empButton = (Button) findViewById(R.id.buttonJuridico);
        propButton = (Button) findViewById(R.id.buttonPropriedade);
        relatorioButton = (Button) findViewById(R.id.buttonRelatorio);
        fotoButton = (Button) findViewById(R.id.buttonFotos);
        saveButton = (Button) findViewById(R.id.buttonSave);
        regLatLng = (Button) findViewById(R.id.buttonLatLng);

        if(isNew.equals("true")){
            if (tipo.equals("juridica")){
                nomeText.setText("Pessoa Jurídica");
                registerButton.setVisibility(View.INVISIBLE);
                registerButton.setEnabled(false);
                empButton.setVisibility(View.VISIBLE);
                empButton.setEnabled(true);
            }
        } else {
            LatLng latlng = dbLatLng.getLatLng();
            if(!latlng.getLat().equals(""))
                lat = Double.parseDouble(latlng.getLat());
            if(!latlng.getLng().equals(""))
                lng = Double.parseDouble(latlng.getLng());
            if (tipo.equals("fisica")) {
                nomeText.setText(dbProprietario.getName(id));
                if(!latlng.getLat().isEmpty()) {
                    latText.setText("Lat: " + latlng.getLat());
                    lngText.setText("Lng: " + latlng.getLng());
                    //latText.setText("Lat: 0.0");
                    //lngText.setText("Lng: 0.0");
                }else{
                    latText.setText("Lat: 0.0");
                    lngText.setText("Lng: 0.0");
                }

            } else {
                nomeText.setText(dbEmpresa.getName(id));
                registerButton.setVisibility(View.INVISIBLE);
                empButton.setVisibility(View.VISIBLE);
                registerButton.setEnabled(false);
                empButton.setEnabled(true);
                if (!latlng.getLat().isEmpty()) {
                    latText.setText("Lat: " + latlng.getLat());
                    lngText.setText("Lng: " + latlng.getLng());
                    //latText.setText("Lat: 0.0");
                    //lngText.setText("Lng: 0.0");
                } else {
                    latText.setText("Lat: 0.0");
                    lngText.setText("Lng: 0.0");
                }
            }
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, RegisterActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();

            }
        });

        empButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, RegisterJuridicoActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        propButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, infoPropActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });



        relatorioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, RelatorioActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        fotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, FotosActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        regLatLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCoord();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new myLocationlistener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);


    }

    @Override
    public void onBackPressed(){
        if(tipo.equals("fisica"))
            dbStatus.setStatusChanged(id, 1);
        else
            dbStatus.setStatusChanged(id, 2);
        LatLng latLng = new LatLng();
        latLng.setLat(lat.toString());
        latLng.setLng(lng.toString());
        dbLatLng.addLatLng(id, latLng);
        Intent intent = new Intent(addNewRegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void getCoord(){
        if(lat != 0.0)
            latText.setText("Lat: " + lat.toString().substring(0,8));
        else
            latText.setText("Lat: " + lat.toString());
        if(lng != 0.0)
            lngText.setText("Lng: " + lng.toString().substring(0,8));
        else
            lngText.setText("Lng: " + lng.toString());

    }

    private class myLocationlistener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if(location != null){
                lat = location.getLatitude();
                lng = location.getLongitude();
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }


}
