package woowacourse.paint.drawing

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

sealed interface Drawing2 {
    fun setUpDefaultPaint()

    // 캔버스에 전체를 모두 그릴 때
    fun drawOn(canvas: Canvas)

    // 다음 윤곽선의 시작을 점(x,y)으로 설정합니다.
    fun setStartPoint(x: Float, y: Float)

    // 마지막 점의 선을 지정된 점(x,y)에 추가합니다.
    fun pathLineTo(x: Float, y: Float)

    fun copyWithPaint(thickness: Float): Drawing2

    fun copyWithPaint(color: Int): Drawing2

    fun copy(rect: RectF): Drawing2

    companion object {
        val DEFAULT_BRUSH_STYLE = Paint.Style.STROKE
        val DEFAULT_BRUSH_CAP = Paint.Cap.ROUND
    }
}
