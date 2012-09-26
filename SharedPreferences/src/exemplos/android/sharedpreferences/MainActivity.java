package exemplos.android.sharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

  public static final String ARQ_PREFS = "teste";
  public static final String PREF_STR1 = "pref_text1";
  public static final String PREF_STR2 = "pref_text2";

  private EditText et1;
  private EditText et2;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    et1 = (EditText) findViewById(R.id.editText1);
    et2 = (EditText) findViewById(R.id.editText2);

    // Obtendo objeto de preferências
    SharedPreferences prefs = getSharedPreferences(ARQ_PREFS, 0);
    //
    et1.setText(prefs.getString(PREF_STR1, ""));
    et2.setText(prefs.getString(PREF_STR2, ""));

    final TextView tv = (TextView) findViewById(R.id.textView1);
    final CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);

    Button b = (Button) findViewById(R.id.button1);
    b.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, Preferences.class);
        startActivity(i);
      }
    });

    // Obtendo objeto de preferências
    prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    //
    tv.setText(prefs.getString("et", ""));
    cb.setChecked(prefs.getBoolean("cb", false));

    prefs
      .registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(
          SharedPreferences sharedPreferences, String key) {
          if (key.equals("et")) {
            tv.setText(sharedPreferences
              .getString(key, tv.getText().toString()));
          }
          else if (key.equals("cb")) {
            cb.setChecked(sharedPreferences.getBoolean("cb", cb.isChecked()));
          }
        }
      });
  }

  @Override
  protected void onStop() {
    super.onStop();
    // Obtendo objeto de preferências
    SharedPreferences prefs = getSharedPreferences(ARQ_PREFS, 0);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(PREF_STR1, et1.getText().toString());
    editor.putString(PREF_STR2, et2.getText().toString());
    editor.commit();
  }
}