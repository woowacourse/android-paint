package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PainterView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val brushView = BrushView(context, attrs)
    private val canvasBitmap: Bitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)
    private val brushPaint = Paint()
    private val diagramViews: List<DiagramView> =
        listOf(
            RectangleView(context, attrs),
            CircleView(context, attrs),
            TriangleView(context, attrs),
        )
    private var diagramView: DiagramView = diagramViews[0]

    var mode = PainterMode.BRUSH

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap, 0f, 0f, null)
        if (mode == PainterMode.DIAGRAM) diagramView.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        brushView.onTouchEvent(event)
        diagramView.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                if (mode == PainterMode.BRUSH) drawBrush()
            }

            MotionEvent.ACTION_UP -> {
                if (mode == PainterMode.DIAGRAM) drawDiagram()
                diagramView.clear()
                brushView.clear()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun drawBrush() = drawViewOnCanvasBitmap(brushView)

    private fun drawDiagram() = drawViewOnCanvasBitmap(diagramView)

    private fun drawViewOnCanvasBitmap(view: View) {
        val canvas = Canvas(canvasBitmap)
        val viewBitmap = getBitmapFromView(view)
        canvas.drawBitmap(viewBitmap, 0f, 0f, brushPaint)
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun setStrokeWidth(value: Float) {
        brushView.setStrokeWidth(value)
    }

    fun setColor(color: Int) {
        brushView.setColor(color)
        diagramView.setColor(color)
        brushPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
    }

    fun setBrush() {
        mode = PainterMode.BRUSH
    }

    fun setSquare() {
        mode = PainterMode.DIAGRAM
        diagramView = diagramViews[0]
    }

    fun setCircle() {
        mode = PainterMode.DIAGRAM
        diagramView = diagramViews[1]
    }

    fun setTriangle() {
        mode = PainterMode.DIAGRAM
        diagramView = diagramViews[2]
    }

    fun setEraser() {
        brushPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }
}
