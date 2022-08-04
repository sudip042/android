package Broadcasting;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.bookcarservicing.R;

import CreateChannels.CreateChannels;

public class BroadCasting extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadCasting(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean noConnectivity;
        notificationManagerCompat=NotificationManagerCompat.from(context);
        CreateChannels createChannels=new CreateChannels(context);
        createChannels.createChannel();

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            noConnectivity=intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                    false);

            if (noConnectivity)
            {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_LONG).show();
                DisplayNotification();
            }
            else {
                Toast.makeText(context, "Connected", Toast.LENGTH_LONG).show();
                DisplayNotification1();
            }
        }

    }

    private void DisplayNotification()
    {
        Notification notification=new NotificationCompat.Builder(context, CreateChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.car)
                .setContentTitle("Connection Error!!")
                .setContentText("Connect to internet")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(1,notification);
    }

    private void DisplayNotification1()
    {
        Notification notification=new NotificationCompat.Builder(context,CreateChannels.CHANNEL_2)
                .setSmallIcon(R.drawable.car)
                .setContentTitle("Network detected")
                .setContentText("Ready to Race!!!")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();
        notificationManagerCompat.notify(2,notification);
    }

}
