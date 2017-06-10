package horusgeo.br158;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;


public class RegisterActivity extends AppCompatActivity {

    EditText nomePropText;
    EditText nacioPropText;
    EditText profPropText;
    EditText idPropText;
    EditText tipoPropText;
    EditText cpfPropText;
    EditText emailPropText;
    EditText tel1PropText;
    EditText tel2PropText;

    EditText nomeConjText;
    EditText nacioConjText;
    EditText profConjText;
    EditText idConjText;
    EditText tipoConjText;
    EditText cpfConjText;
    EditText tel1ConjText;

    EditText ruaResText;
    EditText numResText;
    EditText complResText;
    EditText bairroResText;
    EditText cepResText;
    EditText munResText;
    EditText ufResText;
    EditText comResText;
    EditText ufComResText;
    EditText pRefResText;

    EditText ruaObjText;
    EditText numObjText;
    EditText complObjText;
    EditText bairroObjText;
    EditText cepObjText;
    EditText pRefObjText;

    EditText roteiroFisicaText;

    Spinner spinnerEstadoCivil;

    Switch switchPropPoss;

    Person prop = new Person();
    Person conj = new Person();
    Endereco endRes = new Endereco();
    Endereco endObj = new Endereco();
    Roteiro roteiro = new Roteiro();

    DBProprietario dbProprietario;
    DBConjuge dbConjuge;
    DBEndPerson dbEndPerson;
    DBEndObj dbEndObj;
    DBRoteiroAcesso dbRoteiroAcesso;

    Boolean newRegister = true;
    Integer newID = 0;

    FloatingActionButton addRegister;
    FloatingActionButton cancelRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomePropText = (EditText) findViewById(R.id.nomePropText);
        nacioPropText = (EditText) findViewById(R.id.nacioPropText);
        profPropText = (EditText) findViewById(R.id.profPropText);
        idPropText = (EditText) findViewById(R.id.idPropText);
        tipoPropText = (EditText) findViewById(R.id.tipoPropText);
        cpfPropText = (EditText) findViewById(R.id.cpfPropText);
        emailPropText = (EditText) findViewById(R.id.emailPropText);
        tel1PropText = (EditText) findViewById(R.id.tel1PropText);
        tel2PropText = (EditText) findViewById(R.id.tel2PropText);

        nomeConjText = (EditText) findViewById(R.id.nomeConjText);
        nacioConjText = (EditText) findViewById(R.id.nacioConjText);
        profConjText = (EditText) findViewById(R.id.profConjText);
        idConjText = (EditText) findViewById(R.id.idConjText);
        tipoConjText = (EditText) findViewById(R.id.tipoConjText);
        cpfConjText = (EditText) findViewById(R.id.cpfConjText);
        tel1ConjText = (EditText) findViewById(R.id.tel1ConjText);

        ruaResText = (EditText) findViewById(R.id.ruaResText);
        numResText = (EditText) findViewById(R.id.numResText);
        complResText = (EditText) findViewById(R.id.complResText);
        bairroResText = (EditText) findViewById(R.id.bairroResText);
        cepResText = (EditText) findViewById(R.id.cepResText);
        munResText = (EditText) findViewById(R.id.munResText);
        ufResText = (EditText) findViewById(R.id.ufResText);
        comResText = (EditText) findViewById(R.id.comResText);
        ufComResText = (EditText) findViewById(R.id.ufComResText);
        pRefResText = (EditText) findViewById(R.id.pRefResText);

        ruaObjText = (EditText) findViewById(R.id.ruaObjText);
        numObjText = (EditText) findViewById(R.id.numObjText);
        complObjText = (EditText) findViewById(R.id.complObjText);
        bairroObjText = (EditText) findViewById(R.id.bairroObjText);
        cepObjText = (EditText) findViewById(R.id.cepObjText);
        pRefObjText = (EditText) findViewById(R.id.pRefObjText);

        roteiroFisicaText = (EditText) findViewById(R.id.roteiroFisicaText);

        spinnerEstadoCivil = (Spinner) findViewById(R.id.spinnerEstadoCivil);

        switchPropPoss = (Switch) findViewById(R.id.switchPropPoss);

        addRegister = (FloatingActionButton) findViewById(R.id.registerAddButton);
        cancelRegister = (FloatingActionButton) findViewById(R.id.registerCancelButton);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        final String id = intent.getStringExtra("id");

        newID = Integer.parseInt(id);

        dbProprietario = new DBProprietario(this, null, null, 1);
        dbConjuge = new DBConjuge(this, null, null, 1);
        dbEndPerson = new DBEndPerson(this, null, null, 1);
        dbEndObj = new DBEndObj(this, null, null, 1);
        dbRoteiroAcesso = new DBRoteiroAcesso(this, null, null, 1);

        if(isNew.equals("false")){
            newRegister = false;
            prop = dbProprietario.getProp(Integer.parseInt(id));
            conj = dbConjuge.getConj(Integer.parseInt(id));
            endRes = dbEndPerson.getEndPerson(Integer.parseInt(id));
            endObj = dbEndObj.getEndObj(Integer.parseInt(id));
            roteiro = dbRoteiroAcesso.getRoteiro(Integer.parseInt(id));
        }

        addRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRegister();
                onBackPressed();
            }
        });

        cancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    protected void onResume(){
        super.onResume();

        if(!newRegister){

            nomePropText.setText(prop.getNome());
            nacioPropText.setText(prop.getNacionalidade());
            profPropText.setText(prop.getProfissao());
            idPropText.setText(prop.getDocId());
            tipoPropText.setText(prop.getDocTipo());
            cpfPropText.setText(prop.getCpf());
            emailPropText.setText(prop.getEmail());
            tel1PropText.setText(prop.getTel1());
            tel2PropText.setText(prop.getTel2());

            nomeConjText.setText(conj.getNome());
            nacioConjText.setText(conj.getNacionalidade());
            profConjText.setText(conj.getProfissao());
            idConjText.setText(conj.getDocId());
            tipoConjText.setText(conj.getDocTipo());
            cpfConjText.setText(conj.getCpf());
            tel1ConjText.setText(conj.getTel1());

            ruaResText.setText(endRes.getRua());
            numResText.setText(endRes.getNum());
            complResText.setText(endRes.getCompl());
            bairroResText.setText(endRes.getBairro());
            cepResText.setText(endRes.getCep());
            munResText.setText(endRes.getMunicipio());
            ufResText.setText(endRes.getUf1());
            comResText.setText(endRes.getComarca());
            ufComResText.setText(endRes.getUf2());
            pRefResText.setText(endRes.getpRef());

            ruaObjText.setText(endObj.getRua());
            numObjText.setText(endObj.getNum());
            complObjText.setText(endObj.getCompl());
            bairroObjText.setText(endObj.getBairro());
            cepObjText.setText(endObj.getCep());
            pRefObjText.setText(endObj.getpRef());

            roteiroFisicaText.setText(roteiro.getRoteiro());

            ArrayAdapter spinnerAdapter = (ArrayAdapter) spinnerEstadoCivil.getAdapter();

            spinnerEstadoCivil.setSelection(spinnerAdapter.getPosition(prop.getEstadoCivil()));

            Boolean propPoss = false;
            if(prop.getPossProp().equals("true"))
                propPoss = true;

            switchPropPoss.setSelected(propPoss);

        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RegisterActivity.this, addNewRegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveRegister(){

        if(nomePropText.getText().length() > 0) {

            Integer id = newID;
            if(newRegister)
                id = dbProprietario.getNewId();

            prop.setNome(nomePropText.getText().toString());
            prop.setNacionalidade(nacioPropText.getText().toString());
            prop.setProfissao(profPropText.getText().toString());
            prop.setDocId(idPropText.getText().toString());
            prop.setDocTipo(tipoPropText.getText().toString());
            prop.setCpf(cpfPropText.getText().toString());
            prop.setEmail(emailPropText.getText().toString());
            prop.setTel1(tel1PropText.getText().toString());
            prop.setTel2(tel2PropText.getText().toString());
            prop.setId(id);

            conj.setNome(nomeConjText.getText().toString());
            conj.setNacionalidade(nacioConjText.getText().toString());
            conj.setProfissao(profConjText.getText().toString());
            conj.setDocId(idConjText.getText().toString());
            conj.setDocTipo(tipoConjText.getText().toString());
            conj.setCpf(cpfConjText.getText().toString());
            conj.setTel1(tel1ConjText.getText().toString());
            conj.setId(id);

            endRes.setRua(ruaResText.getText().toString());
            endRes.setNum(numResText.getText().toString());
            endRes.setCompl(complResText.getText().toString());
            endRes.setBairro(bairroResText.getText().toString());
            endRes.setCep(cepResText.getText().toString());
            endRes.setMunicipio(munResText.getText().toString());
            endRes.setUf1(ufResText.getText().toString());
            endRes.setComarca(comResText.getText().toString());
            endRes.setUf2(ufComResText.getText().toString());
            endRes.setpRef(pRefResText.getText().toString());
            endRes.setId(id);

            endObj.setRua(ruaObjText.getText().toString());
            endObj.setNum(numObjText.getText().toString());
            endObj.setCompl(complObjText.getText().toString());
            endObj.setBairro(bairroObjText.getText().toString());
            endObj.setCep(cepObjText.getText().toString());
            endObj.setpRef(pRefObjText.getText().toString());
            endObj.setId(id);

            roteiro.setRoteiro(roteiroFisicaText.getText().toString());
            roteiro.setId(id);

            prop.setEstadoCivil(spinnerEstadoCivil.getSelectedItem().toString());

            prop.setPossProp("false");
            if (switchPropPoss.isChecked())
                prop.setPossProp("true");




            dbProprietario.addProp(prop);
            dbConjuge.addConj(conj);
            dbEndPerson.addEndPerson(endRes);
            dbEndObj.addEndObj(endObj);
            dbRoteiroAcesso.addRoteiro(roteiro);
        } else {
            nomePropText.setError(getString(R.string.empty_text));
            nomePropText.requestFocus();
        }
    }

}
