package woowacourse.paint.presentation.bindingAdapter

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.RangeSlider

@BindingAdapter("app:onValueChange")
fun RangeSlider.setOnValueChangeListener(listener: RangeSlider.OnChangeListener) {
    addOnChangeListener(listener)
}
