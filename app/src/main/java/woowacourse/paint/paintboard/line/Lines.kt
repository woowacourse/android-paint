package woowacourse.paint.paintboard.line

import android.graphics.Paint
import android.graphics.Path

class Lines(initValue: List<Line> = emptyList()) {
    private val lines = mutableListOf<Line>().apply { addAll(initValue) }
    private val currentLine: Line? get() = lines.lastOrNull()

    fun startLine(x: Float, y: Float, paint: Paint) {
        val path = Path().apply { moveTo(x, y) }
        lines.add(Line(path, paint))
    }

    fun drawingLine(x: Float, y: Float) {
        currentLine?.path?.lineTo(x, y)
    }

    fun finishLine(x: Float, y: Float) {
        drawingLine(x, y)
    }

    fun revert() {
        lines.removeLastOrNull()
    }

    fun forEach(action: (Line) -> Unit) {
        lines.forEach {
            action(it)
        }
    }
}
