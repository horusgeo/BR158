package horusgeo.br158;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner filterRegistersSpinner;

    //TODO: create functions for buttons
    Button returnToMapButton;
    Button addNewRegisterButton;

    //TODO: create functions for manipulating the list
    ListView registerList;

    ArrayAdapter<CharSequence> adapterFiltros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        filterRegistersSpinner = (Spinner) findViewById(R.id.filterRegistersSpinner);
        adapterFiltros = ArrayAdapter.createFromResource(this, R.array.filtros, android.R.layout.simple_spinner_item);
        adapterFiltros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterRegistersSpinner.setAdapter(adapterFiltros);
        //TODO: create function for filtering

    }
}
