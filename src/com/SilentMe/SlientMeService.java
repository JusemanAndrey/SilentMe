package com.SilentMe;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings.Secure;

public class SlientMeService extends Service
{
  static MyGPSLocation myGPSLocation = null;

  private void toggleGPS(boolean paramBoolean)
  {
    Settings.Secure.getString(getContentResolver(), "location_providers_allowed");
    Intent localIntent = new Intent();
    localIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
    localIntent.addCategory("android.intent.category.ALTERNATIVE");
    localIntent.setData(Uri.parse("3"));
    sendBroadcast(localIntent);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    myGPSLocation = new MyGPSLocation(getBaseContext());
    myGPSLocation.getlocation();
  }

  public void onStart()
  {
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.SlientMeService
 * JD-Core Version:    0.6.2
 */