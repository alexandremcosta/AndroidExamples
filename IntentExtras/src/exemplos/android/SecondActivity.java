package exemplos.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.second);
		super.onCreate(savedInstanceState);
		
		TextView tv = (TextView) findViewById(R.id.textView);
		String text = getIntent().getExtras().getString("TEXTO");
		tv.setText(text);
	}

}
