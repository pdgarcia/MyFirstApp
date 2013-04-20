package com.example.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Messages extends SQLiteOpenHelper {
	String sqlCreate = "CREATE TABLE Messages (_id INTEGER PRIMARY KEY AUTOINCREMENT, message TEXT)";
	private Context context;
	
    public Messages(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        this.context = contexto;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        //Se elimina la versi—n anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Messages");
        //Se crea la nueva versi—n de la tabla
        db.execSQL(sqlCreate);
	}

}
