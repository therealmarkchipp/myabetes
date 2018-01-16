package com.example.mark.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;

/**
 * Tool for diabetics to calculate insulin bolus dose for carbohydrates consumed, blood glucose
 * corrections, and tracking history of carbs, glucose and boluses.
 * Created by mark on 15/01/18.
 * Todo: Try/Catch input errors, include active insulin in bolus calculation
 */

public class BolusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolus);

        final EditText input_glucose = (EditText)findViewById(R.id.input_glucose);
        final EditText input_carbs = (EditText)findViewById(R.id.input_carbs);
        final EditText output_bolus = (EditText)findViewById(R.id.output_bolus);
        final Button button = (Button)findViewById(R.id.button_calculate);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Initialize variables
                float glucose           = 0;
                float carbs             = 0;
                float glucose_adjust    = 0;
                float carbs_adjust      = 0;
                float bolus             = 0;

                //Assign glucose value if input not empty
                if(!(input_glucose.getText().toString().isEmpty()))
                    glucose = Float.valueOf(input_glucose.getText().toString());
                else
                    glucose = 0;

                //Assign carbohydrate value if input not empty
                if(!(input_carbs.getText().toString().isEmpty()))
                    carbs = Float.valueOf(input_carbs.getText().toString());
                else
                    carbs = 0;

                //If glucose is high (above 7) calculation correction dose
                if(glucose > 7)
                    glucose_adjust = (glucose - 7) / 2;
                else
                    glucose_adjust = 0;

                //Calculate dose for carbohydrate intake
                carbs_adjust = carbs / 8;

                //Bolus is always correction + carbohydrate
                bolus = glucose_adjust + carbs_adjust;

                //Return the output for user
                output_bolus.setText(String.valueOf(bolus));

            }
        });
    }
}
