package hjs.zhi.com.gesturedetectordemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hjs.zhi.com.gesturedetectordemo.R;
import hjs.zhi.com.gesturedetectordemo.activity.base.BaseActivity;

import static hjs.zhi.com.gesturedetectordemo.R.id.btn_setting_finger_sliding;
import static hjs.zhi.com.gesturedetectordemo.R.id.btn_setting_gravity_sensor;
import static hjs.zhi.com.gesturedetectordemo.R.id.btn_setting_gyro;
import static hjs.zhi.com.gesturedetectordemo.R.layout.activity_setting;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    Button btnSettingGravitySensor;
    Button btnSettingFingerSliding;
    Button btnSettingGyro;

    @Override
    protected int getLayoutId() {
        return activity_setting;
    }

    @Override
    protected void initView() {
        btnSettingGravitySensor = findViewById(btn_setting_gravity_sensor);
        btnSettingFingerSliding = findViewById(btn_setting_finger_sliding);
        btnSettingGyro = findViewById(btn_setting_gyro);
    }

    @Override
    protected void setListener() {
        btnSettingGravitySensor.setOnClickListener(this);
        btnSettingFingerSliding.setOnClickListener(this);
        btnSettingGyro.setOnClickListener(this);
    }

    Intent intent;

    @Override
    protected void initData() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    public static final String DATA = "2";

    @Override
    public void onClick(View v) {
        intent = new Intent();
        switch (v.getId()) {
            case btn_setting_gravity_sensor:
                intent.putExtra(DATA, 0);
                this.setResult(0, intent);

                Toast.makeText(this, "btn_setting_gravity_sensor", Toast.LENGTH_SHORT).show();
                break;
            case btn_setting_finger_sliding:
                intent.putExtra(DATA, 1);
                this.setResult(1, intent);

                Toast.makeText(this, "btn_setting_finger_sliding", Toast.LENGTH_SHORT).show();
                break;
            case btn_setting_gyro:
                intent.putExtra(DATA, 2);
                this.setResult(2, intent);

                Toast.makeText(this, "btn_setting_gyro", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
