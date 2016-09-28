package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;

public class AltaReservaActivity extends AppCompatActivity {

    TextView datosDepto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        datosDepto = (TextView) findViewById(R.id.datosAltaDepto);

        Departamento depto = (Departamento) getIntent().getSerializableExtra("Departamento");

        datosDepto.setText("Usted alquilará el departamento " + depto.getDescripcion());
    }
}
