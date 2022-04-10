package com.imin;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Toast;
import android.app.Activity;

import android.content.Context;
import android.util.Log;
import com.imin.IminPrinter;
import android.os.Message;
import android.os.Bundle;

import java.util.ArrayList;

import java.util.HashMap;
import java.io.IOException;
import android.graphics.Typeface;
import java.util.List;
import java.util.Map;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import com.imin.printerlib.IminPrintUtils;
import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Print  extends Activity  {
    private  Context context = null;
    private static final String TAG = "imin";
    private IminPrintUtils mIminPrintUtils;


    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        context = this;
        mIminPrintUtils = IminPrintUtils.getInstance(context);

    }

    @Override
    public void onResume() {
         super.onResume();
         startPrint();

    }

    public void startPrint()
    {
      Intent myIntent = getIntent();
      String text = myIntent.getStringExtra("text");

        mIminPrintUtils.printText(text);
        
        //  mIminPrintUtils.printSingleBitmap(text);



        // if(text.contains("/nnn/")){
        //   for (String lineStr : text.split("/nnn/")) {
        //       String line[] = lineStr.split(",");
        //       String func = line[0].trim();
        //       String para1 = line[1].trim();

        //       if(func.equals("printText")){
        //           mIminPrintUtils.printText(para1,1);
        //       }
        //       else if(func.equals("setQrCodeSize")){
        //         int qrSize = Integer.parseInt(para1) ;
        //         if(qrSize >10 )
        //               mIminPrintUtils.setQrCodeSize(10);
        //         else
        //           mIminPrintUtils.setQrCodeSize(Integer.parseInt(para1));
        //       }

        //       else if(func.equals("printQr")){
        //                 mIminPrintUtils.printQrCode(para1, 1);
        //       }
        //       else if(func.equals("printImage")){

        //         byte[] decodedString = Base64.decode(para1, Base64.DEFAULT);

        //         Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //         mIminPrintUtils.printSingleBitmap(decodedByte,1);
        //       }
        //       else if(func.equals("setAlignment")){
        //               mIminPrintUtils.setAlignment(Integer.parseInt(para1));
        //       }

        //       else if(func.equals("setTextStyle")){
        //         if(para1.trim().equals("bold")){
        //             mIminPrintUtils.setTextStyle(Typeface.BOLD);
        //         }else{
        //             mIminPrintUtils.setTextStyle(Typeface.NORMAL);
        //          }

        //       }else if(func.equals("setTextSize")) {
        //             mIminPrintUtils.setTextSize( Integer.parseInt(para1));
        //       }else if(func.equals("printAndFeedPaper")) {
        //             mIminPrintUtils.printAndFeedPaper(Integer.parseInt(para1));
        //       }

        //   }
        // }
        // else {
        //   for (String lineStr : text.split("/n")) {
        //       String line[] = lineStr.split(",");
        //       String func = line[0].trim();
        //       String para1 = line[1].trim();

        //       if(func.equals("printText")){
        //           mIminPrintUtils.printText(para1,1);
        //       }
        //       else if(func.equals("setQrCodeSize")){
        //         int qrSize = Integer.parseInt(para1) ;
        //         if(qrSize > 10 )
        //               mIminPrintUtils.setQrCodeSize(10);
        //         else
        //           mIminPrintUtils.setQrCodeSize(Integer.parseInt(para1));
        //       }

        //       else if(func.equals("printQr")){
        //                 mIminPrintUtils.printQrCode(para1, 1);
        //       }
        //       else if(func.equals("printImage")){

        //         byte[] decodedString = Base64.decode(para1, Base64.DEFAULT);

        //         Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //         mIminPrintUtils.printSingleBitmap(decodedByte,1);
        //       }
        //       else if(func.equals("setAlignment")){
        //               mIminPrintUtils.setAlignment(Integer.parseInt(para1));
        //       }
        //       else if(func.equals("setTextStyle")){
        //         if(para1.trim().equals("bold")){
        //             mIminPrintUtils.setTextStyle(Typeface.BOLD);
        //         }else{
        //             mIminPrintUtils.setTextStyle(Typeface.NORMAL);
        //          }

        //       }else if(func.equals("setTextSize")) {
        //             mIminPrintUtils.setTextSize( Integer.parseInt(para1));
        //       }else if(func.equals("printAndFeedPaper")) {
        //             mIminPrintUtils.printAndFeedPaper(Integer.parseInt(para1));
        //       }

        //   }
        // }
              finish();
    }

}
