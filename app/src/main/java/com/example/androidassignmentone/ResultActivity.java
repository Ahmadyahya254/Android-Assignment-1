package com.example.androidassignmentone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private Button backButton;
    private TextView resultValue;
    private TextView predictionMessage;
    private ListView bmiDetailsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backButton = findViewById(R.id.backButton);
        resultValue = findViewById(R.id.BMIResult);
        predictionMessage = findViewById(R.id.predictionMessage);
        bmiDetailsListView = findViewById(R.id.bmiDetailsListView);

        double bmiValue = getIntent().getDoubleExtra("BMI_VALUE", 0);
        ArrayList<String> predictions = getIntent().getStringArrayListExtra("PREDICTIONS");

        String bmiCategory;
        if (bmiValue < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmiValue >= 18.5 && bmiValue < 24.9) {
            bmiCategory = "Normal weight";
        } else if (bmiValue >= 25 && bmiValue < 29.9) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obesity";
        }

        resultValue.setText("BMI = " + String.format("%.1f", bmiValue) + " (" + bmiCategory + ")");

        if (predictions != null && predictions.contains(bmiCategory)) {
            predictionMessage.setText("Your prediction was correct!");
            predictionMessage.setTextColor(getResources().getColor(R.color.teal_700));
        } else {
            predictionMessage.setText("Your prediction was not correct.");
            predictionMessage.setTextColor(getResources().getColor(R.color.black));
        }

        ArrayList<String> bmiDetails = new ArrayList<>();
        bmiDetails.add("Underweight: BMI < 18.5 - You are below the normal weight range.");
        bmiDetails.add("Normal weight: BMI 18.5 - 24.9 - You are in a healthy weight range.");
        bmiDetails.add("Overweight: BMI 25.0 - 29.9 - You are above the normal weight range.");
        bmiDetails.add("Obesity: BMI >= 30 - You are significantly above the normal weight range.");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bmiDetails);
        bmiDetailsListView.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
