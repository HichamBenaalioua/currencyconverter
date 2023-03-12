package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    Spinner spinner;
    EditText editText;
    float MADTOEURO = 0.091f;
    float EUROTOMAD =11.01f;
    float MADTODOLLAR= 0.097f;
    float DOLLARTOMAD=10.34f;
    float result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(Float.parseFloat(String.valueOf(editText.getText())) < 0){
                        throw new Exception();
                    }
                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        result = Float.parseFloat(String.valueOf(editText.getText())) * MADTOEURO;
                        textView.setText(editText.getText().toString() + " MAD = " + new DecimalFormat("##.##").format(result)+" EURO");
                        break;
                    case 1:
                        result = Float.parseFloat(String.valueOf(editText.getText())) * EUROTOMAD;
                        textView.setText(editText.getText().toString() + " EURO = " + new DecimalFormat("##.##").format(result)+" MAD");
                        break;
                    case 2:
                        result = Float.parseFloat(String.valueOf(editText.getText())) * MADTODOLLAR;
                        textView.setText(editText.getText().toString() + " MAD = " + new DecimalFormat("##.##").format(result)+" DOLLAR");
                        break;
                    case 3:
                        result = Float.parseFloat(String.valueOf(editText.getText())) * DOLLARTOMAD;
                        textView.setText(editText.getText().toString() + " DOLLAR = " + new DecimalFormat("##.##").format(result)+" MAD");
                        break;
                }
            }catch (Exception ex){
                    textView.setText("Entrer des valeurs valides");
                }
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}