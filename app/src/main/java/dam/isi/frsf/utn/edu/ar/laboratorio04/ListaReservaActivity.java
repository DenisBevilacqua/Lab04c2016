package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class ListaReservaActivity extends AppCompatActivity {

    public static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private ListView listViewReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reserva);

        listViewReservas = (ListView) findViewById(R.id.listReservas);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ReservaAdapter adapter = new ReservaAdapter(ListaReservaActivity.this,reservas);
        listViewReservas.setAdapter(adapter);

    }
}
