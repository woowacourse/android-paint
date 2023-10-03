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

    // 나중에 되돌리기 구현할 때 사용
    fun removeLast() {
        paintings.removeLastOrNull()
    }

    // 나중에 전체 지우기 사용할 때 사용
    fun reset() {
        paintings.clear()
    }
}
