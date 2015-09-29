package com.SilentMe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.util.List;

public class MeZoneMap extends MapActivity
{
  public String LAT;
  public String LNG;
  private long LocID;
  String METERS;
  int _id;
  String _radius;
  private Button btnBack;
  Cursor c;
  double colLat;
  double colLng;
  String colName;
  double colRadius;
  DBAdapter db;
  Handler handler = new Handler();
  public String id;
  MyItemizedOverlay1 itemizedOverlay;
  Cursor mCursor;
  public String mData;
  public String mId;
  public String mLocation;
  MapController mMapController;
  private MapView mapView;
  String mode;
  private ProgressDialog pDialog = null;
  public String radius;
  private Runnable returnRes = new Runnable()
  {
    public void run()
    {
      MeZoneMap.this.mapView = ((MapView)MeZoneMap.this.findViewById(2131165213));
      MeZoneMap.this.mapView.clearAnimation();
      MeZoneMap.this.mapView.setTraffic(true);
      MeZoneMap.this.mapView.setSatellite(true);
      MeZoneMap.this.mapView.setBuiltInZoomControls(true);
      List localList = MeZoneMap.this.mapView.getOverlays();
      Drawable localDrawable = MeZoneMap.this.getResources().getDrawable(2130837541);
      MeZoneMap.this.c = MeZoneMap.this.db.getDetail(MeZoneMap.this.LocID);
      GeoPoint localGeoPoint;
      if ((MeZoneMap.this.c != null) && (MeZoneMap.this.c.getCount() > 0))
        localGeoPoint = null;
      for (boolean bool = MeZoneMap.this.c.moveToFirst(); ; bool = MeZoneMap.this.c.moveToNext())
      {
        if (!bool)
        {
          MeZoneMap.this.mMapController = MeZoneMap.this.mapView.getController();
          MeZoneMap.this.mMapController.setZoom(17);
          MeZoneMap.this.mMapController.animateTo(localGeoPoint);
          MeZoneMap.this.pDialog.dismiss();
          MeZoneMap.this.db.close();
          return;
        }
        MeZoneMap.this._id = MeZoneMap.this.c.getInt(MeZoneMap.this.c.getColumnIndex("_id"));
        MeZoneMap.this.colLat = MeZoneMap.this.c.getDouble(MeZoneMap.this.c.getColumnIndex("lat"));
        MeZoneMap.this.colLng = MeZoneMap.this.c.getDouble(MeZoneMap.this.c.getColumnIndex("lng"));
        MeZoneMap.this.colRadius = MeZoneMap.this.c.getDouble(MeZoneMap.this.c.getColumnIndex("radius"));
        MeZoneMap.this.METERS = MeZoneMap.this.c.getString(MeZoneMap.this.c.getColumnIndex("meters"));
        MeZoneMap.this.colName = MeZoneMap.this.c.getString(MeZoneMap.this.c.getColumnIndex("loc_name"));
        MeZoneMap.this.mode = MeZoneMap.this.c.getString(MeZoneMap.this.c.getColumnIndex("mode"));
        MeZoneMap.this.itemizedOverlay = new MyItemizedOverlay1(localDrawable, MeZoneMap.this.mapView);
        localGeoPoint = new GeoPoint((int)(1000000.0D * MeZoneMap.this.colLat), (int)(1000000.0D * MeZoneMap.this.colLng));
        OverlayItem localOverlayItem = new OverlayItem(localGeoPoint, MeZoneMap.this.colName, String.valueOf(MeZoneMap.this._id) + MeZoneMap.this.METERS + MeZoneMap.this.mode);
        MeZoneMap.this.itemizedOverlay.addOverlay(localOverlayItem);
        localList.add(MeZoneMap.this.itemizedOverlay);
      }
    }
  };
  private Runnable viewMap;

  private void startFetchingLocation()
  {
    this.viewMap = new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(500L);
          MeZoneMap.this.db.open();
          MeZoneMap.this.c = MeZoneMap.this.db.getDetail(MeZoneMap.this.LocID);
          MeZoneMap.this.runOnUiThread(MeZoneMap.this.returnRes);
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    };
    new Thread(null, this.viewMap, "").start();
    this.pDialog = ProgressDialog.show(this, "Please wait...", "Loading...", true);
    this.pDialog.setCancelable(true);
  }

  public void deleteRow(String paramString)
  {
    DBAdapter localDBAdapter = new DBAdapter(getBaseContext());
    localDBAdapter.open();
    localDBAdapter.deleteRow(paramString);
    localDBAdapter.close();
    Toast.makeText(getBaseContext(), "Location has been deleted successfully", 1).show();
    finish();
  }

  protected boolean isRouteDisplayed()
  {
    return true;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903047);
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null);
    for (long l = localBundle.getLong("LocID"); ; l = 0L)
    {
      this.LocID = l;
      this.db = new DBAdapter(this);
      startFetchingLocation();
      this.btnBack = ((Button)findViewById(2131165189));
      this.btnBack.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MeZoneMap.this.finish();
        }
      });
      return;
    }
  }

  public void showActivity()
  {
    Intent localIntent = new Intent(getBaseContext(), NotificationAround1.class);
    localIntent.putExtra("LAT", this.LAT);
    localIntent.putExtra("LNG", this.LNG);
    localIntent.putExtra("LocationName", this.mLocation);
    localIntent.putExtra("DATA", this.mData);
    startActivity(localIntent);
    finish();
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.MeZoneMap
 * JD-Core Version:    0.6.2
 */