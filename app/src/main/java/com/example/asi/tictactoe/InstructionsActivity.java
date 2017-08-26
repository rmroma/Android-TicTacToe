package com.example.asi.tictactoe;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


/**
 * <h1>InstructionsActivity</h1><p>
 * This class defines screen of the instructions</br>
 * @author Asi Belachow
 * @version 1.0
 * @since 04-09-2017
 */
public class InstructionsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/AxureHandwriting.ttf");
        TextView tv1 = (TextView) findViewById(R.id.instructionsText);
        TextView tv2 = (TextView) findViewById(R.id.instructionsBottomText);
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
    }
}
