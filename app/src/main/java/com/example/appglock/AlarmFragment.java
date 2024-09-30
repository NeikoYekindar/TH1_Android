package com.example.appglock;

import static android.app.Activity.RESULT_OK;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.Toast;

import com.example.appglock.adapter.AlarmAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        alarmtimes.add("07:30");
        alarmtimes.add("10:15");
        alarmtimes.add("23:15");
        alarmtimes.add("02:30");

        alarmAdapter = new AlarmAdapter(alarmtimes, new AlarmAdapter.OnSwitchToggleListener(){
            @Override
            public void onSwitchToggled(String time, boolean isOn){
                String[] timeParts = time.split(":");
                int hour = Integer.parseInt(timeParts[0]);
                int minute = Integer.parseInt(timeParts[1]);

                if (isOn) {
                    // Cài đặt báo thức với giờ và phút
                    setAlarm(hour, minute);
                } else {
                    // Hủy bỏ báo thức
                    cancelAlarm(hour, minute);
                }
            }






        });
        recyclerView.setAdapter(alarmAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



//        addalarm.setOnClickListener(view1 -> {
//            intent = new Intent(getActivity(), AddAlarmAcitvity.class);
//            startActivityForResult(intent, 1);
//        });
//        receivedIntent = getActivity().getIntent();
//        hour = receivedIntent.getIntExtra("gio", 0);
//        minute = receivedIntent.getIntExtra("phut", 0);





    }
    // Hàm để cài đặt báo thức
    private void setAlarm(int hour, int minute) {
        // Logic để cài đặt báo thức
        // Lấy đối tượng AlarmManager
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        // Tạo một Intent sẽ được kích hoạt khi báo thức chạy (ở đây sử dụng BroadcastReceiver)
        Intent intent = new Intent(getContext(), AlarmReceiver.class);  // AlarmReceiver sẽ nhận báo thức
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // Nếu thời gian đã qua rồi, đặt cho ngày hôm sau
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Đặt báo thức với AlarmManager
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            // Nếu muốn dùng báo thức lặp lại:
            // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        // Thông báo rằng báo thức đã được đặt
        Toast.makeText(getContext(), "Alarm set for " + String.format("%02d:%02d", hour, minute), Toast.LENGTH_SHORT).show();
    }

    // Hàm để hủy báo thức
    private void cancelAlarm(int hour, int minute) {
        // Logic để hủy bỏ báo thức
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);  // Hủy bỏ báo thức
            Toast.makeText(getContext(), "Alarm canceled", Toast.LENGTH_SHORT).show();
        }
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
    private void AddtimeAdapter(String time) {
        alarmtimes.add(time);
        alarmAdapter.notifyItemInserted(alarmtimes.size() - 1);
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