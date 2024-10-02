package woowacourse.paint.util

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.R
import woowacourse.paint.model.DrawingMode
import woowacourse.paint.uimodel.DrawingModeUiModel

@BindingAdapter("colorIsChecked")
fun ImageView.bindPaintColorIsChecked(isChecked: Boolean) {
    if (isChecked) {
        setImageResource(R.drawable.ic_check_white)
    } else {
        setImageResource(0)
    }
}

@BindingAdapter("colorBackgroundTint")
fun ImageView.bindColorBackgroundTint(
    @ColorRes colorRes: Int,
) {
    val color = ContextCompat.getColor(context, colorRes)
    backgroundTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("drawingMode")
fun TextView.bindDrawingMode(drawingMode: DrawingModeUiModel) {
    val drawable = ContextCompat.getDrawable(context, drawingMode.drawingMode.drawableRes)
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)

    val string = ContextCompat.getString(context, drawingMode.drawingMode.stringRes)
    text = string

    val textColorRes = if (drawingMode.isChecked) R.color.teal_200 else R.color.black
    val textColor = ContextCompat.getColor(context, textColorRes)
    setTextColor(textColor)

    val drawableTintRes = if (drawingMode.isChecked) R.color.teal_200 else R.color.black
    val drawableTint = ColorStateList.valueOf(ContextCompat.getColor(context, drawableTintRes))
    compoundDrawableTintList = drawableTint
}

@BindingAdapter("thicknessVisibility")
fun RangeSlider.bindThicknessVisibility(drawingMode: DrawingMode) {
    isVisible = when (drawingMode) {
        DrawingMode.PEN, DrawingMode.ERASER -> true
        else -> false
    }
}

@BindingAdapter("paintColorVisibility")
fun RecyclerView.bindPaintColorVisibility(drawingMode: DrawingMode) {
    isVisible = when (drawingMode) {
        DrawingMode.PEN, DrawingMode.SQUARE, DrawingMode.CIRCLE -> true
        else -> false
    }
}