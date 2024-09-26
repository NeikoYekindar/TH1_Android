package com.example.appglock;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddAlarmAcitvity extends AppCompatActivity {

    TextView text1, addalarm;
    ImageView arrow_up_hour, arrow_down_hour, arrow_up_minute, arrow_down_minute;
    EditText alarm_hour, alarm_minute;
    int hour = 0;
    int minute = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_alarm_acitvity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        text1 = findViewById(R.id.close_add_alarm);
        addalarm = findViewById(R.id.add_alarm);
        arrow_up_hour = findViewById(R.id.arrow_up_hour);
        arrow_down_hour = findViewById(R.id.arrow_down_hour);
        alarm_hour = findViewById(R.id.alarm_hour);
        arrow_up_minute = findViewById(R.id.arrow_up_minute);
        arrow_down_minute = findViewById(R.id.arrow_down_minute);
        alarm_minute = findViewById(R.id.alarm_minute);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(AddAlarmAcitvity.this, MainActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    // Cờ này đảm bảo rằng MainActivity không được khởi chạy lại nếu đã tồn tại
                finish();
            }
        });
        addalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddAlarmAcitvity.this, MainActivity.class);
                intent.putExtra("gio", hour);
                intent.putExtra("phut", minute);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                setResult(RESULT_OK, intent);
                // Cờ này đảm bảo rằng MainActivity không được khởi chạy lại nếu đã tồn tại
                finish();
            }
        });
        alarm_hour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    int input = Integer.parseInt(charSequence.toString());

                    if (input > 23) {

                        alarm_hour.setText("23");
                        alarm_hour.setSelection(alarm_hour.getText().length()); // Move cursor to the end
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        arrow_up_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = alarm_hour.getText().toString();
                hour = Integer.parseInt(a);
                hour++;
                if(hour>23){
                   hour = 0;
                   alarm_hour.setText("00");
                }
                if(hour<10){
                    alarm_hour.setText("0"+hour);
                }else {
                    alarm_hour.setText(""+hour);
                }

            }
        });
        arrow_down_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = alarm_hour.getText().toString();
                hour = Integer.parseInt(a);
                hour--;
                if(hour<0){
                    hour = 23;
                    alarm_hour.setText(""+hour);
                }
                if(hour<10){
                    alarm_hour.setText("0"+hour);
                }else {
                    alarm_hour.setText(""+hour);
                }
            }
        });


        alarm_minute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    int input = Integer.parseInt(charSequence.toString());

                    if (input > 60) {

                        alarm_minute.setText("60");
                        alarm_minute.setSelection(alarm_minute.getText().length()); // Move cursor to the end
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        arrow_up_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = alarm_minute.getText().toString();
                minute = Integer.parseInt(a);
                minute++;
                if(minute>60){
                    minute = 0;
                    alarm_minute.setText("00");
                }
                if(minute<10){
                    alarm_minute.setText("0"+minute);
                }else {
                    alarm_minute.setText(""+minute);
                }
            }
        });
        arrow_down_minute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = alarm_minute.getText().toString();
                minute = Integer.parseInt(a);
                minute--;
                if(minute<0){
                    minute = 60;
                    alarm_minute.setText("60");
                }
                if(minute<10){
                    alarm_minute.setText("0"+minute);
                }else {
                    alarm_minute.setText(""+minute);
                }
            }
        });





    }

}