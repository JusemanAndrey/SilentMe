package com.SilentMe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.util.ArrayList;

public class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem>
{
  private Context c;
  private ArrayList<OverlayItem> m_overlays = new ArrayList();

  public MyItemizedOverlay(Drawable paramDrawable, MapView paramMapView)
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
    super.draw(paramCanvas, paramMapView, false);
    paramMapView.getDrawingCache();
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
 * Qualified Name:     com.SilentMe.MyItemizedOverlay
 * JD-Core Version:    0.6.2
 */