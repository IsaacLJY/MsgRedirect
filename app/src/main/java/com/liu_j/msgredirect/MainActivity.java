package com.liu_j.msgredirect;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch mSwitch;
    private SMSBroadcastReceiver smsBroadcastReceiver;
    private TelephonyManager telephonyManager;
    private MyPhoneStateListener myPhoneStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwitch = (Switch)findViewById(R.id.phone15673940851);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    IntentFilter msgIntentFilter = new IntentFilter();
                    msgIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
                    smsBroadcastReceiver = new SMSBroadcastReceiver();
                    registerReceiver(smsBroadcastReceiver, msgIntentFilter);

                    telephonyManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
                    myPhoneStateListener = new MyPhoneStateListener();
                    telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

                }else{
                    if(smsBroadcastReceiver!=null){
                        unregisterReceiver(smsBroadcastReceiver);
                    }
                    if(telephonyManager!=null && myPhoneStateListener!=null){
                        telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(smsBroadcastReceiver!=null){
            unregisterReceiver(smsBroadcastReceiver);
        }
        if(telephonyManager!=null && myPhoneStateListener!=null){
            telephonyManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
        }
    }
}
