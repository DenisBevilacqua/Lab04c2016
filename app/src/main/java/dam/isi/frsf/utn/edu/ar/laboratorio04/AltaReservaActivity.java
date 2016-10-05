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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        final Reserva res = new Reserva();

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

                Calendar c = new GregorianCalendar();
                c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                Date d1 = c.getTime();

               /* String dtStart = fecha_inicio.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");*/
                try {
                    /*Date date = format.parse(dtStart);
                    res.setFechaInicio(date);*/
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();
                    res.setFechaFin(d1);

                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();
                    res.setFechaInicio(d1);

                    e.printStackTrace();
                }

                /*String dtStart2 = fecha_fin.getText().toString();
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");*/

                try {
                    /*Date date = format.parse(dtStart);
                    res.setFechaFin(date);*/
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();
                    res.setFechaFin(d1);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();
                    res.setFechaFin(d1);

                    e.printStackTrace();
                }

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
                    /*      Your code   to get date and time    */
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Seleccionar Fecha");
        mDatePicker.show();
    }
}
