package com.SilentMe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MeZones extends Activity
{
  DBAdapter db;
  private ListView lv = null;
  private EfficientAdapter m_adapter = null;
  Cursor meZones;

  public void FillData()
  {
    this.db = new DBAdapter(this);
    this.db.open();
    this.meZones = this.db.getDetail();
    this.m_adapter = new EfficientAdapter(this);
    this.lv.setAdapter(this.m_adapter);
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        MeZones.this.meZones.moveToPosition(paramAnonymousInt);
        long l = MeZones.this.meZones.getLong(MeZones.this.meZones.getColumnIndex("_id"));
        Intent localIntent = new Intent(MeZones.this, MeZoneMap.class);
        localIntent.putExtra("LocID", l);
        MeZones.this.startActivityForResult(localIntent, 14);
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    FillData();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903048);
    this.lv = ((ListView)findViewById(2131165216));
    ((Button)findViewById(2131165189)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MeZones.this.finish();
      }
    });
    ((Button)findViewById(2131165215)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(MeZones.this, info.class);
        MeZones.this.startActivity(localIntent);
      }
    });
    FillData();
  }

  public class EfficientAdapter extends BaseAdapter
  {
    private LayoutInflater mInflater;

    public EfficientAdapter(Context arg2)
    {
      Context localContext;
      this.mInflater = LayoutInflater.from(localContext);
    }

    public int getCount()
    {
      return MeZones.this.meZones.getCount();
    }

    public Object getItem(int paramInt)
    {
      return null;
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903049, null);
        localViewHolder = new ViewHolder();
        localViewHolder.tvZone = ((TextView)paramView.findViewById(2131165217));
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        MeZones.this.meZones.moveToPosition(paramInt);
        localViewHolder.tvZone.setText(MeZones.this.meZones.getString(MeZones.this.meZones.getColumnIndex("loc_name")));
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
      }
    }

    class ViewHolder
    {
      TextView tvZone;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.MeZones
 * JD-Core Version:    0.6.2
 */