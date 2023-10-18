package woowacourse.paint.customView.container

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.view.MotionEvent
import woowacourse.paint.customView.PaintInfo
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content
import woowacourse.paint.customView.content.circle.Circle
import woowacourse.paint.customView.content.circle.MendelCircle
import woowacourse.paint.customView.content.eraser.Eraser
import woowacourse.paint.customView.content.rectangle.Rectangle
import woowacourse.paint.customView.content.stroke.Stroke

class ContentContainer(
    private val _drawnContents: MutableList<Content> = mutableListOf(),
) {
    var brushType: BrushType = BrushType.Stroke
    val paintInfo = PaintInfo()

    private val redoAbleContents: MutableList<Content> = mutableListOf()

    fun updateContent(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            _drawnContents.add(createContent(PointF(event.x, event.y)))
            redoAbleContents.clear() // undo를 한 상태에서 다른 그림이 추가되면, redo를 할 수 있는 것들이 모두 지워진다.
        }
        _drawnContents.lastOrNull()?.action(event)
    }

    private fun createContent(point: PointF): Content {
        val id = System.currentTimeMillis()
        val paint = paintInfo.getPaint(brushType)
        return when (brushType) {
            BrushType.Stroke -> Stroke(id, Path(), paint)
            BrushType.Eraser -> Eraser(id, Path(), paint)
            BrushType.Rectangle -> Rectangle(id, RectF(point.x, point.y, point.x, point.y), paint)
            BrushType.Circle -> Circle(id, MendelCircle(point), paint)
        }
    }

    fun drawOnCanvas(canvas: Canvas) {
        _drawnContents.forEach { it.draw(canvas) }
    }

    fun getDrawnContents(): List<Content> {
        return _drawnContents.map { it.deepCopy() }
    }

    fun getRedoAbleContents(): List<Content> {
        return redoAbleContents.map { it.deepCopy() }
    }

    fun changeDrawnContents(contents: List<Content>) {
        _drawnContents.clear()
        _drawnContents.addAll(contents)
    }

    fun changeRedoAbleContents(contents: List<Content>) {
        redoAbleContents.clear()
        redoAbleContents.addAll(contents)
    }

    fun undo(): Boolean {
        if (_drawnContents.isEmpty()) return false
        redoAbleContents.add(_drawnContents.removeLast())
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
