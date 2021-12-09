package com.example.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class SMSReceiver extends BroadcastReceiver {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
    @Override
    public void onReceive(Context context, Intent intent) {
        // Logic for reading incoming SMS
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] arrMessage = (Object[]) bundle.get("pdus");
            if (arrMessage != null) {
                String content, phone, formattedTime;
                long time;
                byte[] bytes;
                for (Object aMsg: arrMessage) {
                    bytes = (byte[]) aMsg;
                    SmsMessage message = SmsMessage.createFromPdu(bytes);
                    content = message.getMessageBody();
                    phone = message.getDisplayOriginatingAddress();
                    time = message.getTimestampMillis();
                    formattedTime = dateFormat.format(time);

                    Toast.makeText(context, "Phone: " + phone + "\n" + "Time: " + formattedTime + "\n" + "Content: " + content, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
