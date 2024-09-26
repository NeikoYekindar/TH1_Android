package com.example.appglock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

public class ClockFace extends View {

    private Paint paint;
    private int width, height;
    private float radius;
    private float centerX, centerY;
    private Calendar calendar = Calendar.getInstance();

    // Các ImageView cho kim đồng hồ
    private ImageView hourHand, minuteHand, secondHand;

    public ClockFace(Context context) {
        super(context);
        init();
    }

    public ClockFace(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#33357B"));  // Màu của mặt đồng hồ
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = Math.min(width, height) / 2f - 50;
        centerX = width / 2f;
        centerY = height / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClockFace(canvas);
    }

    // Phương thức truyền các ImageView từ Activity
    public void setClockHands(ImageView hourHand, ImageView minuteHand, ImageView secondHand) {
        this.hourHand = hourHand;
        this.minuteHand = minuteHand;
        this.secondHand = secondHand;
    }



    private void drawClockFace(Canvas canvas) {
        // Vẽ mặt đồng hồ (đường tròn bên ngoài)
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);  // Độ dày của đường tròn
        canvas.drawCircle(centerX, centerY, radius, paint);

        // Vẽ các số và vạch giờ
        paint.setTextSize(50);  // Kích thước chữ số
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < 12; i++) {
            // Tính góc cho các vạch giờ (đổi thành radian)
            float angle = (float) Math.toRadians(i * 30 - 90);

            // Tính tọa độ cho vạch giờ
            float startX = centerX + (float) (radius * 0.9 * Math.cos(angle));
            float startY = centerY + (float) (radius * 0.9 * Math.sin(angle));
            float stopX = centerX + (float) (radius * Math.cos(angle));
            float stopY = centerY + (float) (radius * Math.sin(angle));

            // Vẽ vạch giờ
            paint.setStrokeWidth(10);
            canvas.drawLine(startX, startY, stopX, stopY, paint);

            // Tính tọa độ cho các số
            float numberX = centerX + (float) (radius * 0.8 * Math.cos(angle));
            float numberY = centerY + (float) (radius * 0.8 * Math.sin(angle));

            // Vẽ các số (1-12)
            String number = String.valueOf(i == 0 ? 12 : i);
            float textWidth = paint.measureText(number);
            canvas.drawText(number, numberX - textWidth / 2, numberY + 20, paint);

            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(centerX, centerY, 25, paint);
        }
    }
}
