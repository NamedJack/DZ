package com.wongxd.partymanage.mqtt.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.wongxd.partymanage.R;
import com.wongxd.partymanage.mqtt.bean.PushMessage;

/**
 * Created by json on 2017/7/14.
 */

public class MyNotification {
    private Context mContext;

    public MyNotification(Context mContext) {
        this.mContext = mContext;
    }

    public void sendNotification(Class<?> pClass, PushMessage message){
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(mContext,pClass);
        intent.putExtra("isPushMessage", true);
        intent.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(message.getContentTitle()+" ")
                .setContentText(message.getContentText()+" ")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX);


        notificationManager.notify(1,builder.build());
    }

}
