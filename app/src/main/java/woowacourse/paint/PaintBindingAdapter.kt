package woowacourse.paint

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("checkedColor")
fun ImageView.bindCheckedColor(colorResId: Int) {
    if (backgroundTintList?.defaultColor == colorResId) {
        setImageResource(R.drawable.ic_check_white)
    } else {
        setImageResource(0)
    }
}
