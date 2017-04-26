package horusgeo.br158;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<String> listRegister = new ArrayList<String>();
    ArrayAdapter listAdapter;

    DBEmpresa dbEmpresa;
    DBProprietario dbProprietario;

    ListView listaCadastros;

    Button returnMap;
    Button addNewRegister;

    Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbProprietario = new DBProprietario(this, null, null, 1);
        dbEmpresa = new DBEmpresa(this, null, null, 1);

        listAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                (String[])listRegister.toArray(new String[0]));

        listaCadastros = (ListView) findViewById(R.id.listaCadastros);

        listaCadastros.setAdapter(listAdapter);

        returnMap = (Button) findViewById(R.id.returnToMapButton);
        addNewRegister = (Button) findViewById(R.id.addNewRegisterButton);

        filterSpinner = (Spinner) findViewById(R.id.filterRegistersSpinner);

        returnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addNewRegisterActivity.class);
                intent.putExtra("isNew", "true");
                intent.putExtra("nome", "");
                startActivity(intent);
                finish();
            }
        });

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterListView(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listaCadastros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, addNewRegisterActivity.class);
                intent.putExtra("isNew", "false");
                intent.putExtra("nome", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
                finish();
            }
        });
    }

    private void filterListView(String type){
        listRegister.clear();
        if(type.equals("Todos")){
            listRegister.addAll(dbProprietario.getAllNames());
            listRegister.addAll(dbEmpresa.getAllNames());
        }else if (type.equals("Pessoa Física")){
            listRegister.addAll(dbProprietario.getAllNames());
        }else if (type.equals("Pessoa Jurídica")){
            listRegister.addAll(dbEmpresa.getAllNames());
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

}



