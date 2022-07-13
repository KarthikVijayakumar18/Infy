package com.example.infyproject.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPAutoFillReceiver extends BroadcastReceiver {

    private static EditText editText;
    public void setEditText(EditText editText)
    {
        OTPAutoFillReceiver.editText=editText;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage sms : messages)
        {
            String msg = sms.getMessageBody();
            String otp = extractDigits(msg);
            if(otp.length()>0){
                editText.setText(otp);
            }
        }
    }

    public static String extractDigits(final String in) {
        final Pattern p = Pattern.compile( "(\\d{6})" );
        final Matcher m = p.matcher( in );
        if ( m.find() ) {
            return m.group( 0 );
        }
        return "";
    }
}