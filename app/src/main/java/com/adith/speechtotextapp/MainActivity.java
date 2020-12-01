package com.adith.speechtotextapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editTextPesan;
    Button buttonSpeak;

    TextToSpeech TTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPesan = findViewById(R.id.editText_pesan);
        buttonSpeak = findViewById(R.id.speakBtn);

        TTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    TTS.setLanguage(Locale.US);

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = editTextPesan.getText().toString().trim();
                if (toSpeak.equals("")) {
                    Toast.makeText(MainActivity.this, "Masukkan pesan", Toast.LENGTH_SHORT).show();

                } else {
                    TTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                }
            }
        });
    }

    @Override
    protected void onPause() {
        if (TTS != null && TTS.isSpeaking()) {
            TTS.stop();
        }
        super.onPause();
    }


}