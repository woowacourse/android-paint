package woowacourse.paint.listener

import woowacourse.paint.model.ColorBox

fun interface OnColorClickListener {
    fun onColorClick(colorBox: ColorBox)
}
