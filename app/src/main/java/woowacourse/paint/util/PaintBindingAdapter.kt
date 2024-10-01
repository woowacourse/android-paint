package woowacourse.paint.util

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import woowacourse.paint.R

@BindingAdapter("isChecked")
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
