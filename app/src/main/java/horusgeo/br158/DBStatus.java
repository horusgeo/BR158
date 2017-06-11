package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.Vector;

public class DBStatus extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_status.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "Status";

    private static final String ID = "id";
    private static final String STATUS = "status";

    public DBStatus(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                STATUS + " INTEGER" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void setStatusChanged(Integer id, Integer status){

        ContentValues values = new ContentValues();

        values.put(ID, id);
        values.put(STATUS, status);

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

    public void deleteStatus(Integer id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + id, null);
        db.close();

    }

    public Vector<Integer> getStatus(){

        Vector<Integer> list = new Vector<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                if(cursor.getInt(1) != 0)
                    list.add(cursor.getInt(0));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return list;

    }

}
