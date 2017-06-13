package com.example.gabo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
            //Intent intent = new Intent(this, SettingsActivity.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.button:
                button = (Button)findViewById(R.id.button);
                abrirSolucion(1, button);
                break;
            case R.id.button2:
                button = (Button)findViewById(R.id.button2);
                abrirSolucion(2, button);
                break;
            case R.id.button3:
                button = (Button)findViewById(R.id.button3);
                abrirSolucion(3, button);
                break;
            case R.id.button4:
                button = (Button)findViewById(R.id.button4);
                abrirSolucion(4, button);
                break;
            case R.id.button5:
                button = (Button)findViewById(R.id.button5);
                abrirSolucion(5, button);
                break;
            case R.id.button6:
                button = (Button)findViewById(R.id.button6);
                abrirSolucion(6, button);
                break;
            case R.id.button7:
                button = (Button)findViewById(R.id.button7);
                abrirSolucion(7, button);
                break;
            case R.id.button8:
                button = (Button)findViewById(R.id.button8);
                abrirSolucion(8, button);
                break;
            case R.id.button9:
                button = (Button)findViewById(R.id.button9);
                abrirSolucion(9, button);
                break;
            case R.id.button10:
                button = (Button)findViewById(R.id.button10);
                abrirSolucion(10, button);
                break;
        }
    }

    public void abrirSolucion(int valor, Button button) {

        ColorDrawable buttonColor;
        int colorId;
        Intent intent = new Intent(this, Solucion.class);

        buttonColor = (ColorDrawable) button.getBackground();
        colorId = buttonColor.getColor();

        intent.putExtra("tabla", valor);
        intent.putExtra("color", colorId);

        startActivity(intent);
    }

}
