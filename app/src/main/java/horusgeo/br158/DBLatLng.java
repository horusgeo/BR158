package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DBLatLng extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_latlng.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "latlng";

    private static final String ID = "id";
    private static final String LAT = "lat";
    private static final String LNG = "lng";

    public DBLatLng(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                LAT + " TEXT, " +
                LNG + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                LAT + " TEXT, " +
                LNG + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addLatLng(Integer id, LatLng latLng){

        ContentValues values = new ContentValues();

        values.put(ID, id);
        values.put(LAT, latLng.getLat());
        values.put(LNG, latLng.getLng());

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.getCount() > 0){
                db.update(TABLE, values, ID + "=" + id, null);
            }else{
                db.insert(TABLE, null, values);
            }


        }finally {
            cursor.close();
        }
        db.close();

    }

    public void deleteLatLng(Integer id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + id, null);
        db.close();

    }

    public LatLng getLatLng(){

        LatLng latLng = new LatLng();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                latLng.setId(cursor.getInt(0));
                latLng.setLat(cursor.getString(1));
                latLng.setLng(cursor.getString(2));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return latLng;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Lat", cursor.getString(1));
                map.put("Lng", cursor.getString(2));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }

}
