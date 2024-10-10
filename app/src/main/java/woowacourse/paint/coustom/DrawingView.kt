package woowacourse.paint.coustom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import woowacourse.paint.model.CircleShape
import woowacourse.paint.model.DrawableShape
import woowacourse.paint.model.PathShape
import woowacourse.paint.model.Shape
import woowacourse.paint.model.ShapeType
import woowacourse.paint.model.SquareShape

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val shapes = mutableListOf<DrawableShape>()

    private var currentPaint: Paint = initialPaint()
    private var currentShape: Shape = PathShape(Path())

    private var shapeType: ShapeType = ShapeType.PEN

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (drawableShape in shapes) {
            drawableShape.shape.draw(canvas, drawableShape.paint)
        }

        currentShape.draw(canvas, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentShape =
                    if (shapeType == ShapeType.ERASER) {
                        PathShape(Path().apply { moveTo(pointX, pointY) })
                    } else {
                        when (shapeType) {
                            ShapeType.PEN -> PathShape(Path().apply { moveTo(pointX, pointY) })
                            ShapeType.SQUARE -> SquareShape(pointX, pointY, pointX, pointY)
                            ShapeType.CIRCLE -> CircleShape(pointX, pointY)
                            else -> PathShape(Path().apply { moveTo(pointX, pointY) })
                        }
                    }
            }

            MotionEvent.ACTION_MOVE -> {
                currentShape.update(pointX, pointY)
            }

            MotionEvent.ACTION_UP -> {
                shapes.add(DrawableShape(currentShape, Paint(currentPaint)))
                resetShape()
            }
        }

        invalidate()
        return true
    }

    private fun setEraseMode(erase: Boolean) {
        if (erase) {
            currentPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        } else {
            currentPaint.xfermode = null
        }
    }

    private fun resetShape() {
        currentShape =
            when (shapeType) {
                ShapeType.PEN, ShapeType.ERASER -> PathShape(Path())
                ShapeType.SQUARE ->
                    SquareShape()

                ShapeType.CIRCLE ->
                    CircleShape()
            }
    }

    private fun initialPaint(): Paint {
        return Paint().apply {
            color = DEFAULT_COLOR
            style = Paint.Style.STROKE
            strokeWidth = DEFAULT_WIDTH
        }
    }

    fun setPaintColor(
        @ColorInt color: Int,
    ) {
        currentPaint.color = color
    }

    fun setPaintWidth(width: Int) {
        currentPaint.strokeWidth = width.toFloat()
    }

    fun setShapeType(type: ShapeType) {
        shapeType = type
        setEraseMode(type == ShapeType.ERASER)
    }

    companion object {
        private const val DEFAULT_COLOR = Color.RED
        private const val DEFAULT_WIDTH = 50f
        private const val INITIAL_WIDTH = 0f
    }
}
