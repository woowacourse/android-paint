package woowacourse.paint.model.shape

import android.graphics.Canvas

sealed interface Shape {

    fun draw(canvas: Canvas)

    fun move(pointX: Float, pointY: Float)
}
