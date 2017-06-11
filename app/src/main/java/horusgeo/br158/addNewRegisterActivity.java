package horusgeo.br158;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class addNewRegisterActivity extends AppCompatActivity {

    Button registerButton;
    Button empButton;
    Button propButton;
    Button fotoButton;
    Button relatorioButton;
    Button saveButton;

    DBProprietario dbProprietario;
    DBEmpresa dbEmpresa;
    DBStatus dbStatus;

    TextView nomeText;

    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_register);

        Intent intent = getIntent();

        final String isNew = intent.getStringExtra("isNew");
        final String tipo = intent.getStringExtra("tipo");
        id = Integer.parseInt(intent.getStringExtra("id"));

        dbProprietario = new DBProprietario(this, null, null, 1);
        dbEmpresa = new DBEmpresa(this, null, null, 1);
        dbStatus = new DBStatus(this, null, null, 1);

        nomeText = (TextView) findViewById(R.id.textNomeProp);

        registerButton = (Button) findViewById(R.id.buttonRegister);
        empButton = (Button) findViewById(R.id.buttonJuridico);
        propButton = (Button) findViewById(R.id.buttonPropriedade);
        relatorioButton = (Button) findViewById(R.id.buttonRelatorio);
        fotoButton = (Button) findViewById(R.id.buttonFotos);
        saveButton = (Button) findViewById(R.id.buttonSave);

        if(isNew.equals("true")){
            if (tipo.equals("juridica")){
                nomeText.setText("Pessoa Jurídica");
                registerButton.setVisibility(View.INVISIBLE);
                registerButton.setEnabled(false);
                empButton.setVisibility(View.VISIBLE);
                empButton.setEnabled(true);
            }
        } else {
            if (tipo.equals("fisica")) {
                nomeText.setText(dbProprietario.getName(id));
            } else {
                nomeText.setText(dbEmpresa.getName(id));
                registerButton.setVisibility(View.INVISIBLE);
                empButton.setVisibility(View.VISIBLE);
                registerButton.setEnabled(false);
                empButton.setEnabled(true);
            }
        }


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, RegisterActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();

            }
        });

        empButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, RegisterJuridicoActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        propButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, infoPropActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });



        relatorioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, RelatorioActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        fotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNewRegisterActivity.this, FotosActivity.class);
                intent.putExtra("isNew", isNew);
                intent.putExtra("tipo", tipo);
                intent.putExtra("id", id.toString());
                startActivity(intent);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }

    @Override
    public void onBackPressed(){
        dbStatus.setStatusChanged(id, 1);
        Intent intent = new Intent(addNewRegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
