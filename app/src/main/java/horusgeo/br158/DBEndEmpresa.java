package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DBEndEmpresa extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_end_emps.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "end_empresa";

    private static final String ID = "id";
    private static final String RUA = "rua";
    private static final String NUM = "num";
    private static final String COMPL = "compl";
    private static final String BAIRRO = "bairro";
    private static final String CEP = "cep";
    private static final String MUNICIPIO = "municipio";
    private static final String UF_1 = "uf_1";
    private static final String P_REF = "p_ref";

    public DBEndEmpresa(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                RUA + " TEXT," +
                NUM + " TEXT," +
                COMPL + " TEXT," +
                BAIRRO + " TEXT," +
                CEP + " TEXT," +
                MUNICIPIO + " TEXT," +
                UF_1 + " TEXT," +
                P_REF + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                RUA + " TEXT," +
                NUM + " TEXT," +
                COMPL + " TEXT," +
                BAIRRO + " TEXT," +
                CEP + " TEXT," +
                MUNICIPIO + " TEXT," +
                UF_1 + " TEXT," +
                P_REF + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addEndEmpresa(Endereco cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(RUA, cadastro.getRua());
        values.put(NUM, cadastro.getNum());
        values.put(COMPL, cadastro.getCompl());
        values.put(BAIRRO, cadastro.getBairro());
        values.put(CEP, cadastro.getCep());
        values.put(MUNICIPIO, cadastro.getMunicipio());
        values.put(UF_1, cadastro.getUf1());
        values.put(P_REF, cadastro.getpRef());

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

    public void deleteEndEmpresa(Endereco cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Endereco getEndEmpresa(Integer id){

        Endereco endereco = new Endereco();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                endereco.setId(cursor.getInt(0));
                endereco.setRua(cursor.getString(1));
                endereco.setNum(cursor.getString(2));
                endereco.setCompl(cursor.getString(3));
                endereco.setBairro(cursor.getString(4));
                endereco.setCep(cursor.getString(5));
                endereco.setMunicipio(cursor.getString(6));
                endereco.setUf1(cursor.getString(7));
                endereco.setpRef(cursor.getString(8));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return endereco;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Rua_end_emp", cursor.getString(1));
                map.put("Num_end_emp", cursor.getString(2));
                map.put("Compl_end_emp", cursor.getString(3));
                map.put("Bairro_end_emp", cursor.getString(4));
                map.put("Cep_end_emp", cursor.getString(5));
                map.put("Municipio_end_emp", cursor.getString(6));
                map.put("Uf1_end_emp", cursor.getString(7));
                map.put("pRef_end_emp", cursor.getString(8));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }


}
