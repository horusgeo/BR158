package horusgeo.br158;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

class ListRegister{
    private int id;
    private String name;

    ListRegister(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class MainActivity extends AppCompatActivity {

    List<ListRegister> listProp = new ArrayList<>();
    List<ListRegister> listEmp = new ArrayList<>();

    DBEmpresa dbEmpresa;
    DBProprietario dbProprietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbProprietario = new DBProprietario(this, null, null, 1);
        dbEmpresa = new DBEmpresa(this, null, null, 1);

        listProp.addAll(dbProprietario.getAllIdNames());
        listEmp.addAll(dbEmpresa.getAllIdNames());

    }
}
