package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.view.MotionEvent

interface Content {
    val id: Int
    val type: ContentType
    fun deepCopy(): Content
    fun action(event: MotionEvent)

    fun draw(canvas: Canvas)
}
