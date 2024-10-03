package woowacourse.paint

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import woowacourse.paint.model.ColorPalette

@BindingAdapter("backgroundTint")
fun setBackgroundTint(view: View, colorPalette: ColorPalette) {
    val color = ContextCompat.getColor(view.context, colorPalette.colorResId)
    view.backgroundTintList = android.content.res.ColorStateList.valueOf(color)
}
