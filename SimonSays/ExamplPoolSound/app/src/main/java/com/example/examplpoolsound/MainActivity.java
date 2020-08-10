package com.example.examplpoolsound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int[] buttons;
    SoundPool soundsPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons=new int[8];
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            soundsPool=new SoundPool.Builder().setMaxStreams(8).build();
        }
        else {
            soundsPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        }


        buttons[0] = soundsPool.load(MainActivity.this, R.raw.xylo1, 1);
        buttons[1] = soundsPool.load(MainActivity.this, R.raw.xylo2, 1);
        buttons[2] = soundsPool.load(MainActivity.this, R.raw.xylo3, 1);
        buttons[3] = soundsPool.load(MainActivity.this, R.raw.xylo4, 1);
        buttons[4] = soundsPool.load(MainActivity.this, R.raw.xylo5, 1);
        buttons[5] = soundsPool.load(MainActivity.this, R.raw.xylo6, 1);
        buttons[6] = soundsPool.load(MainActivity.this, R.raw.xylo7, 1);
        buttons[7] = soundsPool.load(MainActivity.this, R.raw.xylo8, 1);


    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.a_key:

                soundsPool.play(buttons[0], 1, 1, 0, 0, 1);
                break;

            case R.id.b_key:

                soundsPool.play(buttons[1], 1, 1, 0, 0, 1);
                break;

            case R.id.c_key:

            soundsPool.play(buttons[2], 1, 1, 0, 0, 1);
            break;

            case R.id.d_key:

            soundsPool.play(buttons[3], 1, 1, 0, 0, 1);
            break;

            case R.id.e_key:

            soundsPool.play(buttons[4], 1, 1, 0, 0, 1);
            break;

            case R.id.f_key:

            soundsPool.play(buttons[5], 1, 1, 0, 0, 1);
            break;

            case R.id.g_key:

            soundsPool.play(buttons[6], 1, 1, 0, 0, 1);
            break;

            case R.id.h_key:

                soundsPool.play(buttons[7], 1, 1, 0, 0, 1);
                break;
        }
    }

}
