package woowacourse.paint

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider
import woowacourse.paint.model.BrushType
import woowacourse.paint.model.ColorPalette

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

@BindingAdapter("app:setBrush")
fun BoardView.setBrush(brushType: BrushType?) {
    if (brushType == null) return
    setBrush(brushType)
}

@BindingAdapter("app:eraseAll")
fun BoardView.eraseAll(isErasing: Boolean?) {
    if (isErasing == null) return
    if (isErasing) erase()
}

interface OnSlideListener {
    fun changeWidth(width: Float)
}

@BindingAdapter("app:setupSlider")
fun Slider.setupSlider(onSlideListener: OnSlideListener) {
    this.addOnChangeListener { _, value, _ ->
        onSlideListener.changeWidth(value)
    }
}
