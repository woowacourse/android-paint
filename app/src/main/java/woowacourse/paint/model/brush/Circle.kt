package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

object Circle : Brush() {
    private var beforePosition = Pair(0f, 0f)
    override fun setStyle() {
        paintInstance.style = Paint.Style.FILL
    }

    fun setCurrentPosition(xCursor: Float, yCursor: Float) {
        beforePosition = Pair(xCursor, yCursor)
    }

    fun drawPreview(xCursor: Float, yCursor: Float) {
        previewDraw = Pair(
            Path().apply {
                addCircle(
                    beforePosition.first + ((xCursor - beforePosition.first) / 2),
                    beforePosition.second + ((xCursor - beforePosition.first) / 2),
                    (xCursor - beforePosition.first) / 2,
                    Path.Direction.CW,
                )
            },
            Paint().apply { set(paintInstance) },
        )
    }

    fun draw(xCursor: Float, yCursor: Float) {
        previousDraw.add(
            Pair(
                Path().apply {
                    addCircle(
                        beforePosition.first + ((xCursor - beforePosition.first) / 2),
                        beforePosition.second + ((xCursor - beforePosition.first) / 2),
                        (xCursor - beforePosition.first) / 2,
                        Path.Direction.CW,
                    )
                },
                Paint().apply { set(paintInstance) },
            ),
        )
    }
}
