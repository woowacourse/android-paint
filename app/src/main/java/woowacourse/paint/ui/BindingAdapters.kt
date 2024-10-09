package woowacourse.paint.ui

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("setDrawableIcon")
fun TextView.setDrawableLeft(drawableResId: Int) {
    val drawable = ContextCompat.getDrawable(context, drawableResId)
    setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}
