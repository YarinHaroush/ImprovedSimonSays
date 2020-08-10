package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DrumActivity extends AppCompatActivity{

    GameLogic gameLogic;
    TextView turnTv, titleTv;
    Button[] buttons;
    MediaPlayer[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum);

        buttons = BoardFactory.getInstance().getButtons();
//        boardLogic = BoardLogic.getInstance();

        turnTv = findViewById(R.id.normal_player_tv);
        titleTv = findViewById(R.id.text_view_simon);

        buttons[0] = findViewById(R.id.drum1);
        buttons[1] = findViewById(R.id.drum2);
        buttons[2] = findViewById(R.id.drum3);
        buttons[3] = findViewById(R.id.drum4);
        buttons[4] = findViewById(R.id.drum5);

        sounds = new MediaPlayer[buttons.length];
        sounds[0] = MediaPlayer.create(DrumActivity.this, R.raw.drum_sound1);
        sounds[1] = MediaPlayer.create(DrumActivity.this, R.raw.drum_sound2);
        sounds[2] = MediaPlayer.create(DrumActivity.this, R.raw.drum_sound3);
        sounds[3] = MediaPlayer.create(DrumActivity.this, R.raw.drum_sound4);
        sounds[4] = MediaPlayer.create(DrumActivity.this, R.raw.drum_sound5);

        BoardFactory.getInstance().setSounds(sounds);
        BoardFactory.getInstance().setButtons(buttons);
        BoardFactory.getInstance().logicInitialize(turnTv);


    }
}
