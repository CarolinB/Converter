package de.catwalk.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    Button usd2eurButton;
    Button eur2usdButton;
    Button inch2cmButton;
    Button cm2inchButton;

    EditText input;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usd2eurButton = findViewById(R.id.usd2eurButton);
        eur2usdButton = findViewById(R.id.eur2usdButton);
        inch2cmButton = findViewById(R.id.inch2cmButton);
        cm2inchButton = findViewById(R.id.cm2inchButton);

        input = findViewById(R.id.input);
        result = findViewById(R.id.result);

    }

    public void convert(View view) {

        String inputStr = input.getText().toString();
        Double inputValue;

        try {

            inputValue = Double.parseDouble(inputStr);

        } catch(NumberFormatException e) {
            Toast warning = Toast.makeText(MainActivity.this, R.string.warningText, Toast.LENGTH_SHORT);
            warning.show();
            return;
        }

        switch (view.getId()) {
            case R.id.usd2eurButton:
                usd2eur(inputValue);
                break;
            case R.id.eur2usdButton:
                eur2usd(inputValue);
                break;
            case R.id.inch2cmButton:
                inch2cm(inputValue);
                break;
            case R.id.cm2inchButton:
                cm2inch(inputValue);
                break;
        }
    }

    protected void usd2eur(Double input) {
        showResult(input / 1.10, " €");
    }

    protected void eur2usd(Double input) {
        showResult(input * 1.10, " $");
    }

    protected void inch2cm(Double input) {
        showResult(input / 2.54, " cm");
    }

    protected void cm2inch(Double input) {
        showResult(input * 2.54, " inch");
    }

    protected void showResult(Double result, String targetUnit) {
        BigDecimal format = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        String resultStr = format + targetUnit;
        this.result.setVisibility(View.VISIBLE);
        this.result.setText(resultStr);
    }

    public void clear(View view) {
        input.getText().clear();
        this.result.setVisibility(View.INVISIBLE);
        this.result.setText("");
    }

}
