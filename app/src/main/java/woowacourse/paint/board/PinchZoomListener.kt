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
        calculateScaleFactor(currentSpan)
        limitScaleFactor()
        applyScaleFactorToView()
        lastSpan = currentSpan
        return true
    }

    private fun applyScaleFactorToView() {
        targetView.animate()
            .scaleX(currentScaleFactor)
            .scaleY(currentScaleFactor)
            .setDuration(0)
            .start()
    }

    private fun calculateScaleFactor(currentSpan: Float) {
        currentScaleFactor *= 1 + (getScaleRatio(currentSpan)) * SCALE_CORRECTION_FACTOR
    }

    /**
     * 음수를 반환하면 zoom out, 양수를 반환하면 zoom in 상황을 뜻한다.
     */
    private fun getScaleRatio(currentSpan: Float) = currentSpan / lastSpan - 1

    private fun limitScaleFactor() {
        currentScaleFactor = Math.max(MINIMUM_SCALE_DOWN_VALUE, Math.min(currentScaleFactor, MAXIMUM_SCALE_UP_VALUE))
    }

    companion object {
        const val SCALE_CORRECTION_FACTOR = 0.2F
        const val MINIMUM_SCALE_DOWN_VALUE = 1f / 5f
        const val MAXIMUM_SCALE_UP_VALUE = 5f
    }
}
