package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import woowacourse.paint.brush.Brush
import woowacourse.paint.brush.Circle
import woowacourse.paint.brush.Eraser
import woowacourse.paint.brush.Pen
import woowacourse.paint.brush.Rect
import kotlin.reflect.KClass

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val drawings = mutableListOf<Brush>()
    private var currentBrush: Brush =
        Pen(
            CanvasPaint(this.context.getColor(DEFAULT_COLOR.colorId), DEFAULT_BRUSH_SIZE),
        )

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawings.forEach {
            canvas.drawPath(it, it.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startLine(pointX, pointY)
            MotionEvent.ACTION_MOVE -> drawLine(pointX, pointY)
            else -> super.onTouchEvent(event)
        }
        return true
    }

    private fun startLine(
        pointX: Float,
        pointY: Float,
    ) {
        drawings.add(currentBrush.startDraw(pointX, pointY))
        invalidate()
    }

    private fun drawLine(
        pointX: Float,
        pointY: Float,
    ) {
        drawings.lastOrNull()?.moveBrush(pointX, pointY)?.let {
            invalidate()
        }
    }

    fun changePaintColor(colorType: ColorType) {
        currentBrush =
            when (currentBrush) {
                is Eraser -> return
                is Pen -> (currentBrush as Pen).changeColor(colorType, this.context)
                is Circle -> (currentBrush as Circle).changeColor(colorType, this.context)
                is Rect -> (currentBrush as Rect).changeColor(colorType, this.context)
            }
    }

    fun changeBrushWidth(width: Float) {
        currentBrush =
            when (currentBrush) {
                is Eraser -> (currentBrush as Eraser).changeBrushWidth(width)
                is Pen -> (currentBrush as Pen).changeBrushWidth(width)
                is Circle -> return
                is Rect -> return
            }
    }

    fun changeBrush(brush: KClass<out Brush>) {
        currentBrush =
            when (brush) {
                Eraser::class -> Eraser(currentBrush.paint.copy())
                Pen::class -> Pen(currentBrush.paint.copy())
                Circle::class -> Circle(currentBrush.paint.copy())
                Rect::class -> Rect(currentBrush.paint.copy())
                else -> throw IllegalArgumentException("$brush 는 Brush 타입이 아닙니다.")
            }
    }

    companion object {
        const val DEFAULT_BRUSH_SIZE = 10f
        val DEFAULT_COLOR = ColorType.RED
    }
}
