package myeyes.example.ttspeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private EditText etx;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this,this);
        etx = (EditText) findViewById(R.id.etxescribir);
        btn = (Button) findViewById(R.id.btnreproducir);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());
            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                Log.e("TTS","Este lenguaje no es soportado!");
            } else {
                btn.setEnabled(true);
                speakOut();
            }
        } else {
            Log.e("TTS","Inicializacion del lenguaje ha fallado!");
        }
    }

    private void speakOut() {
        String text = etx.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }
}
