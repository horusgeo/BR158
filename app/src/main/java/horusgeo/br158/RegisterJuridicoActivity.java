package horusgeo.br158;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterJuridicoActivity extends AppCompatActivity {

    EditText nomeEmpText;
    EditText cnpjEmpText;
    EditText contatoEmpText;
    EditText emailEmpText;
    EditText tel1EmpText;
    EditText tel2EmpText;

    EditText nomeRepLegalText;
    EditText nacioRepLegalText;
    EditText profRepLegalText;
    EditText idRepLegalText;
    EditText tipoRepLegalText;
    EditText cpfRepLegalText;
    EditText emailRepLegalText;
    EditText tel1RepLegalText;
    Spinner spinnerEstadoCivilRepLegal;

    EditText ruaEmpText;
    EditText numEmpText;
    EditText complEmpText;
    EditText bairroEmpText;
    EditText cepEmpText;
    EditText munEmpText;
    EditText ufEmpText;
    EditText pRefEmpText;

    EditText ruaRepLegalText;
    EditText numRepLegalText;
    EditText complRepLegalText;
    EditText bairroRepLegalText;
    EditText cepRepLegalText;
    EditText munRepLegalText;
    EditText ufRepLegalText;

    EditText ruaObjJurText;
    EditText numObjJurText;
    EditText complObjJurText;
    EditText bairroObjJurText;
    EditText cepObjJurText;
    EditText pRefObjJurText;

    EditText roteiroJuridicoText;

    Person repLegal = new Person();
    Empresa emp = new Empresa();
    Endereco endRepLegal = new Endereco();
    Endereco endObj = new Endereco();
    Endereco endEmp = new Endereco();
    Roteiro roteiro = new Roteiro();

    DBEmpresa dbEmpresa;
    DBEndEmpresa dbEndEmpresa;
    DBEndPerson dbEndPerson;
    DBEndObj dbEndObj;
    DBRoteiroAcesso dbRoteiroAcesso;
    DBJuridico dbJuridico;


    Boolean newRegister = true;

    FloatingActionButton addJuridico;
    FloatingActionButton cancelJuridico;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_juridico);

        nomeEmpText = (EditText) findViewById(R.id.nomeEmpText);
        cnpjEmpText = (EditText) findViewById(R.id.cnpjEmpText);
        contatoEmpText = (EditText) findViewById(R.id.contatoEmpText);
        emailEmpText = (EditText) findViewById(R.id.emailEmpText);
        tel1EmpText = (EditText) findViewById(R.id.tel1EmpText);
        tel2EmpText = (EditText) findViewById(R.id.tel2EmpText);

        nomeRepLegalText = (EditText) findViewById(R.id.nomeRepLegalText);
        nacioRepLegalText = (EditText) findViewById(R.id.nacioRepLegalText);
        profRepLegalText = (EditText) findViewById(R.id.profRepLegalText);
        idRepLegalText = (EditText) findViewById(R.id.idRepLegalText);
        tipoRepLegalText = (EditText) findViewById(R.id.tipoRepLegalText);
        cpfRepLegalText = (EditText) findViewById(R.id.cpfRepLegalText);
        emailRepLegalText = (EditText) findViewById(R.id.emailRepLegalText);
        tel1RepLegalText = (EditText) findViewById(R.id.tel1RepLegalText);
        spinnerEstadoCivilRepLegal = (Spinner) findViewById(R.id.spinnerEstadoCivilRepLegal);

        ruaEmpText = (EditText) findViewById(R.id.ruaEmpText);
        numEmpText = (EditText) findViewById(R.id.numEmpText);
        complEmpText = (EditText) findViewById(R.id.complEmpText);
        bairroEmpText = (EditText) findViewById(R.id.bairroEmpText);
        cepEmpText = (EditText) findViewById(R.id.cepEmpText);
        munEmpText = (EditText) findViewById(R.id.munEmpText);
        ufEmpText = (EditText) findViewById(R.id.ufEmpText);
        pRefEmpText = (EditText) findViewById(R.id.pRefEmpText);

        ruaRepLegalText = (EditText) findViewById(R.id.ruaRepLegalText);
        numRepLegalText = (EditText) findViewById(R.id.numRepLegalText);
        complRepLegalText = (EditText) findViewById(R.id.complRepLegalText);
        bairroRepLegalText = (EditText) findViewById(R.id.bairroRepLegalText);
        cepRepLegalText = (EditText) findViewById(R.id.cepRepLegalText);
        munRepLegalText = (EditText) findViewById(R.id.munRepLegalText);
        ufRepLegalText = (EditText) findViewById(R.id.ufRepLegalText);

        ruaObjJurText = (EditText) findViewById(R.id.ruaObjJurText);
        numObjJurText = (EditText) findViewById(R.id.numObjJurText);
        complObjJurText = (EditText) findViewById(R.id.complObjJurText);
        bairroObjJurText = (EditText) findViewById(R.id.bairroObjJurText);
        cepObjJurText = (EditText) findViewById(R.id.cepObjJurText);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        final String id = intent.getStringExtra("id");

        dbEmpresa = new DBEmpresa(this, null, null, 1);
        dbJuridico = new DBJuridico(this, null, null, 1);
        dbEndEmpresa =  new DBEndEmpresa(this, null, null, 1);
        dbEndPerson = new DBEndPerson(this, null, null, 1);
        dbEndObj = new DBEndObj(this, null, null, 1);
        dbRoteiroAcesso = new DBRoteiroAcesso(this, null, null, 1);

        if(isNew.equals("false")){
            newRegister = false;
            emp = dbEmpresa.getEmpresa(Integer.parseInt(id));
            repLegal = dbJuridico.getJur(Integer.parseInt(id));
            endEmp = dbEndEmpresa.getEndEmpresa(Integer.parseInt(id));
            endRepLegal = dbEndPerson.getEndPerson(Integer.parseInt(id));
            endObj = dbEndObj.getEndObj(Integer.parseInt(id));
            roteiro = dbRoteiroAcesso.getRoteiro(Integer.parseInt(id));
        }

        addJuridico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRegister();
            }
        });

        cancelJuridico.setOnClickListener(new View.OnClickListener() {
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

            nomeEmpText.setText(emp.getNome());
            cnpjEmpText.setText(emp.getCnpj());
            contatoEmpText.setText(emp.getContato());
            emailEmpText.setText(emp.getEmail());
            tel1EmpText.setText(emp.getTel1());
            tel2EmpText.setText(emp.getTel2());

            nomeRepLegalText.setText(repLegal.getNome());
            nacioRepLegalText.setText(repLegal.getNacionalidade());
            profRepLegalText.setText(repLegal.getProfissao());
            idRepLegalText.setText(repLegal.getDocId());
            tipoRepLegalText.setText(repLegal.getDocTipo());
            cpfRepLegalText.setText();
            emailRepLegalText.setText();
            tel1RepLegalText.setText();

            ruaEmpText.setText();
            numEmpText.setText();
            complEmpText.setText();
            bairroEmpText.setText();
            cepEmpText.setText();
            munEmpText.setText();
            ufEmpText.setText();
            pRefEmpText.setText();

            ruaRepLegalText.setText();
            numRepLegalText.setText();
            complRepLegalText.setText();
            bairroRepLegalText.setText();
            cepRepLegalText.setText();
            munRepLegalText.setText();
            ufRepLegalText.setText();

            ruaObjJurText.setText();
            numObjJurText.setText();
            complObjJurText.setText();
            bairroObjJurText.setText();
            cepObjJurText.setText();
            pRefObjJurText.setText();

        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RegisterJuridicoActivity.this, addNewRegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveRegister() {


    }

}
