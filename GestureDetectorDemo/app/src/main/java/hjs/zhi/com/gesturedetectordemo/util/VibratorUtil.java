package hjs.zhi.com.gesturedetectordemo.util;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * 震动
 */
public class VibratorUtil {

    private static volatile VibratorUtil instance = null;

    public static VibratorUtil getInstance() {
        if (instance == null) {
            synchronized (OrientationUtil.class) {
                if (instance == null) {
                    instance = new VibratorUtil();
                }
            }
        }
        return instance;
    }

    private Vibrator mVibrator;  //声明一个振动器对象

    public void init(Context context) {
        /**
         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
         */
        mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
    }


    /**
     * 开启震动
     */
    public void startVibrator() {
        /**
         * 四个参数就是——停止 开启 停止 开启
         * -1不重复，非-1为从pattern的指定下标开始重复
         */
        mVibrator.vibrate(new long[]{1000, 10000, 1000, 10000}, -1);
        //停止1秒，开启震动10秒，然后又停止1秒，又开启震动10秒，不重复.
    }

    /**
     * 停止震动
     */
    public void stopVibrator() {
        mVibrator.cancel();
    }
}
