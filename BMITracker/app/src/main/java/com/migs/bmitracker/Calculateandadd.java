package com.migs.bmitracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Calculateandadd extends AppCompatActivity {
    EditText weight, height, note; // the edittext varibles
    Button calculate,clear, save, history;
    Switch switch1;
    TextView resulted, txtHeight,  txtWeight; // the textview variable used to display the result
    String calculation, BMIresult;// string variables used to format the results
    public static final String EXTRA_MESSAGE =
            "com.migs.mybmi.extra.MESSAGE";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";
    private View layout;

    private EditText mEditWordView,mEditWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculateandadd);
        weight= findViewById(R.id.weight);
        height = findViewById(R.id.height);
        txtHeight= findViewById(R.id.txtheight);
        txtWeight = findViewById(R.id.txtweight);

        resulted = findViewById(R.id.result);
        switch1= findViewById(R.id.switch1);
        layout= (View) findViewById(R.id.hscrene);
        calculate = findViewById(R.id.Calculate_button);
        clear = findViewById(R.id.clear);
        save = findViewById(R.id.save);
        note = findViewById(R.id.note);
    }

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
    { try {
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();
        // String S2 = height.getText().toString(); //declare string variables
        float wightvalue, heightvalue; // declare float variables used in the arithmetic calculations
        wightvalue = Float.parseFloat(S1);
        heightvalue = Float.parseFloat(S2) / 100;
        float bmi = wightvalue / (heightvalue * heightvalue); // the BMI formula used to calculate the BMi

        if (bmi < 16) {
            BMIresult = "Severely Underweight";
            layout.setBackgroundResource(R.drawable.underweight);
        } else if (bmi < 18.5) {
            BMIresult = "Underweight";
            layout.setBackgroundResource(R.drawable.underweight);

        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal weight";
            layout.setBackgroundResource(R.drawable.normalweight);
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "Overweight";
            layout.setBackgroundResource(R.drawable.overweight);
        } else if (bmi >= 30 && bmi <= 34.9) {
            BMIresult = "CLass 1 Obese";
            layout.setBackgroundResource(R.drawable.obese1);
        } else if (bmi >= 35 && bmi <= 39.9) {
            BMIresult = "Class 2 Obese";
            layout.setBackgroundResource(R.drawable.obese2);
        } else {
            BMIresult = "Class 3 Obese";
            layout.setBackgroundResource(R.drawable.obese3);
        }
        bmi = Math.round(bmi * 100.0f) / 100.0f;// round off the BMI value to 2 decimal places
        calculation = "BMI: " + bmi + "\n" + BMIresult;
        resulted.setText(calculation);
    }
    catch (NumberFormatException e)
    {
        showmsg();
    }
    }

    public void impcal()// the impcal method used to calculate the BMI in english units and display the results.
    {try {
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();
        // String S2 = height.getText().toString(); //declare string variables
        float wightvalue, heightvalue; // declare float variables used in the arithmetic calculations
        wightvalue = Float.parseFloat(S1);
        heightvalue = Float.parseFloat(S2);
        float bmi = wightvalue / (heightvalue * heightvalue) * 703; // the BMI formula used to calculate the BMi

        if (bmi < 16) {
            BMIresult = "Severely Underweight";
            layout.setBackgroundResource(R.drawable.underweight);
        } else if (bmi < 18.5) {
            BMIresult = "Underweight";
            layout.setBackgroundResource(R.drawable.underweight);

        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = "Normal weight";
            layout.setBackgroundResource(R.drawable.normalweight);
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = "Overweight";
            layout.setBackgroundResource(R.drawable.overweight);
        } else if (bmi >= 30 && bmi <= 34.9) {
            BMIresult = "CLass 1 Obese";
            layout.setBackgroundResource(R.drawable.obese1);
        } else if (bmi >= 35 && bmi <= 39.9) {
            BMIresult = "Class 2 Obese";
            layout.setBackgroundResource(R.drawable.obese2);
        } else {
            BMIresult = "Class 3 Obese";
            layout.setBackgroundResource(R.drawable.obese3);
        }

        bmi = Math.round(bmi * 100.0f) / 100.0f;// round off the BMI value to 2 decimal places
        calculation = "BMI: " + bmi + "\n" + BMIresult;
        resulted.setText(calculation);
    }
    catch (NumberFormatException e)
    {
        showmsg();
    }

    }
    // the clear method used to clear the edit texts
    public void Clear(View view) {
        resulted.setText("Result");
        weight.setText("");
        height.setText("");
        layout.setBackgroundResource(R.drawable.bmi);
    }

    public void Setdisp(View view) {
        if (switch1.isChecked())
        {
            txtWeight.setText(R.string.dispweight);
            txtHeight.setText(R.string.dispheight);
        }
        else {
            txtWeight.setText("Weight(kg)");
            txtHeight.setText("Height(cm)");

        }
    }
    void showmsg()
    {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();


    }

    void displaye()
    {
        Toast toast = Toast.makeText(this, "calculate your bmi first",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void save(View view) {

        if (weight.length() < 1 || height.length() < 1) {
            displaye();
        } else {


            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fd = df.format(c.getTime());
            String rnote = note.getText().toString();
            Intent replyIntent = new Intent();

            String word = fd + " \n" + calculation + "\n"+rnote  ;
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);

            finish();
        }

    }
}