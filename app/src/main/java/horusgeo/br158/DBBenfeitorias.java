package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBBenfeitorias extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "br158.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "benfeitorias";

    private static final String ID = "id";
    private static final String CONSTRUCAO = "construcao";
    private static final String CONSTRUCAOTEXT = "construcaotext";
    private static final String EQUIPAMENTOS = "equipamentos";
    private static final String EQUIPAMENTOSTEXT = "equipamentostext";
    private static final String CROQUIS = "croquis";
    private static final String CROQUISTEXT = "croquistext";

    public DBBenfeitorias(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                CONSTRUCAO + " INTEGER," +
                CONSTRUCAOTEXT + " TEXT," +
                EQUIPAMENTOS + " INTEGER," +
                EQUIPAMENTOSTEXT + " TEXT," +
                CROQUIS + " INTEGER," +
                CROQUISTEXT + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
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
                benfeitorias.setConstrucoes(cursor.getInt(1));
                benfeitorias.setConstucoesText(cursor.getString(2));
                benfeitorias.setEquipamentos(cursor.getInt(3));
                benfeitorias.setEquipamentosText(cursor.getString(4));
                benfeitorias.setCroquis(cursor.getInt(5));
                benfeitorias.setConstucoesText(cursor.getString(6));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return benfeitorias;

    }



}
