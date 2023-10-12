package woowacourse.paint.listener

import woowacourse.paint.model.BrushBox

fun interface OnBrushClickListener {
    fun onBrushClick(paintBrush: BrushBox)
}
