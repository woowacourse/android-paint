package woowacourse.paint

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter("registerSliderChangeListener")
fun Slider.setRangeSliderOnChange(onChange: (Float) -> Unit) {
    this.addOnChangeListener { _, value, _ ->
        onChange(value)
    }
}
