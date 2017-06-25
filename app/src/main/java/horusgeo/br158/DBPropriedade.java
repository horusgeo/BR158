package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DBPropriedade extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_props.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "propriedade";

    private static final String ID = "id";
    private static final String ZONEAMENTO = "zoneamento";
    private static final String TOPOGRAFIA = "topografia";
    private static final String INFRAREDEEL = "rede_eletrica";
    private static final String INFRASINTEL = "sinal_telefone";
    private static final String INFRAABASAGUA = "abastecimento_agua";
    private static final String MANANCIAL = "manancial";
    private static final String SITUACAO = "situacao";

    public DBPropriedade(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                ZONEAMENTO + " TEXT," +
                TOPOGRAFIA + " TEXT," +
                INFRAREDEEL + " TEXT," +
                INFRASINTEL + " TEXT," +
                INFRAABASAGUA + " TEXT," +
                MANANCIAL + " TEXT," +
                SITUACAO + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                ZONEAMENTO + " TEXT," +
                TOPOGRAFIA + " TEXT," +
                INFRAREDEEL + " TEXT," +
                INFRASINTEL + " TEXT," +
                INFRAABASAGUA + " TEXT," +
                MANANCIAL + " TEXT," +
                SITUACAO + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addPropriedade(Propriedade cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(ZONEAMENTO, cadastro.getZoneamento());
        values.put(TOPOGRAFIA, cadastro.getTopografia());
        values.put(INFRAREDEEL, cadastro.getRedeEletrica());
        values.put(INFRASINTEL, cadastro.getSinalTelefone());
        values.put(INFRAABASAGUA, cadastro.getAbastecimentoAgua());
        values.put(MANANCIAL, cadastro.getManancial());
        values.put(SITUACAO, cadastro.getSituacao());

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

    public void deleteProp(Propriedade cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Propriedade getPropriedade(Integer id){

        Propriedade propriedade = new Propriedade();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                propriedade.setId(cursor.getInt(0));
                propriedade.setZoneamento(cursor.getString(1));
                propriedade.setTopografia(cursor.getString(2));
                propriedade.setSinalTelefone(cursor.getString(3));
                propriedade.setRedeEletrica(cursor.getString(4));
                propriedade.setAbastecimentoAgua(cursor.getString(5));
                propriedade.setManancial(cursor.getString(6));
                propriedade.setSituacao(cursor.getString(7));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return propriedade;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Zoneamento", cursor.getString(1));
                map.put("Topografia", cursor.getString(2));
                map.put("SinalTelefone", cursor.getString(3));
                map.put("RedeEletrica", cursor.getString(4));
                map.put("AbastecimentoAgua", cursor.getString(5));
                map.put("Manancial", cursor.getString(6));
                map.put("Situacao", cursor.getString(7));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }


}
