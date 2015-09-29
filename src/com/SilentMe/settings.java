package com.SilentMe;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.util.Timer;
import java.util.TimerTask;

public class settings extends Activity
{
  static Timer GPSTimer = null;
  TimerTask GPSTimerTask;
  public boolean _active = false;
  private Button btnBack;
  long elapsedTime;
  private ImageButton imButtonGPSOff;
  private ImageButton imButtonGPSOn;
  private Button imButtonMessage;
  private ImageButton imButtonNotifyOff;
  private ImageButton imButtonNotifyOn;
  private ImageButton imButtonOff;
  private ImageButton imButtonOn;
  private Button imButtonSave;
  private boolean imGPSOff;
  private boolean imGPSOn;
  boolean isGPSON = false;
  boolean isStart = false;
  private int mTime;
  String mTime1;
  private Spinner spinner;
  long startTime;
  String strSavedMem3;
  long totalTime;

  private void AddGPSTimer()
  {
    long l = Long.valueOf(this.mTime1).longValue();
    if (GPSTimer == null)
      GPSTimer = new Timer();
    while (true)
    {
      this.GPSTimerTask = new TimerTask()
      {
        public void run()
        {
          if (!((LocationManager)settings.this.getSystemService("location")).isProviderEnabled("gps"))
          {
            settings.this.turnGPSOn();
            Log.e("TimerTask", "Turn GPS ON: " + System.currentTimeMillis());
          }
        }
      };
      GPSTimer.scheduleAtFixedRate(this.GPSTimerTask, 0L, l);
      return;
      if (GPSTimer != null)
      {
        GPSTimer.cancel();
        GPSTimer = null;
        GPSTimer = new Timer();
      }
    }
  }

  private void RemoveGPSTimer()
  {
    if (GPSTimer != null)
    {
      GPSTimer.cancel();
      GPSTimer = null;
    }
  }

