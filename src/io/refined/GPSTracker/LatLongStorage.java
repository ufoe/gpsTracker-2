package io.refined.GPSTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LatLongStorage {
		
	private static final String TAG = "LatLongStorage";

	private final String TABLE = "latlongs";	
	
	private SQLiteDatabase db;
	private LatLongsOpenHelper latLongsOpenHelper;	
	
	public LatLongStorage(Context ctx) {
		latLongsOpenHelper = new LatLongsOpenHelper(ctx);
		this.db = latLongsOpenHelper.getWritableDatabase();
	}
	
	public void save(double lat, double lon) {
		ContentValues cvs = new ContentValues();
		cvs.put("lat", lat);
		cvs.put("long", lon);
		db.insert(TABLE, null, cvs);
		Log.d(TAG, "Saved latlong");
	}
	
	public void dump() {
		String query = "SELECT * FROM latlongs";
		Cursor cursor = db.rawQuery(query, null);
		
		Log.d(TAG, "Dumping...");
		
		while (cursor.moveToNext()) {
			String lat = cursor.getString(0);
			String lon = cursor.getString(1);
			Log.d(TAG, "[" + lat + ", " + lon + "]");
		}				
	}			
}
