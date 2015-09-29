package com.SilentMe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;
import java.util.Locale;

public class NotificationOut extends Activity
  implements TextToSpeech.OnInitListener
{
  private static final String TAG = "TextToSpeechDemo";
  String AudibleNotify;
  AudioManager aManager;
  AlertDialog alertDialog;
  private String isFlag = "1";
  private TextToSpeech mTts;

  protected void ExitZone()
  {
    this.mTts.speak("Your phone is out from the silent zone", 0, null);
  }

  protected void SavePreferences(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("myPrefs", 1).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.AudibleNotify = getSharedPreferences("myPrefs", 1).getString("Notify", "");
    this.mTts = new TextToSpeech(this, this);
    this.alertDialog = new AlertDialog.Builder(this).create();
    this.alertDialog.setTitle(" Notification ");
    this.alertDialog.setMessage("Your phone is out from the silent zone.");
    this.alertDialog.setIcon(2130837531);
    this.alertDialog.setButton("Accept", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        NotificationOut.this.aManager = ((AudioManager)NotificationOut.this.getSystemService("audio"));
        if (NotificationOut.this.AudibleNotify.equals("On"))
        {
          NotificationOut.this.aManager.setRingerMode(2);
          NotificationOut.this.SavePreferences("Flag", "1");
        }
        NotificationOut.this.alertDialog.dismiss();
        NotificationOut.this.finish();
      }
    });
    this.alertDialog.setButton2("Deny", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        NotificationOut.this.SavePreferences("Flag", "1");
        NotificationOut.this.finish();
        NotificationOut.this.alertDialog.dismiss();
      }
    });
    this.alertDialog.show();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.alertDialog != null)
      this.alertDialog.dismiss();
    this.mTts.stop();
    this.mTts.shutdown();
  }

  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      int i = this.mTts.setLanguage(Locale.US);
      if ((i == -1) || (i == -2))
      {
        Log.e("TextToSpeechDemo", "Language is not available.");
        Toast.makeText(getBaseContext(), "Language is not available.", 1).show();
      }
    }
    while (true)
    {
      return;
      Log.e("TextToSpeechDemo", "Could not initialize TextToSpeech.");
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.NotificationOut
 * JD-Core Version:    0.6.2
 */