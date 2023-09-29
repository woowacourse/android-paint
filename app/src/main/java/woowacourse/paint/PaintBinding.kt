package woowacourse.paint

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.RangeSlider

@BindingAdapter("app:setColorPalette")
fun BoardView.setColorPalette(colorPalette: ColorPalette?) {
    if (colorPalette == null) return
    changeColor(colorPalette)
}

@BindingAdapter("app:setWidth")
fun BoardView.setWidth(width: Float?) {
    if (width == null) return
    setWidth(width)
}

@BindingAdapter("app:eraseAll")
fun BoardView.eraseAll(isErasing: Boolean?) {
    if (isErasing == null) return
    if (isErasing) erase()
}

@BindingAdapter("app:setupSlider")
fun RangeSlider.setupSlider(viewModel: MainViewModel) {
    this.valueFrom = MIN_WIDTH_VALUE
    this.valueTo = MAX_WIDTH_VALUE

    this.addOnChangeListener(
        RangeSlider.OnChangeListener { _, value, _ ->
            viewModel.changeWidth(value)
        },
    )
}

private const val MIN_WIDTH_VALUE = 0.0f
private const val MAX_WIDTH_VALUE = 100.0f
