package woowacourse.paint.drawingboard

object Drawings {
    private val _drawings = mutableListOf<Drawing>()
    val drawings get() = _drawings.toList()

    fun addNewDrawing(newDrawing: Drawing) {
        _drawings.add(newDrawing)
    }

    fun removeDrawing(index: Int) {
        _drawings.removeAt(index)
    }
}
