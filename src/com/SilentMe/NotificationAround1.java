package com.SilentMe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

public class NotificationAround1 extends Activity
{
  String LAT;
  String LNG;
  String _radius;
  Cursor cd;
  Bundle extras;
  double feet;
  private Button imButtonBack;
  private Button imButtonSave;
  private ImageButton imButtonSilent;
  private ImageButton imButtonVibrate;
  String mData;
  String mId;
  String mLocation;
  String mMode;
  String mRadius;
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
    this.mData = this.extras.getString("DATA");
    this.mId = this.mData.substring(0, 1);
    this.mRadius = this.mData.substring(1, 4);
    this.mMode = this.mData.substring(4);
    this.imButtonSilent = ((ImageButton)findViewById(2131165224));
    this.imButtonVibrate = ((ImageButton)findViewById(2131165226));
    if ((this.mMode.equals("Slient")) || (this.mMode.equals("Vibrate")))
      if (this.mMode.equals("Slient"))
      {
        this.imButtonSilent.setBackgroundResource(2130837555);
        this.imButtonVibrate.setBackgroundResource(2130837554);
        this.spinner = ((Spinner)findViewById(2131165201));
        this.imButtonBack = ((Button)findViewById(2131165189));
        this.imButtonSave = ((Button)findViewById(2131165220));
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
              NotificationAround1.this.mRadius = "100";
              NotificationAround1.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround1.this.mRadius));
            }
            while (true)
            {
              return;
              if (localObject.equals("200 ft"))
              {
                NotificationAround1.this.mRadius = "200";
                NotificationAround1.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround1.this.mRadius));
              }
              else if (localObject.equals("300 ft"))
              {
                NotificationAround1.this.mRadius = "300";
                NotificationAround1.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround1.this.mRadius));
              }
              else if (localObject.equals("400 ft"))
              {
                NotificationAround1.this.mRadius = "400";
                NotificationAround1.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround1.this.mRadius));
              }
              else if (localObject.equals("500 ft"))
              {
                NotificationAround1.this.mRadius = "500";
                NotificationAround1.this.feet = (3.28083989501D * Integer.parseInt(NotificationAround1.this.mRadius));
              }
            }
          }

          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
          {
          }
        });
        if (!this.mRadius.equals("100"))
          break label514;
        this.spinner.setSelection(0);
      }
    while (true)
    {
      this.imButtonSilent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationAround1.this.imButtonSilent.setBackgroundResource(2130837555);
          NotificationAround1.this.imButtonVibrate.setBackgroundResource(2130837554);
          NotificationAround1.this.mMode = "Slient";
        }
      });
      this.imButtonVibrate.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationAround1.this.imButtonSilent.setBackgroundResource(2130837554);
          NotificationAround1.this.imButtonVibrate.setBackgroundResource(2130837555);
          NotificationAround1.this.mMode = "Vibrate";
        }
      });
      this.imButtonBack.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationAround1.this.finish();
        }
      });
      this.imButtonSave.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DBAdapter localDBAdapter = new DBAdapter(NotificationAround1.this.getBaseContext());
          localDBAdapter.open();
          localDBAdapter.updateValues(NotificationAround1.this.mId, NotificationAround1.this.feet, NotificationAround1.this.mRadius, NotificationAround1.this.mMode);
          Toast.makeText(NotificationAround1.this.getBaseContext(), "Location has been updated successfully", 1).show();
          localDBAdapter.close();
          NotificationAround1.this.finish();
        }
      });
      return;
      if (!this.mMode.equals("Vibrate"))
        break;
      this.imButtonSilent.setBackgroundResource(2130837554);
      this.imButtonVibrate.setBackgroundResource(2130837555);
      break;
      if ((!this.mMode.equals("0Slient")) && (!this.mMode.equals("0Vibrate")))
        break;
      this.mId = this.mData.substring(0, 2);
      this.mRadius = this.mData.substring(2, 5);
      this.mMode = this.mData.substring(5);
      if (this.mMode.equals("Slient"))
      {
        this.imButtonSilent.setBackgroundResource(2130837555);
        this.imButtonVibrate.setBackgroundResource(2130837554);
        break;
      }
      if (!this.mMode.equals("Vibrate"))
        break;
      this.imButtonSilent.setBackgroundResource(2130837554);
      this.imButtonVibrate.setBackgroundResource(2130837555);
      break;
      label514: if (this.mRadius.equals("200"))
        this.spinner.setSelection(1);
      else if (this.mRadius.equals("300"))
        this.spinner.setSelection(2);
      else if (this.mRadius.equals("400"))
        this.spinner.setSelection(3);
      else if (this.mRadius.equals("500"))
        this.spinner.setSelection(4);
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.NotificationAround1
 * JD-Core Version:    0.6.2
 */