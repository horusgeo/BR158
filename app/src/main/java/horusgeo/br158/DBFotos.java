package horusgeo.br158;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DBFotos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_fotos2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "fotos";

    private static final String ID = "id";
    private static final String TIPO = "tipo";
    private static final String FILE = "file";
    private static final String PATH = "path";
    private static final String NOVA = "nova";

    public DBFotos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER, " +
                TIPO + " INTEGER," +
                FILE + " TEXT," +
                PATH + " TEXT," +
                NOVA + " INTEGER" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER, " +
                TIPO + " INTEGER," +
                FILE + " TEXT," +
                PATH + " TEXT," +
                NOVA + " INTEGER" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addFotos(Vector<Fotos> cadastro){

        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();


        for(int i = 0; i < cadastro.size(); i++) {
            if(cadastro.get(i).getNova()==2) {
                values.put(ID, cadastro.get(i).getId());
                values.put(TIPO, cadastro.get(i).getTipo());
                values.put(FILE, cadastro.get(i).getFile());
                values.put(PATH, cadastro.get(i).getPath());
                values.put(NOVA, 1);
            }

            try {
                db.insert(TABLE, null, values);
            } finally {

            }
        }
        db.close();

    }

    public void deleteFotos(Fotos cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId() + " AND " + FILE + " = " + cadastro.getFile(), null);
        db.close();

    }

    public Vector<Fotos> getFotos(Integer id, Integer tipo){

        Vector<Fotos> fotos = new Vector<Fotos>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id + " AND " + TIPO + " = " + tipo;

        Cursor cursor = db.rawQuery(query, null);
        try{
            if (cursor.moveToFirst()) {
                do {
                    Fotos aux = new Fotos();
                    aux.setId(cursor.getInt(0));
                    aux.setTipo(cursor.getInt(1));
                    aux.setFile(cursor.getString(2));
                    aux.setPath(cursor.getString(3));
                    aux.setNova(cursor.getInt(4));
                    fotos.add(aux);
                }while(cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        db.close();

        return fotos;

    }

    public Map<String, String> getMap(Integer id, ContentResolver content){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                do {
                    if(cursor.getInt(4) == 1) {
                        map.put(
                                "foto-" + cursor.getString(2),
                                String.valueOf(cursor.getInt(1)) + " : " + cursor.getString(2) + " : " + getStringImage(content, cursor.getString(3)));
                    }
                }while(cursor.moveToNext());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            cursor.close();
        }

        db.close();

        return map;
    }

    public String getStringImage(ContentResolver content, String path) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bmp = MediaStore.Images.Media.getBitmap(content, Uri.fromFile(new File(path)));
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void setNova(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "UPDATE " + TABLE + " SET " + NOVA + " =  0 WHERE " + ID + " = " + id;

        db.execSQL(sql);

    }


}
