package com.example.appglock;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class timerActivity extends AppCompatActivity {
    private ProgressBar circularProgressBar;
    private TextView timeText;
    private long totalTimeInMillis;
    private long timeRemainingInMillis;
    Button btn_close, btn_pause;
    private boolean isPaused = false;
    private CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    Handler handler  =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        circularProgressBar = findViewById(R.id.circularProgressBar);
        btn_close = findViewById(R.id.close);
        btn_pause = findViewById(R.id.pause);
        timeText = findViewById(R.id.timeText);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarmsound2);
        int hours = getIntent().getIntExtra("gio",0);
        int minutes = getIntent().getIntExtra("phut",0);
        int seconds = getIntent().getIntExtra("giay",0);

        totalTimeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;
        startCircularLoading(totalTimeInMillis);







        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        },1000);

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPaused){
                    resumeCountdown();
                    btn_pause.setHint("Tạm dừng");
                }else{
                    pauseCountdown();
                    btn_pause.setHint("Tiếp tục");
                }
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(timerActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                // Cờ này đảm bảo rằng MainActivity không được khởi chạy lại nếu đã tồn tại
                startActivity(intent);
                finish();
            }
        });




    }
    private void startCircularLoading(long totalTime) {
        circularProgressBar.setMax(100);  // Set progress max to 100%

        countDownTimer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Calculate progress
                timeRemainingInMillis  = (int) millisUntilFinished;


                int progress = (int) ((timeRemainingInMillis  / (float) totalTime) * 100);
                circularProgressBar.setProgress(progress);

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d",
                        (timeRemainingInMillis / 3600000) % 24,
                        (timeRemainingInMillis / 60000) % 60,
                        (timeRemainingInMillis / 1000) % 60);
                timeText.setText(time);



            }

            @Override
            public void onFinish() {
                timeText.setText("00:00:00");
                circularProgressBar.setProgress(0);
                btn_pause.setEnabled(false);
                playalarmsound();
                // Ngăn chặn việc tiếp tục đếm ngược
                isPaused = true;
                countDownTimer.cancel(); //
            }
        }.start();
    }
    private void pauseCountdown(){
        countDownTimer.cancel();
        isPaused = true;
    }
    private void resumeCountdown(){
        startCircularLoading(timeRemainingInMillis);
        isPaused = false;
    }
    private void playalarmsound(){
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}