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

public class DBProprietario extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_propris.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "proprietarios";

    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String NACIONALIDADE = "nacionalidade";
    private static final String PROFISSAO = "profissao";
    private static final String ESTADO_CIVIL = "estado_civil";
    private static final String DOC_ID = "num_id";
    private static final String TIPO = "tipo_id";
    private static final String CPF = "cpf";
    private static final String TEL_1 = "tel_1";
    private static final String TEL_2 = "tel_2";
    private static final String EMAIL = "email";
    private static final String POSSPROP = "poss_prop";

    public DBProprietario(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                NOME + " TEXT," +
                NACIONALIDADE + " TEXT," +
                PROFISSAO + " TEXT," +
                ESTADO_CIVIL + " TEXT," +
                DOC_ID + " TEXT," +
                TIPO + " TEXT," +
                CPF + " TEXT," +
                TEL_1 + " TEXT," +
                TEL_2 + " TEXT," +
                EMAIL + " TEXT," +
                POSSPROP + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                NOME + " TEXT," +
                NACIONALIDADE + " TEXT," +
                PROFISSAO + " TEXT," +
                ESTADO_CIVIL + " TEXT," +
                DOC_ID + " TEXT," +
                TIPO + " TEXT," +
                CPF + " TEXT," +
                TEL_1 + " TEXT," +
                TEL_2 + " TEXT," +
                EMAIL + " TEXT," +
                POSSPROP + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addProp(Person cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(NOME, cadastro.getNome());
        values.put(NACIONALIDADE, cadastro.getNacionalidade());
        values.put(PROFISSAO, cadastro.getProfissao());
        values.put(ESTADO_CIVIL, cadastro.getEstadoCivil());
        values.put(DOC_ID, cadastro.getDocId());
        values.put(TIPO, cadastro.getDocTipo());
        values.put(CPF, cadastro.getCpf());
        values.put(TEL_1, cadastro.getTel1());
        values.put(TEL_2, cadastro.getTel2());
        values.put(EMAIL, cadastro.getEmail());
        values.put(POSSPROP, cadastro.getPossProp());


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

    public void deleteProp(Person cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Person getProp(Integer id){

        Person proprietario = new Person();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                proprietario.setId(cursor.getInt(0));
                proprietario.setNome(cursor.getString(1));
                proprietario.setNacionalidade(cursor.getString(2));
                proprietario.setProfissao(cursor.getString(3));
                proprietario.setEstadoCivil(cursor.getString(4));
                proprietario.setDocId(cursor.getString(5));
                proprietario.setDocTipo(cursor.getString(6));
                proprietario.setCpf(cursor.getString(7));
                proprietario.setTel1(cursor.getString(8));
                proprietario.setTel2(cursor.getString(9));
                proprietario.setEmail(cursor.getString(10));
                proprietario.setPossProp(cursor.getString(11));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return proprietario;

    }

    public List<String> getAllNames(){
        List<String> registerList = new ArrayList<String>();

        String selectQuery = "SELECT " + NOME +", " + ID + " FROM " + TABLE;

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


    public boolean exist(Integer id){
        String selectQuery = "SELECT " + NOME + " FROM " + TABLE + " WHERE " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean exists = false;

        try {
            if (cursor.getCount()!= 0)
                exists = true;

        }finally{
            cursor.close();
        }
        db.close();
        return exists;
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
                id = 100009;
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
                map.put("Nome_prop", cursor.getString(1));
                map.put("Nacionalidade_prop", cursor.getString(2));
                map.put("Profissao_prop", cursor.getString(3));
                map.put("EstadoCivil_prop", cursor.getString(4));
                map.put("DocId_prop", cursor.getString(5));
                map.put("DocTipo_prop", cursor.getString(6));
                map.put("Cpf_prop", cursor.getString(7));
                map.put("Tel1_prop", cursor.getString(8));
                map.put("Tel2_prop", cursor.getString(9));
                map.put("Email_prop", cursor.getString(10));
                map.put("PossProp_prop", cursor.getString(11));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }

}
