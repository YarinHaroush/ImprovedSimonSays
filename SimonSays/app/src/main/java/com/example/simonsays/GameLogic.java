package com.example.simonsays;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameLogic {

    private Button[] mButtons;
    private ArrayList<Integer> playerClicks =new ArrayList<Integer>();
    private ArrayList<Integer> simonClicks=new ArrayList<Integer>();
    private Player player, computer;
    private eTurnPlayerOrSimon mTurn=eTurnPlayerOrSimon.simon;
    private int currLevel =1;
    private Context context;
    private CountDownTimer countDownTimer;
    private MediaPlayer soundToPlay;
    private MediaPlayer[] sounds;
    private TextView playerNameTv;
    private int toPlay, simonSoundPosition = 0;
    private Random rand = new Random();


    public GameLogic(TextView textView, MediaPlayer[] sounds){
        mButtons = BoardFactory.getInstance().getButtons();
        playerNameTv = textView;
        player = BoardFactory.getInstance().getmPlayer();
        computer =new Player("Simon");
        context = BoardFactory.getInstance().getContext();
        this.sounds = sounds;

        countDownTimer = new CountDownTimer(800L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(soundToPlay!=null) {
                    mButtons[toPlay].setPressed(true);
                }
            }

            @Override
            public void onFinish() {
                if(soundToPlay!=null) {
                    soundToPlay.setVolume(0, 0);
                    mButtons[toPlay].setPressed(false);
                    if(mTurn == eTurnPlayerOrSimon.simon) {
                        simonSoundPosition++;
                        playSimonSounds();
                    }
                }
            }
        };

        addMoves();
    }


    public void addMoves()
    {
//        for(int i = 0; i < currLevel; i++)
//        {
//            Random rand = new Random();
//            int randNum = rand.nextInt(mButtons.length);
//            simonClicks.add(randNum);
//        }
        int randNum = rand.nextInt(mButtons.length);
        simonClicks.add(randNum);
    }

    public void dictator(){
        boolean isMatched =  validateTurn();

        if(!isMatched){
//            context.startActivity(new Intent(context, GameOver.class));
            Toast.makeText(context,"looser", Toast.LENGTH_LONG).show();
        }
        else
        {

            if(playerClicks.size() == simonClicks.size()) {
                currLevel++;
                simonSoundPosition = 0;
                switchTurn();
                addMoves();
                simonPlay();
            }

        }
    }

    public void simonPlay(){
        playerClicks.clear();
        playerNameTv.setText(computer.getName());
        playSimonSounds();
    }

    public void playerPlay(int currSound){
        playerClicks.add(currSound);
        dictator();
    }

    public eTurnPlayerOrSimon getmTurn() {
        return mTurn;
    }

    public boolean validateTurn()
    {
        int currentClick = playerClicks.size();
        boolean res = true;
        int playerLastPressedBtn, simonLastPressedBtn;
        playerLastPressedBtn = playerClicks.get(currentClick-1);
        simonLastPressedBtn = simonClicks.get(currentClick-1);
        if(playerLastPressedBtn != simonLastPressedBtn)
        {
            res=  false;
        }
        return res;
    }


    public void switchTurn()
    {
        if (mTurn == eTurnPlayerOrSimon.player)
        {
            mTurn = eTurnPlayerOrSimon.simon;
            for (Button button: mButtons) {
                button.setClickable(false);
            }
        }
        else
        {
            mTurn = eTurnPlayerOrSimon.player;
            playerNameTv.setText(player.getName());
            for (Button button: mButtons) {
                button.setClickable(true);
            }
        }
    }



    private void playSimonSounds() {
        if(simonSoundPosition < simonClicks.size()) {
            mButtons[simonClicks.get(simonSoundPosition)].callOnClick();
        }
        else {
            switchTurn();
        }
    }



    public void playOneSound(int toPlay) {
        this.toPlay = toPlay;
        soundToPlay = sounds[toPlay];
        soundToPlay.setVolume(1, 1);
        mButtons[toPlay].setPressed(false);
        // TODO make the button pressed(false) if choose the same button as the prev one
//        if(simonClicks.size() != 1 && sounds[simonClicks.get(simonClicks.size()-2)] == sounds[toPlay]) {
//            try {
//                mButtons[toPlay].setPressed(false);
//                TimeUnit.MILLISECONDS.sleep(200L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        soundToPlay.start();
        countDownTimer.start();
        for (int i = 0; i < sounds.length; i++) {
            final int finalCounter = i;
            if(sounds[finalCounter] != sounds[toPlay]) {
                sounds[finalCounter].setVolume(0, 0);
                mButtons[finalCounter].setPressed(false);
            }
        }
    }

}

