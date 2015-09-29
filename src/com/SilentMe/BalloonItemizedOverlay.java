package com.SilentMe;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import java.util.Iterator;
import java.util.List;

public class BalloonItemizedOverlay<Item> extends ItemizedOverlay<OverlayItem>
{
  private BalloonOverlayView balloonView;
  private View clickRegion;
  private MapView mapView;
  final MapController mc;
  private int viewOffset;

  public BalloonItemizedOverlay(Drawable paramDrawable, MapView paramMapView)
  {
    super(paramDrawable);
    this.mapView = paramMapView;
    this.viewOffset = 0;
    this.mc = paramMapView.getController();
  }

  private void hideBalloon()
  {
    if (this.balloonView != null)
      this.balloonView.setVisibility(8);
  }

  private void hideOtherBalloons(List<Overlay> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Overlay localOverlay = (Overlay)localIterator.next();
      if (((localOverlay instanceof BalloonItemizedOverlay)) && (localOverlay != this))
        ((BalloonItemizedOverlay)localOverlay).hideBalloon();
    }
  }

  protected OverlayItem createItem(int paramInt)
  {
    return null;
  }

  protected boolean onBalloonTap(int paramInt)
  {
    return false;
  }

  protected final boolean onTap(int paramInt)
  {
    GeoPoint localGeoPoint = createItem(paramInt).getPoint();
    int i;
    MapView.LayoutParams localLayoutParams;
    if (this.balloonView == null)
    {
      this.balloonView = new BalloonOverlayView(this.mapView.getContext(), this.viewOffset);
      this.clickRegion = this.balloonView.findViewById(2131165187);
      i = 0;
      this.balloonView.setVisibility(8);
      List localList = this.mapView.getOverlays();
      if (localList.size() > 1)
        hideOtherBalloons(localList);
      this.balloonView.setData(createItem(paramInt));
      localLayoutParams = new MapView.LayoutParams(-2, -2, localGeoPoint, 81);
      localLayoutParams.mode = 0;
      this.balloonView.setVisibility(0);
      if (i == 0)
        break label165;
      this.balloonView.setLayoutParams(localLayoutParams);
    }
    while (true)
    {
      this.mapView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          BalloonItemizedOverlay.this.balloonView.setVisibility(8);
          return false;
        }
      });
      return true;
      i = 1;
      break;
      label165: this.mapView.addView(this.balloonView, localLayoutParams);
    }
  }

  public void setBalloonBottomOffset(int paramInt)
  {
    this.viewOffset = paramInt;
  }

  public int size()
  {
    return 0;
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.BalloonItemizedOverlay
 * JD-Core Version:    0.6.2
 */