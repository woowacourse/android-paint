package woowacourse.paint.view

import android.content.Context
import com.google.android.material.slider.RangeSlider

fun rangeSlider(
    context: Context,
    valueFrom: Float = 0.1F,
    valueTo: Float = 1.0F,
    block: (value: Float) -> Unit,
): RangeSlider = RangeSlider(context).also { view ->
    view.valueFrom = valueFrom
    view.valueTo = valueTo
    view.setValues(valueFrom)
    view.addOnChangeListener { _, value, _ -> block(value) }
}
