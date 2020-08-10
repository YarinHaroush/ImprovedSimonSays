package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NormalActivity extends AppCompatActivity{

    GameLogic gameLogic;
    TextView turnTv, titleTv;
    Button[] buttons;
    MediaPlayer[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        buttons = BoardFactory.getInstance().getButtons();
//        boardLogic = BoardLogic.getInstance();

        turnTv = findViewById(R.id.normal_player_tv);
        titleTv = findViewById(R.id.text_view_simon);

        buttons[0] = findViewById(R.id.yellow_button);
        buttons[1] = findViewById(R.id.green_button);
        buttons[2] = findViewById(R.id.blue_button);
        buttons[3] = findViewById(R.id.red_button);

        sounds = new MediaPlayer[buttons.length];
        sounds[0] = MediaPlayer.create(NormalActivity.this,R.raw.sound_green);
        sounds[1] = MediaPlayer.create(NormalActivity.this,R.raw.sound_red);
        sounds[2] = MediaPlayer.create(NormalActivity.this,R.raw.sound_yellow);
        sounds[3] = MediaPlayer.create(NormalActivity.this,R.raw.sound_bluee);

        BoardFactory.getInstance().setSounds(sounds);
        BoardFactory.getInstance().setButtons(buttons);

        BoardFactory.getInstance().logicInitialize(turnTv);
//        BoardFactory.getInstance().getGameLogic().simonPlay();
    }

    @Override
    protected void onStart() {
        super.onStart();
        BoardFactory.getInstance().getGameLogic().simonPlay();

    }
}
