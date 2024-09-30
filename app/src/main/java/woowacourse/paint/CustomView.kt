package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class CustomView(context: Context, attrs: AttributeSet) :
    View(context, attrs) {

    private val pathAttributes: MutableMap<Path, Paint> = mutableMapOf()
    private var path: Path = Path()
    private val paint = Paint()

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        setupPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((path, paint) in pathAttributes) {
            canvas.drawPath(path, paint)
            Log.d("CustomView", "onDraw: ${path} ${paint.color}")
        }
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path = addOval(pointX, pointY)
            }
            MotionEvent.ACTION_MOVE ->
                addPath(
                    path, pointX, pointY
                )

            MotionEvent.ACTION_UP -> {
                pathAttributes[path] = newPaint()
            }

            else -> super.onTouchEvent(event)
        }
        invalidate()
        return true
    }

    private fun addOval(pointX: Float, pointY: Float): Path {
        val path = Path()
        path.addOval(
            pointX,
            pointY,
            pointX,
            pointY,
            Path.Direction.CW
        )
        return path
    }

    private fun addPath(
        path: Path, srcX: Float, srcY: Float,
    ) {
        path.lineTo(srcX, srcY)
    }


    private fun setupPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE;
        paint.strokeWidth = 30f;
        paint.isAntiAlias = true;
        paint.isDither = true;
        paint.strokeJoin = Paint.Join.ROUND;
        paint.strokeCap = Paint.Cap.ROUND;
    }

    private fun newPaint(): Paint {
        val newPaint = Paint()
        newPaint.color = paint.color
        newPaint.strokeWidth = paint.strokeWidth

        newPaint.style = Paint.Style.STROKE;
        newPaint.isAntiAlias = true;
        newPaint.isDither = true;
        newPaint.strokeJoin = Paint.Join.ROUND;
        newPaint.strokeCap = Paint.Cap.ROUND;
        return newPaint
    }

    fun setColor(color: Int) {
        paint.color = color
    }

    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
    }
}
