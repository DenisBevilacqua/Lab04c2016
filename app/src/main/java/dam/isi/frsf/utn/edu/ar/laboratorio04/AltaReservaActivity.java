package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AltaReservaActivity extends AppCompatActivity {

    TextView datosDepto;
    Button botonReservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        final Reserva res = new Reserva();

        datosDepto = (TextView) findViewById(R.id.datosAltaDepto);
        botonReservar = (Button)findViewById(R.id.reservar);

        Departamento depto = (Departamento) getIntent().getSerializableExtra("Departamento");
        res.setAlojamiento(depto);

        datosDepto.setText("Usted alquilará el departamento " + depto.getDescripcion());

        botonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaReservaActivity.reservas.add(res);
                Toast.makeText(getApplicationContext(), "Se ha reservado el alojamiento",Toast.LENGTH_LONG).show();
            }
        });
    }
}
