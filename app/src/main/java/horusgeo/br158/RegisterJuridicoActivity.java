package horusgeo.br158;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    Integer newID = 0;
    String tipoRegister;

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

        roteiroJuridicoText = (EditText) findViewById(R.id.roteiroJuridicoText);

        addJuridico = (FloatingActionButton) findViewById(R.id.juridicoAddButton);
        cancelJuridico = (FloatingActionButton) findViewById(R.id.juridicoCancelButton);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        tipoRegister = intent.getStringExtra("tipo");
        final String id = intent.getStringExtra("id");

        newID = Integer.parseInt(id);

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
                onBackPressed();
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
            cpfRepLegalText.setText(repLegal.getCpf());
            emailRepLegalText.setText(repLegal.getEmail());
            tel1RepLegalText.setText(repLegal.getTel1());

            ruaEmpText.setText(endEmp.getRua());
            numEmpText.setText(endEmp.getNum());
            complEmpText.setText(endEmp.getCompl());
            bairroEmpText.setText(endEmp.getBairro());
            cepEmpText.setText(endEmp.getCep());
            munEmpText.setText(endEmp.getMunicipio());
            ufEmpText.setText(endEmp.getUf1());
            pRefEmpText.setText(endEmp.getpRef());

            ruaRepLegalText.setText(endRepLegal.getRua());
            numRepLegalText.setText(endRepLegal.getNum());
            complRepLegalText.setText(endRepLegal.getCompl());
            bairroRepLegalText.setText(endRepLegal.getBairro());
            cepRepLegalText.setText(endRepLegal.getCep());
            munRepLegalText.setText(endRepLegal.getMunicipio());
            ufRepLegalText.setText(endRepLegal.getUf1());

            ruaObjJurText.setText(endObj.getRua());
            numObjJurText.setText(endObj.getNum());
            complObjJurText.setText(endObj.getCompl());
            bairroObjJurText.setText(endObj.getBairro());
            cepObjJurText.setText(endObj.getCep());


            roteiroJuridicoText.setText(roteiro.getRoteiro());

            ArrayAdapter spinnerAdapter = (ArrayAdapter) spinnerEstadoCivilRepLegal.getAdapter();

            spinnerEstadoCivilRepLegal.setSelection(spinnerAdapter.getPosition(repLegal.getEstadoCivil()));

        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(RegisterJuridicoActivity.this, addNewRegisterActivity.class);
        intent.putExtra("isNew", "false");
        intent.putExtra("tipo", tipoRegister);
        intent.putExtra("id", newID.toString());
        startActivity(intent);
        finish();
    }

    public void saveRegister() {

        if(nomeEmpText.getText().length() > 0){

            emp.setNome(nomeEmpText.getText().toString());
            emp.setCnpj(cnpjEmpText.getText().toString());
            emp.setContato(contatoEmpText.getText().toString());
            emp.setEmail(emailEmpText.getText().toString());
            emp.setTel1(tel1EmpText.getText().toString());
            emp.setTel2(tel2EmpText.getText().toString());
            emp.setId(newID);

            repLegal.setNome(nomeRepLegalText.getText().toString());
            repLegal.setNacionalidade(nacioRepLegalText.getText().toString());
            repLegal.setProfissao(profRepLegalText.getText().toString());
            repLegal.setDocId(idRepLegalText.getText().toString());
            repLegal.setDocTipo(tipoRepLegalText.getText().toString());
            repLegal.setCpf(cpfRepLegalText.getText().toString());
            repLegal.setEmail(emailRepLegalText.getText().toString());
            repLegal.setTel1(tel1RepLegalText.getText().toString());
            repLegal.setId(newID);
            repLegal.setEstadoCivil(spinnerEstadoCivilRepLegal.getSelectedItem().toString());

            endEmp.setRua(ruaEmpText.getText().toString());
            endEmp.setNum(numEmpText.getText().toString());
            endEmp.setCompl(complEmpText.getText().toString());
            endEmp.setBairro(bairroEmpText.getText().toString());
            endEmp.setCep(cepEmpText.getText().toString());
            endEmp.setMunicipio(munEmpText.getText().toString());
            endEmp.setUf1(ufEmpText.getText().toString());
            endEmp.setpRef(pRefEmpText.getText().toString());
            endEmp.setId(newID);

            endRepLegal.setRua(ruaRepLegalText.getText().toString());
            endRepLegal.setNum(numRepLegalText.getText().toString());
            endRepLegal.setCompl(complRepLegalText.getText().toString());
            endRepLegal.setBairro(bairroRepLegalText.getText().toString());
            endRepLegal.setCep(cepRepLegalText.getText().toString());
            endRepLegal.setMunicipio(munRepLegalText.getText().toString());
            endRepLegal.setUf1(ufRepLegalText.getText().toString());
            endRepLegal.setId(newID);

            endObj.setRua(ruaObjJurText.getText().toString());
            endObj.setNum(numObjJurText.getText().toString());
            endObj.setCompl(complObjJurText.getText().toString());
            endObj.setBairro(bairroObjJurText.getText().toString());
            endObj.setCep(cepObjJurText.getText().toString());
            endObj.setId(newID);

            roteiro.setRoteiro(roteiroJuridicoText.getText().toString());
            roteiro.setId(newID);

            dbEmpresa.addEmpresa(emp);
            dbJuridico.addJur(repLegal);
            dbEndEmpresa.addEndEmpresa(endEmp);
            dbEndPerson.addEndPerson(endRepLegal);
            dbEndObj.addEndObj(endObj);
            dbRoteiroAcesso.addRoteiro(roteiro);

        } else {
            nomeEmpText.setError(getString(R.string.empty_text));
            nomeEmpText.requestFocus();
        }




    }

}
