package com.SilentMe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class info extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    ((RelativeLayout)findViewById(2131165211)).setVisibility(8);
    ((TextView)findViewById(2131165210)).setText("Welcome to Silent Me! The app you can set up and forget about. This app will do all the work for you and you will never have to remember to silence your phone again, making your smart phone... well, smart!\n\nHow does it work? Simple, you choose the location either by address or by dropping a pin via your coordinates via GPS. Once the pin is placed, you determine the range of your silent zone and whether you want silent or vibration. Once this is done, close the app and let it do the rest of the work for you.\n\nIf you want to get a little more creative, you can have an auto reply text sent to those trying to contact you while you are in your silent zone. Now your significant other won't think you are ignoring them when you actually miss their text or phone call.\n\nNow that you are done, whenever you walk into your silent zone, your phone will automatically silence itself and will go back to normal after you leave it. Sound simple? It is! We hope you enjoy our app and allows you piece of mind from worrying if your phone is silenced in those critical moments at work, school, home or while at the movies.\n");
    ((Button)findViewById(2131165189)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        info.this.finish();
      }
    });
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.info
 * JD-Core Version:    0.6.2
 */