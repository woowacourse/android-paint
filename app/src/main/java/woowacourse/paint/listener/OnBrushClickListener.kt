package woowacourse.paint.listener

import woowacourse.paint.model.PaintBrush

fun interface OnBrushClickListener {
    fun onBrushClick(paintBrush: PaintBrush)
}
