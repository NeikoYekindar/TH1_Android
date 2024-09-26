package com.example.appglock;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import kotlin.sequences.Sequence;


public class TimerFragment extends Fragment {


    Button btn_30p, btn_15p, btn_10p;
    EditText gio, phut, giay;
    Button btn_start_timer;
    int h,m,s;
    ImageView up_h,down_h, up_m, down_m, up_s, down_s;

    private Button currentlyOnButton = null;// Button tạm để kiểm tra
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_30p = view.findViewById(R.id.btn_30_p);
        btn_15p = view.findViewById(R.id.btn_15_p);
        btn_10p = view.findViewById(R.id.btn_10_p);
        gio = view.findViewById(R.id.hour);
        phut = view.findViewById(R.id.minute);
        giay = view.findViewById(R.id.second);
        btn_start_timer = view.findViewById(R.id.btn_start_timer);
        up_h = view.findViewById(R.id.arrow_up_hour_timer);
        down_h = view.findViewById(R.id.arrow_down_hour_timer);
        up_m = view.findViewById(R.id.arrow_up_minute_timer);
        down_m  = view.findViewById(R.id.arrow_down_minute_timer);
        up_s = view.findViewById(R.id.arrow_up_second_timer);
        down_s = view.findViewById(R.id.arrow_down_second_timer);
        setButtonOff(btn_30p);
        setButtonOff(btn_15p);
        setButtonOff(btn_10p);
        btn_30p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonState(btn_30p);
                gio.setText("00");
                phut.setText("30");
                giay.setText("00");

            }
        });

        btn_15p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonState(btn_15p);
                gio.setText("00");
                phut.setText("15");
                giay.setText("00");
            }
        });

        btn_10p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonState(btn_10p);
                gio.setText("00");
                phut.setText("10");
                giay.setText("00");
            }
        });

        gio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    int input = Integer.parseInt(charSequence.toString());

                    if (input > 99) {

                        gio.setText("99");
                        gio.setSelection(gio.getText().length());
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    int input = Integer.parseInt(charSequence.toString());

                    if (input > 60) {

                        phut.setText("60");
                        phut.setSelection(phut.getText().length());
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        giay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    int input = Integer.parseInt(charSequence.toString());

                    if (input > 60) {

                        giay.setText("60");
                        giay.setSelection(giay.getText().length());
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_start_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h  = Integer.parseInt(gio.getText().toString());
                m = Integer.parseInt(phut.getText().toString());
                s = Integer.parseInt(giay.getText().toString());
                Intent intent = new Intent(getActivity(), timerActivity.class);
                intent.putExtra("gio", h);
                intent.putExtra("phut", m);
                intent.putExtra("giay",s);
                startActivity(intent);

            }
        });
        up_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = gio.getText().toString();
                int hour = Integer.parseInt(a);
                hour++;
                if(hour>99){
                    hour = 0;
                    gio.setText("00");
                }
                if(hour<10){
                    gio.setText("0"+hour);
                }else {
                    gio.setText(""+hour);
                }
            }
        });
        down_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = gio.getText().toString();
                int hour = Integer.parseInt(a);
                hour--;
                if(hour<0){
                    hour = 99;
                    gio.setText(""+hour);
                }
                if(hour<10){
                    gio.setText("0"+hour);
                }else {
                    gio.setText(""+hour);
                }
            }
        });
        up_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = phut.getText().toString();
                int minute = Integer.parseInt(a);
                minute++;
                if(minute>60){
                    minute = 0;
                    phut.setText("00");
                }
                if(minute<10){
                    phut.setText("0"+minute);
                }else {
                    phut.setText(""+minute);
                }
            }
        });
        down_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = phut.getText().toString();
                int minute = Integer.parseInt(a);
                minute--;
                if(minute<0){
                    minute = 60;
                    phut.setText("60");
                }
                if(minute<10){
                    phut.setText("0"+minute);
                }else {
                    phut.setText(""+minute);
                }
            }
        });
        up_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = giay.getText().toString();
                int second = Integer.parseInt(a);
                second++;
                if(second>60){
                    second = 0;
                    giay.setText("00");
                }
                if(second<10){
                    giay.setText("0"+second);
                }else {
                    giay.setText(""+second);
                }
            }
        });
        down_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = giay.getText().toString();
                int second = Integer.parseInt(a);
                second--;
                if(second<0){
                    second = 60;
                    giay.setText("60");
                }
                if(second<10){
                    giay.setText("0"+second);
                }else {
                    giay.setText(""+second);
                }
            }
        });


    }
    private void toggleButtonState(Button clickedButton) {

        if (currentlyOnButton != null && currentlyOnButton != clickedButton) {
            setButtonOff(currentlyOnButton);
        }


        if (currentlyOnButton == clickedButton) {
            setButtonOff(clickedButton);
            currentlyOnButton = null;
        } else {
            setButtonOn(clickedButton);
            currentlyOnButton = clickedButton;
        }
    }
    private void setButtonOn(Button button) {
        button.setTypeface(null, Typeface.BOLD);
        button.setBackground(getResources().getDrawable(R.drawable.btn_timer_on));
    }

    // Set the button to "OFF" state with custom style
    private void setButtonOff(Button button) {
        button.setTypeface(null, Typeface.NORMAL);
        button.setBackground(getResources().getDrawable(R.drawable.btn_timer_ex));
    }
}