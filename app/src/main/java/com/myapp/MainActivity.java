package com.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    //basic button
    Button FunctionCalling;

    SpeechExample speechExample;
    SoundExample soundExample;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechExample = new SpeechExample(getApplicationContext());
        soundExample = new SoundExample();

//        new MediaPlayerExample(getApplicationContext());

        SurfaceView surface = (SurfaceView) findViewById(R.id.surfaceView);
        new Bresenham(surface);

        ((Button) findViewById(R.id.btnSound)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("Sound", "Un sonido breve");
                soundExample.playTestSound();
            }
        });

        ((Button) findViewById(R.id.btnTTS)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("TTS", "Un saludito");
                speechExample.createSpeak();
            }
        });

        ((Button) findViewById(R.id.btnMIDI)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("MIDI", "En breve, una canción famosa");
                new MediaPlayerExample(getApplicationContext());
            }
        });

    }

    private void doToast(String btnName, String message){
        ((EditText) findViewById(R.id.editText)).setText(btnName+" clicked");
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    // function declaration
    private void functionCallingExample() {
//        speechExample.createSpeak();
//        soundExample.playTestSound();
//        new MediaPlayerExample(getApplicationContext());
    }



    @Override
    protected void onResume() {
        super.onResume();
//        soundExample.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        speechExample.onDestroy();
    }


    private void killApp() {

    }
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) return;

        Toast.makeText(MainActivity.this, "Presionaste HOME", Toast.LENGTH_SHORT).show();
        finish();
        System.exit(1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // your code
            finish();
            System.exit(1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
            // your code
            Toast.makeText(MainActivity.this, "Presionaste HOME", Toast.LENGTH_SHORT).show();
            finish();
            System.exit(1);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}