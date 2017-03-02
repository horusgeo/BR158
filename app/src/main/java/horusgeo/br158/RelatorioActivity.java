package horusgeo.br158;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

public class RelatorioActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {

    ImageButton dataRelatorioButtom;
    ImageButton horarioChegadaRelatorioButtom;
    ImageButton horarioSaidaRelatorioButtom;

    Calendar dataCalendar = Calendar.getInstance();
    Calendar horarioChegadaCalendar = Calendar.getInstance();
    Calendar horarioSaidaCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        dataRelatorioButtom = (ImageButton) findViewById(R.id.dataRelatorioButton);
        horarioChegadaRelatorioButtom = (ImageButton) findViewById(R.id.horarioChegadaRelatorioButton);
        horarioSaidaRelatorioButtom = (ImageButton) findViewById(R.id.horarioSaidaRelatorioButton);

        dataRelatorioButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(RelatorioActivity.this, null, dataCalendar.get(Calendar.YEAR),
                        dataCalendar.get(Calendar.MONTH),
                        dataCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();

            }
        });

        horarioChegadaRelatorioButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(RelatorioActivity.this, null, horarioChegadaCalendar.get(Calendar.HOUR_OF_DAY),
                        horarioChegadaCalendar.get(Calendar.MINUTE), true)
                        .show();
            }
        });

        horarioSaidaRelatorioButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(RelatorioActivity.this, null, horarioSaidaCalendar.get(Calendar.HOUR_OF_DAY),
                        horarioSaidaCalendar.get(Calendar.MINUTE), true)
                        .show();
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        // TODO Auto-generated method stub
        dataCalendar.set(Calendar.YEAR, year);
        dataCalendar.set(Calendar.MONTH, monthOfYear);
        dataCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    }




}
