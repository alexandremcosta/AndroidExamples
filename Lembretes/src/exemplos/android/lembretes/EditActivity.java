package exemplos.android.lembretes;

import exemplos.android.lembretes.EditActivity;
import exemplos.android.lembretes.LembreteTable;
import exemplos.android.lembretes.LembretesContentProvider;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends Activity {
	private Spinner mCategory;
	private EditText mTitleText;
	private EditText mBodyText;

	private Uri todoUri;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.edit);

		mCategory = (Spinner) findViewById(R.id.category);
		mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
		mBodyText = (EditText) findViewById(R.id.todo_edit_description);
		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);

		Bundle extras = getIntent().getExtras();

		todoUri =
				(bundle == null) ? null : (Uri) bundle
						.getParcelable(LembretesContentProvider.CONTENT_ITEM_TYPE);

		if (extras != null) {
			todoUri =
					extras.getParcelable(LembretesContentProvider.CONTENT_ITEM_TYPE);

			fillData(todoUri);
		}

		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (TextUtils.isEmpty(mTitleText.getText().toString())) {
					Toast.makeText(EditActivity.this, "Por favor, entre com um t�tulo.",
							Toast.LENGTH_LONG).show();
				}
				else {
					setResult(RESULT_OK);
					finish();
				}
			}

		});
	}

	private void fillData(Uri uri) {
		String[] projection =
			{ LembreteTable.COLUMN_SUMMARY, LembreteTable.COLUMN_DESCRIPTION,
				LembreteTable.COLUMN_CATEGORY };
		Cursor cursor =
				getContentResolver().query(uri, projection, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			String category =
					cursor.getString(cursor
							.getColumnIndexOrThrow(LembreteTable.COLUMN_CATEGORY));

			for (int i = 0; i < mCategory.getCount(); i++) {

				String s = (String) mCategory.getItemAtPosition(i);
				if (s.equalsIgnoreCase(category)) {
					mCategory.setSelection(i);
				}
			}

			mTitleText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(LembreteTable.COLUMN_SUMMARY)));
			mBodyText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(LembreteTable.COLUMN_DESCRIPTION)));

			cursor.close();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putParcelable(LembretesContentProvider.CONTENT_ITEM_TYPE, todoUri);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	private void saveState() {
		String category = (String) mCategory.getSelectedItem();
		String summary = mTitleText.getText().toString();
		String description = mBodyText.getText().toString();

		if (description.length() == 0 && summary.length() == 0) {
			return;
		}

		ContentValues values = new ContentValues();
		values.put(LembreteTable.COLUMN_CATEGORY, category);
		values.put(LembreteTable.COLUMN_SUMMARY, summary);
		values.put(LembreteTable.COLUMN_DESCRIPTION, description);

		if (todoUri == null) {
			// New todo
			todoUri =
			getContentResolver().insert(LembretesContentProvider.CONTENT_URI,
					values);
		}
		else {
			// Update todo
			getContentResolver().update(todoUri, values, null, null);
		}
	}

}
