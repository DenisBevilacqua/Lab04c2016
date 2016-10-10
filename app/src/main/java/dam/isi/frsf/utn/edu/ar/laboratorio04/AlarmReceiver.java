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
        Integer resto = milisegundos%2;

        //int e = Log.e(milisegundos);

        if(resto == 0){
            Toast.makeText(context, "Es multiplo de 3. Se generará una notificacion." , Toast.LENGTH_SHORT).show();


        }
        else
        { Toast.makeText(context, "No se generará notificación." , Toast.LENGTH_SHORT).show();
        }



    }
}