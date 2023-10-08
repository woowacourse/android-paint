package woowacourse.paint.main

import woowacourse.paint.customview.paint.BrushColor

interface ColorClickListener {
    fun onColorClick(brushColor: BrushColor)
}
