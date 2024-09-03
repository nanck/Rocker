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
                rocker.setListener(new RockerView.RockerListener() {

                    @Override
                    public void callback(int eventType, int currentAngle, float currentDistance, RockerView.State currentState) {
                        switch (eventType) {
                            case RockerView.EVENT_ACTION:
                            case RockerView.EVENT_CLOCK:
                                // 定时回调
                                // 触摸事件回调
                                Log.e("EVENT_ACTION-------->", "angle=" + currentAngle + " - distance" + currentDistance + " - state " + currentState.text);
                                eachDebug(eventType, currentAngle, currentDistance, currentState);
                                break;
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eachDebug(int eventType, int currentAngle, float currentDistance, RockerView.State currentState) {
        String info = String.format(Locale.getDefault(),
                "debug\n事件类型: %d\n角度: $%d\n距离: %f\n状态:%s\ndata1: %.2f\ndata2: %.2f",
                eventType, currentAngle, currentDistance, currentState.text, currentState.data1, currentState.data2);
        tvDebug.post(() -> {
            tvDebug.setText(info);
        });
    }
}