  private void turnGPSOn()
  {
    String str = Settings.Secure.getString(getContentResolver(), "location_providers_allowed");
    Intent localIntent = new Intent();
    if (!str.contains("gps"))
    {
      localIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
      localIntent.addCategory("android.intent.category.ALTERNATIVE");
      localIntent.setData(Uri.parse("3"));
      sendBroadcast(localIntent);
    }
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
    setContentView(2130903055);
    this.imButtonNotifyOn = ((ImageButton)findViewById(2131165195));
    this.imButtonNotifyOff = ((ImageButton)findViewById(2131165196));
    this.imButtonOn = ((ImageButton)findViewById(2131165185));
    this.imButtonOff = ((ImageButton)findViewById(2131165186));
    this.imButtonGPSOn = ((ImageButton)findViewById(2131165202));
    this.imButtonGPSOff = ((ImageButton)findViewById(2131165203));
    this.btnBack = ((Button)findViewById(2131165189));
    this.imButtonMessage = ((Button)findViewById(2131165240));
    this.imButtonSave = ((Button)findViewById(2131165220));
    this.spinner = ((Spinner)findViewById(2131165201));
    ArrayAdapter localArrayAdapter = ArrayAdapter.createFromResource(getBaseContext(), 2131099649, 17367048);
    localArrayAdapter.setDropDownViewResource(17367049);
    this.spinner.setAdapter(localArrayAdapter);
    SharedPreferences localSharedPreferences = getSharedPreferences("myPrefs", 1);
    this.strSavedMem3 = localSharedPreferences.getString("mGPS", "");
    label296: String str1;
    String str2;
    if ((this.strSavedMem3.equals("0")) || (this.strSavedMem3.equals("")))
    {
      this.imButtonGPSOn.setBackgroundResource(2130837572);
      this.imButtonGPSOff.setBackgroundResource(2130837571);
      this.spinner.setEnabled(false);
      this.imGPSOff = true;
      this.imGPSOn = false;
      this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          Object localObject = paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
          if (localObject.equals("1 Minute"))
            if ((settings.this.strSavedMem3.equals("0")) || (settings.this.strSavedMem3.equals("")))
              settings.this.imGPSOff = false;
          while (true)
          {
            return;
            settings.this.imGPSOn = true;
            settings.this.mTime = 60000;
            settings.this.mTime1 = String.valueOf(settings.this.mTime);
            continue;
            if (localObject.equals("5 Minute"))
            {
              settings.this.imGPSOff = false;
              settings.this.imGPSOn = true;
              settings.this.mTime = 300000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
            else if (localObject.equals("10 Minute"))
            {
              settings.this.imGPSOff = false;
              settings.this.imGPSOn = true;
              settings.this.mTime = 600000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
            else if (localObject.equals("15 Minute"))
            {
              settings.this.imGPSOff = false;
              settings.this.imGPSOn = true;
              settings.this.mTime = 900000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
            else if (localObject.equals("30 Minute"))
            {
              settings.this.imGPSOff = false;
              settings.this.imGPSOn = true;
              settings.this.mTime = 1800000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
            else if (localObject.equals("1 Hrs"))
            {
              settings.this.imGPSOff = false;
              settings.this.imGPSOn = true;
              settings.this.mTime = 3600000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
            else if (localObject.equals("2 Hrs"))
            {
              settings.this.imGPSOff = false;
              settings.this.imGPSOn = true;
              settings.this.mTime = 7200000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
          }
        }

        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
        {
        }
      });
      if (!this.strSavedMem3.equals("60000"))
        break label563;
      this.spinner.setSelection(0);
      str1 = localSharedPreferences.getString("Notify", "");
      str2 = localSharedPreferences.getString("AutoStart", "");
      if (!str1.equals("On"))
        break label683;
      this.imButtonNotifyOn.setBackgroundResource(2130837573);
      this.imButtonNotifyOff.setBackgroundResource(2130837570);
      label355: if (!str2.equals("On"))
        break label717;
      this.imButtonOn.setBackgroundResource(2130837573);
      this.imButtonOff.setBackgroundResource(2130837570);
    }
    while (true)
    {
      this.btnBack.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.finish();
        }
      });
      this.imButtonNotifyOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.imButtonNotifyOn.setBackgroundResource(2130837573);
          settings.this.imButtonNotifyOff.setBackgroundResource(2130837570);
          settings.this.SavePreferences("Notify", "On");
        }
      });
      this.imButtonNotifyOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.imButtonNotifyOn.setBackgroundResource(2130837572);
          settings.this.imButtonNotifyOff.setBackgroundResource(2130837571);
          settings.this.SavePreferences("Notify", "Off");
        }
      });
      this.imButtonGPSOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.imButtonGPSOn.setBackgroundResource(2130837573);
          settings.this.imButtonGPSOff.setBackgroundResource(2130837570);
          settings.this.imGPSOff = false;
          settings.this.imGPSOn = true;
          settings.this.spinner.setEnabled(true);
        }
      });
      this.imButtonSave.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (settings.this.imGPSOn)
          {
            if (settings.this.strSavedMem3.equals("0"))
            {
              settings.this.mTime = 60000;
              settings.this.mTime1 = String.valueOf(settings.this.mTime);
            }
            settings.this.SavePreferences("mGPS", String.valueOf(settings.this.mTime));
            settings.this.AddGPSTimer();
            Log.e("Time:", "GPSTimerTASK : GPSTimerON");
          }
          while (true)
          {
            settings.this.finish();
            return;
            if (settings.this.imGPSOff)
            {
              if (settings.GPSTimer != null)
              {
                settings.GPSTimer.cancel();
                settings.GPSTimer = null;
                settings.GPSTimer = new Timer();
              }
              settings.this.SavePreferences("mGPS", String.valueOf(settings.this.mTime));
            }
          }
        }
      });
      this.imButtonGPSOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.RemoveGPSTimer();
          settings.this.turnGPSOn();
          if (!((LocationManager)settings.this.getSystemService("location")).isProviderEnabled("gps"))
            new AlertDialog.Builder(settings.this).setTitle("GPS Error!").setMessage("GPS is not available on this device. Please check your settings to make sure it is turned on.").setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                Intent localIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                settings.this.startActivity(localIntent);
              }
            }).show();
          settings.this.imButtonGPSOn.setBackgroundResource(2130837572);
          settings.this.imButtonGPSOff.setBackgroundResource(2130837571);
          settings.this.mTime = 0;
          settings.this.imGPSOff = true;
          settings.this.imGPSOn = false;
          settings.this.spinner.setEnabled(false);
        }
      });
      this.imButtonOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.imButtonOn.setBackgroundResource(2130837573);
          settings.this.imButtonOff.setBackgroundResource(2130837570);
          settings.this.SavePreferences("AutoStart", "On");
        }
      });
      this.imButtonOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          settings.this.imButtonOn.setBackgroundResource(2130837572);
          settings.this.imButtonOff.setBackgroundResource(2130837571);
          settings.this.SavePreferences("AutoStart", "Off");
        }
      });
      this.imButtonMessage.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(settings.this.getBaseContext(), MessageSettings.class);
          settings.this.startActivity(localIntent);
        }
      });
      return;
      this.imButtonGPSOn.setBackgroundResource(2130837573);
      this.imButtonGPSOff.setBackgroundResource(2130837570);
      this.spinner.setEnabled(true);
      this.imGPSOn = true;
      this.imGPSOff = false;
      break;
      label563: if (this.strSavedMem3.equals("300000"))
      {
        this.spinner.setSelection(1);
        break label296;
      }
      if (this.strSavedMem3.equals("600000"))
      {
        this.spinner.setSelection(2);
        break label296;
      }
      if (this.strSavedMem3.equals("1800000"))
      {
        this.spinner.setSelection(3);
        break label296;
      }
      if (this.strSavedMem3.equals("3600000"))
      {
        this.spinner.setSelection(4);
        break label296;
      }
      if (!this.strSavedMem3.equals("7200000"))
        break label296;
      this.spinner.setSelection(5);
      break label296;
      label683: if (!str1.equals("Off"))
        break label355;
      this.imButtonNotifyOn.setBackgroundResource(2130837572);
      this.imButtonNotifyOff.setBackgroundResource(2130837571);
      break label355;
      label717: if (str2.equals("Off"))
      {
        this.imButtonOn.setBackgroundResource(2130837572);
        this.imButtonOff.setBackgroundResource(2130837571);
      }
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.settings
 * JD-Core Version:    0.6.2
 */