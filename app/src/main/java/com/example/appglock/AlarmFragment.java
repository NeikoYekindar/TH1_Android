package com.example.appglock;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appglock.adapter.AlarmAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AlarmFragment extends Fragment {

    ImageView addalarm;
    private TextView tvCurrentTime;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateTimeRunnable;
    RecyclerView recyclerView;
    AlarmAdapter alarmAdapter;
    List<String> alarmtimes;
    int hour, minute;
    Intent intent;
    Intent receivedIntent ;
    String timeadd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCurrentTime = view.findViewById(R.id.ngaythangnam);
        startUpdatingTime();


        addalarm = view.findViewById(R.id.addAlarm);
        recyclerView =view.findViewById(R.id.recyclerView);
        alarmtimes = new ArrayList<>();
        alarmtimes.add("08:00");
        alarmtimes.add("07:15");


        alarmAdapter = new AlarmAdapter(alarmtimes);
        recyclerView.setAdapter(alarmAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        addalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), AddAlarmAcitvity.class);
                startActivityForResult(intent, 1);
            }
        });
        receivedIntent = getActivity().getIntent();
        hour = receivedIntent.getIntExtra("gio", 0);
        minute = receivedIntent.getIntExtra("phut", 0);





    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            hour = data.getIntExtra("gio",-1);
            minute = data.getIntExtra("phut",-1);
            if(hour!=-1&&minute!=-1){
                timeadd = String.format("%02d:%02d",hour,minute);
                AddtimeAdapter(timeadd);
            }
        }


    }
    private  void AddtimeAdapter(String time){
        alarmtimes.add(time);
        alarmAdapter.notifyDataSetChanged();
    }
    private void startUpdatingTime() {
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                displayCurrentTime(); // Cập nhật thời gian
                handler.postDelayed(this, 1000); // Lặp lại sau mỗi 1 giây
            }
        };
        handler.post(updateTimeRunnable); // Bắt đầu quá trình cập nhật thời gian
    }
    private void displayCurrentTime() {
        // Lấy thời gian hiện tại
        Date now = new Date();

        // Định dạng ngày giờ: dd/MM/yyyy HH:mm
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String formattedDate = sdf.format(now);

        // Hiển thị thời gian trong TextView
        tvCurrentTime.setText(formattedDate);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(updateTimeRunnable); // Ngừng cập nhật khi view bị hủy
    }
}