package woowacourse.paint

import android.view.View
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("isVisible")
    fun isVisible(view: View, isVisible: Boolean) {
        when (isVisible) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("setBackgroundColorRes")
    fun setBackgroundColorRes(view: View, @ColorRes colorRes: Int) {
        view.setBackgroundResource(colorRes)
    }
}
