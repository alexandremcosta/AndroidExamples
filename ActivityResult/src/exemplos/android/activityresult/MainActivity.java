package exemplos.android.activityresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int CALL_ACTIVITY = 1;
	private TextView tvNome;
	
    @Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        setTvNome((TextView) findViewById(R.id.tvNome));
        
        Button btn = (Button) findViewById(R.id.btnCallActivity);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SecondActivity.class);
				startActivityForResult(i, CALL_ACTIVITY);				
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode == RESULT_OK && requestCode == CALL_ACTIVITY) {
    		if (data.hasExtra("name")) {
    			tvNome.setText(data.getExtras().getString("name"));
    		}
    		
    	}
    }
    
    

	public TextView getTvNome() {
		return tvNome;
	}

	public void setTvNome(TextView tvNome) {
		this.tvNome = tvNome;
	}
    
}
