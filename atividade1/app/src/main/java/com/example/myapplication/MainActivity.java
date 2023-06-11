package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;
    private Button sumButton;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.enterNumber1);
        et2 = (EditText) findViewById(R.id.enterNumber2);

        sumButton = (Button) findViewById(R.id.sumButton);

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v1 = Integer.parseInt(et1.getText().toString());
                int v2 = Integer.parseInt(et2.getText().toString());
                String out = "Result: " + Integer.toString(v1+v2);

                result = (TextView) findViewById(R.id.result);
                result.setText(out);
            }
        });
    }
}