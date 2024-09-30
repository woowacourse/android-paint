package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paths = mutableListOf<Pair<Path, Paint>>()

    private var currentPath: Path = Path()
    private var currentPaint: Paint = Paint()

    private var ovalSize: Int = OVAL_SIZE

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        initPaint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for ((path, paint) in paths) {
            canvas.drawPath(path, paint)
        }

        canvas.drawPath(currentPath, currentPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath =
                    Path().apply {
                        addOval(
                            pointX,
                            pointY,
                            pointX + ovalSize,
                            pointY + ovalSize,
                            Path.Direction.CW,
                        )
                    }

                paths.add(Pair(currentPath, Paint(currentPaint)))
            }

            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_UP -> {
                paths.add(Pair(currentPath, Paint(currentPaint)))
                currentPath = Path()
            }
        }

        invalidate()
        return true
    }

    private fun initPaint() {
        currentPaint =
            Paint().apply {
                color = Color.RED
            }
    }

    fun setPaintColor(color: Int) {
        currentPaint.color = color
    }

    companion object {
        private const val OVAL_SIZE = 50
    }
}
