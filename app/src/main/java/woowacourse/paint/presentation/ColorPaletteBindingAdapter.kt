package woowacourse.paint.presentation

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("backgroundColorResId")
fun ImageView.backgroundColorResId(@ColorRes colorResId: Int) {
    val color = ContextCompat.getColor(context, colorResId)
    setBackgroundColor(color)
}
