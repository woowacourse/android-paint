package woowacourse.paint.model

import android.graphics.Path
import android.graphics.RectF

class Strokes(value: MutableList<Stroke> = mutableListOf()) {
    private val _value: MutableList<Stroke> = value
    val value: List<Stroke>
        get() = _value

    fun add(stroke: Stroke) {
        _value.add(stroke)
    }

    fun removeAt(index: Int) {
        _value.removeAt(index)
    }

    fun clear() {
        _value.clear()
    }

    fun removeIntersectingStrokes(eraserPath: Path) {
        val strokesToRemove = mutableListOf<Int>()
        value.forEachIndexed { index, stroke ->
            if (isPathIntersecting(
                    stroke.path,
                    eraserPath
                )
            ) {
                strokesToRemove.add(index)
            }
        }

        strokesToRemove.asReversed().forEach { index ->
            _value.removeAt(index)
        }
    }

    private fun isPathIntersecting(path1: Path, path2: Path): Boolean {
        val intersectionPath = Path()
        intersectionPath.op(path1, path2, Path.Op.INTERSECT)
        return !intersectionPath.isEmpty
    }
}
