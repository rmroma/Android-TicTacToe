package com.example.asi.tictactoe;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * <h1>InstructionsActivity</h1><p>
 * This class defines screen of the game</br>
 * @author Asi Belachow
 * @version 1.0
 * @since 11/04/2017
 */
public class GameActivity extends Activity {
    final MediaPlayer mp = new MediaPlayer();
    boolean sound = false;
    Player player1 = new Player();
    Player player2 = new Player();
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,moreRoundBtn;
    TextView playerNameOneTv, playerNameTwoTv, playerScoreOneTv,playerScoreTwoTv,playerTurnTv;
    int numOfMoves = 0;

    MyOnClickListener onClick = new MyOnClickListener();


    /**
     * <h1>playTurnSound</h1><p>
     * <i><ul>void playTurnSound(Player p)<i><p>
     * Plays the sound of drawing O or X
     * @param p - the player (X or O)
     */
    private void playTurnSound(Player p){
        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            AssetFileDescriptor afd = null;
            if(p == player1)
                 afd = getAssets().openFd("sounds/x_sound.mp3");
            else if(p == player2)
                afd = getAssets().openFd("sounds/o_sound.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h1>playWrongChoiceSound</h1><p>
     * <i><ul>void playWrongChoiceSound()<i><p>
     * Plays the sound of click on button that already marked
     */
    private void playWrongChoiceSound(){
        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            AssetFileDescriptor afd = null;
            afd = getAssets().openFd("sounds/worngCell.WAV");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * <h1>playWinnerSound</h1><p>
     * <i><ul>void playWinnerSound()<i><p>
     * Plays the sound of winner
     */
    private void playWinnerSound(){
        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            AssetFileDescriptor afd = getAssets().openFd("sounds/win.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * <h1>playTurnSound</h1><p>
     * <i><ul>oid playDrawSound()<i><p>
     * Plays the sound of draw
     */
    private void playDrawSound(){
        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            AssetFileDescriptor afd = getAssets().openFd("sounds/draw.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <h1>MyOnClickListener</h1><p>
     * This class defines logic of the game</br>
     * @author Asi Belachow
     * @version 1.0
     * @since 04-09-2017
     */
    public class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Button btn = (Button) v;
            switch (v.getId()) {
                case R.id.btn1:
                    handle(btn1);
                    break;
                case R.id.btn2:
                    handle(btn2);
                    break;
                case R.id.btn3:
                    handle(btn3);
                    break;
                case R.id.btn4:
                    handle(btn4);
                    break;
                case R.id.btn5:
                    handle(btn5);
                    break;
                case R.id.btn6:
                    handle(btn6);
                    break;
                case R.id.btn7:
                    handle(btn7);
                    break;
                case R.id.btn8:
                    handle(btn8);
                    break;
                case R.id.btn9:
                    handle(btn9);
                    break;
            }
        }



        /**
         * <h1>handle</h1><p>
         * <i><ul>void handle(Button btn)<i><p>
         * Handle the actions after clicking on game board
         */
        private void handle(Button btn){

            if(btn.getText().equals("")) {
                if (player1.isTurn()) {
                    if(sound)
                        playTurnSound(player1);
                    btn.setText(player1.getCh());
                    numOfMoves++;
                    if(getWinner(player1.getCh())){
                        Toast.makeText(GameActivity.this, player1.getName()+ " Win the game!!! ", Toast.LENGTH_SHORT).show();
                        player1.setScore(player1.getScore()+1);
                        if(sound)
                             playWinnerSound();
                        playerScoreOneTv.setText(player1.getName()+" : "+player1.getScore());
                        numOfMoves = 0;
                        disableButtonsWhenWin();
                        playerTurnTv.setText(player1.getName()+" win!!!");
                        playerTurnTv.setTextColor(Color.rgb(0,155,0));

                    }
                    else{
                        playerTurnTv.setText(player2.getName()+" turn");
                        playerNameTwoTv.setTextColor(Color.BLUE);
                        playerNameOneTv.setTextColor(Color.WHITE);
                        player1.setTurn(false);
                        player2.setTurn(true);

                    }
                }else if(player2.isTurn()){
                    if(sound)
                        playTurnSound(player2);
                    btn.setText(player2.getCh());

                    numOfMoves++;
                    if(getWinner(player2.getCh())) {
                        Toast.makeText(GameActivity.this, player2.getName() + " Win the game!!! ", Toast.LENGTH_SHORT).show();
                        player2.setScore(player2.getScore()+1);
                        playerScoreTwoTv.setText(player2.getName()+" : "+player2.getScore());
                        numOfMoves = 0;
                        disableButtonsWhenWin();
                        if(sound)
                            playWinnerSound();
                        player2.setTurn(false);
                        player1.setTurn(true);
                        playerTurnTv.setText(player2.getName()+" win");
                        playerTurnTv.setTextColor(Color.rgb(0,155,0));
                    }else{
                        playerTurnTv.setText(player1.getName()+" turn");
                        playerNameTwoTv.setTextColor(Color.WHITE);
                        playerNameOneTv.setTextColor(Color.BLUE);
                        player2.setTurn(false);
                        player1.setTurn(true);
                    }
                }

            }else{
                playWrongChoiceSound();
                if(player1.isTurn())
                       Toast.makeText(GameActivity.this,player1.getName()+": Please choose empty square",Toast.LENGTH_SHORT).show();
                if(player2.isTurn())
                    Toast.makeText(GameActivity.this,player2.getName()+": Please choose empty square",Toast.LENGTH_SHORT).show();
            }

            if(numOfMoves==9){
                disableButtonsWhenWin();
                Toast.makeText(GameActivity.this,"It's a tie!!!",Toast.LENGTH_SHORT).show();
                playerTurnTv.setText("Draw!!!");
                playerTurnTv.setTextColor(Color.RED);
                disableButtonsWhenWin();
                player1.setTurn(true);
                player2.setTurn(false);
                numOfMoves = 0;
                playDrawSound();
                return;
            }

        }

        /**
         * <h1>getWinner</h1><p>
         * <i><ul>boolean getWinner(String ch)<i><p>
         * Checks if there is a winner
         * @return boolean - true if there is a winner, else false
         */
        private boolean getWinner(String ch) {
            if (numOfMoves <5)
                return false;

            String val1,val2,val3,val4,val5,val6,val7,val8,val9;

            val1 = btn1.getText().toString();
            val2 = btn2.getText().toString();
            val3 = btn3.getText().toString();
            val4 = btn4.getText().toString();
            val5 = btn5.getText().toString();
            val6 = btn6.getText().toString();
            val7 = btn7.getText().toString();
            val8 = btn8.getText().toString();
            val9 = btn9.getText().toString();

            if(val1.equals(val2) && val1.equals(val3) && val1.equals(ch)){
                btn1.setTextColor(Color.BLUE);
                btn2.setTextColor(Color.BLUE);
                btn3.setTextColor(Color.BLUE);
                return true;

            }
            if (val1.equals(val4) && val1.equals(val7) && val1.equals(ch)) {
                btn1.setTextColor(Color.BLUE);
                btn4.setTextColor(Color.BLUE);
                btn7.setTextColor(Color.BLUE);
                return true;
            }

            if (val1.equals(val5) && val1.equals(val9) && val1.equals(ch)){
                 btn1.setTextColor(Color.BLUE);
                 btn5.setTextColor(Color.BLUE);
                 btn9.setTextColor(Color.BLUE);
                return true;
             }
            if (val2.equals(val5) && val2.equals(val8) && val2.equals(ch)){
                btn2.setTextColor(Color.BLUE);
                btn5.setTextColor(Color.BLUE);
                btn8.setTextColor(Color.BLUE);
                return true;

            }
            if (val3.equals(val6) && val3.equals(val9) && val3.equals(ch)){
                btn3.setTextColor(Color.BLUE);
                btn6.setTextColor(Color.BLUE);
                btn9.setTextColor(Color.BLUE);
                return true;

            }
            if (val3.equals(val5) && val3.equals(val7) && val3.equals(ch)){
                btn3.setTextColor(Color.BLUE);
                btn5.setTextColor(Color.BLUE);
                btn7.setTextColor(Color.BLUE);
                return true;

            }
            if (val4.equals(val5) && val5.equals(val6) && val4.equals(ch)){
                btn4.setTextColor(Color.BLUE);
                btn5.setTextColor(Color.BLUE);
                btn6.setTextColor(Color.BLUE);
                return true;
            }
            if (val7.equals(val8) && val7.equals(val9) && val9.equals(ch)){
                btn7.setTextColor(Color.BLUE);
                btn8.setTextColor(Color.BLUE);
                btn9.setTextColor(Color.BLUE);
                return true;
            }

            return false;
        }


        /**
         * <h1>disableButtonsWhenWin</h1><p>
         * <i><ul>void disableButtonsWhenWin()<i><p>
         * Disable the buttons when player win the game
         */
        private void disableButtonsWhenWin(){
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
            btn5.setEnabled(false);
            btn6.setEnabled(false);
            btn7.setEnabled(false);
            btn8.setEnabled(false);
            btn9.setEnabled(false);
            moreRoundBtn.setEnabled(true);
        }
    }

    /**
     * <h1>disableButtonsWhenWin</h1><p>
     * <i><ul>void disableButtonsWhenWin()<i><p>
     * Enable the buttons when press on rew round button
     */
    private void enableButtonsWhenNewRound(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        btn1.setTextColor(Color.BLACK);
        btn2.setTextColor(Color.BLACK);
        btn3.setTextColor(Color.BLACK);
        btn4.setTextColor(Color.BLACK);
        btn5.setTextColor(Color.BLACK);
        btn6.setTextColor(Color.BLACK);
        btn7.setTextColor(Color.BLACK);
        btn8.setTextColor(Color.BLACK);
        btn9.setTextColor(Color.BLACK);
        moreRoundBtn.setEnabled(false);
        playerTurnTv.setTextColor(Color.BLACK);

        if(player1.isTurn()) {
            playerTurnTv.setText(player1.getName() + " turn");
            playerNameOneTv.setTextColor(Color.BLUE);
            playerNameTwoTv.setTextColor(Color.WHITE);

        }

        if(player2.isTurn()) {
            playerTurnTv.setText(player1.getName() + " turn");
            playerNameTwoTv.setTextColor(Color.BLUE);
            playerNameOneTv.setTextColor(Color.WHITE);
        }
    }

    /**
     * <h1>setButtons</h1><p>
     * <i><ul>void setButtons()<i><p>
     * Set all the button
     */
    private void setButtons(){
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwritingBold.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwriting.ttf");
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setTypeface(typeface);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setTypeface(typeface);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setTypeface(typeface);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setTypeface(typeface);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setTypeface(typeface);
        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setTypeface(typeface);
        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setTypeface(typeface);
        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setTypeface(typeface);
        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setTypeface(typeface);
        moreRoundBtn = (Button) findViewById(R.id.moreRoundBtn);
        moreRoundBtn.setTypeface(typefaceBold);

    }

    /**
     * <h1>setPlayers</h1><p>
     * <i><ul>void setPlayers()<i><p>
     * Set the players - names and turns and character X/O
     */
    private void setPlayers(){
        Bundle bundle = getIntent().getExtras();
        String name1 = bundle.getString("name1");
        String name2 = bundle.getString("name2");
        player1.setName(name1);
        player1.setCh("X");
        player1.setTurn(true);
        player2.setName(name2);
        player2.setCh("O");
        player2.setTurn(false);

    }

    /**
     * <h1>setTextView</h1><p>
     * <i><ul>void setTextView()<i><p>
     * Set all the text views
     */
    private void setTextView(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwritingBold.ttf");
        TextView vs = (TextView) findViewById(R.id.vs);
        vs.setTypeface(typeface);
        playerTurnTv = (TextView) findViewById(R.id.playerTurnTv);
        playerTurnTv.setTypeface(typeface);
        playerScoreOneTv = (TextView) findViewById(R.id.player1ScoreTv);
        playerScoreOneTv.setTypeface(typeface);
        playerScoreTwoTv = (TextView) findViewById(R.id.player2ScoreTv);
        playerScoreTwoTv.setTypeface(typeface);
        playerNameOneTv = (TextView) findViewById(R.id.player_one_name);
        playerNameOneTv.setTypeface(typeface);
        playerNameTwoTv = (TextView) findViewById(R.id.player_two_name);
        playerNameTwoTv.setTypeface(typeface);
        setPlayers();
        playerNameOneTv.setText(player1.getName());
        playerNameTwoTv.setText(player2.getName());


    }

    /**
     * <h1>setSound</h1><p>
     * <i><ul>void setTextView()<i><p>
     * Get the value from the checkbox form the ({@link MainActivity}
     * and define if to play sound or not
     */
    private void setSound(){
        Bundle bundle = getIntent().getExtras();
        String sound = bundle.getString("sound");
        if(sound.equals("true"))
            this.sound = true;
        else if (sound.equals("false"))
            this.sound = false;


    }

    /**
     * <h1>setListeners</h1><p>
     * <i><ul>void setListeners()<i><p>
     * Set listener to all the button
     */
    private void setListeners(){
        btn1.setOnClickListener(onClick);
        btn2.setOnClickListener(onClick);
        btn3.setOnClickListener(onClick);
        btn4.setOnClickListener(onClick);
        btn5.setOnClickListener(onClick);
        btn6.setOnClickListener(onClick);
        btn7.setOnClickListener(onClick);
        btn8.setOnClickListener(onClick);
        btn9.setOnClickListener(onClick);

        moreRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               enableButtonsWhenNewRound();
            }
        });
    }



    /**
     * <h1>setWidgets</h1><p>
     * <i><ul>void setWidgets()<i><p>
     * Set all the widgets
     */
    private void setWidgets(){
        setButtons();
        setTextView();
        setListeners();
        moreRoundBtn.setEnabled(false);
        playerScoreOneTv.setText(player1.getName()+" : "+player1.getScore());
        playerScoreTwoTv.setText(player2.getName()+" : "+player2.getScore());
        if(player1.isTurn()) {
            playerNameOneTv.setTextColor(Color.BLUE);
            playerTurnTv.setText(player1.getName() + " turn");
            Toast.makeText(GameActivity.this,player1.getName()+" start to play",Toast.LENGTH_SHORT).show();

        }

        else if(player2.isTurn()) {
            playerNameOneTv.setTextColor(Color.WHITE);
            playerTurnTv.setText(player2.getName() + " turn");
            Toast.makeText(GameActivity.this,player2.getName()+" start to play",Toast.LENGTH_SHORT).show();
        }
        player1.setScore(0);
        player2.setScore(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setSound();
        setWidgets();



    }
}
