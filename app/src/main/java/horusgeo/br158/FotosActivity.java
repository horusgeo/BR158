package horusgeo.br158;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class FotosActivity extends AppCompatActivity {

    ListView listaFotos;
    Button voltar;

    String isNew;
    String tipo;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);

        listaFotos = (ListView) findViewById(R.id.listaFotos);
        voltar = (Button) findViewById(R.id.buttonVoltar);

        Intent intent = getIntent();

        isNew = intent.getStringExtra("isNew");
        tipo = intent.getStringExtra("tipo");
        id = intent.getStringExtra("id");


        listaFotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FotosActivity.this, FotosInternActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("tipo_foto", String.valueOf(i));
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(FotosActivity.this, addNewRegisterActivity.class);
        intent.putExtra("isNew", isNew);
        intent.putExtra("tipo", tipo);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

}
