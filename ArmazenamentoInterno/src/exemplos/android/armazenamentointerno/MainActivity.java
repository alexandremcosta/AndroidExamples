package exemplos.android.armazenamentointerno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

  public final static String FILENAME = "arq_teste";

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final EditText et = (EditText) findViewById(R.id.editText1);

    Button wb = (Button) findViewById(R.id.btnSalvarInterno);
    wb.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        FileOutputStream fos;
        try {
          fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
          fos.write(et.getText().toString().getBytes());
          fos.close();
        }
        catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    Button rb = (Button) findViewById(R.id.btnLerInterno);
    rb.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        int ch;
        StringBuffer buf = new StringBuffer();
        FileInputStream fis;
        try {
          fis = openFileInput(FILENAME);
          while ((ch = fis.read()) != -1) {
            buf.append((char) ch);
          }
          fis.close();
        }
        catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        et.setText(buf.toString());
      }
    });

    Button rbest = (Button) findViewById(R.id.btnLerEstatico);
    rbest.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        int ch;
        StringBuffer buf = new StringBuffer();
        InputStream is;
        try {
          is = getResources().openRawResource(R.raw.exemplo);
          while ((ch = is.read()) != -1) {
            buf.append((char) ch);
          }
          is.close();
        }
        catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        et.setText(buf.toString());
      }
    });

  }
}