package woowacourse.paint.paintboard

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import woowacourse.paint.DrawablesBitmap
import woowacourse.paint.R
import woowacourse.paint.paintboard.common.Brush
import woowacourse.paint.paintboard.drawables.BrushDrawables

class PaintBoard(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    val drawables = BrushDrawables(Brush.LineBrush(DEFAULT_BRUSH_COLOR, DEFAULT_LINE_WIDTH))
    private lateinit var drawablesBitmap: DrawablesBitmap

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    var canvasColor: Int = DEFAULT_CANVAS_COLOR
    private val canvasPaint = Paint().apply {
        color = ContextCompat.getColor(this@PaintBoard.context, canvasColor)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawablesBitmap = DrawablesBitmap(w, h, canvasPaint)
    }

    override fun onDraw(canvas: Canvas) {
        // 캔버스 배경 그리기
        drawables.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        drawables.action(event)
        invalidate()
        return true
    }

    fun undo() {
        drawables.undo()
        invalidate()
    }

    fun redo() {
        drawables.redo()
        invalidate()
    }

    fun clear() {
        drawables.clear()
        invalidate()
    }

    fun updateBrush(brush: Brush) {
        drawables.brush = brush
    }

    fun getBitmap(): Bitmap {
        return drawablesBitmap.create(drawables)
    }

    companion object {
        val DEFAULT_CANVAS_COLOR = R.color.white
        val DEFAULT_BRUSH_COLOR = R.color.black
        const val DEFAULT_LINE_WIDTH = 20
    }
}
