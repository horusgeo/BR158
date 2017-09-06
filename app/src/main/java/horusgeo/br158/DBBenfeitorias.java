package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DBBenfeitorias extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "BR_158_benfs_2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "benfeitorias";

    private static final String ID = "id";
    private static final String CONSTRUCAO = "construcao";
    private static final String CONSTRUCAOTEXT = "construcaotext";
    private static final String EQUIPAMENTOS = "equipamentos";
    private static final String EQUIPAMENTOSTEXT = "equipamentostext";
    private static final String CROQUIS = "croquis";
    private static final String CROQUISTEXT = "croquistext";
    private static final String PLANTACOES = "plantacoes";


    public DBBenfeitorias(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                CONSTRUCAO + " TEXT," +
                CONSTRUCAOTEXT + " TEXT," +
                EQUIPAMENTOS + " TEXT," +
                EQUIPAMENTOSTEXT + " TEXT," +
                CROQUIS + " TEXT," +
                CROQUISTEXT + " TEXT," +
                PLANTACOES  + " TEXT" +

                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                CONSTRUCAO + " INTEGER," +
                CONSTRUCAOTEXT + " TEXT," +
                EQUIPAMENTOS + " INTEGER," +
                EQUIPAMENTOSTEXT + " TEXT," +
                CROQUIS + " INTEGER," +
                CROQUISTEXT + " TEXT," +
                PLANTACOES  + " INTEGER" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addBenf(Benfeitorias cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(CONSTRUCAO, cadastro.getConstrucoes());
        values.put(CONSTRUCAOTEXT, cadastro.getConstucoesText());
        values.put(EQUIPAMENTOS, cadastro.getEquipamentos());
        values.put(EQUIPAMENTOSTEXT, cadastro.getEquipamentosText());
        values.put(CROQUIS, cadastro.getCroquis());
        values.put(CROQUISTEXT, cadastro.getCroquisText());
        values.put(PLANTACOES, cadastro.getPlantacoes());

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

    public void deleteBenf(Benfeitorias cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Benfeitorias getBenf(Integer id){

        Benfeitorias benfeitorias = new Benfeitorias();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                benfeitorias.setId(cursor.getInt(0));
                benfeitorias.setConstrucoes(cursor.getString(1));
                benfeitorias.setConstucoesText(cursor.getString(2));
                benfeitorias.setEquipamentos(cursor.getString(3));
                benfeitorias.setEquipamentosText(cursor.getString(4));
                benfeitorias.setCroquis(cursor.getString(5));
                benfeitorias.setCroquisText(cursor.getString(6));
                benfeitorias.setPlantacoes(cursor.getString(7));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return benfeitorias;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Construcoes", cursor.getString(1));
                map.put("ConstucoesText", cursor.getString(2));
                map.put("Equipamentos", cursor.getString(3));
                map.put("EquipamentosText", cursor.getString(4));
                map.put("Croquis", cursor.getString(5));
                map.put("CroquisText", cursor.getString(6));
                map.put("Plantacoes", cursor.getString(7));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }



}
