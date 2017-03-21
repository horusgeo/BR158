package horusgeo.br158;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBJuridico extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "br158.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "juridico";

    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String NACIONALIDADE = "nacionalidade";
    private static final String PROFISSAO = "profissao";
    private static final String ESTADO_CIVIL = "estado_civil";
    private static final String DOC_ID = "num_id";
    private static final String TIPO = "tipo_id";
    private static final String CPF = "cpf";
    private static final String TEL_1 = "tel_1";
    private static final String EMAIL = "email";

    public DBJuridico(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
                EMAIL + " TEXT," +
                ")";
        db.execSQL(CREATE_PROP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addProp(Person cadastro){

        ContentValues values = new ContentValues();

        values.put(NOME, cadastro.getNome());
        values.put(NACIONALIDADE, cadastro.getNacionalidade());
        values.put(PROFISSAO, cadastro.getProfissao());
        values.put(ESTADO_CIVIL, cadastro.getEstadoCivil());
        values.put(DOC_ID, cadastro.getDocId());
        values.put(TIPO, cadastro.getDocTipo());
        values.put(CPF, cadastro.getCpf());
        values.put(TEL_1, cadastro.getTel1());
        values.put(EMAIL, cadastro.getEmail());
        values.put(ID, cadastro.getId());

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

        Person juridico = new Person();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        try{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                juridico.setId(cursor.getInt(0));
                juridico.setNome(cursor.getString(1));
                juridico.setNacionalidade(cursor.getString(2));
                juridico.setProfissao(cursor.getString(3));
                juridico.setEstadoCivil(cursor.getString(4));
                juridico.setDocId(cursor.getString(5));
                juridico.setDocTipo(cursor.getString(6));
                juridico.setCpf(cursor.getString(7));
                juridico.setTel1(cursor.getString(8));
                juridico.setEmail(cursor.getString(9));
            }
        }finally{
            cursor.close();
        }

        db.close();

        return juridico;

    }


}
