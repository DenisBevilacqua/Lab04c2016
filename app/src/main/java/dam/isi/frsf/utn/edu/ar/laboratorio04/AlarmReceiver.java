package dam.isi.frsf.utn.edu.ar.laboratorio04;

/**
 * Created by denis on 10/10/2016.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AlarmReceiver extends BroadcastReceiver {

    @Override

    public void onReceive(Context context, Intent intent) {

        Reserva reserva = (Reserva) intent.getSerializableExtra("Reserva");

        // For our recurring task, we'll just display a message

        long mili = System.currentTimeMillis();

        int milisegundos = (int) mili;

        Integer resto = milisegundos%3;

        if(resto == 0){
            Toast.makeText(context, "Es multiplo de 3. Se generará una notificacion." , Toast.LENGTH_SHORT).show();
            showNotification(context, reserva);
             }

        else

        {
            Toast.makeText(context, "No se generará notificación. No es múltiplo de 3." , Toast.LENGTH_SHORT).show();
        }

    }

    private void showNotification(Context context, Reserva reserva) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, ListaReservaActivity.class), 0);

        // Invoking the default notification service
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setContentTitle("Reserva realizada");
        mBuilder.setContentText("Se ha reservado el departamento "+ reserva.getDepartamento().getDescripcion());
        mBuilder.setTicker("Se ha realizado la reserva");
        mBuilder.setSmallIcon(R.drawable.ic_menu_camera);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Se ha reservado el departamento "+ reserva.getDepartamento().getDescripcion());
        bigText.setBigContentTitle("Reserva de Alojamiento");
        bigText.setSummaryText("Se ha reservado su departamento.");

        mBuilder.setStyle(bigText);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }
}