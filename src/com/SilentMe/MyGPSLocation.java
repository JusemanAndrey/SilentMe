package com.SilentMe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.widget.Toast;
import java.util.Locale;

public class MyGPSLocation
  implements TextToSpeech.OnInitListener
{
  private static final String TAG = "TextToSpeechDemo";
  double Lat = 0.0D;
  double Longi = 0.0D;
  int _id;
  AudioManager aManager;
  double colLat;
  double colLng;
  String colName;
  double colRadius;
  DBAdapter db;
  public boolean isValidLocation = false;
  private LocationListener locationListener;
  private LocationManager locationManager;
  private Context mContext;
  String message_notification;
  String mode;
  String radius;
  private TextToSpeech talker;

  public MyGPSLocation(Context paramContext)
  {
    this.mContext = paramContext;
    this.talker = new TextToSpeech(paramContext, this);
  }

  private void EnterZone()
  {
    this.talker.speak("Your phone is entered in silent zone", 0, null);
  }

  private void toggleGPS(boolean paramBoolean)
  {
    Settings.Secure.getString(this.mContext.getContentResolver(), "location_providers_allowed");
    Intent localIntent = new Intent();
    localIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
    localIntent.addCategory("android.intent.category.ALTERNATIVE");
    localIntent.setData(Uri.parse("3"));
    this.mContext.sendBroadcast(localIntent);
  }

  protected void ExitZone()
  {
    this.talker.speak("Your phone is out from the silent zone", 0, null);
  }

  protected void SavePreferences(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.mContext.getSharedPreferences("myPrefs", 1).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void getLocations()
  {
    this.db = new DBAdapter(this.mContext);
    this.db.open();
    Cursor localCursor = this.db.getDetail();
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("myPrefs", 1);
    String str1 = localSharedPreferences.getString("notification", "");
    String str2 = localSharedPreferences.getString("AutoStart", "");
    String str3 = localSharedPreferences.getString("Notify", "");
    if (str2.equals(""))
      SavePreferences("AutoStart", "On");
    if (str1.equals(""))
      SavePreferences("notification", "On");
    if (str3.equals(""))
      SavePreferences("Notify", "On");
    Location localLocation1 = new Location("Current location");
    localLocation1.setLatitude(this.Lat);
    localLocation1.setLongitude(this.Longi);
    boolean bool = localCursor.moveToFirst();
    if (!bool)
    {
      this.db.close();
      return;
    }
    this._id = localCursor.getInt(localCursor.getColumnIndex("_id"));
    this.colLat = localCursor.getDouble(localCursor.getColumnIndex("lat"));
    this.colLng = localCursor.getDouble(localCursor.getColumnIndex("lng"));
    this.colRadius = localCursor.getDouble(localCursor.getColumnIndex("radius"));
    this.colName = localCursor.getString(localCursor.getColumnIndex("loc_name"));
    this.radius = localCursor.getString(localCursor.getColumnIndex("meters"));
    this.mode = localCursor.getString(localCursor.getColumnIndex("mode"));
    this.message_notification = localCursor.getString(localCursor.getColumnIndex("notification"));
    this.aManager = ((AudioManager)this.mContext.getSystemService("audio"));
    Location localLocation2 = new Location("point");
    localLocation2.setLatitude(this.colLat);
    localLocation2.setLongitude(this.colLng);
    float f = Float.valueOf(this.radius).floatValue() / Float.valueOf("3.2808399").floatValue();
    if (localLocation2.distanceTo(localLocation1) < f)
      if ((this.message_notification.equals("0")) || (this.message_notification.equals("-1")))
      {
        if (!str2.equals("On"))
          break label584;
        if (str1.equals("On"))
        {
          if (!this.mode.equals("Slient"))
            break label560;
          this.aManager.setRingerMode(0);
          label474: Intent localIntent2 = new Intent(this.mContext, NotificationIn.class);
          localIntent2.addFlags(268435456);
          localIntent2.putExtra("mode", this.mode);
          this.mContext.startActivity(localIntent2);
          this.db.updateMessageNotification(this._id, "1");
        }
        if (str3.equals("On"))
          EnterZone();
      }
    while (true)
    {
      bool = localCursor.moveToNext();
      break;
      label560: if (!this.mode.equals("Vibrate"))
        break label474;
      this.aManager.setRingerMode(1);
      break label474;
      label584: str2.equals("Off");
      continue;
      if ((this.message_notification.equals("1")) || (this.message_notification.equals("-1")))
        if (str2.equals("On"))
        {
          if (str1.equals("On"))
          {
            Intent localIntent1 = new Intent(this.mContext, NotificationOut.class);
            localIntent1.addFlags(268435456);
            this.mContext.startActivity(localIntent1);
            this.db.updateMessageNotification(this._id, "0");
          }
          if (str3.equals("On"))
            ExitZone();
          this.aManager.setRingerMode(2);
        }
        else
        {
          str2.equals("Off");
        }
    }
  }

  public void getlocation()
  {
    this.locationManager = ((LocationManager)this.mContext.getSystemService("location"));
    this.locationListener = new MyLocationListener();
    this.locationManager.requestLocationUpdates("network", 0L, 0.0F, this.locationListener);
  }

  public void onInit(int paramInt)
  {
    if (paramInt == 0)
    {
      int i = this.talker.setLanguage(Locale.US);
      if ((i == -1) || (i == -2))
        Log.e("TextToSpeechDemo", "Language is not available.");
    }
    while (true)
    {
      return;
      Log.e("TextToSpeechDemo", "Could not initialize TextToSpeech.");
    }
  }

  class MyLocationListener
    implements LocationListener
  {
    MyLocationListener()
    {
    }

    public void onLocationChanged(Location paramLocation)
    {
      if (paramLocation != null)
      {
        MyGPSLocation.this.Lat = paramLocation.getLatitude();
        MyGPSLocation.this.Longi = paramLocation.getLongitude();
        MyGPSLocation.this.getLocations();
        String str = MyGPSLocation.this.mContext.getSharedPreferences("myPrefs", 1).getString("mGPS", "");
        if ((!str.equals("0")) && (!str.equals("")) && (((LocationManager)MyGPSLocation.this.mContext.getSystemService("location")).isProviderEnabled("gps")))
        {
          MyGPSLocation.this.toggleGPS(false);
          Log.e("TimerTask", "Turn GPS OFF: " + System.currentTimeMillis());
          Toast.makeText(MyGPSLocation.this.mContext, "Turn GPS OFF", 1).show();
        }
      }
    }

    public void onProviderDisabled(String paramString)
    {
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.MyGPSLocation
 * JD-Core Version:    0.6.2
 */