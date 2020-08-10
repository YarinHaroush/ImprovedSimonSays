package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    BoardFactory boardFactory;
    String boardName, playerName;
    EditText playerNameET;
    Button boardNameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerNameET = findViewById(R.id.editText);
        boardNameBtn = findViewById(R.id.button);
        boardFactory = BoardFactory.getInstance();
        getPlayerNameAndBoardType();

        boardNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    private void getPlayerNameAndBoardType() {
        playerName = playerNameET.getText().toString();
        boardName = boardNameBtn.getText().toString().toLowerCase();
    }

    public void startGame(){
        boardFactory.setPlayer(playerName);
        boardFactory.setTypeBoard(boardName);
        boardFactory.setContext(this);
        boardFactory.boardInitialize();

    }
}
