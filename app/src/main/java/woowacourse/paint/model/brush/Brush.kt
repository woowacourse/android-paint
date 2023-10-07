package woowacourse.paint.model.brush

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import woowacourse.paint.model.brush.Circle.drawPreview
import woowacourse.paint.model.brush.Circle.setCurrentPosition
import woowacourse.paint.model.brush.Eraser.erase
import woowacourse.paint.model.brush.Pen.drawLine
import woowacourse.paint.model.brush.Pen.startDraw
import woowacourse.paint.model.brush.Rectangle.drawPreview
import woowacourse.paint.model.brush.Rectangle.setCurrentPosition

sealed class Brush : BrushSetting {

    fun onActionDown(xCursor: Float, yCursor: Float) {
        when (this) {
            is Pen -> {
                startDraw(xCursor, yCursor)
            }

            is Rectangle -> {
                setCurrentPosition(xCursor, yCursor)
            }

            is Circle -> {
                setCurrentPosition(xCursor, yCursor)
            }

            is Eraser -> {
                erase(xCursor, yCursor)
            }
        }
    }

    fun onActionMove(xCursor: Float, yCursor: Float) {
        when (this) {
            is Pen -> {
                drawLine(xCursor, yCursor)
            }

            is Rectangle -> {
                drawPreview(xCursor, yCursor)
            }

            is Circle -> {
                drawPreview(xCursor, yCursor)
            }

            is Eraser -> {}
        }
    }

    fun onActionUp(xCursor: Float, yCursor: Float) {
        when (this) {
            is Pen -> Unit

            is Rectangle -> {
                Rectangle.draw(xCursor, yCursor)
            }

            is Circle -> {
                Circle.draw(xCursor, yCursor)
            }

            is Eraser -> {}
        }
    }

    companion object {
        val paintInstance = Paint().apply {
            color = Color.RED
        }
        val previousDraw: MutableList<Pair<Path, Paint>> = mutableListOf()
        var previewDraw: Pair<Path, Paint> = Pair(Path(), Paint())
    }
}
