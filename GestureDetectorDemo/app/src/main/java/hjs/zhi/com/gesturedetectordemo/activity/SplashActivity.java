package hjs.zhi.com.gesturedetectordemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hjs.zhi.com.gesturedetectordemo.R;
import hjs.zhi.com.gesturedetectordemo.activity.MainActivity;
import hjs.zhi.com.gesturedetectordemo.wifi.WifiHost;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        btnNext = findViewById(R.id.btn_splash_next);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (WifiHost.isWifiApOpen(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            showExitDialog();
        }
    }

    // 简单消息提示框
    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setMessage("请开启个人热点")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent("android.settings.AIRPLANE_MODE_SETTINGS"));
                    }
                })
                .show();
    }
}
