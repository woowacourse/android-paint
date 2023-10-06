package woowacourse.paint.model

import android.graphics.Canvas
import android.graphics.Paint

interface Brush {
    val paint: Paint

    fun start(x: Float, y: Float, onCommit: () -> Unit = {})

    fun move(x: Float, y: Float, onCommit: () -> Unit = {})

    fun drawOn(canvas: Canvas)
}
