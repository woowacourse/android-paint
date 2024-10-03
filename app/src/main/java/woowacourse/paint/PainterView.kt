package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PainterView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val brushView = BrushView(context, attrs)
    private val canvasBitmap: Bitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap, 0f, 0f, null)
        brushView.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        brushView.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                drawOnCanvasBitmap()
                brushView.clear()
            }
            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun drawOnCanvasBitmap() {
        val canvas = Canvas(canvasBitmap)
        val brushBitmap = getBitmapFromTool()
        canvas.drawBitmap(brushBitmap, 0f, 0f, null)
    }

    private fun getBitmapFromTool(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        brushView.draw(canvas)
        return bitmap
    }

    fun setStrokeWidth(value: Float) {
        brushView.setStrokeWidth(value)
    }

    fun setColor(color: Int) {
        brushView.setColor(color)
    }
}
