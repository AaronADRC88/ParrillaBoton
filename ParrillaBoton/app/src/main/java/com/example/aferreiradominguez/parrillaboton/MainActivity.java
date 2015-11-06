package com.example.aferreiradominguez.parrillaboton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.aferreiradominguez.parrillaboton.MESSAGE";
    public static String progresoSeek;
    public static String spinnerPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View vies,
                                       int position, long id) {
                // Aquí se codifica la lógica que se ejecutará al seleccionar un elemento del Spinner.
                spinnerPlanet = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {

            }
        });
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                // Toast.makeText(getApplicationContext(), "cambiando el progreso del seekbar", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Has movido la seekbar", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Vibrator vib = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(seekBar.getProgress() * 10);
                Toast.makeText(getApplicationContext(), "Progreso: " + seekBar.getProgress() + "/" + seekBar.getMax(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "seekbar en reposo", Toast.LENGTH_SHORT).show();
                progresoSeek = String.valueOf(seekBar.getProgress());
            }
        });

        Button btAcept = (Button) findViewById(R.id.btAceptar);
        btAcept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Has pulsado Aceptar!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                TextView textView = (TextView) findViewById(R.id.edit_message_del_display);
                String message = data.getStringExtra("resultado");
                textView.setText(message);
            }
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("spinner", spinnerPlanet);
        //intent.putExtra("progreso",);
        startActivityForResult(intent, 1);
    }

    public void irpavolver(View v) {
        Intent intent1 = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent1.putExtra(EXTRA_MESSAGE, message);
        intent1.putExtra("spinner", spinnerPlanet);
        startActivityForResult(intent1, 1);
    }

    public void llama(View v) {
        EditText editText = (EditText) findViewById(R.id.edit_tel);
        String message = editText.getText().toString();
        Uri number = Uri.parse("tel:".concat(message));
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void navega(View v) {
        EditText editText = (EditText) findViewById(R.id.edit_url);
        String message = editText.getText().toString();
        Uri webpage = Uri.parse("http://www." + message + ".com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

    public void localiza(View v) {
        EditText editText = (EditText) findViewById(R.id.edit_mapa);
        String message = editText.getText().toString();
// Map point based on address
        Uri location = Uri.parse("geo:0,0?q=" + message);
// Or map point based on latitude/longitude
// Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
