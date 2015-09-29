package com.SilentMe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.io.PrintStream;

public class SlientMeDatabaseHelper
{
  private static final String DATABASE_NAME = "SilentMe.db";
  private static final int DATABASE_VERSION = 2;
  private static String DB_PATH = "/data/data/com.SilentMe/databases/";
  private static final String TAG = "SlientMeDatabaseHelper";
  private static final String tbl_Location = "Location";
  private final Context mCtx;
  private SQLiteDatabase mDb;
  private DatabaseHelper mDbHelper;

  public SlientMeDatabaseHelper(Context paramContext)
  {
    this.mCtx = paramContext;
  }

  public void close()
  {
    this.mDbHelper.close();
  }

  public Cursor getDetail()
    throws SQLException
  {
    Cursor localCursor = null;
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.mDb;
      String[] arrayOfString = new String[7];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "Latitude";
      arrayOfString[2] = "Longitude";
      arrayOfString[3] = "Radius";
      arrayOfString[4] = "LocationName";
      arrayOfString[5] = "meters";
      arrayOfString[6] = "mode";
      localCursor = localSQLiteDatabase.query("Location", arrayOfString, null, null, null, null, "_id");
      if (localCursor != null)
        localCursor.moveToFirst();
      return localCursor;
    }
    catch (Exception localException)
    {
      while (true)
        Toast.makeText(this.mCtx, localException.toString(), 1).show();
    }
  }

  public boolean insert(String paramString1, String paramString2, double paramDouble, String paramString3, String paramString4, String paramString5)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("Latitude", paramString1);
    localContentValues.put("Longitude", paramString2);
    localContentValues.put("Radius", Double.valueOf(paramDouble));
    localContentValues.put("LocationName", paramString3);
    localContentValues.put("meters", paramString4);
    localContentValues.put("mode", paramString5);
    if (this.mDb.insert("Location", null, localContentValues) > 0L);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public SlientMeDatabaseHelper open()
    throws SQLException
  {
    this.mDbHelper = new DatabaseHelper(this.mCtx);
    try
    {
      this.mDbHelper.createDataBase();
    }
    catch (SQLException localSQLException1)
    {
      try
      {
        while (true)
        {
          this.mDbHelper.openDataBase();
          return this;
          localSQLException1 = localSQLException1;
          System.out.print("Unable to create database");
        }
      }
      catch (SQLException localSQLException2)
      {
        throw localSQLException2;
      }
    }
  }

  public class DatabaseHelper extends SQLiteOpenHelper
  {
    DatabaseHelper(Context arg2)
    {
      super("SilentMe.db", null, 2);
    }

    private boolean checkDataBase()
    {
      Object localObject = null;
      try
      {
        SQLiteDatabase localSQLiteDatabase = SQLiteDatabase.openDatabase(SlientMeDatabaseHelper.DB_PATH + "SilentMe.db", null, 0);
        localObject = localSQLiteDatabase;
        label33: if (localObject != null)
          localObject.close();
        if (localObject != null);
        for (boolean bool = true; ; bool = false)
          return bool;
      }
      catch (SQLiteException localSQLiteException)
      {
        break label33;
      }
    }

    /** @deprecated */
    public void close()
    {
      try
      {
        if (SlientMeDatabaseHelper.this.mDb != null)
          SlientMeDatabaseHelper.this.mDb.close();
        super.close();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void createDataBase()
    {
      if (!checkDataBase())
      {
        SlientMeDatabaseHelper.this.mDb = SlientMeDatabaseHelper.this.mDbHelper.getWritableDatabase();
        SlientMeDatabaseHelper.this.mDb.execSQL("CREATE TABLE Location (_id integer primary key autoincrement, lat double,  lng double,  radius double,  loc_name text, meters text,  mode text);");
      }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.w("SlientMeDatabaseHelper", "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS folder");
      onCreate(paramSQLiteDatabase);
    }

    public void openDataBase()
      throws SQLException
    {
      String str = SlientMeDatabaseHelper.DB_PATH + "SilentMe.db";
      SlientMeDatabaseHelper.this.mDb = SQLiteDatabase.openDatabase(str, null, 0);
    }
  }
}

/* Location:           /Users/jon/Desktop/dex2jar-0.0.9.8/classes_dex2jar.jar
 * Qualified Name:     com.SilentMe.SlientMeDatabaseHelper
 * JD-Core Version:    0.6.2
 */