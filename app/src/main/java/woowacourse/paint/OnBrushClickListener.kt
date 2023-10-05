package woowacourse.paint

import woowacourse.paint.model.PaintBrush

interface OnBrushClickListener {
    fun onBrushClick(paintBrush: PaintBrush)
}
