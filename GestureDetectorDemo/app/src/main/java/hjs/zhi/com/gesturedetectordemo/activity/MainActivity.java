package hjs.zhi.com.gesturedetectordemo.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hjs.zhi.com.gesturedetectordemo.NetworkConnectChangedReceiver;
import hjs.zhi.com.gesturedetectordemo.R;
import hjs.zhi.com.gesturedetectordemo.util.OrientationUtil;
import hjs.zhi.com.gesturedetectordemo.weight.RockerView;
import hjs.zhi.com.gesturedetectordemo.weight.widght.PopupButton;
import hjs.zhi.com.gesturedetectordemo.weight.widght.PopupCircleView;

public class MainActivity extends AppCompatActivity {
    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private RockerView mRockerView;
    private PopupCircleView popupCircleView;
    private TextView tvSetting;

    BroadcastReceiver bReceiver;
    IntentFilter iFilter;

    String gravity_sensor;
    String finger_sliding;
    String gyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        mRockerView = findViewById(R.id.btn_main_rocker);
        popupCircleView = findViewById(R.id.pcv);
        tvSetting = findViewById(R.id.tv_mian_setting);

        setRockerViewClick();
        setSpeed();

        iFilter = new IntentFilter();
        iFilter.addAction(CONNECTIVITY_CHANGE_ACTION);
        bReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(bReceiver, iFilter);
        settingOnClick();

        gravity_sensor = getIntent().getStringExtra(SettingActivity.DATA);
        finger_sliding = getIntent().getStringExtra(SettingActivity.DATA);
        gyro = getIntent().getStringExtra(SettingActivity.DATA);


    }

    private void sysout(boolean b) {
        System.out.println("isChecked==" + b);
    }

    /**
     * 设置速度
     */
    private void setSpeed() {

        popupCircleView.setmOnMenuEventListener(new PopupCircleView.OnMenuEventListener() {
            @Override
            public void onMenuToggle(PopupButton popupButton) {
                boolean isChecked = true;

                switch (popupButton.getId()) {
                    case R.id.pb_favorite:
                        if (isChecked) {
//                            popupButton.setChecked(isChecked);
                            sysout(popupButton.isChecked());

                            Toast.makeText(MainActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                        } else {
                            sysout(popupButton.isChecked());
                            Toast.makeText(MainActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.pb_like:
                        if (popupButton.isChecked()) {
//                            isChecked = true;
//                            popupButton.setChecked(isChecked);
                            sysout(popupButton.isChecked());
                            Toast.makeText(MainActivity.this, "赞", Toast.LENGTH_SHORT).show();
                        } else {
//                            isChecked = false;
                            popupButton.setChecked(false);
                            sysout(popupButton.isChecked());
                            Toast.makeText(MainActivity.this, "取消赞", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.pb_share:
                        if (popupButton.isChecked()) {
//                            isChecked = true;
//                            popupButton.setChecked(isChecked);
                            sysout(popupButton.isChecked());
                            Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
                        } else {
//                            isChecked = false;
//                            popupButton.setChecked(isChecked);
                            sysout(popupButton.isChecked());
                            Toast.makeText(MainActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    /**
     * 设置陀螺仪的点击事件
     */
    private void setRockerViewClick() {
        mRockerView.setOnShakeListener(RockerView.DirectionMode.DIRECTION_4_ROTATE_0, new RockerView.OnShakeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void direction(RockerView.Direction direction) {
                if (direction == RockerView.Direction.DIRECTION_CENTER) {
                    Toast.makeText(MainActivity.this, "当前方向：中心", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_DOWN) {
                    Toast.makeText(MainActivity.this, "当前方向：下", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_LEFT) {
                    Toast.makeText(MainActivity.this, "当前方向：左", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_UP) {
                    Toast.makeText(MainActivity.this, "当前方向：上", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_RIGHT) {
                    Toast.makeText(MainActivity.this, "当前方向：右", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_DOWN_LEFT) {
                    Toast.makeText(MainActivity.this, "当前方向：左下", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_DOWN_RIGHT) {
                    Toast.makeText(MainActivity.this, "当前方向：右下", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_UP_LEFT) {
                    Toast.makeText(MainActivity.this, "当前方向：左上", Toast.LENGTH_SHORT).show();
                } else if (direction == RockerView.Direction.DIRECTION_UP_RIGHT) {
                    Toast.makeText(MainActivity.this, "当前方向：右上", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {

            }
        });

        mRockerView.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
            @Override
            public void onStart() {
                Log.d("TAG", "start");
            }

            @Override
            public void angle(double angle) {
                Toast.makeText(MainActivity.this, "当前角度：" + angle, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {

            }
        });

        mRockerView.setOnDistanceLevelListener(new RockerView.OnDistanceLevelListener() {
            @Override
            public void onDistanceLevel(int level) {
                Toast.makeText(MainActivity.this, "当前距离级别：" + level, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void settingOnClick() {
        tvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, SettingActivity.class), 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                hideView();
                OrientationUtil.getInstance().onCreat();
                Log.d("TAG DATA", ">>>>>>>" + requestCode + "  " + resultCode + "  " + gyro);
                break;
            case 1:
                hideView();
                Log.d("TAG DATA", ">>>>>>>" + requestCode + "  " + resultCode + "  " + gyro);
                break;
            case 2:
                showView();
                Log.d("TAG DATA", ">>>>>>>" + requestCode + "  " + resultCode + "  " + gyro);
                break;
        }
    }

    private void hideView() {
        mRockerView.setVisibility(View.GONE);
    }

    private void showView() {
        mRockerView.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (OrientationUtil.sm != null) {
            OrientationUtil.getInstance().onPause();
            unregisterReceiver(bReceiver);
        }

    }
}
