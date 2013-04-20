package com.example.myfirstapp;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.MenuItem;

public class DisplayMessageActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    
	    setContentView(R.layout.activity_display_message);
	    
		Messages msg = new Messages(this,"DBMessages",null,2);	    
        SQLiteDatabase db = msg.getWritableDatabase();

        if(db != null) {
        	//Insertamos los datos en la tabla Usuarios
            db.execSQL("INSERT INTO Messages (message) " + "VALUES ('" + message +"')");
            Cursor c = db.rawQuery("SELECT * from Messages",null) ;
            SimpleCursorAdapter items = new SimpleCursorAdapter(this, R.layout.row, c,new String[] {"message"}, new int[] {R.id.rowtext});
            setListAdapter(items);
            db.close();
        }
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
