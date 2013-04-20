package com.example.myfirstapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private static final int NOTIF_ALERTA_ID = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        TextView TextViewCountry = (TextView) findViewById(R.id.textViewcountry);
        TextViewCountry.setText(pref.getString("selected_country", getString(R.string.no_country_selected )));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        ToggleButton TButton = (ToggleButton)findViewById(R.id.BtnBoton2);
        String message = TButton.getText().toString()+':'+editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                Log.i("ActionBar", "Nuevo!");
                return true;
            case R.id.menu_save:
                Log.i("ActionBar", "Guardar!");
                return true;
            case R.id.menu_settings:
            	startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendNotif1(View view) {
    	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    	NotificationManager mNotificationManager =
        	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(this)
    	        .setSmallIcon(android.R.drawable.stat_sys_warning)
    	        .setContentTitle("Mensaje :" + pref.getString("opcion2", ""))
    	        .setContentText("Country: ." + pref.getString("selected_country", getString(R.string.no_country_selected )))
    	        .setDefaults(Notification.DEFAULT_ALL);
    	
    	Intent resultIntent = new Intent(this, MainActivity.class);
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    	stackBuilder.addParentStack(MainActivity.class);
    	stackBuilder.addNextIntent(resultIntent);
    	
    	PendingIntent resultPendingIntent =
    	        stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );

    	mBuilder.setContentIntent(resultPendingIntent);

    	mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
    }

    public void sendNotif2(View view) {
    	Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Toast por defecto", Toast.LENGTH_SHORT);
            toast1.show();
    }
}
