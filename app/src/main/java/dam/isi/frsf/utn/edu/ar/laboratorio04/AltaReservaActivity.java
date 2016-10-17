package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
//import android.icu.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
//import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AltaReservaActivity extends AppCompatActivity {

    TextView datosDepto;
    Button botonReservar;
    EditText fecha_inicio;
    EditText fecha_fin;
    Reserva res;
    NotificationManager mNotificationManager;
    private PendingIntent pendingIntent;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        res = new Reserva();

        datosDepto = (TextView) findViewById(R.id.datosAltaDepto);
        botonReservar = (Button) findViewById(R.id.reservar);
        fecha_inicio = (EditText) findViewById(R.id.fecha_inicio);
        fecha_fin = (EditText) findViewById(R.id.fecha_fin);

        Departamento depto = (Departamento) getIntent().getSerializableExtra("Departamento");
        res.setAlojamiento(depto);

        final Intent alarmIntent = new Intent(AltaReservaActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(AltaReservaActivity.this, 0, alarmIntent, 0);

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
                Toast.makeText(getApplicationContext(), "Se ha reservado el alojamiento", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), ListaReservaActivity.class);



                startActivityForResult(intent, 0);


               /* AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                int interval = 2000;

                manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
                Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_SHORT).show();*/

                final AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                //int interval = 1000 * 60 * 20;

                 /* Set the alarm to start at 10:30 AM */
                final Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 30);


                alarmIntent.putExtra("Reserva", res);
                /* Repetir cada 15 segundps */
                // Despues de android 5.1 no se pueden generar notificaciones con intervalos de menos de 1 minuto
                //manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 1 * 2, pendingIntent);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                       // manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        sendBroadcast(alarmIntent);

                        //Do something after 20 seconds
                        handler.postDelayed(this, 15000);
                    }
                }, 2000);  //the time is in miliseconds


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void abrirDatepicker(final EditText elemento) {
        // Para mostrar la fecha actual en el datepicker.
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(AltaReservaActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Seleccionar Fecha");
        mDatePicker.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AltaReserva Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://dam.isi.frsf.utn.edu.ar.laboratorio04/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AltaReserva Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://dam.isi.frsf.utn.edu.ar.laboratorio04/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
