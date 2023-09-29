package woowacourse.paint.presentation.bindingAdapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import woowacourse.paint.R

@BindingAdapter("app:visible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("app:colorSelected")
fun View.isColorSelected(isSelected: Boolean) {
    foreground =
        if (isSelected) ContextCompat.getDrawable(context, R.drawable.ic_selected) else null
}
