package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DBRelatorio extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BR_158_rels.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "relatorio";

    private static final String ID = "id";
    private static final String DATA1 = "data1";
    private static final String DATA2 = "data2";
    private static final String DATA3 = "data3";
    private static final String HORARIO_CHEGADA = "horario_chegada";
    private static final String HORARIO_SAIDA = "horario_saida";
    private static final String HORARIO2 = "horario2";
    private static final String HORARIO3 = "horario3";
    private static final String DESCRICAO1 = "descricao1";
    private static final String DESCRICAO2 = "descricao2";
    private static final String DESCRICAO3 = "descricao3";
    private static final String RESPONSAVEL = "responsavel";

    public DBRelatorio(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                DATA1 + " TEXT," +
                DATA2 + " TEXT," +
                DATA3 + " TEXT," +
                HORARIO_CHEGADA + " TEXT," +
                HORARIO_SAIDA + " TEXT," +
                HORARIO2 + " TEXT," +
                HORARIO3 + " TEXT," +
                DESCRICAO1 + " TEXT," +
                DESCRICAO2 + " TEXT," +
                DESCRICAO3 + " TEXT," +
                RESPONSAVEL + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    public void print() {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                DATA1 + " TEXT," +
                DATA2 + " TEXT," +
                DATA3 + " TEXT," +
                HORARIO_CHEGADA + " TEXT," +
                HORARIO_SAIDA + " TEXT," +
                HORARIO2 + " TEXT," +
                HORARIO3 + " TEXT," +
                DESCRICAO1 + " TEXT," +
                DESCRICAO2 + " TEXT," +
                DESCRICAO3 + " TEXT," +
                RESPONSAVEL + " TEXT" +
                ")";
        Log.d("HORUSGEO_LOG", CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addRelatorio(Relatorio cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(DATA1, cadastro.getData1());
        values.put(DATA2, cadastro.getData2());
        values.put(DATA3, cadastro.getData3());
        values.put(HORARIO_CHEGADA, cadastro.getHorarioChegada());
        values.put(HORARIO_SAIDA, cadastro.getHorarioSaida());
        values.put(HORARIO2, cadastro.getHorario2());
        values.put(HORARIO3, cadastro.getHorario3());
        values.put(DESCRICAO1, cadastro.getDescricao1());
        values.put(DESCRICAO2, cadastro.getDescricao2());
        values.put(DESCRICAO3, cadastro.getDescricao3());
        values.put(RESPONSAVEL, cadastro.getResponsavel());

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

    public void deleteRelatorio(Relatorio cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Relatorio getRelatorio(Integer id){

        Relatorio relatorio = new Relatorio();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                relatorio.setId(cursor.getInt(0));
                relatorio.setData1(cursor.getString(1));
                relatorio.setData2(cursor.getString(2));
                relatorio.setData3(cursor.getString(3));
                relatorio.setHorarioChegada(cursor.getString(4));
                relatorio.setHorarioSaida(cursor.getString(5));
                relatorio.setHorario2(cursor.getString(6));
                relatorio.setHorario3(cursor.getString(7));
                relatorio.setDescricao1(cursor.getString(8));
                relatorio.setDescricao2(cursor.getString(9));
                relatorio.setDescricao3(cursor.getString(10));
                relatorio.setResponsavel(cursor.getString(11));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return relatorio;

    }

    public Map<String, String> getMap(Integer id){
        Map<String, String> map = new HashMap<String, String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                map.put("Data1", cursor.getString(1));
                map.put("Data2", cursor.getString(2));
                map.put("Data3", cursor.getString(3));
                map.put("HorarioChegada", cursor.getString(4));
                map.put("HorarioSaida", cursor.getString(5));
                map.put("Horario2", cursor.getString(6));
                map.put("Horario3", cursor.getString(7));
                map.put("Descricao1", cursor.getString(8));
                map.put("Descricao2", cursor.getString(9));
                map.put("Descricao3", cursor.getString(10));
                map.put("Responsavel", cursor.getString(11));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return map;
    }

}
