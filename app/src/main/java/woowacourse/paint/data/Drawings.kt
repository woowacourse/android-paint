package woowacourse.paint.data

import woowacourse.paint.model.Drawing

object Drawings {
    private val _drawings: MutableList<Drawing> = mutableListOf()
    val drawing: List<Drawing>
        get() = _drawings

    fun addNewDrawing(newDrawing: Drawing) {
        _drawings.add(newDrawing)
    }
}
