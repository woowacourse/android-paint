package woowacourse.paint.view

import android.content.Context
import com.google.android.material.slider.RangeSlider

fun rangeSlider(
    context: Context,
    block: (value: Float) -> Unit,
): RangeSlider = RangeSlider(context).also { view ->
    view.addOnChangeListener { _, value, _ -> block(value) }
}
