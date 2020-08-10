package com.example.simonsays;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;


public class BoardFactory{
    private Button[] buttons;
    private Player mPlayer;
    private String typeBoard;
    private eTurnPlayerOrSimon mTurn = eTurnPlayerOrSimon.simon;
    private static BoardFactory instance;
    private Map<String, Integer> numberOfButtonsInBoard = new HashMap<String, Integer>();
    private Map<String, Class> layouts = new HashMap<String, Class>();
    private Context context;
    GameLogic gameLogic;
    private MediaPlayer[] sounds;


    public static BoardFactory getInstance() {
        if (instance == null) instance = new BoardFactory();

        return instance;
    }

    private BoardFactory() {
        numberOfButtonsInBoard.put("normal", 4);
        numberOfButtonsInBoard.put("drum", 5);
        numberOfButtonsInBoard.put("piano", 8);
        numberOfButtonsInBoard.put("xylophone", 8);

        layouts.put("normal", NormalActivity.class);
        layouts.put("drum", DrumActivity.class);
        //layouts.put("piano", 8);
        //layouts.put("xylophone",8);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void boardInitialize(){
        buttons = new Button[numberOfButtonsInBoard.get(typeBoard)];
        Class nextLayout = layouts.get(typeBoard);
        Intent intent = new Intent(context, nextLayout);
        context.startActivity(intent);
    }

    public void logicInitialize(TextView playerNameTv){
        gameLogic = new GameLogic(playerNameTv, sounds);
        setButtonsListeners();
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public Player getmPlayer() {
        return mPlayer;
    }

    public void setPlayer(String player) {
        mPlayer = new Player(player);
    }

    public String getTypeBoard() {
        return typeBoard;
    }

    public void setTypeBoard(String typeBoard) {
        this.typeBoard = typeBoard;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] mButtons) {
        this.buttons = mButtons;
    }

    private void setButtonsListeners(){
        for(int btnNum = 0; btnNum < buttons.length; btnNum++) {
            final int soundToPlay = btnNum;
            buttons[btnNum].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(gameLogic.getmTurn() == eTurnPlayerOrSimon.player) {
                        gameLogic.playOneSound(soundToPlay);
                        gameLogic.playerPlay(soundToPlay);
                    }
                    else{
                        gameLogic.playOneSound(soundToPlay);
                    }
                }
            });
        }
    }

    public void setSounds(MediaPlayer[] sounds) {
        this.sounds  = sounds;
    }
}
