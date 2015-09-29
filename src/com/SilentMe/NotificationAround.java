package com.SilentMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class NotificationAround extends Activity
{
  String LAT;
  String LNG;
  String _radius;
  Bundle extras;
  double feet;
  private Button imButtonBack;
  private Button imButtonSave;
  private ImageButton imButtonSilent;
  private ImageButton imButtonVibrate;
  String mLocation;
  private boolean mSlient;
  private boolean mVibrate;
  String mode;
  private Spinner spinner;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903053);
    this.extras = getIntent().getExtras();
    this.LAT = this.extras.getString("LAT");
    this.LNG = this.extras.getString("LNG");
    this.mLocation = this.extras.getString("LocationName");
    this.spinner = ((Spinner)findViewById(2131165201));
    this.imButtonSilent = ((ImageButton)findViewById(2131165224));
    this.imButtonVibrate = ((ImageButton)findViewById(2131165226));
    this.imButtonSave = ((Button)findViewById(2131165220));
    this.imButtonBack = ((Button)findViewById(2131165189));
    ArrayAdapter localArrayAdapter = ArrayAdapter.createFromResource(getBaseContext(), 2131099648, 17367048);
    localArrayAdapter.setDropDownViewResource(17367049);
    this.spinner.setAdapter(localArrayAdapter);
    this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        Object localObject = paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
        if (localObject.equals("100 ft"))
        {
          NotificationAround.this._radius = "100";
          NotificationAround.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround.this._radius));
        }
        while (true)
        {
          return;
          if (localObject.equals("200 ft"))
          {
            NotificationAround.this._radius = "200";
            NotificationAround.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround.this._radius));
          }
          else if (localObject.equals("300 ft"))
          {
            NotificationAround.this._radius = "300";
            NotificationAround.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround.this._radius));
          }
          else if (localObject.equals("400 ft"))
          {
            NotificationAround.this._radius = "400";
            NotificationAround.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround.this._radius));
          }
          else if (localObject.equals("500 ft"))
          {
            NotificationAround.this._radius = "500";
            NotificationAround.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround.this._radius));
          }
        }
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
      }
    });
    this.imButtonSilent.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NotificationAround.this.imButtonSilent.setBackgroundResource(2130837555);
        NotificationAround.this.imButtonVibrate.setBackgroundResource(2130837554);
        NotificationAround.this.mode = "Slient";
        NotificationAround.this.mSlient = true;
        NotificationAround.this.mVibrate = false;
      }
    });
    this.imButtonVibrate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NotificationAround.this.imButtonSilent.setBackgroundResource(2130837554);
        NotificationAround.this.imButtonVibrate.setBackgroundResource(2130837555);
        NotificationAround.this.mode = "Vibrate";
        NotificationAround.this.mSlient = false;
        NotificationAround.this.mVibrate = true;
      }
    });
    this.imButtonSave.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DBAdapter localDBAdapter = new DBAdapter(NotificationAround.this.getBaseContext());
        localDBAdapter.open();
        localDBAdapter.insert(NotificationAround.this.LAT, NotificationAround.this.LNG, NotificationAround.this.feet, NotificationAround.this.mLocation, NotificationAround.this._radius, NotificationAround.this.mode, "-1");
        Toast.makeText(NotificationAround.this.getBaseContext(), "Location has been saved successfully", 1).show();
        localDBAdapter.close();
        NotificationAround.this.finish();
      }
    });
    this.imButtonBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NotificationAround.this.finish();
      }
    });
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.NotificationAround
 * JD-Core Version:    0.6.2
 */