package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBEmpresa extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_emps.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "empresa";

    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String CNPJ = "cnpj";
    private static final String TEL_1 = "tel_1";
    private static final String TEL_2 = "tel_2";
    private static final String CONTATO = "contato";
    private static final String EMAIL = "email";

    public DBEmpresa(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                NOME + " TEXT," +
                CNPJ + " TEXT," +
                TEL_1 + " TEXT," +
                TEL_2 + " TEXT," +
                CONTATO + " TEXT," +
                EMAIL + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                NOME + " TEXT," +
                CNPJ + " TEXT," +
                TEL_1 + " TEXT," +
                TEL_2 + " TEXT," +
                CONTATO + " TEXT," +
                EMAIL + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addEmpresa(Empresa cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(NOME, cadastro.getNome());
        values.put(CNPJ, cadastro.getCnpj());
        values.put(TEL_1, cadastro.getTel1());
        values.put(TEL_2, cadastro.getTel2());
        values.put(CONTATO, cadastro.getContato());
        values.put(EMAIL, cadastro.getEmail());



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

    public void deleteEmpresa(Empresa cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Empresa getEmpresa(Integer id){

        Empresa empresa = new Empresa();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                empresa.setId(cursor.getInt(0));
                empresa.setNome(cursor.getString(1));
                empresa.setCnpj(cursor.getString(2));
                empresa.setTel1(cursor.getString(3));
                empresa.setTel2(cursor.getString(4));
                empresa.setContato(cursor.getString(5));
                empresa.setEmail(cursor.getString(6));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return empresa;

    }

    public List<String> getAllNames(){
        List<String> registerList = new ArrayList<String>();

        String selectQuery = "SELECT " + NOME + ", " + ID + " FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    String cadastro = cursor.getString(0);
                    String id = String.valueOf(cursor.getInt(1));
                    String nome = cadastro + "-" + id;
                    registerList.add(nome);
                } while (cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }
        db.close();
        return registerList;
    }

    public Integer getName2ID(String nome){
        String selectQuery = "SELECT " + ID + " FROM " + TABLE + " WHERE " + NOME + " = " + nome;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        Integer id = 0;

        try {
            if (cursor.moveToFirst())
                id = cursor.getInt(0);
        }finally{
            cursor.close();
        }
        db.close();
        return id;
    }

    public String getName(Integer id){
        String selectQuery = "SELECT " + NOME + " FROM " + TABLE + " WHERE " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        String nome = "";

        try {
            if (cursor.moveToFirst())
                nome = cursor.getString(0);
        }finally{
            cursor.close();
        }
        db.close();
        return nome;
    }

    public Integer getNewId(){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT " + ID + " FROM " + TABLE;

        Cursor cursor = db.rawQuery(query, null);

        Integer id = 0;
        try {
            if (cursor.getCount() > 0) {
                cursor.moveToLast();
                id = cursor.getInt(0) + 1;
            } else {
                id = 190000;
            }
        }finally{
            cursor.close();
        }

        db.close();

        return id;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Nome_emp", cursor.getString(1));
                map.put("Cnpj_emp", cursor.getString(2));
                map.put("Tel1_emp", cursor.getString(3));
                map.put("Tel2_emp", cursor.getString(4));
                map.put("Contato_emp", cursor.getString(5));
                map.put("Email_emp", cursor.getString(6));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }



}

