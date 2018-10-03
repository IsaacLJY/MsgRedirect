package com.liu_j.msgredirect;

import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liu_j on 2018/10/3.
 */

public class MyPhoneStateListener extends PhoneStateListener {

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state){
            case TelephonyManager.CALL_STATE_RINGING:
                Date date = new Date(System.currentTimeMillis());
                String date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                String msg = "来电号码："+incomingNumber+"\n来电时间："+date_time+"\n";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("15674930851", null, msg, null, null);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                break;
        }
    }
}
