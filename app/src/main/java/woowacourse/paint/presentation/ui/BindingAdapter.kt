package woowacourse.paint.presentation.ui

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("setColor")
fun View.setColor(color: Int) {
    this.setBackgroundColor(color)
}
