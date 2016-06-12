package com.lightcone.lifecyclemethods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.math.BigDecimal;
import java.util.Set;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final int TL = Toast.LENGTH_SHORT;   // Toast.LENGTH_LONG for longer
    private static final String AC = "Main Activity: ";
    private static final String TAG = "LIFECYCLES";
    private static final double MB = 1048576;  // bytes in megabyte
    private String message = "";
    private SharedPreferences mPrefs;
    private EditText textfield;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = "onCreate() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);

        // Add Click listeners for buttons
        View button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        View button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        // Identify EditText field
        textfield = (EditText) findViewById(R.id.EditText01);

        // Set up shared prefs to hold username
        mPrefs = getSharedPreferences("LSprefs",0);
        userName = mPrefs.getString("user_name", "");
        textfield.setText(userName);

        // Explore the contents of the savedInstanceState Bundle restored by Android by obtaining
        // the set of keys in the Bundle and then displaying the corresponding content

        if(savedInstanceState != null){
            Set<String> set = savedInstanceState.keySet();
            Object sset[] = set.toArray();
            for(int i=0; i<sset.length; i++){
                String thisKey = sset[i].toString();
                Log.i(TAG, " Restored Bundle savedInstanceState key "+i+" = "+thisKey);
                Log.i(TAG, " value = "+savedInstanceState.get(thisKey).toString());
            }
        } else {
            Log.i(TAG, " Restored Bundle savedInstanceState is null");
        }

        // Write memory usage when initialized.
        // See http://androidcommunity.com/forums/f4/how-to-intialise-activitymanager-5399/

        ActivityManager aMan = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo ();
        aMan.getMemoryInfo( memInfo );
        // Output memory information converted to megabytes
        Log.i(TAG, "Android Activity Manager:");
        Log.i( TAG, " Available Memory:  "
                + rounded((double)memInfo.availMem/MB, 1)+" MB");
        Log.i( TAG,  " Low Memory Threshhold:  "
                + rounded((double)memInfo.threshold/MB, 1)+" MB");
        Log.i( TAG, " Low Memory:  " + memInfo.lowMemory );
    }

    /** Lifecycle method: Called when the activity is becoming visible to user. */
    @Override
    protected void onStart(){
        super.onStart();
        message = "onStart() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    /** Lifecycle method: Called when the activity begins interacting with the user. */
    @Override
    protected void onResume(){
        super.onResume();
        message = "onResume() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    /** Lifecycle method: Called when the activity is being placed in the background.
     * Any user data that need to persist should be written to long-term storage here since
     * after onPause() returns there is no guarantee that further lifecycle methods will be
     * executed if the system decides to kill the app while it is in the background to reclaim
     * resources.  We shall use shared preferences to illustrate writing to long-term storage. */

    @Override
    protected void onPause(){
        super.onPause();

        message = "onPause() called (storing persistent user data)";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);

        // Write current userName to shared prefs so that it will persist even if the app is killed
        // after onPause() returns.

        userName = textfield.getText().toString();
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_name", userName);
        editor.commit();

        message = "onPause() returning (user data stored)";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    /** Lifecycle method: Called after the activity has been stopped, prior to restarting. */
    @Override
    protected void onRestart(){
        super.onRestart();
        message = "onRestart() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    /** Lifecycle method: Called when the activity is no longer visible to the user. */
    @Override
    protected void onStop(){
        super.onStop();
        message = "onStop() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    /** Lifecycle method: The final call received before the activity is destroyed. */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        message = "onDestroy() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

        // Example: insert user data into Bundle outState (illustrate: put current time in ms)
        outState.putLong("timeMillis", System.currentTimeMillis());

        message = "onSaveInstanceState(Bundle outState) called.";
        message += " Bundle mappings = "+outState.size();
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);

        // Explore the contents of the outState Bundle saved by Android by obtaining
        // the set of keys in the Bundle and then displaying the corresponding content.
        // This should show entries for both the user interface data saved automatically
        // by Android and the private user data inserted in the Bundle above.

        Set<String> set = outState.keySet();
        Object sset[] = set.toArray();
        for(int i=0; i<sset.length; i++){
            String thisKey = sset[i].toString();
            Log.i(TAG, " Saved Bundle outState key "+i+" = "+thisKey);
            Log.i(TAG, " value = "+outState.get(thisKey).toString());
        }
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onRestoreInstanceState (Bundle inState) {
        super.onRestoreInstanceState(inState);
        message = "onRestoreInstanceState(Bundle inState) called.";
        message += " Bundle mappings = "+inState.size();
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);

        // Explore the contents of the inState Bundle restored by Android by obtaining
        // the set of keys in the Bundle and then displaying the corresponding content
        if(inState != null){
            Set<String> set = inState.keySet();
            Object sset[] = set.toArray();
            for(int i=0; i<sset.length; i++){
                String thisKey = sset[i].toString();
                Log.i(TAG, " Restored Bundle inState key "+i+" = "+thisKey);
                Log.i(TAG, " value = "+inState.get(thisKey).toString());
            }
        } else {
            Log.i(TAG," Restored Bundle inState is null");
        }
    }

    // Process button clicks
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                Intent j = new Intent(this, NewActivity.class);
                startActivity(j);
                break;
            case R.id.button2:
                message = AC+"Calling finish()";
                Toast.makeText(this, message, TL).show();
                Log.i(TAG, message);
                finish();
        }
    }

    // Utility method to round a double to fixed number of decimal places.
    // http://groups.google.com/group/android-beginners/browse_thread/thread/c28370ba97c97d67
    private static double rounded(double d, int decimalPlace){
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
