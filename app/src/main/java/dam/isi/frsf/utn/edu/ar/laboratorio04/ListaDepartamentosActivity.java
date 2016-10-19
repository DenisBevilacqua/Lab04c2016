package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

public class ListaDepartamentosActivity extends AppCompatActivity implements BusquedaFinalizadaListener<Departamento> {

    private TextView tvEstadoBusqueda;
    private ListView listaAlojamientos;
    private DepartamentoAdapter departamentosAdapter;
    private List<Departamento> lista;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamientos);
        lista= new ArrayList<>();
        listaAlojamientos= (ListView ) findViewById(R.id.listaAlojamientos);
        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);

        setTitle("Listado de departamentos");

        registerForContextMenu(listaAlojamientos);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Boolean esBusqueda = intent.getExtras().getBoolean("esBusqueda");
        if(esBusqueda){
            FormBusqueda fb = (FormBusqueda ) intent.getSerializableExtra("frmBusqueda");
            new BuscarDepartamentosTask(ListaDepartamentosActivity.this).execute(fb);
            tvEstadoBusqueda.setText("Buscando....");
            tvEstadoBusqueda.setVisibility(View.VISIBLE);
        }else{
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista=Departamento.getAlojamientosDisponibles();
        }
        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this,lista);
        listaAlojamientos.setAdapter(departamentosAdapter);

        user = (Usuario) intent.getSerializableExtra("Usuario");
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.listaAlojamientos)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

            Departamento dep = (Departamento)  listaAlojamientos.getAdapter().getItem(info.position);

            menu.setHeaderTitle("Opciones para " + dep.getDescripcion());
        }

        inflater.inflate(R.menu.reservar,menu);

        // Get the list
        ListView list = (ListView) v;

        // Get the list item position
        /*AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;*/

        // Now you can do whatever.. (Example, load different menus for different items)
        //list.getItem(position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {


            case R.id.compartir:

                /*Toast.makeText(this, "Compartiendo",
                        Toast.LENGTH_LONG).show();*/

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Me interesa el departamento '" + listaAlojamientos.getAdapter().getItem(info.position).toString() + "'. Enviado desde Lab04c2016");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;

            case R.id.reservar:

                //TODO

                Intent intent = new Intent(this, AltaReservaActivity.class);

                Departamento dep = (Departamento) listaAlojamientos.getAdapter().getItem(info.position);

                intent.putExtra("Departamento", dep);

                intent.putExtra("Usuario", user);
                startActivityForResult(intent, 0);

                /*Departamento dep = (Departamento) listaAlojamientos.getAdapter().getItem(info.position);
                Reserva res = new Reserva();
                res.setAlojamiento(dep);*/

                /*Toast.makeText(this, "Usted se ha postulado a la oferta de trabajo " + listaAlojamientos.getAdapter().getItem(info.position).toString(),
                        Toast.LENGTH_LONG).show();*/


        }

        return true;
    }

    @Override
    public void busquedaFinalizada(List<Departamento> listaDepartamento) {
        tvEstadoBusqueda.setVisibility(View.GONE);
        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this,listaDepartamento);
        listaAlojamientos.setAdapter(departamentosAdapter);
        listaAlojamientos.setVisibility(View.VISIBLE);
    }

    @Override
    public void busquedaActualizada(String msg) {
        tvEstadoBusqueda.setText(" Buscando..."+msg);
    }

}
