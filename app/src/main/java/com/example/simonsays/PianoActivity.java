package com.example.simonsays;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PianoActivity extends AppCompatActivity {

    private TextView turnTv,scoreTv;
    private Button[] buttons;
    private MediaPlayer[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        buttons = BoardFactory.getInstance().getButtons();

        scoreTv = findViewById(R.id.score_player_tv);
        turnTv = findViewById(R.id.piano_player_tv);


        buttons[0]  = findViewById(R.id.do_btn);
        buttons[1] = findViewById(R.id.re_btn);
        buttons[2] = findViewById(R.id.mi_btn);
        buttons[3] = findViewById(R.id.fa_btn);
        buttons[4] = findViewById(R.id.sol_btn);
        buttons[5] = findViewById(R.id.la_btn);


        sounds = new MediaPlayer[buttons.length];
        sounds[0] = MediaPlayer.create(PianoActivity.this, R.raw.piano_a_major);
        sounds[1] = MediaPlayer.create(PianoActivity.this, R.raw.piano_b_major);
        sounds[2] = MediaPlayer.create(PianoActivity.this, R.raw.piano_c_major);
        sounds[3] = MediaPlayer.create(PianoActivity.this, R.raw.piano_d_major);
        sounds[4] = MediaPlayer.create(PianoActivity.this, R.raw.piano_e_major);
        sounds[5] = MediaPlayer.create(PianoActivity.this, R.raw.piano_f_major);


        BoardFactory.getInstance().setSounds(sounds);
        BoardFactory.getInstance().setButtons(buttons);
        BoardFactory.getInstance().setContext(this);
        BoardFactory.getInstance().logicInitialize(turnTv,scoreTv);

    }

    @Override
    protected void onStart() {
        super.onStart();
        BoardFactory.getInstance().getGameLogic().simonPlay();
    }

    @Override
    public void onBackPressed()
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getText(R.string.menu_back).toString());
        alertDialogBuilder
                .setMessage(getText(R.string.brings_out).toString())
                .setCancelable(false)
                .setPositiveButton(getText(R.string.lo_rotze).toString(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent =new Intent(PianoActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })

                .setNegativeButton(getText(R.string.rotze).toString(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

