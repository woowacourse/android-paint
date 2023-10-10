package woowacourse.paint.ui.main

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter("registerSliderOnTouchListener")
fun Slider.setSliderOnTouchChange(onValueChange: (Float) -> Unit) {
    this.addOnSliderTouchListener(object :
        Slider.OnSliderTouchListener {
        override fun onStartTrackingTouch(slider: Slider) {}

        override fun onStopTrackingTouch(slider: Slider) {
            onValueChange(value)
        }
    })
}
