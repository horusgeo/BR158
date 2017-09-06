package horusgeo.br158;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;


public class infoPropActivity extends AppCompatActivity {

    Spinner zoneamento;
    Spinner topografia;
    Spinner manancial;
    Spinner situacao;
    Switch eletrica;
    Switch telefone;
    Switch agua;

    Switch lixo;
    Switch transporte;
    Switch saude;
    Switch escola;
    Switch comercio;
    Switch lazer;
    Switch seguranca;
    Spinner uso;

    EditText direita;
    EditText esquerda;
    EditText frente;
    EditText fundos;

    Switch contrucoes;
    EditText construcoesText;
    Switch equipamentos;
    EditText equipamentosText;
    Switch croquis;
    EditText croquisText;
    Switch plantacoes;

    EditText tipo;
    EditText idade;
    EditText compl;
    EditText obs;

    Benfeitorias benf = new Benfeitorias();
    Confrontantes conf = new Confrontantes();
    Plantacoes plant = new Plantacoes();
    Regiao regiao = new Regiao();
    Propriedade prop = new Propriedade();

    DBBenfeitorias dbBenfeitorias;
    DBConfrontantes dbConfrontantes;
    DBPlantacoes dbPlantacoes;
    DBRegiao dbRegiao;
    DBPropriedade dbPropriedade;

    Boolean newRegister = true;
    Integer newID = 0;
    String tipoRegister;

