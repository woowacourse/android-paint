package woowacourse.paint.customView.container

import android.graphics.Canvas
import android.graphics.Path
import android.view.MotionEvent
import woowacourse.paint.customView.PaintInfo
import woowacourse.paint.customView.content.Content
import woowacourse.paint.customView.content.ContentType
import woowacourse.paint.customView.content.Stroke

class ContentContainer(
    private val _drawnContents: MutableList<Content> = mutableListOf(),
) {
    var contentType: ContentType = ContentType.Stroke
    val paintInfo = PaintInfo()

    fun updateContent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val drawingContent = createContent()
                drawingContent.action(event)
                _drawnContents.add(drawingContent)
            }

            MotionEvent.ACTION_MOVE -> {
                _drawnContents.lastOrNull()?.action(event)
            }

            MotionEvent.ACTION_UP -> {
                _drawnContents.lastOrNull()?.action(event)
            }
        }
    }

    private fun createContent(): Content {
        return when (contentType) {
            ContentType.Stroke -> Stroke(Path(), paintInfo.getPaint(contentType))
        }
    }

    fun drawOnCanvas(canvas: Canvas) {
        _drawnContents.forEach { it.draw(canvas) }
    }

    fun getDrawnContents(): List<Content> {
        return _drawnContents.map { it.deepCopy() }
    }

    fun changeDrawnContents(contents: List<Content>) {
        _drawnContents.clear()
        _drawnContents.addAll(contents)
    }

    private fun removeLast() {
        _drawnContents.removeLast()
    }
}
