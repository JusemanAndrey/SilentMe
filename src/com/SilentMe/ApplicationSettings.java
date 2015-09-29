package com.SilentMe;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
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
import android.view.LayoutInflater;
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

public class ApplicationSettings extends Activity
{
  private static final int DIALOG_AppSettings = 1;
  private static final int DIALOG_AudibleSettings = 4;
  private static final int DIALOG_GPSSettings = 3;
  static Timer GPSTimer = null;
  TimerTask GPSTimerTask;
  public boolean _active = false;
  Button btnClose;
  long elapsedTime;
  private ImageButton imButtonGPSOff;
  private ImageButton imButtonGPSOn;
  private ImageButton imButtonNotifyOff;
  private ImageButton imButtonNotifyOn;
  private ImageButton imButtonOff;
  private ImageButton imButtonOn;
  private boolean imGPSOff;
  private boolean imGPSOn;
  boolean isGPSON = false;
  boolean isStart = false;
  View layout;
  Dialog mDialog;
  private int mTime;
  String mTime1;
  private Spinner spinner;
  long startTime;
  private String strSavedMem1;
  private String strSavedMem2;
  private String strSavedMem3;
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
          if (!((LocationManager)ApplicationSettings.this.getSystemService("location")).isProviderEnabled("gps"))
          {
            ApplicationSettings.this.turnGPSOn();
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

  private void createDialog(int paramInt)
  {
    GetAppPref();
    switch (paramInt)
    {
    case 2:
    default:
    case 1:
    case 4:
    case 3:
    }
    while (true)
    {
      return;
      this.mDialog = new Dialog(this, 16973840);
      this.mDialog.setCancelable(false);
      this.layout = getLayoutInflater().inflate(2130903040, null);
      this.mDialog.setContentView(this.layout);
      this.mDialog.show();
      this.imButtonOn = ((ImageButton)this.layout.findViewById(2131165185));
      this.imButtonOff = ((ImageButton)this.layout.findViewById(2131165186));
      if (this.strSavedMem2.equals("On"))
      {
        this.imButtonOn.setBackgroundResource(2130837573);
        this.imButtonOff.setBackgroundResource(2130837570);
      }
      while (true)
      {
        this.btnClose = ((Button)this.layout.findViewById(2131165184));
        this.btnClose.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.mDialog.dismiss();
          }
        });
        this.imButtonOn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.imButtonOn.setBackgroundResource(2130837573);
            ApplicationSettings.this.imButtonOff.setBackgroundResource(2130837570);
            ApplicationSettings.this.SavePreferences("AutoStart", "On");
          }
        });
        this.imButtonOff.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.imButtonOn.setBackgroundResource(2130837572);
            ApplicationSettings.this.imButtonOff.setBackgroundResource(2130837571);
            ApplicationSettings.this.SavePreferences("AutoStart", "Off");
          }
        });
        break;
        if (this.strSavedMem2.equals("Off"))
        {
          this.imButtonOn.setBackgroundResource(2130837572);
          this.imButtonOff.setBackgroundResource(2130837571);
        }
      }
      this.mDialog = new Dialog(this, 16973840);
      this.mDialog.setCancelable(false);
      this.layout = getLayoutInflater().inflate(2130903042, null);
      this.mDialog.setContentView(this.layout);
      this.mDialog.show();
      this.imButtonNotifyOn = ((ImageButton)this.layout.findViewById(2131165195));
      this.imButtonNotifyOff = ((ImageButton)this.layout.findViewById(2131165196));
      if (this.strSavedMem1.equals("On"))
      {
        this.imButtonNotifyOn.setBackgroundResource(2130837573);
        this.imButtonNotifyOff.setBackgroundResource(2130837570);
      }
      while (true)
      {
        this.btnClose = ((Button)this.layout.findViewById(2131165184));
        this.btnClose.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.mDialog.dismiss();
          }
        });
        this.imButtonNotifyOn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.imButtonNotifyOn.setBackgroundResource(2130837573);
            ApplicationSettings.this.imButtonNotifyOff.setBackgroundResource(2130837570);
            ApplicationSettings.this.SavePreferences("Notify", "On");
          }
        });
        this.imButtonNotifyOff.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.imButtonNotifyOn.setBackgroundResource(2130837572);
            ApplicationSettings.this.imButtonNotifyOff.setBackgroundResource(2130837571);
            ApplicationSettings.this.SavePreferences("Notify", "Off");
          }
        });
        break;
        if (this.strSavedMem1.equals("Off"))
        {
          this.imButtonNotifyOn.setBackgroundResource(2130837572);
          this.imButtonNotifyOff.setBackgroundResource(2130837571);
        }
      }
      this.mDialog = new Dialog(this, 16973840);
      this.mDialog.setCancelable(false);
      this.layout = getLayoutInflater().inflate(2130903044, null);
      this.mDialog.setContentView(this.layout);
      this.mDialog.show();
      this.imButtonGPSOn = ((ImageButton)this.layout.findViewById(2131165202));
      this.imButtonGPSOff = ((ImageButton)this.layout.findViewById(2131165203));
      this.spinner = ((Spinner)this.layout.findViewById(2131165201));
      ArrayAdapter localArrayAdapter = ArrayAdapter.createFromResource(getBaseContext(), 2131099649, 17367048);
      localArrayAdapter.setDropDownViewResource(17367049);
      this.spinner.setAdapter(localArrayAdapter);
      if ((this.strSavedMem3.equals("0")) || (this.strSavedMem3.equals("")))
      {
        this.imButtonGPSOn.setBackgroundResource(2130837572);
        this.imButtonGPSOff.setBackgroundResource(2130837571);
        this.spinner.setEnabled(false);
        this.imGPSOff = true;
        this.imGPSOn = false;
      }
      while (true)
      {
        this.btnClose = ((Button)this.layout.findViewById(2131165184));
        this.btnClose.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.mDialog.dismiss();
            ApplicationSettings.this.SaveGPSSettings();
          }
        });
        this.imButtonGPSOn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.imButtonGPSOn.setBackgroundResource(2130837573);
            ApplicationSettings.this.imButtonGPSOff.setBackgroundResource(2130837570);
            ApplicationSettings.this.imGPSOff = false;
            ApplicationSettings.this.imGPSOn = true;
            ApplicationSettings.this.spinner.setEnabled(true);
          }
        });
        this.imButtonGPSOff.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ApplicationSettings.this.RemoveGPSTimer();
            ApplicationSettings.this.turnGPSOn();
            if (!((LocationManager)ApplicationSettings.this.getSystemService("location")).isProviderEnabled("gps"))
              new AlertDialog.Builder(ApplicationSettings.this).setTitle("GPS Error!").setMessage("GPS is not available on this device. Please check your settings to make sure it is turned on.").setPositiveButton("OK", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  Intent localIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                  ApplicationSettings.this.startActivity(localIntent);
                }
              }).show();
            ApplicationSettings.this.imButtonGPSOn.setBackgroundResource(2130837572);
            ApplicationSettings.this.imButtonGPSOff.setBackgroundResource(2130837571);
            ApplicationSettings.this.mTime = 0;
            ApplicationSettings.this.imGPSOff = true;
            ApplicationSettings.this.imGPSOn = false;
            ApplicationSettings.this.spinner.setEnabled(false);
          }
        });
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            Object localObject = paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
            if (localObject.equals("1 Minute"))
              if ((ApplicationSettings.this.strSavedMem3.equals("0")) || (ApplicationSettings.this.strSavedMem3.equals("")))
                ApplicationSettings.this.imGPSOff = false;
            while (true)
            {
              return;
              ApplicationSettings.this.imGPSOn = true;
              ApplicationSettings.this.mTime = 60000;
              ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              continue;
              if (localObject.equals("5 Minute"))
              {
                ApplicationSettings.this.imGPSOff = false;
                ApplicationSettings.this.imGPSOn = true;
                ApplicationSettings.this.mTime = 300000;
                ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              }
              else if (localObject.equals("10 Minute"))
              {
                ApplicationSettings.this.imGPSOff = false;
                ApplicationSettings.this.imGPSOn = true;
                ApplicationSettings.this.mTime = 600000;
                ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              }
              else if (localObject.equals("15 Minute"))
              {
                ApplicationSettings.this.imGPSOff = false;
                ApplicationSettings.this.imGPSOn = true;
                ApplicationSettings.this.mTime = 900000;
                ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              }
              else if (localObject.equals("30 Minute"))
              {
                ApplicationSettings.this.imGPSOff = false;
                ApplicationSettings.this.imGPSOn = true;
                ApplicationSettings.this.mTime = 1800000;
                ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              }
              else if (localObject.equals("1 Hrs"))
              {
                ApplicationSettings.this.imGPSOff = false;
                ApplicationSettings.this.imGPSOn = true;
                ApplicationSettings.this.mTime = 3600000;
                ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              }
              else if (localObject.equals("2 Hrs"))
              {
                ApplicationSettings.this.imGPSOff = false;
                ApplicationSettings.this.imGPSOn = true;
                ApplicationSettings.this.mTime = 7200000;
                ApplicationSettings.this.mTime1 = String.valueOf(ApplicationSettings.this.mTime);
              }
            }
          }

          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
          {
          }
        });
        if (!this.strSavedMem3.equals("60000"))
          break label799;
        this.spinner.setSelection(0);
        break;
        this.imButtonGPSOn.setBackgroundResource(2130837573);
        this.imButtonGPSOff.setBackgroundResource(2130837570);
        this.spinner.setEnabled(true);
        this.imGPSOn = true;
        this.imGPSOff = false;
      }
      label799: if (this.strSavedMem3.equals("300000"))
        this.spinner.setSelection(1);
      else if (this.strSavedMem3.equals("600000"))
        this.spinner.setSelection(2);
      else if (this.strSavedMem3.equals("1800000"))
        this.spinner.setSelection(3);
      else if (this.strSavedMem3.equals("3600000"))
        this.spinner.setSelection(4);
      else if (this.strSavedMem3.equals("7200000"))
        this.spinner.setSelection(5);
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

  public void GetAppPref()
  {
    SharedPreferences localSharedPreferences = getSharedPreferences("myPrefs", 1);
    this.strSavedMem3 = localSharedPreferences.getString("mGPS", "");
    this.strSavedMem1 = localSharedPreferences.getString("Notify", "");
    this.strSavedMem2 = localSharedPreferences.getString("AutoStart", "");
  }

  public void SaveGPSSettings()
  {
    if (this.imGPSOn)
    {
      if (this.strSavedMem3.equals("0"))
      {
        this.mTime = 60000;
        this.mTime1 = String.valueOf(this.mTime);
      }
      SavePreferences("mGPS", String.valueOf(this.mTime));
      AddGPSTimer();
      Log.e("Time:", "GPSTimerTASK : GPSTimerON");
    }
    while (true)
    {
      return;
      if (this.imGPSOff)
      {
        if (GPSTimer != null)
        {
          GPSTimer.cancel();
          GPSTimer = null;
          GPSTimer = new Timer();
        }
        SavePreferences("mGPS", String.valueOf(this.mTime));
      }
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
    setContentView(2130903041);
    ((Button)findViewById(2131165189)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ApplicationSettings.this.finish();
      }
    });
    ((Button)findViewById(2131165190)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ApplicationSettings.this.createDialog(1);
      }
    });
    ((Button)findViewById(2131165191)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(ApplicationSettings.this, NotificationViewer.class);
        ApplicationSettings.this.startActivity(localIntent);
      }
    });
    ((Button)findViewById(2131165192)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ApplicationSettings.this.createDialog(3);
      }
    });
    ((Button)findViewById(2131165193)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ApplicationSettings.this.createDialog(4);
      }
    });
    ((Button)findViewById(2131165194)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(ApplicationSettings.this, MessageSettings.class);
        ApplicationSettings.this.startActivity(localIntent);
      }
    });
    GetAppPref();
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.ApplicationSettings
 * JD-Core Version:    0.6.2
 */