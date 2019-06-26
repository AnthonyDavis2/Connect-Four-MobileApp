package com.example.projectmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


public class connectFourGame extends AppCompatActivity {

    private static final int MAX_ROW = 6;
    private static final int MAX_COL = 7;
    int turn, row;
    Drawable player1, player2,empty;

    ImageButton[][] space = new ImageButton[MAX_ROW][MAX_COL];

    //Identifiers for the rows (user can only select a row)
    LinearLayout COL0,COL1,COL2,COL3,COL4,COL5,COL6;

    //Players 1 and two, may need to make these objects with states ( in play, not in play , chip color )
    Boolean isPlayerOneTurn, isPlayerTwoTurn;
    Boolean[] isFilled = new Boolean[MAX_COL];

    Button newGame, help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four_game);

        //Show messages for the first two turns only. Initialize board to have no fill color on any space
        turn = 0;
        turnMessage();

        // Set player 1 to start
        isPlayerOneTurn = true;
        isPlayerTwoTurn = false;

        //Column init
        COL0 = findViewById(R.id.C0);
        COL1 = findViewById(R.id.C1);
        COL2 = findViewById(R.id.C2);
        COL3 = findViewById(R.id.C3);
        COL4 = findViewById(R.id.C4);
        COL5 = findViewById(R.id.C5);
        COL6 = findViewById(R.id.C6);

        space= new ImageButton[][]{ {findViewById(R.id.AF),findViewById(R.id.BF),findViewById(R.id.CF),findViewById(R.id.DF),findViewById(R.id.EF),findViewById(R.id.FF),findViewById(R.id.GF)},
                {findViewById(R.id.AE),findViewById(R.id.BE),findViewById(R.id.CE),findViewById(R.id.DE),findViewById(R.id.EE),findViewById(R.id.FE),findViewById(R.id.GE)},
                {findViewById(R.id.AD),findViewById(R.id.BD),findViewById(R.id.CD),findViewById(R.id.DD),findViewById(R.id.ED),findViewById(R.id.FD),findViewById(R.id.GD)},
                {findViewById(R.id.AC),findViewById(R.id.BC),findViewById(R.id.CC),findViewById(R.id.DC),findViewById(R.id.EC),findViewById(R.id.FC),findViewById(R.id.GC)},
                {findViewById(R.id.AB),findViewById(R.id.BB),findViewById(R.id.CB),findViewById(R.id.DB),findViewById(R.id.EB),findViewById(R.id.FB),findViewById(R.id.GB)},
                {findViewById(R.id.AA),findViewById(R.id.BA),findViewById(R.id.CA),findViewById(R.id.DA),findViewById(R.id.EA),findViewById(R.id.FA),findViewById(R.id.GA)}};

        isFilled = new Boolean[] { false, false, false, false, false, false, false};

        player1 = ContextCompat.getDrawable(getApplicationContext(),R.drawable.red_chip);
        player2 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.blue_chip);
        empty = ContextCompat.getDrawable(getApplicationContext(),R.drawable.game_bg);


        newGame = findViewById(R.id.newGame);
        help = findViewById(R.id.help);

        newGame.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),helpScreen.class);
                startActivity(intent);
            }
        });
    }



    public void buttonClick(View view){
        if(turn<1) turnMessage();

        switch(view.getId()){

            case R.id.C0:
                findSpace(0);
                turn++;
                break;
            case R.id.C1:
                findSpace(1);
                turn++;
                break;
            case R.id.C2:
                findSpace(2);
                turn++;
                break;
            case R.id.C3:
                findSpace(3);
                turn++;
                break;
            case R.id.C4:
                findSpace(4);
                turn++;
                break;
            case R.id.C5:
                findSpace(5);
                turn++;
                break;
            case R.id.C6:
                findSpace(6);
                turn++;
                break;
        }

    }

    public void turnMessage(){
        if(turn==0){
            Toast.makeText(this, "Lets Go! Player 1, you're up!", Toast.LENGTH_SHORT).show();
            turn++;
        }
        else if(turn==1){
            Toast.makeText(this, "Player 2, It's your turn!", Toast.LENGTH_SHORT).show();
            turn++;
        }
    }

    public void findSpace(int column) {
        int i;
        if(!isFilled[column]){
            for(i=5;i>=0;i--)
            {
                if(space[i][column].getBackground().getConstantState().equals(empty.getConstantState())){

                    if(isPlayerOneTurn){
                        space[i][column].setBackground(player1);
                        if(turn>=7) checkWin(i,column, player1);
                        isPlayerOneTurn=false;
                        isPlayerTwoTurn=true;
                    }
                    else {
                        space[i][column].setBackground(player2);
                        if(turn>=7) checkWin(i,column, player2);
                        isPlayerOneTurn = true;
                        isPlayerTwoTurn = false;
                    }
                    isFilled[column] = (i==0) ? true: false;
                    return;
                }
            }
        }
        else{
            Toast.makeText(this, "Column Filled! Try another one", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkWin(int currentRow, int currentCol, Drawable playercolor){
        int count =0;

        //Winner Down
        for (int i = 0; i < MAX_ROW; i++) {
            if (space[i][currentCol].getBackground().getConstantState().equals(playercolor.getConstantState())) {
                count++;
            } else {
                count = 0;
            }
        }
        if (count >= 4) {
            winMessage();
            return;
        }

        // Winner across
        for (int j = 0; j < MAX_COL; j++) {
            if (space[currentRow][j].getBackground().getConstantState().equals(playercolor.getConstantState())) {
                count++;
            } else {
                count = 0;
            }
        }
        if (count >= 4) {
            winMessage();
            return;
        }

        // Winner diagonal right (backward diagonal -> \)
        for (int i = currentRow, j = currentCol; i < MAX_ROW && j < MAX_COL; i++, j++) {
            if (space[i][j].getBackground().getConstantState().equals(playercolor.getConstantState())) {
                count++;
            } else {
                count = 0;
            }
        }
        if (count >= 4) {
            winMessage();
            return;
        }

        // Winner diagonal left (forward diagonal -> /)
        for (int i = currentRow, j = currentCol; i < MAX_ROW && j >= 0; i++, j--) {
            if (space[i][j].getBackground().getConstantState().equals(playercolor.getConstantState())) {
                count++;
            } else {
                count = 0;
            }
        }
        if (count >= 4) {
            winMessage();
            return;
        }
    }

    public void winMessage(){
        if (isPlayerOneTurn) Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        else{ Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();}
        COL0.setOnClickListener(null);
        COL1.setOnClickListener(null);
        COL2.setOnClickListener(null);
        COL3.setOnClickListener(null);
        COL4.setOnClickListener(null);
        COL5.setOnClickListener(null);
        COL6.setOnClickListener(null);

    }
}