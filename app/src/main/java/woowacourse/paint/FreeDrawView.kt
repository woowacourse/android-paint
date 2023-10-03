package woowacourse.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt

class FreeDrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint()
    private val previousDraw: MutableList<Pair<Path, Paint>> = mutableListOf()

    init {
        initStroke()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((path, paint) in previousDraw) {
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                previousDraw.add(
                    Pair(
                        Path().apply { moveTo(event.x, event.y) },
                        Paint().apply {
                            set(paint)
                        },
                    ),
                )
            }

            MotionEvent.ACTION_MOVE -> {
                previousDraw.last().first.lineTo(event.x, event.y)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
            }

            else -> super.onTouchEvent(event)
        }

        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun updateColor(@ColorInt color: Int) {
        paint.color = color
    }

    fun updateThickness(thickness: Float) {
        paint.strokeWidth = thickness
    }

    private fun initStroke() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            color = Color.RED
        }
    }
}
