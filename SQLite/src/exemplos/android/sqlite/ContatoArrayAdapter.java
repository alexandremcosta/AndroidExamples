package exemplos.android.sqlite;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContatoArrayAdapter extends ArrayAdapter<Contato> {

  private final Context context;
  private final List<Contato> contatos;

  public ContatoArrayAdapter(Context context, List<Contato> contatos) {
    super(context, R.layout.contatorow, contatos);
    this.context = context;
    this.contatos = contatos;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater =
      (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.contatorow, parent, false);

    Contato c = contatos.get(position);

    TextView tvId = (TextView) rowView.findViewById(R.id.contatoId);
    tvId.setText(String.valueOf(c.getID()));
    TextView tvNome = (TextView) rowView.findViewById(R.id.contatoNome);
    tvNome.setText(c.getNome());
    TextView tvTelefone = (TextView) rowView.findViewById(R.id.contatoTelefone);
    tvTelefone.setText(c.getTelefone());

    return rowView;
  }
}