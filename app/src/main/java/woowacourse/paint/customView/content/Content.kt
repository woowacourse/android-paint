package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.view.MotionEvent

abstract class Content {
    abstract val id: Long
    abstract val brushType: BrushType
    abstract fun deepCopy(): Content
    abstract fun action(event: MotionEvent)

    abstract fun draw(canvas: Canvas)

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other is Content) return id == other.id
        return false
    }

    override fun hashCode(): Int {
        return (id xor (id ushr 32)).toInt()
    }
}
