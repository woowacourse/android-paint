package woowacourse.paint.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.utils.dp

class DrawingPaperView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
    ) : View(context, attrs, defStyle) {
        var currentColor: Int = Color.BLACK
        var currentStrokeWidth: Float = DEFAULT_STROKE_WIDTH
        private val paths = mutableListOf<Path>()
        private val paints = mutableListOf<Paint>()
        private var currentPath = Path()
            set(value) {
                field = value
                invalidate()
            }
        private var currentPaint = createPaint(currentColor, currentStrokeWidth)
            set(value) {
                field = value
                invalidate()
            }

        init {
            isFocusable = true
            isFocusableInTouchMode = true
        }

        private fun createPaint(
            color: Int,
            strokeWidth: Float,
        ): Paint {
            return Paint().apply {
                this.color = color
                isAntiAlias = true
                this.strokeWidth = strokeWidth
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.ROUND
            }
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawPrePaths()
            canvas.drawPath(currentPath, currentPaint)
        }

        private fun Canvas.drawPrePaths() {
            paths.indices.forEach { index ->
                drawPath(paths[index], paints[index])
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    currentPath =
                        Path().apply {
                            moveTo(x, y)
                        }
                    currentPaint = createPaint(currentColor, currentStrokeWidth)
                    paths.add(currentPath)
                    paints.add(currentPaint)
                }

                MotionEvent.ACTION_MOVE -> {
                    currentPath.lineTo(x, y)
                    invalidate()
                }

                else -> return super.onTouchEvent(event)
            }
            return true
        }

        companion object {
            private val DEFAULT_STROKE_WIDTH = 5f.dp
        }
    }
