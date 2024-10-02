package woowacourse.paint

interface OnPaletteClickListener {
    fun onClickChangePaintButton()

    fun onClickChangeThicknessButton()

    fun onClickChangeBrushButton()

    fun onClickPaint(color: Int)

    fun onClickBrushChange(brushName: String)
}
