package woowacourse.paint.paintboard.drawables

import android.graphics.Canvas
import android.view.MotionEvent
import woowacourse.paint.entity.Point

interface BrushDrawable {
    fun action(event: MotionEvent)
    fun draw(canvas: Canvas)
    fun contain(point: Point): Boolean
}
