package pl.edu.pl.shopping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;

import pl.edu.pl.shopping.data.SmsParser;
import pl.edu.pl.shopping.presentation.SettingsActivity;

public class SmsListener extends BroadcastReceiver{
    private SharedPreferences prefs;
    private ArrayList<String> numbers = new ArrayList<>(3);

    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            prefs = ShoppingApplication.provideSharedPreferences();

            numbers.add(prefs.getString(SettingsActivity.PHONE_NO_1, null));
            numbers.add(prefs.getString(SettingsActivity.PHONE_NO_2, null));
            numbers.add(prefs.getString(SettingsActivity.PHONE_NO_3, null));

            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from = null;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        if (numbers.contains(msg_from)) {
                            SmsParser parser = new SmsParser();
                            parser.parser(msgBody, msg_from);
                        }
                    }
                }catch(Exception e){
                           Log.d("Exception caught",e.getMessage());
                }


                PackageManager pm = context.getPackageManager();
                Intent launchIntent = pm.getLaunchIntentForPackage("pl.edu.pl.shopping");
                launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(launchIntent);
            }
        }
    }
}