package com.example.notification;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";
    Switch aSwitch;
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aSwitch =(Switch)findViewById(R.id.switch1);
      final ToggleButton b = (ToggleButton) findViewById(R.id.mybutton);
        TextView dd=(TextView)findViewById(R.id.textView);
        int year =Calendar.getInstance().get(Calendar.YEAR);
        int month=Calendar.getInstance().get(Calendar.MONTH);
        int day=Calendar.getInstance().get(Calendar.DATE);
        String mm=day+"-"+month+"-"+year;
        dd.setText(mm);
       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(b.isChecked()){
                   new OoOAlertDialog.Builder(MainActivity.this)
                           .setTitle("OoOAlertDialog")
                           .setMessage("Deseja fechar este OoOAlertDialog?")
                           .setCancelable(false)
                           .setAnimation(Animation.ZOOM)
                          // .setPositiveButtonColor(Color.GREEN)
                           //.setNegativeButtonColor(Color.RED)
                           .setImage(R.color.blue)
                           .setPositiveButton("Yes", new OnClickListener() {
                               @Override
                               public void onClick() {
                                   Toast.makeText(MainActivity.this, "HI", Toast.LENGTH_SHORT).show();
                               }
                           }).setNegativeButton("No", new OnClickListener() {
                       @Override
                       public void onClick() {
                           Toast.makeText(MainActivity.this, "BYE", Toast.LENGTH_SHORT).show();
                       }
                   })
                           .build();

               }
               else {

               }

           }
       });
    }
    public void sendNotification(View view) {
try {

    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

        // Configure the notification channel.
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setVibrationPattern(new long[]{ 1000, 500,1000});
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setVibrate(new long[]{0, 100, 100, 100, 100, 100})
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Notification")
            .setContentText("Simple Notification");

    notificationManager.notify(NOTIFICATION_ID, builder.build());

}catch (Exception e){
    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
}
    }

}