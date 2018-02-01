package com.example.bwise.tempconverter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;


import java.text.NumberFormat;

import static java.lang.Float.*;

public class MainActivity extends Activity
        implements TextView.OnEditorActionListener {
    // define widget variable
    private EditText inputeditText;
    private TextView outputtextView;

    //define instance variables - not saved
    public String temperatureValueString = "";
    public float celsiusTemp;

    // define the SharedPreferences object
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to widgets
        inputeditText = findViewById(R.id.inputeditText);
        outputtextView = findViewById(R.id.outputtextView);

        //listener
        inputeditText.setOnEditorActionListener(this);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause (){
        super.onPause();  // Always call the superclass method first
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("temperatureValue", temperatureValueString );
        editor.commit();
    }

    @Override
    public void onResume (){
        temperatureValueString = savedValues.getString("temperatureValue", "");
        inputeditText.setText(temperatureValueString);
        calculateAndDisplay();
        super.onResume();  // Always call the superclass method first
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }//end onEditorAction

    private void calculateAndDisplay() {
        //c=(F-32)*5/9
        //take user entry convert/parse to float
       temperatureValueString = inputeditText.getText().toString();
       float temperatureValue;

        if (temperatureValueString.equals("")){
           temperatureValue = 0;
        }
        else {
           temperatureValue = parseFloat(temperatureValueString);
           celsiusTemp = (temperatureValue - 32) * 5/9;
        }


        //display
        NumberFormat display = NumberFormat.getInstance();
        outputtextView.setText(display.format(celsiusTemp));

    } //end calculateAndDisplay method 
}//end main activity
