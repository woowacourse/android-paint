package woowacourse.paint

import androidx.databinding.BindingAdapter

@BindingAdapter("app:setColorPalette")
fun BoardView.setColorPalette(colorPalette: ColorPalette?) {
    if (colorPalette == null) return
    changeColor(colorPalette)
}
