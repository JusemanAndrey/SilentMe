package com.SilentMe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Projection;

public class MyOwnLocationOverlay extends MyLocationOverlay
{
  private Paint circlePainter;
  private GeoPoint geoCurrentPoint;
  private Context mContext;
  private MapView mapView;
  private int meters;
  private Point screenCurrentPoint;

  public MyOwnLocationOverlay(Context paramContext, MapView paramMapView)
  {
    super(paramContext, paramMapView);
    this.mapView = paramMapView;
    this.mContext = paramContext;
  }

  /** @deprecated */
  public boolean draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean, long paramLong)
  {
    try
    {
      this.circlePainter = new Paint();
      this.circlePainter.setAntiAlias(true);
      this.circlePainter.setStrokeWidth(2.0F);
      this.circlePainter.setColor(-10066177);
      this.circlePainter.setStyle(Paint.Style.FILL_AND_STROKE);
      this.circlePainter.setAlpha(70);
      Projection localProjection = paramMapView.getProjection();
      this.geoCurrentPoint = new GeoPoint((int)30.740241999999999D, (int)7.677784D);
      this.screenCurrentPoint = new Point();
      localProjection.toPixels(this.geoCurrentPoint, this.screenCurrentPoint);
      int i = metersToRadius(30.740241999999999D / 1000000.0D);
      Toast.makeText(this.mContext, i, 1).show();
      paramCanvas.drawCircle(this.screenCurrentPoint.x, this.screenCurrentPoint.y, i, this.circlePainter);
      boolean bool = super.draw(paramCanvas, paramMapView, paramBoolean, paramLong);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int metersToRadius(double paramDouble)
  {
    return (int)(this.mapView.getProjection().metersToEquatorPixels(this.meters) * (1.0D / Math.cos(Math.toRadians(paramDouble))));
  }

  public void setMeters(int paramInt)
  {
    this.meters = paramInt;
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.MyOwnLocationOverlay
 * JD-Core Version:    0.6.2
 */