package com.SilentMe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Welcome extends Activity
{
  protected boolean _active = true;
  protected int _splashTime = 5000;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903046);
    new Thread()
    {
      public void run()
      {
        int i = 0;
        try
        {
          while (true)
          {
            if (Welcome.this._active)
            {
              int j = Welcome.this._splashTime;
              if (i < j);
            }
            else
            {
              return;
            }
            sleep(100L);
            boolean bool = Welcome.this._active;
            if (bool)
              i += 100;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
          {
            Welcome.this.finish();
            Welcome.this.startActivity(new Intent(Welcome.this, SearchHome.class));
          }
        }
        finally
        {
          Welcome.this.finish();
          Welcome.this.startActivity(new Intent(Welcome.this, SearchHome.class));
        }
      }
    }
    .start();
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.Welcome
 * JD-Core Version:    0.6.2
 */