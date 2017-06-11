package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class DBRegiao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_regs.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "regiao";

    private static final String ID = "id";
    private static final String LIXO = "lixo";
    private static final String TRANSPORTE = "transporte";
    private static final String SAUDE = "saude";
    private static final String ESCOLA = "escola";
    private static final String COMERCIO = "comercio";
    private static final String LAZER = "lazer";
    private static final String SEGURANCA = "seguranca";
    private static final String USO = "uso";

    public DBRegiao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                LIXO + " TEXT," +
                TRANSPORTE + " TEXT," +
                SAUDE + " TEXT," +
                ESCOLA + " TEXT," +
                COMERCIO + " TEXT," +
                LAZER + " TEXT," +
                SEGURANCA + " TEXT," +
                USO + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addRegiao(Regiao cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(LIXO, cadastro.getColetaLixo());
        values.put(TRANSPORTE, cadastro.getTransporte());
        values.put(SAUDE, cadastro.getSaude());
        values.put(ESCOLA, cadastro.getEscola());
        values.put(COMERCIO, cadastro.getComercio());
        values.put(LAZER, cadastro.getLazer());
        values.put(SEGURANCA, cadastro.getSeguranca());
        values.put(USO, cadastro.getUso());

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

    public void deleteRegiao(Regiao cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Regiao getRegiao(Integer id){

        Regiao regiao = new Regiao();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                regiao.setId(cursor.getInt(0));
                regiao.setColetaLixo(cursor.getString(1));
                regiao.setTransporte(cursor.getString(2));
                regiao.setSaude(cursor.getString(3));
                regiao.setEscola(cursor.getString(4));
                regiao.setComercio(cursor.getString(5));
                regiao.setLazer(cursor.getString(6));
                regiao.setSeguranca(cursor.getString(7));
                regiao.setUso(cursor.getString(8));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return regiao;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("ColetaLixo", cursor.getString(1));
                map.put("Transporte", cursor.getString(2));
                map.put("Saude", cursor.getString(3));
                map.put("Escola", cursor.getString(4));
                map.put("Comercio", cursor.getString(5));
                map.put("Lazer", cursor.getString(6));
                map.put("Seguranca", cursor.getString(7));
                map.put("Uso", cursor.getString(8));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }


}
