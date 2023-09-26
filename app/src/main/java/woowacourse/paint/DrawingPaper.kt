package woowacourse.paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingPaper(
    private val context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    // 너비 및 색상 기본값 지정, 기본 Path, Paint 객체 추가
    private val pathRecords: MutableList<Path> = mutableListOf(Path())
    private val paintRecords: MutableList<Paint> = mutableListOf(Paint())
    private var brushWidth = DEFAULT_WIDTH
    private var brushColor = DEFAULT_COLOR

    // RangeSlider로부터 너비를 지정하며, 새로운 Path, Paint 객체 추가
    fun changeWidth(width: Float) {
        val newPath = Path()
        val newPaint = Paint()

        pathRecords.add(newPath)
        paintRecords.add(newPaint)
        brushWidth = width
    }

    // 색깔 RecyclerView로부터 색상을 지정하며, 새로운 Path, Paint 객체 추가
    fun changeColor(color: Int) {
        val newPath = Path()
        val newPaint = Paint()

        pathRecords.add(newPath)
        paintRecords.add(newPaint)
        brushColor = color
    }

    // 가장 최신 Paint의 너비 및 색상 지정
    // 이전 Path와 Paint를 순회하며, 이전 기록들을 다시 그림
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paintRecords.last().strokeWidth = brushWidth
        paintRecords.last().color = context.getColor(brushColor)

        for (record in pathRecords.indices) {
            val path = pathRecords[record]
            val paint = paintRecords[record].setBrush()

            canvas.drawPath(path, paint)
        }
    }

    // 누를 때 점이 찍히며, 드래그 시 선이 그려짐
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> pathRecords.last().addDot(event)
            MotionEvent.ACTION_MOVE -> pathRecords.last().lineTo(event.x, event.y)
            else -> super.onTouchEvent(event)
        }

        invalidate()
        return true
    }

    // 점과 선들 모두 각이 없으며 부드럽고 원만한 선을 그리도록 세팅
    private fun Paint.setBrush(): Paint = apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        strokeMiter = SOFT_ANGLE
        isAntiAlias = true
    }

    private fun Path.addDot(event: MotionEvent) {
        moveTo(event.x, event.y)
        lineTo(event.x, event.y)
    }

    companion object {
        @SuppressLint("NonConstantResourceId")
        private const val DEFAULT_COLOR = R.color.black
        private const val DEFAULT_WIDTH = 0f
        private const val SOFT_ANGLE = 0f
    }
}