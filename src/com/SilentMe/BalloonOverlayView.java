package com.SilentMe;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class BalloonOverlayView extends FrameLayout
{
  String LAT;
  String LNG;
  double Lat;
  double Lng;
  Dialog _dialog;
  private EditText etAddress;
  private Button imButtonNext;
  private RelativeLayout layout;
  private Context mContext;
  String mLocation;
  private TextView title;

  public BalloonOverlayView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mContext = paramContext;
    setPadding(10, 0, 10, paramInt);
    this.layout = new RelativeLayout(paramContext);
    this.layout.setVisibility(0);
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903043, this.layout);
    this.imButtonNext = ((Button)localView.findViewById(2131165199));
    this.title = ((TextView)localView.findViewById(2131165198));
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 0;
    addView(this.layout, localLayoutParams);
  }

  public void setData(OverlayItem paramOverlayItem)
  {
    GeoPoint localGeoPoint = paramOverlayItem.getPoint();
    this.Lat = localGeoPoint.getLatitudeE6();
    this.Lng = localGeoPoint.getLongitudeE6();
    this.LAT = String.valueOf(this.Lat / 1000000.0D);
    this.LNG = String.valueOf(this.Lng / 1000000.0D);
    this.mLocation = paramOverlayItem.getSnippet();
    this.title.setText(paramOverlayItem.getSnippet());
    this.imButtonNext.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((SearchHome)BalloonOverlayView.this.mContext).showActivity();
      }
    });
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.BalloonOverlayView
 * JD-Core Version:    0.6.2
 */