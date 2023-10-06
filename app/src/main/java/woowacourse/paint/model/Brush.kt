package woowacourse.paint.model

import android.graphics.Canvas

interface Brush {

    fun start(x: Float, y: Float, onCommit: () -> Unit = {})

    fun move(x: Float, y: Float, onCommit: () -> Unit = {})

    fun drawOn(canvas: Canvas)
}
