package hjs.zhi.com.gesturedetectordemo.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 设置ContentView
     *
     * @return R.layout.xxx
     */
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        setListener();
        initData();
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * add Listener
     */
    protected abstract void setListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * view点击
     *
     * @param v
     */
    public abstract void widgetClick(View v);


    // Toast
    protected void $toast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    // Toast
    protected void $toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    protected boolean isDebug = true;

    // Log
    protected void $Log(String msg) {
        if (isDebug) {
            Log.d(this.getClass().getName(), msg);
        }
    }

}
