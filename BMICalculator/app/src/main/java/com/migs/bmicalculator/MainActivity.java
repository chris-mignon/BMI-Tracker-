package com.migs.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // declare global variables used in computing the BMI
EditText weight, height; // the edittext varibles
    Switch switch1;
TextView resulted, txtHeight,  txtWeight; // the textview variable used to display the result
String calculation, BMIresult;// string variables used to format the results
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight= findViewById(R.id.weight);
        height = findViewById(R.id.height);
        txtHeight= findViewById(R.id.txtheight);
        txtWeight = findViewById(R.id.txtweight);

        resulted = findViewById(R.id.result);
        switch1= findViewById(R.id.switch1);
    }

    // the CalculateBMI method used select which unit system to calculate the BMI.
    @SuppressLint("SetTextI18n")
    public void CalculateBMI(View view) {

        if (switch1.isChecked())
        {
            impcal();
        }
        else {
            metcal();
        }
    }

    public void metcal()// the metcal method used to calculate the BMI in SI units and display the results.
    {
        String  S1 = weight.getText().toString();
        String S2 = height.getText().toString();
        // String S2 = height.getText().toString(); //declare string variables
        float wightvalue, heightvalue; // declare float variables used in the arithmetic calculations
        wightvalue = Float.parseFloat(S1);
        heightvalue = Float.parseFloat(S2) / 100;
        float bmi = wightvalue / (heightvalue * heightvalue); // the BMI formula used to calculate the BMi

        if (bmi < 16) {
            BMIresult = "Severely Underweight";
        } else if (bmi < 18.5) {
            BMIresult = "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "Overweight";
        } else {
            BMIresult = "Obese";
        }
        bmi = Math.round(bmi*100.0f)/100.0f;// round off the BMI value to 2 decimal places
        calculation ="BMI: \n" + bmi + "\n" + BMIresult;
        resulted.setText(calculation);
    }
    public void impcal()// the impcal method used to calculate the BMI in english units and display the results.
    {
        String  S1 = weight.getText().toString();
        String S2 = height.getText().toString();
        // String S2 = height.getText().toString(); //declare string variables
        float wightvalue, heightvalue; // declare float variables used in the arithmetic calculations
        wightvalue = Float.parseFloat(S1);
        heightvalue = Float.parseFloat(S2);
        float bmi = wightvalue / (heightvalue * heightvalue) *703; // the BMI formula used to calculate the BMi

        if (bmi < 16) {
            BMIresult = "Severely Underweight";
        } else if (bmi < 18.5) {
            BMIresult = "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal weight";
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "Overweight";
        } else {
            BMIresult = "Obese";
        }

        bmi = Math.round(bmi*100.0f)/100.0f;// round off the BMI value to 2 decimal places
        calculation ="BMI: " + bmi + "\n" + BMIresult;
        resulted.setText(calculation);
    }
// the clear method used to clear the edit texts
    public void Clear(View view) {
        resulted.setText("Result");
        weight.setText("");
        height.setText("");

    }

    public void Setdisp(View view) {
        if (switch1.isChecked())
        {
            txtWeight.setText("Weight(lbs)");
            txtHeight.setText("Height(in)");
        }
        else {
            txtWeight.setText("Weight(kg)");
            txtHeight.setText("Height(cm)");

        }
    }
}
