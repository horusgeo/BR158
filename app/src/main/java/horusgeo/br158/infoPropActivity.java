package horusgeo.br158;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    EditText tipo;
    EditText idade;
    EditText compl;
    EditText obs;


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

        tipo = (EditText) findViewById(R.id.tipoPlantacaoText);
        idade = (EditText) findViewById(R.id.idadePlantacaoText);
        compl = (EditText) findViewById(R.id.complPlantacaoText);
        obs = (EditText) findViewById(R.id.obsInfoPropText);



    }
}
