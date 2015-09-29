package com.SilentMe;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import java.util.ArrayList;

public class MyItemizedOverlay1 extends BalloonItemizedOverlay1<OverlayItem>
{
  String METERS;
  private Paint borderPaint;
  private Context c;
  Cursor cd;
  private Paint circlePainter;
  double colLat;
  double colLng;
  DBAdapter db;
  private GeoPoint geoCurrentPoint;
  private ArrayList<OverlayItem> m_overlays = new ArrayList();
  float meters;
  private Point screenCurrentPoint;

  public MyItemizedOverlay1(Drawable paramDrawable, MapView paramMapView)
  {
    super(boundCenter(paramDrawable), paramMapView);
    this.c = paramMapView.getContext();
    this.m_overlays.clear();
  }

  public void addOverlay(OverlayItem paramOverlayItem)
  {
    this.m_overlays.add(paramOverlayItem);
    populate();
  }

  protected OverlayItem createItem(int paramInt)
  {
    return (OverlayItem)this.m_overlays.get(paramInt);
  }

  public void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    this.db = new DBAdapter(this.c);
    this.db.open();
    this.cd = this.db.getDetail();
    for (boolean bool = this.cd.moveToFirst(); ; bool = this.cd.moveToNext())
    {
      if (!bool)
      {
        this.db.close();
        paramMapView.getDrawingCache();
        return;
      }
      this.colLat = this.cd.getDouble(this.cd.getColumnIndex("lat"));
      this.colLng = this.cd.getDouble(this.cd.getColumnIndex("lng"));
      this.METERS = this.cd.getString(this.cd.getColumnIndex("meters"));
      this.meters = (Float.valueOf(this.METERS).floatValue() / Float.valueOf("3.2808399").floatValue());
      this.circlePainter = new Paint();
      this.circlePainter.setAntiAlias(true);
      this.circlePainter.setStrokeWidth(2.0F);
      this.circlePainter.setColor(8900346);
      this.circlePainter.setStyle(Paint.Style.FILL_AND_STROKE);
      this.circlePainter.setAlpha(17);
      Paint localPaint = new Paint();
      localPaint.setStyle(Paint.Style.FILL);
      localPaint.setStrokeWidth(8.0F);
      localPaint.setColor(-65536);
      localPaint.setTextSize(24.0F);
      localPaint.setTextScaleX(1.0F);
      Projection localProjection = paramMapView.getProjection();
      this.geoCurrentPoint = new GeoPoint((int)(1000000.0D * this.colLat), (int)(1000000.0D * this.colLng));
      this.screenCurrentPoint = new Point();
      localProjection.toPixels(this.geoCurrentPoint, this.screenCurrentPoint);
      int i = metersToRadius(this.geoCurrentPoint.getLatitudeE6() / 1000000, paramMapView, this.meters);
      paramCanvas.drawCircle(this.screenCurrentPoint.x, this.screenCurrentPoint.y, i, this.circlePainter);
      paramCanvas.drawCircle(this.screenCurrentPoint.x, this.screenCurrentPoint.y, i, getBorderPaint());
      paramCanvas.drawText(this.METERS + " ft", 8 + this.screenCurrentPoint.x, 20 + this.screenCurrentPoint.y, localPaint);
      super.draw(paramCanvas, paramMapView, false);
    }
  }

  public Paint getBorderPaint()
  {
    if (this.borderPaint == null)
    {
      this.borderPaint = new Paint();
      this.borderPaint.setARGB(255, 135, 206, 250);
      this.borderPaint.setAntiAlias(true);
      this.borderPaint.setStyle(Paint.Style.STROKE);
      this.borderPaint.setStrokeWidth(1.0F);
    }
    return this.borderPaint;
  }

  public int metersToRadius(double paramDouble, MapView paramMapView, float paramFloat)
  {
    return (int)(paramMapView.getProjection().metersToEquatorPixels(paramFloat) * (1.0D / Math.cos(Math.toRadians(paramDouble))));
  }

  protected boolean onBalloonTap(int paramInt)
  {
    Toast.makeText(this.c, "onBalloonTap for overlay index " + paramInt, 1).show();
    return true;
  }

  public int size()
  {
    return this.m_overlays.size();
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.MyItemizedOverlay1
 * JD-Core Version:    0.6.2
 */