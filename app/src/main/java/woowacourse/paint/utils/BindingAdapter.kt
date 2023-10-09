package woowacourse.paint.utils

import android.widget.Button
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("isSelected")
    @JvmStatic
    fun isSelected(button: Button, isSelected: Boolean) {
        button.isSelected = isSelected
    }
}
