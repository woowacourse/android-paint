package woowacourse.paint

import android.graphics.Canvas

class Paintings(initPaintings: List<Painting> = emptyList()) {

    constructor(
        initPresentPainting: Painting,
    ) : this(initPaintings = mutableListOf(initPresentPainting))

    private val paintings: MutableList<Painting> = initPaintings.toMutableList()

    val presentPainting: Painting?
        get() = paintings.lastOrNull()

    fun drawOnCanvas(canvas: Canvas) {
        paintings.forEach { painting -> painting.drawOnCanvas(canvas) }
    }

    fun startNewPainting() {
        paintings.add(presentPainting?.getInitializedPathPainting() ?: return)
    }

    fun undoPainting() {
        if (paintings.size == EMPTY_PREVIOUS_PAINTING_SIZE) {
            return
        } else {
            paintings.removeAt(paintings.size - LATEST_PAINTING_MINUS_NUMBER)
        }
    }

    companion object {
        private const val EMPTY_PREVIOUS_PAINTING_SIZE = 1
        private const val LATEST_PAINTING_MINUS_NUMBER = 2
    }
}
