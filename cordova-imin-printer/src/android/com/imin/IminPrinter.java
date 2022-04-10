
package com.imin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Iterator;

import android.graphics.Typeface;

import android.os.Build;

import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import com.imin.printerlib.IminPrintUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;

import android.os.SystemClock;

import android.Manifest;
import android.annotation.TargetApi;

import android.content.pm.PackageManager;
import android.content.res.Configuration;

import android.text.TextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.List;

import com.imin.Print;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class IminPrinter extends CordovaPlugin {

    private static final String TAG = "Imin";

    CallbackContext callbackContext = null;
    Context context = null;
    private IminPrintUtils mIminPrintUtils;
    private BluetoothStateReceiver mBluetoothStateReceiver;
    private Handler progressHandler = new Handler();

    private boolean openHead;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
        this.callbackContext = callbackContext;
        Log.d(TAG, action);
        List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();
        context = cordova.getActivity().getApplicationContext();

        initReceiver();
        mIminPrintUtils = IminPrintUtils.getInstance(context);
        if (action.equals("printText")) {
            final String text = args.getString(0);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, printerDevices.get(0));
                        startPrint(context, text);
                        callbackContext.success(0);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.error(-1);
                    }
                }
            });

            return true;
        } else if (action.equals("setTextStyleBold")) {

            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, printerDevices.get(0));
                        mIminPrintUtils.setTextStyle(Typeface.BOLD);
                        callbackContext.success(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.success(-1);

                    }
                }
            });

        } else if (action.equals("setTextStyleNormal")) {

            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, printerDevices.get(0));
                        mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                        callbackContext.success(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.success(-1);

                    }
                }
            });
        } else if (action.equals("printBitmap")) {
            final String text = args.getString(0);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, printerDevices.get(0));
                        byte[] decodedString = Base64.decode(text, Base64.DEFAULT);

                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        mIminPrintUtils.printSingleBitmap(decodedByte, 1);
                        mIminPrintUtils.printAndFeedPaper(100);

                        callbackContext.success(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.success(-1);

                    }
                }
            });
        } else if (action.equals("initPrinter")) {

            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, printerDevices.get(0));
                        callbackContext.success(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.success(-1);

                    }
                }
            });

        } else if (action.equals("printAndFeedPaper")) {
            final String value = args.getString(0);
            int val = Integer.parseInt(value);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, printerDevices.get(0));
                        mIminPrintUtils.printAndFeedPaper(val);
                        callbackContext.success(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.success(-1);

                    }
                }
            });
        }

        else if (action.equals("checkStatus")) {

            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();

                        if (printerDevices.size() > 0)
                            callbackContext.success(0);
                        else {
                            callbackContext.error(-1);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d(TAG, e.toString());
                        callbackContext.error(-1);
                    }

                }
            });

        }

        return true;

    }

    private void startPrint(Context context, String text) {
        Intent intent = new Intent(context, Print.class);
        intent.putExtra("text", text);
        cordova.getActivity().startActivity(intent);

    }

    private void initReceiver() {
        mBluetoothStateReceiver = new BluetoothStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        cordova.getActivity().registerReceiver(mBluetoothStateReceiver, filter);
    }

    class BluetoothStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            switch (state) {
                case BluetoothAdapter.STATE_TURNING_ON:
                    toast("Bluetooth ON");
                    break;

                case BluetoothAdapter.STATE_TURNING_OFF:
                    toast("Bluetooth OFF");
                    break;
            }
        }
    }

    protected void toast(String message) {

    }

}
