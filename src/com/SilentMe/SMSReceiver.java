package com.SilentMe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver
{
  private String IsMessage;
  AudioManager aManager;
  private Context mContext;
  private String message;
  int mode;
  String phonenumber;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.mContext = paramContext;
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("myPrefs", 1);
    this.message = localSharedPreferences.getString("message", "");
    this.IsMessage = localSharedPreferences.getString("IsMessage", "On");
    if (this.message.equals(""))
      this.message = "Call me later.I am in slientzone area.";
    this.aManager = ((AudioManager)this.mContext.getSystemService("audio"));
    this.mode = this.aManager.getRingerMode();
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle == null);
    while (true)
    {
      return;
      Log.i("IncomingCallReceiver", localBundle.toString());
      String str = localBundle.getString("state");
      Log.i("IncomingCallReceiver", "State: " + str);
      if (str.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))
      {
        this.phonenumber = localBundle.getString("incoming_number");
        Log.i("IncomingCallReceiver", "Incoming Number: " + this.phonenumber);
      }
      if ((str.equals("RINGING")) && (this.mode == 0) && (this.IsMessage.equals("On")))
        SmsManager.getDefault().sendTextMessage(this.phonenumber, null, this.message, null, null);
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.SMSReceiver
 * JD-Core Version:    0.6.2
 */