package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by RAIMONDI on 29/09/2016.
 */

public class ReservaAdapter extends ArrayAdapter<Reserva> {

    private LayoutInflater inflater;

    public ReservaAdapter(Context context, List<Reserva> reservas) {
        super(context,R.layout.fila_reserva, reservas);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        DateFormat df = DateFormat.getDateInstance();

        if (row == null) row = inflater.inflate(R.layout.fila_reserva, parent, false);

        TextView detalleDepto = (TextView) row.findViewById(R.id.detalleDepartamento);
        TextView fechaInicio = (TextView) row.findViewById(R.id.fechaInicio);
        TextView fechaFin = (TextView) row.findViewById(R.id.fechaFin);

        detalleDepto.setText(this.getItem(position).getAlojamiento().getDescripcion());

        //Faltan estas dos lineas pero explota por puntero nulo
        //fechaInicio.setText(df.format(this.getItem(position).getFechaInicio()));
        //fechaFin.setText(df.format(this.getItem(position).getFechaFin()));



        return row;
    }
}
