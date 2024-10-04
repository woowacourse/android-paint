package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.utils.dp

class SliderView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : View(context, attrs, defStyleAttr) {
        private var sliderBarPaint =
            Paint().apply {
                color = Color.LTGRAY
                strokeWidth = 4f.dp
                isAntiAlias = true
            }

        private var runningSliderBarPaint =
            Paint().apply {
                color = Color.BLACK
                strokeWidth = 4f.dp
                isAntiAlias = true
            }

        private var handlePaint =
            Paint().apply {
                color = Color.BLACK
                isAntiAlias = true
            }

        private var textPaint =
            Paint().apply {
                color = Color.BLACK
                textSize = 20f.dp
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }

        var sliderPosition = SLIDER_DEFAULT_POSITION.dp
            private set(value) {
                field = value
                onSliderPositionChangeListener?.onPositionChanged(value)
                invalidate()
            }
        var minValue: Float = DEFAULT_MIN_VALUE
        var maxValue: Float = DEFAULT_MAX_VALUE
        private val handleRadius: Float = DEFAULT_THUMB_RADIUS
        private val barStartX: Float = paddingLeft + handleRadius
        private val barEndX: Float get() = width - paddingRight - handleRadius
        private val barY: Float get() = height / 2f
        private var onSliderPositionChangeListener: OnSliderPositionChangeListener? = null

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            drawBaseBar(canvas)
            drawProgressBar(canvas)
            drawHandle(canvas)
            drawText(canvas)
        }

        private fun drawBaseBar(canvas: Canvas) {
            canvas.drawLine(barStartX, barY, barEndX, barY, sliderBarPaint)
        }

        private fun drawProgressBar(canvas: Canvas) {
            val progressBarEndX = viewPositionFrom(sliderPosition)
            canvas.drawLine(barStartX, barY, progressBarEndX, barY, runningSliderBarPaint)
        }

        private fun drawHandle(canvas: Canvas) {
            val handlePosX = viewPositionFrom(sliderPosition)
            canvas.drawCircle(handlePosX, barY, handleRadius, handlePaint)
        }

        private fun drawText(canvas: Canvas) {
            val marginBottom = 10f.dp
            val textY = barY - handleRadius - marginBottom
            canvas.drawText(
                sliderPosition.toInt().toString(),
                viewPositionFrom(sliderPosition),
                textY,
                textPaint,
            )
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {
            val posX = event.x
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    sliderPosition = barPositionFrom(posX).coerceIn(minValue, maxValue)
                }
            }
            return true
        }

        private fun viewPositionFrom(trackPosX: Float): Float {
            val currentTrackPosXRatio = (trackPosX - minValue) / (maxValue - minValue)
            return barStartX + (barEndX - barStartX) * currentTrackPosXRatio
        }

        private fun barPositionFrom(viewPosX: Float): Float {
            val currentViewPosXRatio = (viewPosX - barStartX) / (barEndX - barStartX)
            return minValue + (maxValue - minValue) * currentViewPosXRatio
        }

        fun setOnSliderChangeListener(listener: OnSliderPositionChangeListener) {
            onSliderPositionChangeListener = listener
        }

        fun interface OnSliderPositionChangeListener {
            fun onPositionChanged(position: Float)
        }

        companion object {
            private const val DEFAULT_MIN_VALUE = 0f
            private const val DEFAULT_MAX_VALUE = 100f
            private const val SLIDER_DEFAULT_POSITION = 0f
            private val DEFAULT_THUMB_RADIUS = 15f.dp
        }
    }
