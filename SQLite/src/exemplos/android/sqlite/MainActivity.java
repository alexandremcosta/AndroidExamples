package exemplos.android.sqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ListActivity {

  private static final int ADD_ACTION_CODE = 1;

  private ContatoDatabaseHandler handler;

  private ContatoArrayAdapter adapter;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    handler = new ContatoDatabaseHandler(this);

    Button bAdd = (Button) findViewById(R.id.add);
    bAdd.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, ContatoAddActivity.class);
        startActivityForResult(i, ADD_ACTION_CODE);
      }
    });

    Button bDelete = (Button) findViewById(R.id.delete);
    bDelete.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        if (getListAdapter().getCount() > 0) {
          Contato contato = (Contato) getListAdapter().getItem(0);
          handler.apagarContato(contato);
          adapter.remove(contato);
        }
      }
    });

    adapter = new ContatoArrayAdapter(this, handler.getContatos());
    setListAdapter(adapter);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK && requestCode == ADD_ACTION_CODE) {
      Contato contato = new Contato();
      contato
        .setNome(data.getExtras().getString(ContatoAddActivity.EXTRA_NOME));
      contato.setTelefone(data.getExtras().getString(
        ContatoAddActivity.EXTRA_TELEFONE));
      handler.addContato(contato);
      adapter.add(contato);
    }
  }
}