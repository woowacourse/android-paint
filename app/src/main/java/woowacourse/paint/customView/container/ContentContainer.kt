package woowacourse.paint.customView.container

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.view.MotionEvent
import woowacourse.paint.customView.PaintInfo
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Circle
import woowacourse.paint.customView.content.Content
import woowacourse.paint.customView.content.Eraser
import woowacourse.paint.customView.content.Rectangle
import woowacourse.paint.customView.content.Stroke

class ContentContainer(
    private val _drawnContents: MutableList<Content> = mutableListOf(),
) {
    var brushType: BrushType = BrushType.Stroke
    val paintInfo = PaintInfo()

    private val redoAbleContents: MutableList<Content> = mutableListOf()

    fun updateContent(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val drawingContent = createContent()
                drawingContent.action(event)
                _drawnContents.add(drawingContent)
                redoAbleContents.clear() // undo를 한 상태에서 뒤로가기를 누르면, redo를 할 수 있는 것들이 모두 지워진다.
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
        val id = System.currentTimeMillis()
        val paint = paintInfo.getPaint(brushType)
        return when (brushType) {
            BrushType.Stroke -> Stroke(id, Path(), paint)
            BrushType.Eraser -> Eraser(id, Path(), paint)
            BrushType.Rectangle -> Rectangle(id, RectF(), paint)
            BrushType.Circle -> Circle(id, PointF(), 0f, paint)
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

    fun undo(): Boolean {
        if (_drawnContents.isEmpty()) return false
        val redoContent = _drawnContents.removeLast()
        redoAbleContents.add(redoContent)
        return true
    }

    fun redo(): Boolean {
        if (redoAbleContents.isEmpty()) return false
        _drawnContents.add(redoAbleContents.removeLast())
        return true
    }

    fun clear(): Boolean {
        if (_drawnContents.isEmpty()) return false
        _drawnContents.clear()
        redoAbleContents.clear()
        return true
    }
}
