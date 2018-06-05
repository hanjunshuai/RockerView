package hjs.zhi.com.gesturedetectordemo.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

/**
 * 重力感应
 */
public class OrientationUtil {
    public static SensorManager sm;
    //需要两个Sensor
    private Sensor aSensor;
    private Sensor mSensor;

    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];

    private static final String TAG = "sensor";

    //将同步内容下方到if内部，提高了执行的效率，不必每次获取对象时都进行同步，只有第一次才同步，创建了以后就没必要了。
    private static volatile OrientationUtil instance = null;

    public OrientationUtil() {
    }

    public void onCreat(Context context) {
        this.context = context;
        sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        aSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sm.registerListener(myListener, aSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(myListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    //这里提供了一个供外部访问本class的静态方法，可以直接访问
    public static OrientationUtil getInstance() {
        if (instance == null) {
            synchronized (OrientationUtil.class) {
                if (instance == null) {
                    instance = new OrientationUtil();
                }
            }
        }
        return instance;
    }

    private void calculateOrientation() {
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
        SensorManager.getOrientation(R, values);

        // 要经过一次数据格式的转换，转换为度
        values[0] = (float) Math.toDegrees(values[0]);
        Log.i(TAG, values[0] + "");
        //values[1] = (float) Math.toDegrees(values[1]);
        //values[2] = (float) Math.toDegrees(values[2]);

        if (values[0] >= -80 && values[0] < -40 && Math.abs(values[0]) >= 20) {
            Log.i(TAG, "正北");
            $toast(TAG + "前");
        } else if (values[0] >= 5 && values[0] < 85) {
            Log.i(TAG, "东北");
        } else if (values[0] >= 85 && values[0] <= 95) {
            Log.i(TAG, "正东");
        } else if (values[0] >= 95 && values[0] < 175) {
            Log.i(TAG, "东南");
        } else if ((values[0] >= 175 && values[0] <= 180) || (values[0]) >= -180 && values[0] < -175) {
            Log.i(TAG, "正南");
        } else if (values[0] >= -175 && values[0] < -95) {
            Log.i(TAG, "西南");
            $toast(TAG + "hjs左");
        } else if (values[0] >= -95 && values[0] < -85) {
            Log.i(TAG, "正西");
        } else if (values[0] >= -85 && values[0] < -5) {
            Log.i(TAG, "西北");
            $toast(TAG + "hjs右");

        }
    }

    //再次强调：注意activity暂停的时候释放
    public void onPause() {
        sm.unregisterListener(myListener);
    }

    final SensorEventListener myListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {

            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                magneticFieldValues = sensorEvent.values;
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                accelerometerValues = sensorEvent.values;
            calculateOrientation();
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };


    // Toast
    Context context;

    protected void $toast(CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
