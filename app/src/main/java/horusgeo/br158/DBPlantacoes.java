package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class DBPlantacoes extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "BR_158_plants.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "plantacoes";

    private static final String ID = "id";
    private static final String TIPO = "tipo";
    private static final String IDADE = "idade";
    private static final String COMPLEMENTO = "complemento";
    private static final String OBS = "observacao";

    public DBPlantacoes(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                TIPO + " TEXT," +
                IDADE + " TEXT," +
                COMPLEMENTO + " TEXT," +
                OBS + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addPlantacoes(Plantacoes cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(TIPO, cadastro.getTipo());
        values.put(IDADE, cadastro.getIdade());
        values.put(COMPLEMENTO, cadastro.getCompl());
        values.put(OBS, cadastro.getObs());
        

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

    public void deletePlantacoes(Plantacoes cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Plantacoes getPlantacoes(Integer id){

        Plantacoes plantacoes = new Plantacoes();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                plantacoes.setId(cursor.getInt(0));
                plantacoes.setTipo(cursor.getString(1));
                plantacoes.setIdade(cursor.getString(2));
                plantacoes.setCompl(cursor.getString(3));
                plantacoes.setObs(cursor.getString(4));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return plantacoes;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Tipo_plant", cursor.getString(1));
                map.put("Idade_plant", cursor.getString(2));
                map.put("Compl_plant", cursor.getString(3));
                map.put("Obs_plant", cursor.getString(4));

            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }



}
