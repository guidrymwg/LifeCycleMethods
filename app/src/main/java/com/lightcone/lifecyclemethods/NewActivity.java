package com.lightcone.lifecyclemethods;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by guidry on 6/12/16.
 */
public class NewActivity extends Activity{

    private static final int TL = Toast.LENGTH_SHORT;  // Toast.LENGTH_LONG for longer
    private static final String TAG = "LIFECYCLES";
    private static final String AC = "Second Activity: ";
    private String message = "";

    /** Lifecycle method: Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
        message = "onCreate() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
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

    /** Lifecycle method: Called when the activity is being placed in the background */
    @Override
    protected void onPause(){
        super.onPause();
        message = "onPause() called";
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    /** Lifecycle method: Called after the activity has been stopped, prior to restarting */
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
        message = "onSaveInstanceState(Bundle outState) called.";
        message += " Bundle mappings = "+outState.size();
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }

    // Note that this one is not a lifecycle method
    @Override
    protected void onRestoreInstanceState (Bundle inState) {
        super.onRestoreInstanceState(inState);
        message = "onRestoreInstanceState(Bundle inState) called.";
        message += " Bundle mappings = "+inState.size();
        Toast.makeText(this, AC+message, TL).show();
        Log.i(TAG, AC+message);
    }
}
