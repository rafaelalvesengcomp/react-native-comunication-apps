package br.com.febracis.kitmaster.comunicationapps;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import android.content.ContentResolver;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class ComunicationModule extends ReactContextBaseJavaModule {

    private Uri uriLauncher;

    public ComunicationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ComunicationApps";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void setup(String name, String packagesName, String title, String thumbnail, String background, int progress, String tags, int isAudio, int isVideo){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getReactApplicationContext());
        int firstExecution = preferences.getInt("firstExecution", -1);

        if(firstExecution == -1){
            ContentValues defaultValues = new ContentValues();
            defaultValues.put("_name", name);
            defaultValues.put("_package", packagesName);
            defaultValues.put("_title", title);
            defaultValues.put("_thumbnail", thumbnail);
            defaultValues.put("_background", background);
            defaultValues.put("_progress", progress);
            defaultValues.put("_tags", tags);
            defaultValues.put("_isaudio", isAudio);
            defaultValues.put("_isvideo", isVideo);

            Context c = getReactApplicationContext();
            Uri uriTmp = Uri.parse("content://br.com.febracis.kitmaster.home.provider/general");

            Uri id = c.getContentResolver().insert(uriTmp, defaultValues);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("firstExecution", 0);
            editor.putString("uri", String.valueOf(id));
            uriLauncher = Uri.parse(String.valueOf(id));
            editor.apply();
        }else{
            String uri = preferences.getString("uri", "fail");
            uriLauncher = Uri.parse(String.valueOf(uri));
        }
    }

    @ReactMethod
    public void update(String name, String packagesName, String title, String thumbnail, String background, int progress, String tags, int isAudio, int isVideo){
        ContentValues defaultValues = new ContentValues();
        defaultValues.put("_name", name);
        defaultValues.put("_package", packagesName);
        defaultValues.put("_title", title);
        defaultValues.put("_thumbnail", thumbnail);
        defaultValues.put("_background", background);
        defaultValues.put("_progress", progress);
        defaultValues.put("_tags", tags);
        defaultValues.put("_isaudio", isAudio);
        defaultValues.put("_isvideo", isVideo);

        Context c = getReactApplicationContext();
        c.getContentResolver().update(uriLauncher, defaultValues, null, null);
    }

    @ReactMethod
    public void getInformations(){

        Context c = getReactApplicationContext();
        Uri uri = Uri.parse("content://br.com.febracis.kitmaster.home.provider/general");
        Cursor cursor = c.getContentResolver().query(uri, null, null, null, "");
        WritableMap params = Arguments.createMap();

        if (cursor.moveToFirst()) {
            do{
                WritableMap paramsTmp = Arguments.createMap();
                String name = "";
                String packagesName = "";
                String title = "";
                String thumbnail = "";
                String background = "";
                int progress = 0;
                String tags = "";
                int isAudio = 0;
                int isVideo = 0;

                name = cursor.getString(cursor.getColumnIndex("_name"));
                packagesName = cursor.getString(cursor.getColumnIndex("_package"));
                title = cursor.getString(cursor.getColumnIndex("_title"));
                thumbnail = cursor.getString(cursor.getColumnIndex("_thumbnail"));
                background = cursor.getString(cursor.getColumnIndex("_background"));
                progress = cursor.getInt(cursor.getColumnIndex("_progress"));
                tags = cursor.getString(cursor.getColumnIndex("_tags"));
                isAudio = cursor.getInt(cursor.getColumnIndex("_isaudio"));
                isVideo = cursor.getInt(cursor.getColumnIndex("_isvideo"));

                paramsTmp.putString("_name", name);
                paramsTmp.putString("_package", packagesName);
                paramsTmp.putString("_title", title);
                paramsTmp.putString("_thumbnail", thumbnail);
                paramsTmp.putString("_background", background);
                paramsTmp.putInt("_progress", progress);
                paramsTmp.putString("_tags", tags);
                paramsTmp.putInt("_isaudio", isAudio);
                paramsTmp.putInt("_isvideo", isVideo);

                params.putMap(title, paramsTmp);
            } while (cursor.moveToNext());
        }

        sendEvent(getReactApplicationContext(), "receiveInformations",params);
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}