package com.example.appglock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appglock.R;

import java.security.PublicKey;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private List<String> alarmTimes;


     public AlarmAdapter(List<String> alarmtimes){
         this.alarmTimes = alarmtimes;

     }


    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position){ // Phương thức này được gọi để gán dữ liệu cho ViewHolder cụ thể ở vị trí position.
         String time = alarmTimes.get(position);
         holder.alarm_time.setText(time);
        // Thêm lắng nghe sự kiện thay đổi trạng thái của switch
        holder.switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Xử lý logic khi switch được bật/tắt
            if (isChecked) {
                // Switch được bật
            } else {
                // Switch bị tắt
            }
        });
    }
    @Override
    public int getItemCount() {
        return alarmTimes.size();
    }//getItemCount: Phương thức này trả về số lượng item trong danh sách alarmtimes,
    // giúp RecyclerView biết cần tạo bao nhiêu ViewHolder.
    public class AlarmViewHolder extends RecyclerView.ViewHolder{
         //AlarmViewHolder: Là một lớp con của RecyclerView.ViewHolder,
         // giữ các reference tới các view trong item layout.
        TextView alarm_time;
        SwitchCompat switchCompat;
        public AlarmViewHolder(@NonNull View view){
            super(view);
            alarm_time = view.findViewById(R.id.alarm_time);
            switchCompat = view.findViewById(R.id.switchAlarm);
        }
    }

}
