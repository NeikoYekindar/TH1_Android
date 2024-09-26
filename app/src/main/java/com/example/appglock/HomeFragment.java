package com.example.appglock;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment {


    LinearLayout a,b,c;
    //Handler handler;
    Runnable runnable;
    float gxGiay = 0f;
    float gxPhut = 0f;
    float gxGio = 0f;
    float lastSecondAngle = 0;
    ValueAnimator secondAnimator;
    ValueAnimator minuteAnimator;
    ValueAnimator hourAnimator;
    float giaytam  = 0f;
    TextView second_time_home, digital_clock;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateTimeRunnable;
    TextView date_month;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        a = view.findViewById(R.id.giay);
        b = view.findViewById(R.id.phut);
        c = view.findViewById(R.id.gio);
        date_month = view.findViewById(R.id.date_month);
        Calendar calendar  = Calendar.getInstance();
        int giay = calendar.get(Calendar.SECOND);
        int phut = calendar.get(Calendar.MINUTE);
        int gio = calendar.get(Calendar.HOUR);


        digital_clock = view.findViewById(R.id.digital_clock);

        //kim giây
        float newGxGiay = giay * 6;
        secondAnimator = ValueAnimator.ofFloat(newGxGiay, newGxGiay + 360f);
        secondAnimator.setDuration(60000);
        secondAnimator.setRepeatCount(ValueAnimator.INFINITE);
        secondAnimator.setInterpolator(new LinearInterpolator());
        secondAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            a.setRotation(animatedValue);
        });
        secondAnimator.start();
        //kim phút
        float newGxPhut = ((phut + giay / 60f) * 6f);
        minuteAnimator  = ValueAnimator.ofFloat(newGxPhut,newGxPhut+360f);
        minuteAnimator.setDuration(3600000);
        minuteAnimator.setRepeatCount(ValueAnimator.INFINITE);
        minuteAnimator.setInterpolator(new LinearInterpolator());
        minuteAnimator.addUpdateListener(animation ->{
            float animationValue = (float) animation.getAnimatedValue();
            b.setRotation(animationValue);
        });
        minuteAnimator.start();
        //kim giờ
        float newGxGio = ((gio + phut / 60f) * 30f);
        hourAnimator  = ValueAnimator.ofFloat(newGxGio,newGxGio+360f);
        hourAnimator.setDuration(43200000);
        hourAnimator.setRepeatCount(ValueAnimator.INFINITE);
        hourAnimator.setInterpolator(new LinearInterpolator());
        hourAnimator.addUpdateListener(animation ->{
            float animationValue = (float) animation.getAnimatedValue();
            c.setRotation(animationValue);
        });
        hourAnimator.start();




        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                Calendar calendar1 = Calendar.getInstance();
                int giay2=calendar1.get(Calendar.SECOND);

                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String time = sdf.format(now);
                digital_clock.setText(time);
                SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE dd/MM/yyyy",Locale.getDefault());
                String dm = sdf2.format(now);
                date_month.setText(dm);


                handler.postDelayed(this, 1000); // Lặp lại sau mỗi 1 giây
            }
        };
        handler.post(updateTimeRunnable); // Bắt đầu quá trình cập nhật thời gian



    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        if (secondAnimator != null) {
            secondAnimator.cancel();
        }
        if (minuteAnimator != null) {
            minuteAnimator.cancel();
        }
        if (hourAnimator != null) {
            hourAnimator.cancel();
        }

        // Hủy Runnable khi Fragment bị hủy
        if (handler != null && updateTimeRunnable != null) {
            handler.removeCallbacks(updateTimeRunnable);
        }
    }
}