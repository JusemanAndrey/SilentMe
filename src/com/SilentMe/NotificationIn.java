package com.SilentMe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;
import java.util.Locale;

public class NotificationIn extends Activity
  implements TextToSpeech.OnInitListener
{
  private static final String TAG = "TextToSpeechDemo";
  private static TextToSpeech talker;
  String AudibleNotify;
  AudioManager aManager;
  Cursor c;
  DBAdapter db;
  Bundle extras;
  String mode;

  private void EnterZone()
  {
    talker.speak("Your phone is entered in silent zone", 0, null);
  }

  public void StopTalker()
  {
    if (talker != null)
    {
      talker.stop();
      talker.shutdown();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SharedPreferences localSharedPreferences = getSharedPreferences("myPrefs", 1);
    this.extras = getIntent().getExtras();
    this.mode = this.extras.getString("mode");
    this.AudibleNotify = localSharedPreferences.getString("Notify", "");
    talker = new TextToSpeech(this, this);
    final AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(" Notification ");
    localAlertDialog.setMessage("Your phone is entered in slient zone.");
    localAlertDialog.setIcon(2130837531);
    localAlertDialog.setButton("Accept", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        NotificationIn.this.aManager = ((AudioManager)NotificationIn.this.getSystemService("audio"));
        NotificationIn.this.AudibleNotify.equals("On");
        if (NotificationIn.this.mode.equals("Slient"))
          NotificationIn.this.aManager.setRingerMode(0);
        while (true)
        {
          localAlertDialog.dismiss();
          NotificationIn.this.finish();
          return;
          if (NotificationIn.this.mode.equals("Vibrate"))
            NotificationIn.this.aManager.setRingerMode(1);
        }
      }
    });
    localAlertDialog.setButton2("Deny", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        NotificationIn.this.finish();
        localAlertDialog.dismiss();
      }
    });
    localAlertDialog.show();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      int i = talker.setLanguage(Locale.US);
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
 * Qualified Name:     com.SilentMe.NotificationIn
 * JD-Core Version:    0.6.2
 */