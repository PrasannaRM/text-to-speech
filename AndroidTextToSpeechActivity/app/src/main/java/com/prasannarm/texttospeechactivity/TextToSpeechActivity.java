package com.prasannarm.texttospeechactivity;

import android.os.Bundle;

import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.Toast;

public class TextToSpeechActivity extends Activity {

    TextToSpeech textToSpeech;
    EditText textBox;
    Button speakButton;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText)findViewById(R.id.editText);
        speakButton = (Button)findViewById(R.id.speak);
        clearButton = (Button)findViewById(R.id.clear);


        textToSpeech =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = textBox.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBox.getText().clear();
                Toast.makeText(getApplicationContext(), "Clearing the Text",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}
