package woowacourse.paint

import android.util.Log
import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter("rangeSliderOnChange")
fun Slider.setRangeSliderOnChange(onChange: (Float) -> Unit) {
    this.addOnChangeListener { _, value, _ ->
        Log.d("mendel!", "$value")
        onChange(value)
    }
}
