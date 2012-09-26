package exemplos.android.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContatoAddActivity extends Activity {

  public static final String EXTRA_NOME = "contato.nome";

  public static final String EXTRA_TELEFONE = "contato.telefone";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add);

    final EditText etNome = (EditText) findViewById(R.id.etNome);
    final EditText etTelefone = (EditText) findViewById(R.id.etTelefone);

    Button bOk = (Button) findViewById(R.id.bOk);
    bOk.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent data = new Intent();
        data.putExtra(EXTRA_NOME, etNome.getText().toString());
        data.putExtra(EXTRA_TELEFONE, etTelefone.getText().toString());
        setResult(RESULT_OK, data);
        finish();
      }
    });

    Button bCancelar = (Button) findViewById(R.id.bCancelar);
    bCancelar.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        setResult(RESULT_CANCELED);
        finish();
      }
    });

  }
}
