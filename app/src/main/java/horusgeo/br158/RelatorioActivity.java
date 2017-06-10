package horusgeo.br158;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RelatorioActivity extends AppCompatActivity{

    EditText data1;
    EditText data2;
    EditText data3;

    EditText horarioChegada;
    EditText horarioSaida;
    EditText horario2;
    EditText horario3;

    EditText descricao1;
    EditText descricao2;
    EditText descricao3;

    EditText responsavel;

    Relatorio relatorio;

    DBRelatorio dbrelatorio;

    Boolean newRegister = true;
    Integer newID = 0;

    FloatingActionButton addRelatorio;
    FloatingActionButton cancelRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        data1 = (EditText) findViewById(R.id.dataRelatorioText);
        data2 = (EditText) findViewById(R.id.data2RelatorioText);
        data3 = (EditText) findViewById(R.id.data3RelatorioText);

        horarioChegada = (EditText) findViewById(R.id.horarioChegadaRelatorioText);
        horarioSaida = (EditText) findViewById(R.id.horarioSaidaRelatorioText);
        horario2 = (EditText) findViewById(R.id.horario2RelatorioText);
        horario3 = (EditText) findViewById(R.id.horario3RelatorioText);

        descricao1 = (EditText) findViewById(R.id.descricaoRelatorioText);
        descricao2 = (EditText) findViewById(R.id.descricao2RelatorioText);
        descricao3 = (EditText) findViewById(R.id.descricao3RelatorioText);

        responsavel = (EditText) findViewById(R.id.responsavelRelatorioText);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        final String id = intent.getStringExtra("id");

        newID = Integer.parseInt(id);

        dbrelatorio = new DBRelatorio(this, null, null, 1);

        if (isNew.equals("false")) {
            newRegister = false;
            relatorio = dbrelatorio.getRelatorio(Integer.parseInt(id));
        }

        addRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRegister();
                onBackPressed();
            }
        });

        cancelRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RelatorioActivity.this, addNewRegisterActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!newRegister) {
            data1.setText(relatorio.getData1());
            data2.setText(relatorio.getData2());
            data3.setText(relatorio.getData3());
            horarioChegada.setText(relatorio.getHorarioChegada());
            horarioSaida.setText(relatorio.getHorarioSaida());
            horario2.setText(relatorio.getHorario2());
            horario3.setText(relatorio.getHorario3());
            descricao1.setText(relatorio.getDescricao1());
            descricao2.setText(relatorio.getDescricao2());
            descricao3.setText(relatorio.getDescricao3());
            responsavel.setText(relatorio.getResponsavel());


        }

    }

    public void saveRegister() {
        if (newID != 0) {
            relatorio.setId(newID);
            relatorio.setData1(data1.getText().toString());
            relatorio.setData2(data2.getText().toString());
            relatorio.setData3(data3.getText().toString());
            relatorio.setHorarioChegada(horarioChegada.getText().toString());
            relatorio.setHorarioSaida(horarioSaida.getText().toString());
            relatorio.setHorario2(horario2.getText().toString());
            relatorio.setHorario3(horario3.getText().toString());
            relatorio.setDescricao1(descricao1.getText().toString());
            relatorio.setDescricao2(descricao2.getText().toString());
            relatorio.setDescricao3(descricao3.getText().toString());
            relatorio.setResponsavel(responsavel.getText().toString());
        }

    }





}
