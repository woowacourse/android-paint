package woowacourse.paint.model.brush

import android.graphics.Paint

class BrushPaint : Paint() {
    fun setPenBrush(beforePaint: Paint) = this.apply {
        set(beforePaint)
        style = Style.STROKE
        strokeCap = Cap.ROUND
        strokeJoin = Join.ROUND
    }

    fun setFigureBrush(beforePaint: Paint) = this.apply {
        set(beforePaint)
        style = Style.FILL
    }
}
