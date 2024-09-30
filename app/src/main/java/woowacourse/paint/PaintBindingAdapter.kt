package woowacourse.paint

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("colorRes", "checkedColorRes")
fun ImageView.bindPaintColorChecked(
    colorRes: Int,
    checkedColorRes: Int,
) {
    if (colorRes == checkedColorRes) {
        setImageResource(R.drawable.ic_check_white)
    } else {
        setImageResource(0)
    }
}

@BindingAdapter("colorBackgroundTint")
fun ImageView.bindColorBackgroundTint(colorRes: Int) {
    val color = ContextCompat.getColor(context, colorRes)
    backgroundTintList = ColorStateList.valueOf(color)
}
