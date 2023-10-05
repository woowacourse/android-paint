package woowacourse.paint.ui.main

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter("registerSliderChangeListener")
fun Slider.setRangeSliderOnChange(onValueChange: (Float) -> Unit) {
    this.addOnChangeListener { _, value, _ ->
        onValueChange(value)
    }
}
