package woowacourse.paint.presentation.bindingAdapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}
