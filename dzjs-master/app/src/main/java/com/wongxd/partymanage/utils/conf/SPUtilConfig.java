package com.wongxd.partymanage.utils.conf;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/2/21.
 */

public class SPUtilConfig {
    public static final String PWD = "token";

    private final String SPFILENAME = "userMessage";
    private Context context;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static SPUtilConfig instance;

    public SPUtilConfig(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static synchronized SPUtilConfig getInstance(Context context) {
        if (instance == null) {
            instance = new SPUtilConfig(context);
        }
        return instance;
    }

    //    public static SPUtilConfig getInstance(){
//        if(instance==null){
//            instance=new SPUtilConfig(context);
//        }
//        if(instance==null){
//            throw new RuntimeException("class should init!");
//        }
//        return instance;
//    }
    public void clearData() {
        editor.clear();
        editor.commit();
    }

    public void saveData(String key,Object data){
        String type=data.getClass().getSimpleName();

        if ("Integer".equals(type)){
            editor.putInt(key,(Integer)data);
        }else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean) data);
        }else if ("String".equals(type)){
            editor.putString(key, (String) data);
        }else if ("Float".equals(type)){
            editor.putFloat(key, (Float) data);
        }else if ("Long".equals(type)){
            editor.putLong(key, (Long) data);
        }
        editor.commit();
    }
    public Object getData(String key,Object defValue ){
        String type=defValue.getClass().getSimpleName();
        if ("Integer".equals(type)){
            return sp.getInt(key, (Integer) defValue);
        }else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean) defValue);
        }else if ("String".equals(type)){
            return sp.getString(key, (String) defValue);
        }else if("Float".equals(type)){
            return sp.getFloat(key, (Float) defValue);
        }else if ("Long".equals(type)){
            return sp.getLong(key, (Long) defValue);
        }
        return null;
    }


}
