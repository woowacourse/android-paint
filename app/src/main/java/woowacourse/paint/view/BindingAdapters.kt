package woowacourse.paint.view

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.slider.Slider

@InverseBindingAdapter(attribute = "value")
fun getSliderValue(slider: Slider): Float {
    return slider.value
}

@BindingAdapter("app:value")
fun setSliderValue(
    slider: Slider,
    value: Float,
) {
    if (slider.value != value) {
        slider.value = value
    }
}

@BindingAdapter("app:valueAttrChanged")
fun setSliderListeners(
    slider: Slider,
    attrChange: InverseBindingListener,
) {
    slider.addOnChangeListener { _, _, _ ->
        attrChange.onChange()
    }
}

@BindingAdapter("lineColor")
fun setLineColor(
    view: CanvasView,
    color: Int,
) {
    view.setLineColor(color)
}

@BindingAdapter("lineWidth")
fun setLineWidth(
    view: CanvasView,
    width: Float,
) {
    view.setLineWidth(width)
}
