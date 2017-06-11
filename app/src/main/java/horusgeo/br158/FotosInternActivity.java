package horusgeo.br158;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class FotosInternActivity extends AppCompatActivity {

    ListView listaFotosIntern;
    DBFotos dbFotos;
    Vector<Fotos> fotos;

    ArrayList<String> listImage = new ArrayList<String>();
    ListViewAdapter listAdapter;

    Button button;

    ImageHelper imgPhoto;

    String isNew;
    String tipo;
    Integer tipo_foto;
    String id;

    Uri fileUri;

    FloatingActionButton addFotosButton;
    FloatingActionButton cancelFotosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_intern);

        listaFotosIntern = (ListView) findViewById(R.id.listaFotosIntern);

        listAdapter = new ListViewAdapter(FotosInternActivity.this, listImage);


        listaFotosIntern.setAdapter(listAdapter);

        imgPhoto = new ImageHelper();

        Intent intent = getIntent();

        isNew = intent.getStringExtra("isNew");
        tipo = intent.getStringExtra("tipo");
        tipo_foto = Integer.parseInt(intent.getStringExtra("tipo_foto"));
        id = intent.getStringExtra("id");

        dbFotos = new DBFotos(this, null, null, 1);

        fotos = dbFotos.getFotos(Integer.parseInt(id), tipo_foto);

        button = (Button) findViewById(R.id.buttonFoto);
        addFotosButton = (FloatingActionButton) findViewById(R.id.fotoAddButton);
        cancelFotosButton = (FloatingActionButton) findViewById(R.id.fotoCancelButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        addFotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbFotos.addFotos(fotos);
                onBackPressed();
            }
        });

        cancelFotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(FotosInternActivity.this, FotosActivity.class);
        intent.putExtra("isNew", isNew);
        intent.putExtra("tipo", tipo);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();

        listAdapter.clear();
        for(Fotos temp : fotos){
            listImage.add(temp.getPath());
        }
        listAdapter.notifyDataSetChanged();

    }


    public void takePhoto(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = imgPhoto.getOutputMediaFileUri(imgPhoto.MEDIA_TYPE_IMAGE, this); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        intent.putExtra("isNew", isNew);
        intent.putExtra("tipo", tipo);
        intent.putExtra("tipo_foto", String.valueOf(tipo_foto));
        intent.putExtra("id", id);
        // start the image capture Intent
        this.startActivityForResult(intent, tipo_foto);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();

        isNew = intent.getStringExtra("isNew");
        tipo = intent.getStringExtra("tipo");
        tipo_foto = Integer.parseInt(intent.getStringExtra("tipo_foto"));
        id = intent.getStringExtra("id");

        if (resultCode == RESULT_OK){
            String[] name = fileUri.getPath().split("/");
            Fotos aux = new Fotos();
            aux.setId(Integer.parseInt(id));
            aux.setFile(name[name.length-1]);
            aux.setPath(fileUri.getPath());
            aux.setNova(2);
            aux.setTipo(tipo_foto);
            fotos.add(aux);

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Foto cancelada!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Alguma coisa deu errado!", Toast.LENGTH_LONG).show();
        }


    }

}
