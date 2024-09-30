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

class DrawingBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val path = Path()
    val paint = Paint()
    var brushThickness: Float = 5f

    init {
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE  // 선으로 그리기 위해 STROKE 설정
            strokeWidth = brushThickness
            strokeCap = Paint.Cap.ROUND  // 선 끝을 둥글게 처리
            isAntiAlias = true  // 선을 부드럽게 처리
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)  // 새로운 경로 시작
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(pointX, pointY)  // 이전 점에서 현재 점까지 선을 그림
            }
            MotionEvent.ACTION_UP -> {

            }
            else -> return false
        }
        invalidate()
        return true
    }
}

private const val TAG = "DrawingBoardView"