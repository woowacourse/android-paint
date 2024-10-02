package woowacourse.paint.view.listener

import woowacourse.paint.model.Brush

interface BrushChangeListener {
    fun onBrushChanged(brush: Brush)
}
