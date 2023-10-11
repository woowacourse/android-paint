package woowacourse.paint

import android.graphics.Canvas
import woowacourse.paint.painting.Circle
import woowacourse.paint.painting.Eraser
import woowacourse.paint.painting.Line
import woowacourse.paint.painting.Painting
import woowacourse.paint.painting.PaintingType
import woowacourse.paint.painting.PaintingType.CIRCLE
import woowacourse.paint.painting.PaintingType.ERASER
import woowacourse.paint.painting.PaintingType.LINE
import woowacourse.paint.painting.PaintingType.RECTANGLE
import woowacourse.paint.painting.Rectangle

class Drawer(initPaintings: List<Painting>) {

    constructor(
        initPresentPainting: Painting,
    ) : this(initPaintings = mutableListOf(initPresentPainting))

    init {
        check(initPaintings.isNotEmpty()) { "Draower 는 최소 하나 이상의 Painting을 가지고 있어야 합니다" }
    }

    private val paintings: MutableList<Painting> = initPaintings.toMutableList()

    private val lastIndex: Int
        get() = paintings.size - 1

    val presentPainting: Painting
        get() = paintings.last()

    fun drawOnCanvas(canvas: Canvas) {
        paintings.forEach { painting -> painting.drawOnCanvas(canvas) }
    }

    fun startNewPainting() {
        paintings.add(presentPainting.getInitializedPathPainting())
    }

    fun setPaintingType(paintingType: PaintingType) {
       paintings[lastIndex] = when (paintingType) {
            ERASER -> Eraser(
                paintColor = presentPainting.paint.color,
                paintWidth = presentPainting.paint.strokeWidth,
            )

            LINE -> Line(
                paintColor = presentPainting.paint.color,
                paintWidth = presentPainting.paint.strokeWidth,
            )

            RECTANGLE -> Rectangle(
                paintColor = presentPainting.paint.color,
                paintWidth = presentPainting.paint.strokeWidth,
            )

            CIRCLE -> Circle(
                paintColor = presentPainting.paint.color,
                paintWidth = presentPainting.paint.strokeWidth,
            )
        }

    fun removePreviousPainting() {
        if (paintings.size == EMPTY_PREVIOUS_PAINTING_SIZE) {
            return
        } else {
            paintings.removeAt(paintings.size - LATEST_PAINTING_Position)
        }
    }

    companion object {
        private const val EMPTY_PREVIOUS_PAINTING_SIZE = 1
        private const val LATEST_PAINTING_Position = 2
    }
}
