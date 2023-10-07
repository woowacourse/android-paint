package woowacourse.paint.model.brush

import android.graphics.Paint
import android.graphics.Path

object Rectangle : Brush() {
    private var beforePosition = Pair(0f, 0f)
    override fun setStyle() {
        paintInstance.style = Paint.Style.FILL
    }

    fun setCurrentPosition(xCursor: Float, yCursor: Float) {
        beforePosition = Pair(xCursor, yCursor)
    }

    fun drawPreview(x: Float, y: Float) {
        previewDraw = Pair(
            Path().apply {
                addRect(
                    beforePosition.first,
                    beforePosition.second,
                    x,
                    y,
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
                    addRect(
                        beforePosition.first,
                        beforePosition.second,
                        xCursor,
                        yCursor,
                        Path.Direction.CCW,
                    )
                },
                Paint().apply { set(paintInstance) },
            ),
        )
    }
}
