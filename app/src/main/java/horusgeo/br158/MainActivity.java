package horusgeo.br158;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> listRegister = new ArrayList<String>();
    ArrayAdapter listAdapter;

    DBBenfeitorias dbBenfeitorias;
    DBConfrontantes dbConfrontantes;
    DBConjuge dbConjuge;
    DBEmpresa dbEmpresa;
    DBEndEmpresa dbEndEmpresa;
    DBEndObj dbEndObj;
    DBEndPerson dbEndPerson;
    DBJuridico dbJuridico;
    DBPlantacoes dbPlantacoes;
    DBPropriedade dbPropriedade;
    DBProprietario dbProprietario;
    DBRegiao dbRegiao;
    DBRelatorio dbRelatorio;
    DBRoteiroAcesso dbRoteiroAcesso;
    DBStatus dbStatus;
    DBFotos dbFotos;

    ListView listaCadastros;

    Button returnMap;
    Button addNewRegister;
    Button addNewEmp;
    Button sendRegister;

    Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbProprietario = new DBProprietario(this, null, null, 1);
        dbEmpresa = new DBEmpresa(this, null, null, 1);

        listAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                listRegister);

        listaCadastros = (ListView) findViewById(R.id.listaCadastros);

        listaCadastros.setAdapter(listAdapter);

        returnMap = (Button) findViewById(R.id.returnToMapButton);
        addNewRegister = (Button) findViewById(R.id.addNewRegisterButton);
        addNewEmp = (Button) findViewById(R.id.addNewEmpButton);
        sendRegister = (Button) findViewById(R.id.sendButton);

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
                intent.putExtra("tipo", "fisica");
                intent.putExtra("id", String.valueOf(dbProprietario.getNewId()));
                startActivity(intent);
                finish();
            }
        });

        addNewEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addNewRegisterActivity.class);
                intent.putExtra("isNew", "true");
                intent.putExtra("tipo", "juridica");
                intent.putExtra("id", String.valueOf(dbEmpresa.getNewId()));
                startActivity(intent);
                finish();
            }
        });

        sendRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
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
                String nome = adapterView.getItemAtPosition(i).toString();
                String[] parts = nome.split("-");
                Integer id = Integer.parseInt(parts[parts.length-1]);

                if(dbProprietario.exist(id)) {
                    intent.putExtra("tipo", "fisica");
                    intent.putExtra("id", String.valueOf(id));
                } else {
                    intent.putExtra("tipo", "juridica");
                    intent.putExtra("id", String.valueOf(id));
                }
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

    private void send(){

        final Vector<Integer> list = dbStatus.getStatus();

        final int max = list.size();
        if (max > 0) {

            final ProgressDialog dialogProgress = new ProgressDialog(this);

            RequestQueue queue = Volley.newRequestQueue(this);

            dialogProgress.setMessage("Enviando Cadastros! Por Favor, aguarde!");
            dialogProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialogProgress.setProgress(0);
            dialogProgress.show();
            dialogProgress.setCancelable(false);

            final String url = "http://horusgeo.com.br/br158/dbfunctions/upload.php";

            dbBenfeitorias = new DBBenfeitorias(this, null, null, 1);
            dbConfrontantes = new DBConfrontantes(this, null, null, 1);
            dbConjuge = new DBConjuge(this, null, null, 1);
            dbEndEmpresa = new DBEndEmpresa(this, null, null, 1);
            dbEndObj = new DBEndObj(this, null, null, 1);
            dbEndPerson = new DBEndPerson(this, null, null, 1);
            dbJuridico = new DBJuridico(this, null, null, 1);
            dbPlantacoes = new DBPlantacoes(this, null, null, 1);
            dbPropriedade = new DBPropriedade(this, null, null, 1);
            dbRegiao = new DBRegiao(this, null, null, 1);
            dbRelatorio = new DBRelatorio(this, null, null, 1);
            dbRoteiroAcesso = new DBRoteiroAcesso(this, null, null, 1);
            dbStatus = new DBStatus(this, null, null, 1);
            dbFotos = new DBFotos(this, null, null, 1);


            for (int i = 0; i < list.size(); i++) {
                final Integer id = list.elementAt(i);
                String msg = "Enviando Cadastros! Por Favor, aguarde!\nEnviando cadastro:" + id;
                dialogProgress.setMessage(msg);
                dialogProgress.setProgress(i / max);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                dialogProgress.dismiss();
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Falha ao enviar o cadastro:" + id, Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id", String.valueOf(id));
                        params.putAll(dbBenfeitorias.getMap(id));
                        params.putAll(dbConfrontantes.getMap(id));
                        params.putAll(dbConjuge.getMap(id));
                        params.putAll(dbEmpresa.getMap(id));
                        params.putAll(dbEndEmpresa.getMap(id));
                        params.putAll(dbEndObj.getMap(id));
                        params.putAll(dbEndPerson.getMap(id));
                        params.putAll(dbJuridico.getMap(id));
                        params.putAll(dbPlantacoes.getMap(id));
                        params.putAll(dbPropriedade.getMap(id));
                        params.putAll(dbProprietario.getMap(id));
                        params.putAll(dbRegiao.getMap(id));
                        params.putAll(dbRelatorio.getMap(id));
                        params.putAll(dbRoteiroAcesso.getMap(id));
                        params.putAll(dbFotos.getMap(id, getContentResolver()));
                        return params;
                    }
                };
                queue.add(postRequest);

            }
        }
    }

}



