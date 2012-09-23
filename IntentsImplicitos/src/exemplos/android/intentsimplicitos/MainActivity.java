package exemplos.android.intentsimplicitos;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        Uri uri = (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM);
        ImageView iv = (ImageView) findViewById(R.id.image);
        iv.setImageURI(uri);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(uri.toString());
    }
}
