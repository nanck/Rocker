package com.gcssloop.rockertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.gcssloop.widget.RockerView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDebug = (TextView) findViewById(R.id.tvDebug);

        try {
            RockerView rocker = (RockerView) findViewById(R.id.rocker);
            if (null != rocker) {
                rocker.setListener((eventType, currentAngle, currentDistance, currentState) -> {
                    float d1 = currentState.data1;
                    float d2 = currentState.data2;

                    switch (eventType) {
                        case RockerView.EVENT_ACTION:
                        case RockerView.EVENT_CLOCK:
                            // 定时回调
                            // 触摸事件回调
                            Log.e("EVENT_ACTION-------->", "angle=" + currentAngle + " - distance" + currentDistance + " - state " + currentState.text);
                            eachDebug(eventType, currentAngle, currentDistance, currentState);
                            break;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eachDebug(int eventType, int currentAngle, float currentDistance, RockerView.State currentState) {
        String direction = angleToDirection(currentAngle);
        String info = String.format(Locale.getDefault(),
                "debug\n事件类型: %d\n角度: $%d\n方向: %s\n距离: %f\n状态:%s\ndata1: %.2f\ndata2: %.2f",
                eventType, currentAngle, direction, currentDistance, currentState.text, currentState.data1, currentState.data2);
        tvDebug.post(() -> {
            tvDebug.setText(info);
        });
    }


    public static String angleToDirection(double angle) {
        String[] directions = {"东", "东北", "北", "西北", "西", "西南", "南", "东南"};
        int index = (int) ((angle % 360) / 45);
        return directions[index];
    }
}
