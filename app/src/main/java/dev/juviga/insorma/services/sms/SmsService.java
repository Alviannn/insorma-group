package dev.juviga.insorma.services.sms;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsService {

    private final SmsManager manager;
    private final Context context;

    public SmsService(Context context) {
        this.context = context;
        this.manager = SmsManager.getDefault();
    }

    public void sendMessage(String phone, String message) {
        manager.sendTextMessage(phone, null, message, null, null);
        Toast.makeText(context, "SMS message was sent", Toast.LENGTH_SHORT).show();
    }

}
