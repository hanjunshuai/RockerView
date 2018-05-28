package myapplication.hjs.zhi.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import myapplication.hjs.zhi.com.weight.RockerView;

public class MainActivity extends AppCompatActivity {
    private RockerView mRockerView;
    private TextView mTvShake;
    private TextView mTvAngle;
    private TextView mTvLevel;
    private TextView mTvModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setRockerViewClick();
        mTvModel.setText("当前模式：方向有改变时回调；8个方向");
    }

    private void initView() {
        mRockerView = findViewById(R.id.my_rocker);
        mTvAngle = findViewById(R.id.tv_now_angle);
        mTvLevel = findViewById(R.id.tv_now_level);
        mTvModel = findViewById(R.id.tv_now_model);
        mTvShake = findViewById(R.id.tv_now_shake);
    }

    private void setRockerViewClick() {
        mRockerView.setOnShakeListener(RockerView.DirectionMode.DIRECTION_8, new RockerView.OnShakeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void direction(RockerView.Direction direction) {
                if (direction == RockerView.Direction.DIRECTION_CENTER) {
                    mTvShake.setText("当前方向：中心");

                } else if (direction == RockerView.Direction.DIRECTION_DOWN) {
                    mTvShake.setText("当前方向：下");
                } else if (direction == RockerView.Direction.DIRECTION_LEFT) {
                    mTvShake.setText("当前方向：左");
                } else if (direction == RockerView.Direction.DIRECTION_UP) {
                    mTvShake.setText("当前方向：上");
                } else if (direction == RockerView.Direction.DIRECTION_RIGHT) {
                    mTvShake.setText("当前方向：右");
                } else if (direction == RockerView.Direction.DIRECTION_DOWN_LEFT) {
                    mTvShake.setText("当前方向：左下");
                } else if (direction == RockerView.Direction.DIRECTION_DOWN_RIGHT) {
                    mTvShake.setText("当前方向：右下");
                } else if (direction == RockerView.Direction.DIRECTION_UP_LEFT) {
                    mTvShake.setText("当前方向：左上");
                } else if (direction == RockerView.Direction.DIRECTION_UP_RIGHT) {
                    mTvShake.setText("当前方向：右上");
                }
            }

            @Override
            public void onFinish() {

            }
        });

        mRockerView.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void angle(double angle) {
                mTvAngle.setText("当前角度：" + angle);
            }

            @Override
            public void onFinish() {

            }
        });

        mRockerView.setOnDistanceLevelListener(new RockerView.OnDistanceLevelListener() {
            @Override
            public void onDistanceLevel(int level) {
                mTvLevel.setText("当前距离级别：" + level);
            }
        });
    }
}
