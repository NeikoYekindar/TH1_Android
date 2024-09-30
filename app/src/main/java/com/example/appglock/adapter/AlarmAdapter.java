package com.example.appglock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appglock.R;

import java.security.PublicKey;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {


    private List<String> alarmTimes;







    private List<Boolean> alarmStates; // Danh sách trạng thái của từng alarm
    private OnSwitchToggleListener switchToggleListener;

     public AlarmAdapter(List<String> alarmtimes, OnSwitchToggleListener switchToggleListener){
         this.alarmTimes = alarmtimes;
         this.switchToggleListener = switchToggleListener;
         this.alarmStates = alarmStates;

     }


    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        String time = alarmTimes.get(position);
        holder.alarm_time.setText(time);

        // Xóa lắng nghe sự kiện trước đó để tránh lặp
        holder.switchAlarm.setOnCheckedChangeListener(null);

        // Thiết lập trạng thái của switch từ danh sách alarmStates
       // boolean isAlarmOn = alarmStates.get(position);
       // holder.switchAlarm.setChecked(isAlarmOn);

        // Thêm lắng nghe sự kiện thay đổi trạng thái của switch
        holder.switchAlarm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (switchToggleListener != null) {
                // Truyền thời gian và trạng thái của Switch khi được bật/tắt
               // switchToggleListener.onSwitchToggled(time, isChecked);
                // Cập nhật trạng thái của switch trong danh sách
               // alarmStates.set(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarmTimes != null ? alarmTimes.size() : 0;
    }
    //getItemCount: Phương thức này trả về số lượng item trong danh sách alarmtimes,
    // giúp RecyclerView biết cần tạo bao nhiêu ViewHolder.
    static class AlarmViewHolder extends RecyclerView.ViewHolder {

        //AlarmViewHolder: Là một lớp con của RecyclerView.ViewHolder,
        // giữ các reference tới các view trong item layout.
        TextView alarm_time;
        SwitchCompat switchAlarm;

        public AlarmViewHolder(@NonNull View view) {
            super(view);
            alarm_time = view.findViewById(R.id.alarm_time);
            switchAlarm = view.findViewById(R.id.switchAlarm);
        }

    }
    public interface OnSwitchToggleListener {
        void onSwitchToggled(String time, boolean isOn);
    }

}
