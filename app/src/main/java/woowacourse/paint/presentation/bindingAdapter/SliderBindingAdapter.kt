package woowacourse.paint.presentation.bindingAdapter

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter("app:onValueChange")
fun Slider.setOnValueChangeListener(listener: SliderValueChangeListener) {
    addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
        override fun onStartTrackingTouch(slider: Slider) {
        }

        override fun onStopTrackingTouch(slider: Slider) {
            listener.onValueChange(slider.value)
        }
    })
}

interface SliderValueChangeListener {
    fun onValueChange(value: Float)
}
