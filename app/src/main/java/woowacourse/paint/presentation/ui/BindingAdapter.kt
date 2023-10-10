package woowacourse.paint.presentation.ui

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import woowacourse.paint.R
import woowacourse.paint.domain.model.BrushType

@BindingAdapter("setColor")
fun View.setColor(color: Int) {
    this.setBackgroundColor(color)
}

@BindingAdapter("setBrushTypeText")
fun TextView.setBrushTypeText(brushType: BrushType) {
    val resId = when (brushType) {
        BrushType.LINE -> R.string.brush_type_pen
        BrushType.RECTANGLE -> R.string.brush_type_rectangle
        BrushType.CIRCLE -> R.string.brush_type_circle
        BrushType.ERASER -> R.string.brush_type_eraser
    }
    setText(resId)
}
