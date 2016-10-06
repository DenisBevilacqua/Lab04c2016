package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.net.ParseException;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AltaReservaActivity extends AppCompatActivity {

    TextView datosDepto;
    Button botonReservar;
    EditText fecha_inicio;
    EditText fecha_fin;
    Reserva res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        res = new Reserva();

        datosDepto = (TextView) findViewById(R.id.datosAltaDepto);
        botonReservar = (Button)findViewById(R.id.reservar);
        fecha_inicio = (EditText)findViewById(R.id.fecha_inicio);
        fecha_fin = (EditText)findViewById(R.id.fecha_fin);

        Departamento depto = (Departamento) getIntent().getSerializableExtra("Departamento");
        res.setAlojamiento(depto);

        datosDepto.setText("Usted alquilará el departamento " + depto.getDescripcion());

        setTitle("Realizar reserva");

        fecha_inicio.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                abrirDatepicker(fecha_inicio);

                }
        });

        fecha_fin.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                abrirDatepicker(fecha_fin);

            }
        });

        botonReservar.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {



               String dtStart = fecha_inicio.getText().toString();

                res.setFechaInicio(dtStart);


                String dtStart2 = fecha_fin.getText().toString();

                res.setFechaFin(dtStart2);


                //res.setFechaInicio();
                ListaReservaActivity.reservas.add(res);
                Toast.makeText(getApplicationContext(), "Se ha reservado el alojamiento",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), ListaReservaActivity.class);

                startActivityForResult(intent, 0);

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void abrirDatepicker(final EditText elemento){
        // Para mostrar la fecha actual en el datepicker.
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear=mcurrentDate.get(Calendar.YEAR);
        int mMonth=mcurrentDate.get(Calendar.MONTH);
        int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(AltaReservaActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub

                elemento.setText(selectedday + "/" + selectedmonth + "/" + selectedyear);

                String dtStart = elemento.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = format.parse(dtStart);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }


            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Seleccionar Fecha");
        mDatePicker.show();
    }
}
