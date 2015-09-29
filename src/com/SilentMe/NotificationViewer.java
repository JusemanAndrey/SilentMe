package com.SilentMe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class NotificationViewer extends Activity
{
  private Button btnBack;
  private ImageButton imButtonOff;
  private ImageButton imButtonOn;
  private ImageButton imButtonSilent;
  private ImageButton imButtonVibrate;

  protected void SavePreferences(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("myPrefs", 1).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903052);
    this.imButtonSilent = ((ImageButton)findViewById(2131165224));
    this.imButtonVibrate = ((ImageButton)findViewById(2131165226));
    this.imButtonOn = ((ImageButton)findViewById(2131165185));
    this.imButtonOff = ((ImageButton)findViewById(2131165186));
    SharedPreferences localSharedPreferences = getSharedPreferences("myPrefs", 1);
    String str1 = localSharedPreferences.getString("mode", "");
    String str2 = localSharedPreferences.getString("notification", "");
    if (str1.equals("Slient"))
    {
      this.imButtonSilent.setBackgroundResource(2130837555);
      this.imButtonVibrate.setBackgroundResource(2130837554);
      if (!str2.equals("On"))
        break label268;
      this.imButtonOn.setBackgroundResource(2130837573);
      this.imButtonOff.setBackgroundResource(2130837570);
    }
    while (true)
    {
      this.btnBack = ((Button)findViewById(2131165189));
      this.btnBack.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationViewer.this.finish();
        }
      });
      this.imButtonSilent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationViewer.this.imButtonSilent.setBackgroundResource(2130837555);
          NotificationViewer.this.imButtonVibrate.setBackgroundResource(2130837554);
          NotificationViewer.this.SavePreferences("mode", "Slient");
        }
      });
      this.imButtonVibrate.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationViewer.this.imButtonSilent.setBackgroundResource(2130837554);
          NotificationViewer.this.imButtonVibrate.setBackgroundResource(2130837555);
          NotificationViewer.this.SavePreferences("mode", "Vibrate");
        }
      });
      this.imButtonOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationViewer.this.imButtonOn.setBackgroundResource(2130837573);
          NotificationViewer.this.imButtonOff.setBackgroundResource(2130837570);
          NotificationViewer.this.SavePreferences("notification", "On");
        }
      });
      this.imButtonOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NotificationViewer.this.imButtonOn.setBackgroundResource(2130837572);
          NotificationViewer.this.imButtonOff.setBackgroundResource(2130837571);
          NotificationViewer.this.SavePreferences("notification", "Off");
        }
      });
      return;
      if (!str1.equals("Vibrate"))
        break;
      this.imButtonSilent.setBackgroundResource(2130837554);
      this.imButtonVibrate.setBackgroundResource(2130837555);
      break;
      label268: if (str2.equals("Off"))
      {
        this.imButtonOn.setBackgroundResource(2130837572);
        this.imButtonOff.setBackgroundResource(2130837571);
      }
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.NotificationViewer
 * JD-Core Version:    0.6.2
 */