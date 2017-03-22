package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConfrontantes extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "br158.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "confrontantes";

    private static final String ID = "id";
    private static final String DIREITA = "direita";
    private static final String ESQUERDA = "esquerda";
    private static final String FRENTE = "frente";
    private static final String FUNDOS = "fundos";
    
    public DBConfrontantes(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROP_TABLE = "CREATE TABLE " + TABLE + "(" +
                ID + " INTEGER NOT NULL UNIQUE," +
                DIREITA + " TEXT," +
                ESQUERDA + " TEXT," +
                FRENTE + " TEXT," +
                FUNDOS + " TEXT" +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addConfrontantes(Confrontantes cadastro){

        ContentValues values = new ContentValues();

        values.put(ID, cadastro.getId());
        values.put(DIREITA, cadastro.getDireita());
        values.put(ESQUERDA, cadastro.getEsquerda());
        values.put(FRENTE, cadastro.getFrente());
        values.put(FUNDOS, cadastro.getFundos());
        
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

    public void deleteConfrontantes(Confrontantes cadastro){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = " + cadastro.getId(), null);
        db.close();

    }

    public Confrontantes getConfrontantes(Integer id){

        Confrontantes confrontantes = new Confrontantes();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                confrontantes.setId(cursor.getInt(0));
                confrontantes.setDireita(cursor.getString(1));
                confrontantes.setEsquerda(cursor.getString(2));
                confrontantes.setFrente(cursor.getString(3));
                confrontantes.setFundos(cursor.getString(4));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return confrontantes;

    }


}
