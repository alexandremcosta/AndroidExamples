package exemplos.android.lembretes;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

  private static final int ACTIVITY_CREATE = 0;
  private static final int ACTIVITY_EDIT = 1;
  private static final int DELETE_ID = Menu.FIRST + 1;

  private SimpleCursorAdapter adapter;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    fillData();
    registerForContextMenu(getListView());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.insert:
        createTodo();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.add(0, DELETE_ID, 0, R.string.menu_delete);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case DELETE_ID:
        AdapterContextMenuInfo info =
          (AdapterContextMenuInfo) item.getMenuInfo();
        //- A partir do objeto que est� sendo selecionado para menu de contexto,
        //  criar a URI com seu identififcador (URI/id) 
        Uri uri = Uri.parse(LembretesContentProvider.CONTENT_URI + "/" + info.id);
        //- Chamar o m�todo delete do ContentProvider
        getContentResolver().delete(uri, null, null);
        fillData();        
        return true;
    }
    return super.onContextItemSelected(item);
  }

  private void createTodo() {
    Intent i = new Intent(this, EditActivity.class);
    startActivityForResult(i, ACTIVITY_CREATE);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    Intent i = new Intent(this, EditActivity.class);

    //- No toque de um lembrete dever� abrir a EditActivity para edi��o
    //- Criar uma URI atrav�s do m�todo Uri.parse passando como par�metro a Uri de lembretes e o identificador do mesmo (URI/id)
    //- Colocar esse valor como um Extra do intent, usando como chave o valor LembretesContentProvider.CONTENT_ITEM_TYPE    
    Uri todoUri = Uri.parse(LembretesContentProvider.CONTENT_URI + "/" + id);
    i.putExtra(LembretesContentProvider.CONTENT_ITEM_TYPE, todoUri);
    
    startActivityForResult(i, ACTIVITY_EDIT);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
  }

  /**
   * M�todo utilizado para recuperar lembretes do ContentProvider e mostr�-los
   * na ListActivity
   */
  private void fillData() {

    //- Ser� necess�rio chamar o m�todo query do ContentProvider passando como par�meteros a Uri do mesmo
    //  e a proje��o das colunas necess�rias (identificador e t�tulo)
    //- Com o cursor retornado, criar um adapter para objetos Cursor e atribuir ListAcvitity
	  String[] from = new String[] {LembreteTable.COLUMN_SUMMARY};
	  int[] to = new int[] {R.id.label};
	  String[] projection = {LembreteTable.COLUMN_ID, LembreteTable.COLUMN_SUMMARY };
	  
	  Cursor lembretes = getContentResolver().query(LembretesContentProvider.CONTENT_URI, projection, null, null, null);
	  
	  adapter = new SimpleCursorAdapter(this, R.layout.row, lembretes, from, to);
	  setListAdapter(adapter);
  }
}