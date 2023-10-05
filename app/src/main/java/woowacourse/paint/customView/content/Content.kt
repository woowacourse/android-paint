package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.view.MotionEvent

interface Content {
    val brushType: BrushType
    fun deepCopy(): Content
    fun action(event: MotionEvent)

    fun draw(canvas: Canvas)
}
