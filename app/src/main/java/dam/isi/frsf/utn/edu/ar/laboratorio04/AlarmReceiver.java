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

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message

        long mili = System.currentTimeMillis();

        int milisegundos = (int) mili;

        Integer resto = milisegundos%3;

        if(resto == 0){
            Toast.makeText(context, "Es multiplo de 3. Se generará una notificacion." , Toast.LENGTH_SHORT).show();
            showNotification(context);
             }

        else

        {
            Toast.makeText(context, "No se generará notificación. No es múltiplo de 3." , Toast.LENGTH_SHORT).show();
        }


    }

    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, AltaReservaActivity.class), 0);

        // Invoking the default notification service
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setContentTitle("Laboratorio 4");
        mBuilder.setContentText("Nuevo mensaje recibido");
        mBuilder.setTicker("Mensaje");
        mBuilder.setSmallIcon(R.drawable.ic_menu_camera);

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }
}