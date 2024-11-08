package com.example.androidassignmentone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public EditText heightED;
    public EditText weightED;
    public Spinner mySpinner;
    public TextView reselt;
    public TextView Error;
    public Button Done;
    public Button information;

    private RadioGroup genderGroup;
    private RadioButton radioMale, radioFemale;

    private CheckBox checkboxUnderweight, checkboxNormal, checkboxOverweight, checkboxObesity;

    ArrayAdapter<String> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightED = findViewById(R.id.h);
        weightED = findViewById(R.id.w);
        mySpinner = findViewById(R.id.spinner);
        Error = findViewById(R.id.ShowError);
        Done = findViewById(R.id.button);
        reselt = findViewById(R.id.resF);
        information = findViewById(R.id.next);

        genderGroup = findViewById(R.id.genderGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);

        checkboxUnderweight = findViewById(R.id.checkboxUnderweight);
        checkboxNormal = findViewById(R.id.checkboxNormal);
        checkboxOverweight = findViewById(R.id.checkboxOverweight);
        checkboxObesity = findViewById(R.id.checkboxObesity);

        final double[] height = {0};
        final double[] weight = {0};

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heightED.getText().toString().equals("") || weightED.getText().toString().equals("") ||
                        Double.parseDouble(heightED.getText().toString()) <= 0 || Double.parseDouble(weightED.getText().toString()) <= 0) {
                    Error.setVisibility(View.VISIBLE);
                    Error.setText("Height or weight is invalid input");
                } else {
                    height[0] = Double.parseDouble(heightED.getText().toString());
                    weight[0] = Double.parseDouble(weightED.getText().toString());

                    Person person = new Person();
                    double bmiValue = person.calculateBMI(height[0], weight[0]);

                    String gender = "";
                    int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                    if (selectedGenderId == R.id.radioMale) {
                        gender = "Male";
                    } else if (selectedGenderId == R.id.radioFemale) {
                        gender = "Female";
                    }

                    ArrayList<String> predictions = new ArrayList<>();
                    if (checkboxUnderweight.isChecked()) {
                        predictions.add("Underweight");
                    }
                    if (checkboxNormal.isChecked()) {
                        predictions.add("Normal weight");
                    }
                    if (checkboxOverweight.isChecked()) {
                        predictions.add("Overweight");
                    }
                    if (checkboxObesity.isChecked()) {
                        predictions.add("Obesity");
                    }

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("BMI_VALUE", bmiValue);
                    intent.putExtra("GENDER", gender);
                    intent.putStringArrayListExtra("PREDICTIONS", predictions);
                    startActivity(intent);
                }
            }
        });

        BMI bmi = new BMI();
        list = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, bmi.getRanges());
        mySpinner.setAdapter(list);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedIndex = position;

                if (selectedIndex == 0) {
                    reselt.setText("Underweight");
                } else if (selectedIndex == 1) {
                    reselt.setText("Normal weight Good");
                } else if (selectedIndex == 2) {
                    reselt.setText("Overweight");
                } else if (selectedIndex == 3) {
                    reselt.setText("Obesity");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                reselt.setVisibility(View.VISIBLE);
            }
        });


        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, secActivity.class);
                startActivity(intent1);
            }
        });
    }
}
