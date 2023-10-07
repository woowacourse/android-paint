package woowacourse.paint.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import woowacourse.paint.customview.brush.Brush
import woowacourse.paint.customview.brush.Circle
import woowacourse.paint.customview.brush.Pen
import woowacourse.paint.customview.brush.Rectangle

class FreeDrawView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val previousDraw: MutableList<Pair<Path, Paint>> =
        mutableListOf()
    private var brush: Brush = Pen
    private var currentPosition = Pair(0f, 0f)
    private var previewDraw = Pair(Path(), Paint())

    init {
        brush.setStyle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        previousDraw.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
        if (brush == Circle || brush == Rectangle) {
            canvas.drawPath(previewDraw.first, previewDraw.second)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                when (brush) {
                    is Pen -> {
                        previousDraw.add(
                            Pair(
                                Path().apply { moveTo(event.x, event.y) },
                                Paint().apply {
                                    set(brush.paint)
                                },
                            ),
                        )
                    }

                    is Rectangle, Circle -> {
                        currentPosition = Pair(event.x, event.y)
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                when (brush) {
                    is Pen -> {
                        previousDraw.last().first.lineTo(event.x, event.y)
                    }

                    is Rectangle -> {
                        previewDraw = Pair(
                            Path().apply {
                                addRect(
                                    currentPosition.first,
                                    currentPosition.second,
                                    event.x,
                                    event.y,
                                    Path.Direction.CW,
                                )
                            },
                            Paint().apply { set(brush.paint) },

                        )
                    }

                    is Circle -> {
                        previewDraw = Pair(
                            Path().apply {
                                addCircle(
                                    currentPosition.first + ((event.x - currentPosition.first) / 2),
                                    currentPosition.second + ((event.x - currentPosition.first) / 2),
                                    (event.x - currentPosition.first) / 2,
                                    Path.Direction.CW,
                                )
                            },
                            Paint().apply { set(brush.paint) },
                        )
                    }
                }
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                when (brush) {
                    is Pen -> {
                    }

                    is Rectangle -> {
                        previousDraw.add(
                            Pair(
                                Path().apply {
                                    addRect(
                                        currentPosition.first,
                                        currentPosition.second,
                                        event.x,
                                        event.y,
                                        Path.Direction.CCW,
                                    )
                                },
                                Paint().apply { set(brush.paint) },
                            ),
                        )
                        invalidate()
                    }

                    is Circle -> {
                        previousDraw.add(
                            Pair(
                                Path().apply {
                                    addCircle(
                                        currentPosition.first + ((event.x - currentPosition.first) / 2),
                                        currentPosition.second + ((event.x - currentPosition.first) / 2),
                                        (event.x - currentPosition.first) / 2,
                                        Path.Direction.CW,
                                    )
                                },
                                Paint().apply { set(brush.paint) },
                            ),
                        )
                        invalidate()
                    }
                }
            }
            else -> super.onTouchEvent(event)
        }

        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun updateColor(@ColorInt color: Int) {
        brush.paint.color = color
    }

    fun updateThickness(thickness: Float) {
        brush.paint.strokeWidth = thickness
    }

    fun setBrushType(brush: Brush) {
        this.brush = brush
        this.brush.setStyle()
    }
}
