package hjs.zhi.com.gesturedetectordemo.weight.widght;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimUtil {
    public static ObjectAnimator toAlphaOne(View view, int duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, "alpha", new FloatEvaluator(), 0.0f, 1.0f);
        objectAnimator.setDuration(duration);
        return objectAnimator;
    }

    public static ObjectAnimator toAlphaZero(View view, int duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, "alpha", new FloatEvaluator(), 1.0f, 0f);
        objectAnimator.setDuration(duration);
        return objectAnimator;
    }
}
