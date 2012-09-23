package exemplos.android.activityresult;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class SecondActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		final EditText et = (EditText) findViewById(R.id.etNome);
		Button btnOk = (Button) findViewById(R.id.btnOk);
		
		btnOk.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent data = new Intent();
				data.putExtra("name", et.getText().toString());
				setResult(RESULT_OK, data);
				finish();
			}
		});
	}

}
