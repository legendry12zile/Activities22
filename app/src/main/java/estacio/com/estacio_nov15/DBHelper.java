package estacio.com.estacio_nov15;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "stud.db";
    final static int VER = 1;
    Cursor rs;
    final static  String table = "grade";
    public String ID;

    public DBHelper(Context context) {
        super(context, DBName,  null, VER);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cTable = "CREATE TABLE grade (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Lname TEXT, Lgrade INTEGER)";
        db.execSQL(cTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dTable = "DROP TABLE IF EXISTS grade ";
        db.execSQL(dTable);
        onCreate(db);
    }

    public boolean insert(String first, String last, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FName", first);
        cv.put("LName", last);
        cv.put("Score", score);
        long inserted = db.insert(table, null, cv);
        if (inserted == -1){
            return false;
        } else return true;

    }

    public boolean update(String ID, String first, String last, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FName", first);
        cv.put("LName", last);
        cv.put("Score", score);
        db.update(table,cv, "ID = ?",new String[]{ID});
        return true;

    }

    public boolean delete(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table,"ID = ?",new String[]{ID});
        return true;
    }

    public Cursor selectRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery( "SELECT * FROM grade", null );

    }
}
