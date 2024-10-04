package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

data class Rectangle(
    val vertexes: Vertexes,
    val color: Int,
    val strokeWidth: Float,
) : Sketch() {
    private val startX = vertexes.startX
    private val startY = vertexes.startY
    private val endX = vertexes.endX
    private val endY = vertexes.endY

    private val paint = Paint().apply {
        color = this@Rectangle.color
        style = Paint.Style.STROKE
        strokeWidth = this@Rectangle.strokeWidth
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    val rectF = RectF(
        startX.coerceAtMost(endX),
        startY.coerceAtMost(endY),
        startX.coerceAtLeast(endX),
        startY.coerceAtLeast(endY)
    )

    override fun draw(canvas: Canvas) {
        val originalColor = paint.color
        val originalStrokeWidth = paint.strokeWidth

        // 사각형에 맞게 페인트 설정
        paint.color = color
        paint.strokeWidth = strokeWidth
        paint.style = Paint.Style.STROKE

        // 사각형 그리기
        canvas.drawRect(rectF, paint)

        // 기존 페인트 속성 복원
        paint.color = originalColor
        paint.strokeWidth = originalStrokeWidth
    }
}