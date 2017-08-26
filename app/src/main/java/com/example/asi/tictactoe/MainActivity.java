package com.example.asi.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;


/**
 * <h1>class MainActivity</h1><p>
 * This class defines start screen of the game</br>
 * @author Asi Belachow
 * @version 1.0
 * @since 04-09-2017
 */

public class MainActivity extends Activity {



    /**Button fot start game **/
    Button startGameBtn;
    /**Button fot instructions **/
    Button instructionsBtn;

    /**EditText for player 1 name **/
    EditText player1;
    /**EditText for player 2 name **/
    EditText player2;


    /**EditText for player 2 name **/
    TextView playerOneName,playerTwoName,headLine;

    /**CheckBox for play sound **/
    CheckBox playSound;


    /**
     * <h1>setCheckBox</h1><p>
     * <i><ul>void setCheckBox()<i><p>
     * Set the CheckBox
     */
    private void setCheckBox(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwritingBold.ttf");
        playSound = (CheckBox) findViewById(R.id.playSound);
        playSound.setTypeface(typeface);

    }

    /**
     * <h1>setTextView</h1><p>
     * <i><ul>void setTextView()<i><p>
     * Set the TextViews
     */
    private void setTextView(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwritingBold.ttf");
        playerOneName = (TextView) findViewById(R.id.playerOneName);
        playerOneName.setTypeface(typeface);
        playerTwoName = (TextView) findViewById(R.id.playerTwoName);
        playerTwoName.setTypeface(typeface);
        headLine = (TextView) findViewById(R.id.headLine);
        headLine .setTypeface(typeface);

    }

    /**
     * <h1>setButtons</h1><p>
     * <i><ul>void setButtons()<i><p>
     * Set all the buttons
     */
    private void setButtons(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwritingBold.ttf");
        startGameBtn = (Button) findViewById(R.id.startGameBtn);
        startGameBtn.setTypeface(typeface);
        instructionsBtn  = (Button) findViewById(R.id.instructionsBtn);
        instructionsBtn.setTypeface(typeface);
    }

    /**
     * <h1>setEditText</h1><p>
     * <i><ul>void setEditText()<i><p>
     * Set all the edit text
     */
    private void setEditText(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwritingBold.ttf");
        player1 = (EditText) findViewById(R.id.playerOneNameEt);
        player1.setTypeface(typeface);
        player2 = (EditText) findViewById(R.id.playerTwoNameEt);
        player2.setTypeface(typeface);
    }


    /**
     * <h1>isEditTextEmpty</h1><p>
     * <i><ul>boolean isEditTextEmpty(EditText eText)<i><p>
     * Checks if given edit text empty
     * @param eText - The given edit text
     */
    private boolean isEditTextEmpty(EditText eText) {
        if (eText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }


    /**
     * <h1>setListeners</h1><p>
     * <i><ul>void setListeners()<i><p>
     * Set all the listeners to the widgets
     */
    private void setListeners(){

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !isEditTextEmpty(player1) && !isEditTextEmpty(player2)){
                    String name1 = player1.getText().toString();
                    String name2 = player2.getText().toString();
                    String sound = null;
                    if(playSound.isChecked())
                        sound = "true";
                    else
                        sound="false";
                    forwardData(name1,name2,sound);
                }
                else
                    Toast.makeText(MainActivity.this,"Please fill in all players names",Toast.LENGTH_SHORT).show();

            }
        });
        instructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InstructionsActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * <h1>forwardData</h1><p>
     * <i><ul>void forwardData(String name1,String name2,String sound)<i><p>
     * Forward the players names to the game activity and start the game activity
     * @param name1 - player 1 name
     * @param name2 - player 2 name
     * @param sound - value of playSound checkbox
     */
    private void forwardData(String name1,String name2,String sound){
        Bundle bundle = new Bundle();
        bundle.putString("name1",name1);
        bundle.putString("name2",name2);
        bundle.putString("sound",sound);
        Intent intent = new Intent(MainActivity.this,GameActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtons();
        setEditText();
        setTextView();
        setCheckBox();
        setListeners();
    }
}
