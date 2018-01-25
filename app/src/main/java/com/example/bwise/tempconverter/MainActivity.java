package com.example.bwise.tempconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity
        implements TextView.OnEditorActionListener {
    // define widget variable
    private EditText inputeditText;
    private TextView outputtextView;

    //define instance variables - not saved
    private String temperatureValueString = "";
   //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to widgets
        inputeditText = findViewById(R.id.inputeditText);
        outputtextView = findViewById(R.id.outputtextView);

        //listener
        inputeditText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }//end onEditorAction

    public void calculateAndDisplay() {
        //c=(F-32)*5/9
        //take user entry convert/parse to float
       temperatureValueString = inputeditText.getText().toString();
       float temperatureValue;
      // float celsiusTemp;


       if (temperatureValueString.equals("")){
           temperatureValue = 0;
       }
       else {
           temperatureValue = Float.parseFloat(temperatureValueString);
       }
       //calculate temperature
        float celsiusTemp = (temperatureValue - 32) * (5/9);
        //display
        //NumberFormat numberFormat = NumberFormat.getNumberInstance();
        outputtextView.setText(String.valueOf(celsiusTemp));

    } //end calculateAndDisplay method 
}//end main activity
