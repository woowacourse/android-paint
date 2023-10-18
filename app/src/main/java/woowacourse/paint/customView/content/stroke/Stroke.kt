package woowacourse.paint.customView.content.stroke

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content

class Stroke(
    override val id: Long,
    private val path: Path,
    private val paint: Paint,
) : Content() {
    override val brushType: BrushType = BrushType.Stroke
    override fun deepCopy(): Content {
        return Stroke(id, Path(path), Paint(paint))
    }

    override fun action(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
            }

            MotionEvent.ACTION_UP -> {
                path.lineTo(event.x, event.y)
            }
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
