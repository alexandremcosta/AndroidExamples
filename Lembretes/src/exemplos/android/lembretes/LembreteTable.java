package exemplos.android.lembretes;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LembreteTable {

  //Colunas
  public static final String TABLE_TODO = "LEMBRETE";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_CATEGORY = "categoria";
  public static final String COLUMN_SUMMARY = "resumo";
  public static final String COLUMN_DESCRIPTION = "descricao";

  //Comando SQL de criação da tabela
  private static final String DATABASE_CREATE = "create table " + TABLE_TODO
    + "(" + COLUMN_ID + " integer primary key autoincrement, "
    + COLUMN_CATEGORY + " text not null, " + COLUMN_SUMMARY + " text not null,"
    + COLUMN_DESCRIPTION + " text not null" + ");";

  public static void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  public static void onUpgrade(SQLiteDatabase database, 
		  						int oldVersion,
		  						int newVersion) {
    Log.w(LembreteTable.class.getName(), "Upgrading database from version "
      + oldVersion + " to " + newVersion + ", which will destroy all old data");
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
    onCreate(database);
  }
}