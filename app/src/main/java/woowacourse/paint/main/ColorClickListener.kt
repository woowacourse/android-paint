package woowacourse.paint.main

import woowacourse.paint.main.model.BrushColorBox

interface ColorClickListener {
    fun onColorClick(clickedBrushColorBox: BrushColorBox)
}
