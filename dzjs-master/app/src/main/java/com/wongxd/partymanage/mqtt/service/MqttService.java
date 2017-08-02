package com.wongxd.partymanage.mqtt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wongxd.partymanage.App;
import com.wongxd.partymanage.AtyMainActivity;
import com.wongxd.partymanage.mqtt.Notification.MyNotification;
import com.wongxd.partymanage.mqtt.bean.PushMessage;
import com.wongxd.partymanage.utils.conf.UrlConf;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by json on 2017/7/13.
 */

public class MqttService extends Service {

    private MqttAndroidClient androidClient;
    private MqttConnectOptions connectOptions;

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.instance);
    private String clintID = "dz" + preferences.getString("account",null) + Math.random()*100;
    private static final int QOS = 2;
    boolean retained = false;
//    String host = "tcp://192.168.181.152:61613";
    String host = UrlConf.MQTTHOST;
    String mqttUserName = "admin";
    String mqttUserPwd = "password";
    String myTopic = "toclient/"+1;//


    @Override
    public int onStartCommand(Intent intent,   int flags, int startId) {
        initSetting();
        return START_STICKY;
    }

    private void initSetting() {
//        String toPic = myTopic;
//        String message = "{\"terminal_uid\":\"" + clintID + "\"}";
//        boolean doConnect = true;

        androidClient = new MqttAndroidClient(this, host, clintID);
        Log.e("msg","id"+clintID);
        androidClient.setCallback(mqttCallback);

        connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);//清除缓存
        connectOptions.setUserName(mqttUserName);
        connectOptions.setPassword(mqttUserPwd.toCharArray());
        connectOptions.setConnectionTimeout(10);//超时时间      10s
        connectOptions.setKeepAliveInterval(20);//心跳发送时间间隔  20s

        doClientConnection();

    }

    /**
     * 连接服务器
     */
    private void doClientConnection() {
        if(androidClient != null && isConnectIsNomarl()){
            try {
                androidClient.connect(connectOptions, null, iMqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 有无网络
     * @return
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
//            String name = info.getTypeName();
//            Log.e("msg", "MQTT当前网络名称：" + name);
            return true;
        } else {
//            Log.e("msg", "MQTT 没有可用网络");
//            SnackbarUtil.IndefiniteSnackbar(,"无网络，是否到网咯设置界面", Snackbar.LENGTH_INDEFINITE ,Confirm);
//            Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 监听是否连接成功
     */
   IMqttActionListener iMqttActionListener = new IMqttActionListener() {
       @Override
       public void onSuccess(IMqttToken asyncActionToken) {
//           androidClient.subscribe(myTopic,1);
           Log.e("msg", "连接成功 ");
           try {
               androidClient.subscribe(myTopic, 1);//订阅消息
           } catch (MqttException e) {
               e.printStackTrace();
           }
       }

       @Override
       public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
           Log.e("msg","失败" + exception.toString());
           exception.printStackTrace();//重连
       }
   };

    MqttCallback  mqttCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable cause) {
            Log.e("msg","连接丢失正在重连" + cause.toString());
            doClientConnection();//重新连接

        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {

            String msg = new String(message.getPayload());
            Log.e("msg",""+msg);
            String title = null;
            String context = null;
            try {
                JSONObject jsonObject = new JSONObject(msg);
                title = jsonObject.getString("alert");
                context = jsonObject.getString("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PushMessage pushMessage = new PushMessage();
            pushMessage.setContentTitle(title);
            pushMessage.setContentText(context);

            MyNotification notification = new MyNotification(getApplicationContext());
            notification.sendNotification(AtyMainActivity.class, pushMessage);


        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    };
}