    FloatingActionButton addInfoProp;
    FloatingActionButton cancelInfoProp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_prop);

        zoneamento = (Spinner) findViewById(R.id.spinnerZoneamento);
        topografia = (Spinner) findViewById(R.id.spinnerTopografia);
        manancial = (Spinner) findViewById(R.id.spinnerManancial);
        situacao = (Spinner) findViewById(R.id.spinnerSituacao);
        eletrica = (Switch) findViewById(R.id.switchRedeEletrica);
        telefone = (Switch) findViewById(R.id.switchSinalTelefone);
        agua = (Switch) findViewById(R.id.switchAbastecimentoAgua);

        lixo = (Switch) findViewById(R.id.switchColetaLixo);
        transporte = (Switch) findViewById(R.id.switchTransporte);
        saude = (Switch) findViewById(R.id.switchSaude);
        escola = (Switch) findViewById(R.id.switchEscola);
        comercio = (Switch) findViewById(R.id.switchComercio);
        lazer = (Switch) findViewById(R.id.switchLazer);
        seguranca = (Switch) findViewById(R.id.switchSeguranca);
        uso = (Spinner) findViewById(R.id.spinnerUso);

        direita = (EditText) findViewById(R.id.confLadoDireitoText);
        esquerda = (EditText) findViewById(R.id.confLadoEsquerdoText);
        frente = (EditText) findViewById(R.id.confFrenteText);
        fundos = (EditText) findViewById(R.id.confFundosText);

        contrucoes = (Switch) findViewById(R.id.switchConstrucoes);
        construcoesText = (EditText) findViewById(R.id.benfConstucoesText);
        equipamentos = (Switch) findViewById(R.id.switchEquipamentos);
        equipamentosText = (EditText) findViewById(R.id.benfEquipamentosText);
        croquis = (Switch) findViewById(R.id.switchCroquis);
        croquisText = (EditText) findViewById(R.id.benfCroquisText);
        plantacoes = (Switch) findViewById(R.id.switchPlantacoes);


        tipo = (EditText) findViewById(R.id.tipoPlantacaoText);
        idade = (EditText) findViewById(R.id.idadePlantacaoText);
        compl = (EditText) findViewById(R.id.complPlantacaoText);
        obs = (EditText) findViewById(R.id.obsInfoPropText);

        addInfoProp = (FloatingActionButton) findViewById(R.id.infoPropAddButton);
        cancelInfoProp = (FloatingActionButton) findViewById(R.id.infoPropCancelButton);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        tipoRegister = intent.getStringExtra("tipo");
        final String id = intent.getStringExtra("id");

        newID = Integer.parseInt(id);

        dbBenfeitorias = new DBBenfeitorias(this, null, null, 1);
        dbConfrontantes = new DBConfrontantes(this, null, null, 1);
        dbPropriedade = new DBPropriedade(this, null, null, 1);
        dbPlantacoes = new DBPlantacoes(this, null, null, 1);
        dbRegiao = new DBRegiao(this, null, null, 1);

        if (isNew.equals("false")) {
            newRegister = false;
            benf = dbBenfeitorias.getBenf(Integer.parseInt(id));
            conf = dbConfrontantes.getConfrontantes(Integer.parseInt(id));
            prop = dbPropriedade.getPropriedade(Integer.parseInt(id));
            plant = dbPlantacoes.getPlantacoes(Integer.parseInt(id));
            regiao = dbRegiao.getRegiao(Integer.parseInt(id));

        }

        addInfoProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRegister();
                onBackPressed();
            }
        });

        cancelInfoProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!newRegister) {
            ArrayAdapter spinnerAdapter;

            spinnerAdapter = (ArrayAdapter) zoneamento.getAdapter();
            zoneamento.setSelection(spinnerAdapter.getPosition(prop.getZoneamento()));
            spinnerAdapter = (ArrayAdapter) topografia.getAdapter();
            topografia.setSelection(spinnerAdapter.getPosition(prop.getTopografia()));
            spinnerAdapter = (ArrayAdapter) manancial.getAdapter();
            manancial.setSelection(spinnerAdapter.getPosition(prop.getManancial()));
            spinnerAdapter = (ArrayAdapter) situacao.getAdapter();
            situacao.setSelection(spinnerAdapter.getPosition(prop.getSituacao()));
            if(prop.getRedeEletrica().equals("true"))
                eletrica.setChecked(true);
            else
                eletrica.setChecked(false);
            if(prop.getAbastecimentoAgua().equals("true"))
                agua.setChecked(true);
            else
                agua.setChecked(false);
            if(prop.getSinalTelefone().equals("true"))
                telefone.setChecked(true);
            else
                telefone.setChecked(false);

            if(regiao.getColetaLixo().equals("true"))
                lixo.setChecked(true);
            else
                lixo.setChecked(false);
            if(regiao.getTransporte().equals("true"))
                transporte.setChecked(true);
            else
                transporte.setChecked(false);
            if(regiao.getSaude().equals("true"))
                saude.setChecked(true);
            else
                saude.setChecked(false);
            if(regiao.getEscola().equals("true"))
                escola.setChecked(true);
            else
                escola.setChecked(false);
            if(regiao.getComercio().equals("true"))
                comercio.setChecked(true);
            else
                comercio.setChecked(false);
            if(regiao.getLazer().equals("true"))
                lazer.setChecked(true);
            else
                lazer.setChecked(false);
            if(regiao.getSeguranca().equals("true"))
                seguranca.setChecked(true);
            else
                seguranca.setChecked(false);
            spinnerAdapter = (ArrayAdapter) uso.getAdapter();
            uso.setSelection(spinnerAdapter.getPosition(regiao.getUso()));

            direita.setText(conf.getDireita());
            esquerda.setText(conf.getEsquerda());
            frente.setText(conf.getFrente());
            fundos.setText(conf.getFundos());

            if(benf.getConstrucoes().equals("true"))
                contrucoes.setChecked(true);
            else
                contrucoes.setChecked(false);
            construcoesText.setText(benf.getConstucoesText());

            if(benf.getEquipamentos().equals("true"))
                equipamentos.setChecked(true);
            else
                equipamentos.setChecked(false);
            equipamentosText.setText(benf.getEquipamentosText());

            if(benf.getCroquis().equals("true"))
                croquis.setChecked(true);
            else
                croquis.setChecked(false);
            croquisText.setText(benf.getCroquisText());

            if (benf.getPlantacoes().equals("true"))
                plantacoes.setChecked(true);
            else
                plantacoes.setChecked(false);

            tipo.setText(plant.getTipo());
            idade.setText(plant.getIdade());
            compl.setText(plant.getCompl());
            obs.setText(plant.getObs());

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(infoPropActivity.this, addNewRegisterActivity.class);
        intent.putExtra("isNew", "false");
        intent.putExtra("tipo", tipoRegister);
        intent.putExtra("id", newID.toString());
        startActivity(intent);
        finish();
    }

    public void saveRegister() {
        if(newID != 0){
            prop.setZoneamento(zoneamento.getSelectedItem().toString());
            prop.setTopografia(topografia.getSelectedItem().toString());
            prop.setManancial(manancial.getSelectedItem().toString());
            prop.setSituacao(situacao.getSelectedItem().toString());
            prop.setRedeEletrica(String.valueOf(eletrica.isChecked()));
            prop.setAbastecimentoAgua(String.valueOf(agua.isChecked()));
            prop.setSinalTelefone(String.valueOf(telefone.isChecked()));
            prop.setId(newID);

            regiao.setColetaLixo(String.valueOf(lixo.isChecked()));
            regiao.setTransporte(String.valueOf(transporte.isChecked()));
            regiao.setSaude(String.valueOf(saude.isChecked()));
            regiao.setEscola(String.valueOf(escola.isChecked()));
            regiao.setComercio(String.valueOf(comercio.isChecked()));
            regiao.setLazer(String.valueOf(lazer.isChecked()));
            regiao.setSeguranca(String.valueOf(seguranca.isChecked()));
            regiao.setUso(uso.getSelectedItem().toString());
            regiao.setId(newID);

            conf.setDireita(direita.getText().toString());
            conf.setEsquerda(esquerda.getText().toString());
            conf.setFrente(frente.getText().toString());
            conf.setFundos(fundos.getText().toString());
            conf.setId(newID);

            benf.setConstrucoes(String.valueOf(contrucoes.isChecked()));
            benf.setConstucoesText(construcoesText.getText().toString());
            benf.setEquipamentos(String.valueOf(equipamentos.isChecked()));
            benf.setEquipamentosText(equipamentosText.getText().toString());

            benf.setCroquis(String.valueOf(croquis.isChecked()));
            benf.setCroquisText(croquisText.getText().toString());

            benf.setPlantacoes(String.valueOf(plantacoes.isChecked()));

            benf.setId(newID);

            plant.setTipo(tipo.getText().toString());
            plant.setIdade(idade.getText().toString());
            plant.setCompl(compl.getText().toString());
            plant.setObs(obs.getText().toString());
            plant.setId(newID);


            dbPropriedade.addPropriedade(prop);
            dbRegiao.addRegiao(regiao);
            dbConfrontantes.addConfrontantes(conf);
            dbBenfeitorias.addBenf(benf);
            dbPlantacoes.addPlantacoes(plant);

        }

    }
}
