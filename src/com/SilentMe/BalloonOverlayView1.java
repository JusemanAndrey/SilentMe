package com.SilentMe;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class BalloonOverlayView1 extends FrameLayout
{
  String LAT;
  String LNG;
  double Lat;
  double Lng;
  Dialog _dialog;
  private Button imButtonNext;
  private RelativeLayout layout;
  private Context mContext;
  String mData;
  String mId;
  String mLocation;
  String mMode;
  private TextView title;

  public BalloonOverlayView1(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mContext = paramContext;
    setPadding(10, 0, 10, paramInt);
    this.layout = new RelativeLayout(paramContext);
    this.layout.setVisibility(0);
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903043, this.layout);
    this.title = ((TextView)localView.findViewById(2131165198));
    this.imButtonNext = ((Button)localView.findViewById(2131165199));
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
    this.mLocation = paramOverlayItem.getTitle();
    this.mData = paramOverlayItem.getSnippet();
    this.mMode = this.mData.substring(4);
    if ((this.mMode.equals("Slient")) || (this.mMode.equals("Vibrate")));
    for (this.mId = this.mData.substring(0, 1); ; this.mId = this.mData.substring(0, 2))
      do
      {
        this.title.setText(this.mLocation);
        this.imButtonNext.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(BalloonOverlayView1.this.mContext);
            localBuilder.setCancelable(true).setTitle("Select      ").setPositiveButton("Edit", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                MeZoneMap localMeZoneMap = (MeZoneMap)BalloonOverlayView1.this.mContext;
                localMeZoneMap.LAT = BalloonOverlayView1.this.LAT;
                localMeZoneMap.LNG = BalloonOverlayView1.this.LNG;
                localMeZoneMap.mLocation = BalloonOverlayView1.this.mLocation;
                localMeZoneMap.mData = BalloonOverlayView1.this.mData;
                localMeZoneMap.showActivity();
              }
            }).setNegativeButton("Delete", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(BalloonOverlayView1.this.mContext);
                localBuilder.setMessage("Are you sure you want to delete this location?").setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    MeZoneMap localMeZoneMap = (MeZoneMap)BalloonOverlayView1.this.mContext;
                    localMeZoneMap.mId = BalloonOverlayView1.this.mId;
                    localMeZoneMap.deleteRow(BalloonOverlayView1.this.mId);
                  }
                }).setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                  {
                    paramAnonymous3DialogInterface.cancel();
                  }
                });
                localBuilder.create().show();
              }
            });
            localBuilder.create().show();
          }
        });
        return;
      }
      while ((!this.mMode.equals("0Slient")) && (!this.mMode.equals("0Vibrate")));
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.BalloonOverlayView1
 * JD-Core Version:    0.6.2
 */