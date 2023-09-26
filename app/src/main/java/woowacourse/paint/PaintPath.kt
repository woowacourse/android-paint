package woowacourse.paint

import android.graphics.Paint
import android.graphics.Path

class PaintPath(data: List<Pair<Path, Paint>> = emptyList()) {
    private val _data: MutableList<Pair<Path, Paint>> = data.toMutableList()
    val data: List<Pair<Path, Paint>>
        get() = _data

    fun add(path: Pair<Path, Paint>) {
        _data.add(path)
    }
}
