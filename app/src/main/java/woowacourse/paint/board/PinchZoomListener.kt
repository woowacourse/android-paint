package woowacourse.paint.board

import android.view.ScaleGestureDetector
import android.view.View

class PinchZoomListener(private val targetView: View) :
    ScaleGestureDetector.SimpleOnScaleGestureListener() {

    private var currentScaleFactor: Float = 1f

    private var lastSpan: Float = 0f

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        super.onScaleBegin(detector)
        lastSpan = detector.currentSpan
        return true
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        super.onScale(detector)
        val currentSpan: Float = detector.currentSpan

        currentScaleFactor *= 1 + (lastSpan / currentSpan - 1) * 0.2f

        currentScaleFactor = Math.max(0.2f, Math.min(currentScaleFactor, 5.0f))

        targetView.animate()
            .scaleX(currentScaleFactor)
            .scaleY(currentScaleFactor)
            .setDuration(0)
            .start()

        lastSpan = currentSpan
        return true
    }
}
