package com.hongyun.cordova.plugin.install;

import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2017/7/20.
 */

public class Install extends CordovaPlugin {
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        CallbackContext currCallbackContext = callbackContext;
        JSONObject jo = args.getJSONObject(0);
        String file;
        if(action.equals("install")){
            file = jo.getString("file");
            install(file);
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }
        return true;
    }

    private void install(String file){

        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse(file.toString()),"application/vnd.android.package-archive");

        this.cordova.getActivity().startActivity(i);
        //UUApp.getInstance().exit();
        //this.cordova.getActivity().getApplication().exit();
        android.os.Process.killProcess(android.os.Process.myPid());
//      如果没有android.os.Process.killProcess(android.os.Process.myPid());最后不会提示完成、打开。
//      如果没有i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);这一步的话，最后安装好了，点打开，是不会打开新版本应用的。
    }
}
