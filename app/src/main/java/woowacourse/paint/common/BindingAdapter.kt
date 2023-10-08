package woowacourse.paint.common

import android.view.View
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setBackGroundColor")
fun View.setBackGroundColor(@ColorRes colorId: Int) {
    setBackgroundColor(context.getColor(colorId))
}
