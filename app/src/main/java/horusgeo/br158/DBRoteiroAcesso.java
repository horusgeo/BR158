package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DBRoteiroAcesso extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_rots.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "roteiro_acesso";

    private static final String ID = "id";
    private static final String ROTEIRO = "roteiro";

    public DBRoteiroAcesso(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                ROTEIRO + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                ROTEIRO + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addRoteiro(Roteiro cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(ROTEIRO, cadastro.getRoteiro());

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + cadastro.getId();
        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.getCount() > 0){
                db.update(TABLE, values, ID + "=" + cadastro.getId(), null);
            }else{
                db.insert(TABLE, null, values);
            }


        }finally {
            cursor.close();
        }
        db.close();

    }

    public void deleteRoteiro(Roteiro cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Roteiro getRoteiro(Integer id){

        Roteiro roteiro = new Roteiro();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                roteiro.setId(cursor.getInt(0));
                roteiro.setRoteiro(cursor.getString(1));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return roteiro;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Roteiro", cursor.getString(1));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }


}
