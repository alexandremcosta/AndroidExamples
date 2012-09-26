package exemplos.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ContatoDatabaseHandler extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "contatos";
  private static final String TABLE_CONTACTS = "contatos";

  private static final String KEY_ID = BaseColumns._ID;
  private static final String KEY_NAME = "nome";
  private static final String KEY_PH_NO = "telefone";

  public ContatoDatabaseHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACTS_TABLE =
      "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
        + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
    db.execSQL(CREATE_CONTACTS_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    onCreate(db);
  }

  void addContato(Contato contato) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, contato.getNome());
    values.put(KEY_PH_NO, contato.getTelefone());

    db.insert(TABLE_CONTACTS, null, values);
    db.close();
  }

  public int atualizarContato(Contato contato) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, contato.getNome());
    values.put(KEY_PH_NO, contato.getTelefone());

    return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
      new String[] { String.valueOf(contato.getID()) });
  }

  public void apagarContato(Contato contato) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
      new String[] { String.valueOf(contato.getID()) });
    db.close();
  }

  Contato getContato(int id) {
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor =
      db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO },
        KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null,
        null);
    if (cursor != null)
      cursor.moveToFirst();

    Contato contact =
      new Contato(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
        cursor.getString(2));

    return contact;
  }

  public List<Contato> getContatos() {
    List<Contato> contactList = new ArrayList<Contato>();
    String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
      do {
        Contato contact = new Contato();
        contact.setID(Integer.parseInt(cursor.getString(0)));
        contact.setNome(cursor.getString(1));
        contact.setTelefone(cursor.getString(2));

        contactList.add(contact);
      } while (cursor.moveToNext());
    }

    return contactList;
  }

  public int getNumeroContatos() {
    String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    cursor.close();

    return cursor.getCount();
  }
}