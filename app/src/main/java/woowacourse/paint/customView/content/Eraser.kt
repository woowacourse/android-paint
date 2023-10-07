package woowacourse.paint.customView.content

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent

class Eraser(
    override val id: Long,
    private val path: Path,
    private val paint: Paint,
) : Content() {
    override val brushType: BrushType = BrushType.Eraser

    override fun deepCopy(): Content {
        return Eraser(id, Path(path), Paint(paint))
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
