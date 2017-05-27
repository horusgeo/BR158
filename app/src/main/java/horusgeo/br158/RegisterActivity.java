package horusgeo.br158;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        final String id = intent.getStringExtra("id");

        dbProprietario = new DBProprietario(this, null, null, 1);
        dbConjuge = new DBConjuge(this, null, null, 1);
        dbEndPerson = new DBEndPerson(this, null, null, 1);
        dbEndObj = new DBEndObj(this, null, null, 1);
        dbRoteiroAcesso = new DBRoteiroAcesso(this, null, null, 1);

        if(isNew.equals("false")){
            prop = dbProprietario.getProp(Integer.parseInt(id));
            conj = dbConjuge.getConj(Integer.parseInt(id));
            endRes = dbEndPerson.getEndPerson(Integer.parseInt(id));
            endObj = dbEndObj.getEndObj(Integer.parseInt(id));
            roteiro = dbRoteiroAcesso.getRoteiro(Integer.parseInt(id));
        }


    }

}
