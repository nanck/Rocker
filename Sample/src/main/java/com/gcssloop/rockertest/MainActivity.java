package com.gcssloop.rockertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gcssloop.widget.RockerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RockerView rocker = (RockerView) findViewById(R.id.rocker);
            if (null != rocker) {
                rocker.setListener(new RockerView.RockerListener() {

                    @Override
                    public void callback(int eventType, int currentAngle, float currentDistance, RockerView.State currentState) {
                        switch (eventType) {
                            case RockerView.EVENT_ACTION:
                                // 触摸事件回调
                                Log.e("EVENT_ACTION-------->", "angle=" + currentAngle + " - distance" + currentDistance + " - state " + currentState.text);
                                break;
                            case RockerView.EVENT_CLOCK:
                                // 定时回调
                                Log.e("EVENT_ACTION-------->", "angle=" + currentAngle + " - distance" + currentDistance + " - state " + currentState.text);
                                break;
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
