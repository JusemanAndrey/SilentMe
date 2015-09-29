package com.SilentMe;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SearchHome extends MapActivity
{
  String Address1;
  String Address2;
  String Address3;
  int ArraySize = 0;
  String LAT;
  String LNG;
  double Lat = 0.0D;
  double Long = 0.0D;
  Location UpdatedLocation;
  String _radius;
  List<Address> addresses;
  double argLat;
  double argLng;
  private Button btnInfo;
  private Button btnMeZones;
  private Button btnSearch;
  private Button btnSearchCancel;
  private Button btnSettings;
  public double currentLatitude;
  public double currentLongitude;
  Drawable drawable;
  private EditText etSearch;
  double feet;
  Handler handler = new Handler();
  GeoPoint initGeoPoint;
  MyItemizedOverlay itemizedOverlay;
  double lat;
  double lng;
  private LocationListener locationListener;
  private LocationManager locationManager;
  String mAddress;
  String mLocation;
  private MapController mapController;
  List<Overlay> mapOverlays;
  private MapView mapView;
  public double miles;
  private ProgressDialog pDialog = null;
  GeoPoint point;
  private Runnable returnRes = new Runnable()
  {
    public void run()
    {
      SearchHome.this.ArraySize = SearchHome.this.addresses.size();
      if (SearchHome.this.ArraySize == 1)
      {
        SearchHome.this.mapController = SearchHome.this.mapView.getController();
        SearchHome.this.lat = ((Address)SearchHome.this.addresses.get(0)).getLatitude();
        SearchHome.this.lng = ((Address)SearchHome.this.addresses.get(0)).getLongitude();
        SearchHome.this.LAT = String.valueOf(SearchHome.this.lat);
        SearchHome.this.LNG = String.valueOf(SearchHome.this.lng);
        SearchHome.this.Address1 = ((Address)SearchHome.this.addresses.get(0)).getAddressLine(0);
        SearchHome.this.Address2 = ((Address)SearchHome.this.addresses.get(0)).getAddressLine(1);
        SearchHome.this.Address3 = ((Address)SearchHome.this.addresses.get(0)).getAddressLine(2);
        SearchHome.this.mAddress = (SearchHome.this.Address1 + ", " + SearchHome.this.Address2 + ", " + SearchHome.this.Address3);
        SearchHome.this.point = new GeoPoint((int)(1000000.0D * SearchHome.this.lat), (int)(1000000.0D * SearchHome.this.lng));
        SearchHome.this.mapController.animateTo(SearchHome.this.point);
        SearchHome.this.mapController.setZoom(17);
        SearchHome.this.mapView.invalidate();
        SearchHome.this.mapOverlays = SearchHome.this.mapView.getOverlays();
        Drawable localDrawable = SearchHome.this.getResources().getDrawable(2130837541);
        SearchHome.this.itemizedOverlay = new MyItemizedOverlay(localDrawable, SearchHome.this.mapView);
        OverlayItem localOverlayItem = new OverlayItem(SearchHome.this.point, "", SearchHome.this.mAddress);
        SearchHome.this.itemizedOverlay.addOverlay(localOverlayItem);
        SearchHome.this.mapOverlays.add(SearchHome.this.itemizedOverlay);
      }
      while (true)
      {
        SearchHome.this.pDialog.dismiss();
        return;
        for (int i = 0; i < SearchHome.this.addresses.size() - 1; i++)
        {
          SearchHome.this.point = new GeoPoint((int)(1000000.0D * ((Address)SearchHome.this.addresses.get(i)).getLatitude()), (int)(1000000.0D * ((Address)SearchHome.this.addresses.get(i)).getLongitude()));
          SearchHome.this.Address1 = ((Address)SearchHome.this.addresses.get(0)).getAddressLine(0);
          SearchHome.this.Address2 = ((Address)SearchHome.this.addresses.get(0)).getAddressLine(1);
          SearchHome.this.Address3 = ((Address)SearchHome.this.addresses.get(0)).getAddressLine(2);
          SearchHome.this.mAddress = (SearchHome.this.Address1 + "," + SearchHome.this.Address2 + "," + SearchHome.this.Address3);
          SearchHome.this.mapController = SearchHome.this.mapView.getController();
          SearchHome.this.mapController.animateTo(SearchHome.this.point);
          SearchHome.this.mapController.setZoom(17);
          SearchHome.this.mapView.invalidate();
        }
      }
    }
  };
  private Runnable viewMap;

  private void ShowInfo()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.setCancelable(true);
    localDialog.setTitle("                   Instructions");
    View localView = getLayoutInflater().inflate(2130903045, null);
    ((RelativeLayout)localView.findViewById(2131165205)).setVisibility(8);
    Button localButton = (Button)localView.findViewById(2131165212);
    ((TextView)localView.findViewById(2131165210)).setText("Welcome to Silent Me! The app you can set up and forget about. This app will do all the work for you and you will never have to remember to silence your phone again, making your smart phone... well, smart!\n\nHow does it work? Simple, you choose the location either by address or by dropping a pin via your coordinates via GPS. Once the pin is placed, you determine the range of your silent zone and whether you want silent or vibration. Once this is done, close the app and let it do the rest of the work for you.\n\nIf you want to get a little more creative, you can have an auto reply text sent to those trying to contact you while you are in your silent zone. Now your significant other won't think you are ignoring them when you actually miss their text or phone call.\n\nNow that you are done, whenever you walk into your silent zone, your phone will automatically silence itself and will go back to normal after you leave it. Sound simple? It is! We hope you enjoy our app and allows you piece of mind from worrying if your phone is silenced in those critical moments at work, school, home or while at the movies.\n\n\n\n");
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localDialog.setContentView(localView);
    localDialog.show();
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

  void getPresentLocation()
  {
    Location localLocation1 = this.locationManager.getLastKnownLocation("gps");
    Location localLocation2 = this.locationManager.getLastKnownLocation("network");
    Geocoder localGeocoder2;
    if ((this.Lat == 0.0D) && (this.Long == 0.0D))
    {
      if (localLocation1 == null)
        break label372;
      this.Lat = localLocation1.getLatitude();
      this.Long = localLocation1.getLongitude();
      localGeocoder2 = new Geocoder(getApplicationContext(), Locale.getDefault());
    }
    while (true)
    {
      try
      {
        this.addresses = localGeocoder2.getFromLocation(this.Lat, this.Long, 1);
        this.LAT = String.valueOf(this.Lat);
        this.LNG = String.valueOf(this.Long);
        this.Address1 = ((Address)this.addresses.get(0)).getAddressLine(0);
        this.Address2 = ((Address)this.addresses.get(0)).getAddressLine(1);
        this.Address3 = ((Address)this.addresses.get(0)).getAddressLine(2);
        this.mAddress = (this.Address1 + ", " + this.Address2 + ", " + this.Address3);
        this.point = new GeoPoint((int)(1000000.0D * this.Lat), (int)(1000000.0D * this.Long));
        this.mapOverlays = this.mapView.getOverlays();
        this.itemizedOverlay = new MyItemizedOverlay(getResources().getDrawable(2130837543), this.mapView);
        OverlayItem localOverlayItem2 = new OverlayItem(this.point, "", this.mAddress);
        this.itemizedOverlay.addOverlay(localOverlayItem2);
        this.mapOverlays.add(this.itemizedOverlay);
        this.mapController.setZoom(17);
        this.mapView.invalidate();
        this.mapController.animateTo(this.point);
        return;
      }
      catch (IOException localIOException2)
      {
        localIOException2.printStackTrace();
        continue;
      }
      label372: if (localLocation2 != null)
      {
        this.Lat = localLocation2.getLatitude();
        this.Long = localLocation2.getLongitude();
        Geocoder localGeocoder1 = new Geocoder(getApplicationContext(), Locale.getDefault());
        try
        {
          this.addresses = localGeocoder1.getFromLocation(this.Lat, this.Long, 1);
          this.LAT = String.valueOf(this.Lat);
          this.LNG = String.valueOf(this.Long);
          this.Address1 = ((Address)this.addresses.get(0)).getAddressLine(0);
          this.Address2 = ((Address)this.addresses.get(0)).getAddressLine(1);
          this.Address3 = ((Address)this.addresses.get(0)).getAddressLine(2);
          this.mAddress = (this.Address1 + ", " + this.Address2 + ", " + this.Address3);
          this.point = new GeoPoint((int)(1000000.0D * this.Lat), (int)(1000000.0D * this.Long));
          this.mapController.animateTo(this.point);
          this.mapController.setZoom(17);
          this.mapView.invalidate();
          this.mapOverlays = this.mapView.getOverlays();
          this.itemizedOverlay = new MyItemizedOverlay(getResources().getDrawable(2130837543), this.mapView);
          OverlayItem localOverlayItem1 = new OverlayItem(this.point, "", this.mAddress);
          this.itemizedOverlay.addOverlay(localOverlayItem1);
          this.mapOverlays.add(this.itemizedOverlay);
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
        }
      }
    }
  }

  public void getlocation()
  {
    this.locationManager = ((LocationManager)getSystemService("location"));
    this.locationListener = new MyLocationListener(null);
    Criteria localCriteria = new Criteria();
    localCriteria.setAccuracy(2);
    String str = this.locationManager.getBestProvider(localCriteria, true);
    if ((str != null) && (this.locationManager.isProviderEnabled(str)))
      this.locationManager.requestLocationUpdates(str, 1L, 0.0F, this.locationListener, Looper.getMainLooper());
  }

  protected boolean isRouteDisplayed()
  {
    return true;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903054);
    ShowInfo();
    turnGPSOn();
    startService(new Intent(this, SlientMeService.class));
    this.etSearch = ((EditText)findViewById(2131165233));
    this.btnSearch = ((Button)findViewById(2131165236));
    this.btnMeZones = ((Button)findViewById(2131165230));
    this.btnSettings = ((Button)findViewById(2131165231));
    this.btnSearchCancel = ((Button)findViewById(2131165235));
    this.mapView = ((MapView)findViewById(2131165213));
    this.mapView.setTraffic(true);
    this.mapView.setSatellite(true);
    this.mapView.setBuiltInZoomControls(true);
    this.mapController = this.mapView.getController();
    getlocation();
    getPresentLocation();
    this.btnInfo = ((Button)findViewById(2131165215));
    this.btnInfo.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SearchHome.this, info.class);
        SearchHome.this.startActivity(localIntent);
      }
    });
    this.btnSearchCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchHome.this.etSearch.setText("");
      }
    });
    this.btnMeZones.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SearchHome.this, MeZones.class);
        SearchHome.this.startActivity(localIntent);
      }
    });
    this.btnSettings.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SearchHome.this, ApplicationSettings.class);
        SearchHome.this.startActivity(localIntent);
      }
    });
    this.btnSearch.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((InputMethodManager)SearchHome.this.getSystemService("input_method")).hideSoftInputFromWindow(SearchHome.this.etSearch.getWindowToken(), 0);
        SearchHome.this.viewMap = new Runnable()
        {
          public void run()
          {
            try
            {
              Geocoder localGeocoder = new Geocoder(SearchHome.this.getApplicationContext(), Locale.getDefault());
              SearchHome.this.addresses = localGeocoder.getFromLocationName(SearchHome.this.etSearch.getEditableText().toString(), 1);
              SearchHome.this.runOnUiThread(SearchHome.this.returnRes);
              return;
            }
            catch (Exception localException)
            {
              while (true)
              {
                Runnable local1 = new Runnable()
                {
                  public void run()
                  {
                    SearchHome.this.pDialog.dismiss();
                    AlertDialog localAlertDialog = new AlertDialog.Builder(SearchHome.this).create();
                    localAlertDialog.setTitle("Connection Error");
                    localAlertDialog.setMessage("You are not connected to the internet. SlientMe must have access to the internet.Please close SlientMe, connect to the cellular data network, or a Wi-Fi connection and then restart SlientMe.");
                    localAlertDialog.setButton("OK", new DialogInterface.OnClickListener()
                    {
                      public void onClick(DialogInterface paramAnonymous4DialogInterface, int paramAnonymous4Int)
                      {
                      }
                    });
                    localAlertDialog.show();
                  }
                };
                SearchHome.this.handler.post(local1);
              }
            }
          }
        };
        new Thread(null, SearchHome.this.viewMap, "").start();
        SearchHome.this.pDialog = ProgressDialog.show(SearchHome.this, "Please wait...", "Loading...", true);
        SearchHome.this.pDialog.setCancelable(true);
      }
    });
  }

  public void showActivity()
  {
    Intent localIntent = new Intent(this, NotificationAround.class);
    localIntent.putExtra("LAT", this.LAT);
    localIntent.putExtra("LNG", this.LNG);
    localIntent.putExtra("LocationName", this.mAddress);
    startActivity(localIntent);
  }

  private class MyLocationListener
    implements LocationListener
  {
    private MyLocationListener()
    {
    }

    public void onLocationChanged(Location paramLocation)
    {
      if (paramLocation != null)
      {
        SearchHome.this.Lat = paramLocation.getLatitude();
        SearchHome.this.Long = paramLocation.getLongitude();
      }
    }

    public void onProviderDisabled(String paramString)
    {
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.SearchHome
 * JD-Core Version:    0.6.2
 */