package woowacourse.paint

interface OnClickPaletteListener {
    fun onClickChangePaintButton()

    fun onClickChangeThicknessButton()

    fun onClickPaint(color: Int)
}
