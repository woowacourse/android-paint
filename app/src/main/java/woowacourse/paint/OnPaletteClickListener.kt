package woowacourse.paint

interface OnPaletteClickListener {
    fun onClickChangePaintButton()

    fun onClickChangeThicknessButton()

    fun onClickPaint(color: Int)
}
