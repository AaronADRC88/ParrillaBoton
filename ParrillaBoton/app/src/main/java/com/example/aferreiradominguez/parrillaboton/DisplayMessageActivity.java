package com.example.aferreiradominguez.parrillaboton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_message);
        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String progreso=MainActivity.progresoSeek;
        String planeta=MainActivity.spinnerPlanet;

        // Create the text view
       TextView textView = (TextView)  findViewById(R.id.tW_del_display);

        textView.setText(message+"\n"+progreso+"\n"+planeta);

        // Set the text view as the activity layout
        //setContentView(textView);


    }
    public void vuelveMain(View b){
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.edit_message2);
        String message = editText.getText().toString();
        intent.putExtra("resultado", message);
        setResult(Activity.RESULT_OK,intent);
        finish();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}