package com.SilentMe;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MessageSettings extends Activity
{
  String IsMessage;
  private Button btnBack;
  ImageButton btnMsgOff;
  ImageButton btnMsgOn;
  private EditText etMessage;
  private Button imButtonSave;
  String message;

  protected void SavePreferences(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("myPrefs", 1).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903050);
    this.btnBack = ((Button)findViewById(2131165189));
    this.btnMsgOn = ((ImageButton)findViewById(2131165222));
    this.btnMsgOff = ((ImageButton)findViewById(2131165223));
    this.etMessage = ((EditText)findViewById(2131165219));
    SharedPreferences localSharedPreferences = getSharedPreferences("myPrefs", 1);
    this.message = localSharedPreferences.getString("message", "");
    this.IsMessage = localSharedPreferences.getString("IsMessage", "On");
    this.imButtonSave = ((Button)findViewById(2131165220));
    this.btnBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MessageSettings.this.finish();
      }
    });
    if (this.message.equals(""))
      this.message = "Call me later.I am in slientzone area.";
    this.etMessage.setText(this.message);
    this.imButtonSave.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MessageSettings.this.message = MessageSettings.this.etMessage.getText().toString();
        MessageSettings.this.SavePreferences("message", MessageSettings.this.message);
        Toast.makeText(MessageSettings.this.getBaseContext(), "Your message has been saved successfully", 1).show();
        MessageSettings.this.finish();
      }
    });
    if (this.IsMessage.equals("On"))
    {
      this.btnMsgOn.setBackgroundResource(2130837573);
      this.btnMsgOff.setBackgroundResource(2130837570);
    }
    if (this.IsMessage.equals("Off"))
    {
      this.btnMsgOn.setBackgroundResource(2130837572);
      this.btnMsgOff.setBackgroundResource(2130837571);
    }
    this.btnMsgOn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MessageSettings.this.btnMsgOn.setBackgroundResource(2130837573);
        MessageSettings.this.btnMsgOff.setBackgroundResource(2130837570);
        MessageSettings.this.SavePreferences("IsMessage", "On");
      }
    });
    this.btnMsgOff.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MessageSettings.this.btnMsgOn.setBackgroundResource(2130837572);
        MessageSettings.this.btnMsgOff.setBackgroundResource(2130837571);
        MessageSettings.this.SavePreferences("IsMessage", "Off");
      }
    });
  }

  public static class LinedEditText extends EditText
  {
    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();

    public LinedEditText(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      this.mPaint.setStyle(Paint.Style.STROKE);
      this.mPaint.setColor(0);
    }

    protected void onDraw(Canvas paramCanvas)
    {
      int i = getLineCount();
      Rect localRect = this.mRect;
      Paint localPaint = this.mPaint;
      for (int j = 0; ; j++)
      {
        if (j >= i)
        {
          super.onDraw(paramCanvas);
          return;
        }
        int k = getLineBounds(j, localRect);
        paramCanvas.drawLine(localRect.left, k + 1, localRect.right, k + 1, localPaint);
      }
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.MessageSettings
 * JD-Core Version:    0.6.2
 */