package woowacourse.paint

import android.graphics.Canvas

class Paintings(private val value: MutableList<Painting> = mutableListOf()) {
    constructor(
        initPresentPainting: Painting,
    ) : this(value = mutableListOf(initPresentPainting))

    val presentPainting: Painting?
        get() = value.lastOrNull()

    fun drawOnCanvas(canvas: Canvas) {
        value.forEach { painting -> painting.drawOnCanvas(canvas) }
    }

    fun startNewPainting() {
        value.add(presentPainting?.getInitializedPathPainting() ?: return)
    }

    // 나중에 되돌리기 구현할 때 사용
    fun removeLast() {
        value.removeLastOrNull()
    }

    // 나중에 전체 지우기 사용할 때 사용
    fun reset() {
        value.clear()
    }
}
