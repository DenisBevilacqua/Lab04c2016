package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    public interface AsyncResponse {
        void processFinish(List<Departamento> output);
    }

    public AsyncResponse delegate = null;

    private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
       listener.busquedaFinalizada(departamentos);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);

    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {
        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();
        int contador = 0;

        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        Double precioMinimo = busqueda[0].getPrecioMinimo();
        Double precioMaximo = busqueda[0].getPrecioMaximo();
        Boolean permiteFumar = busqueda[0].getPermiteFumar();
        Integer huespedes = busqueda[0].getHuespedes();


        if(precioMinimo == null) precioMinimo = 0.0;
        if(precioMaximo == null) precioMaximo = 5000.0;
        //if(permiteFumar == null) permiteFumar = false;
        if(huespedes == null) huespedes = 0;



        for (Departamento actual : todos){
            if(actual.getPrecio()>=precioMinimo && actual.getPrecio()<= precioMaximo
                    /*&& (actual.getNoFumador() == permiteFumar) */&& actual.getCiudad().equals(ciudadBuscada)
                    && actual.getCapacidadMaxima()>= huespedes){
                resultado.add(actual);
            }

        }
        //resultado.add(todos.get(0))
        System.out.println(resultado.size());

        // TODO implementar: buscar todos los departamentos del sistema e ir chequeando las condiciones 1 a 1.
        // si cumplen las condiciones agregarlo a los resultados.
        return resultado;
    }
}


