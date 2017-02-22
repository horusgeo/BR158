package horusgeo.br158;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class RegisterActivity extends AppCompatActivity {

    Spinner spinnerEstadoCivil;


    ArrayAdapter<CharSequence> adapterEstadoCivil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        spinnerEstadoCivil = (Spinner) findViewById(R.id.spinnerEstadoCivil);
        adapterEstadoCivil = ArrayAdapter.createFromResource(this, R.array.estadoCivil, android.R.layout.simple_spinner_item);
        adapterEstadoCivil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstadoCivil.setAdapter(adapterEstadoCivil);
    }

}
