package com.example.appglock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Xử lý khi báo thức được kích hoạt (ví dụ: hiển thị thông báo, phát âm thanh...)
        Toast.makeText(context, "Alarm is ringing!", Toast.LENGTH_LONG).show();

        // Bạn có thể thêm logic để phát âm thanh hoặc mở một Activity báo thức tại đây
    }
}
