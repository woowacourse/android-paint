package woowacourse.paint.main

import woowacourse.paint.customview.BrushColor

interface ColorClickListener {
    fun onColorClick(brushColor: BrushColor)
}
