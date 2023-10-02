package woowacourse.paint

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("penToolVisibility")
fun PenToolView.setVisibility(value: Boolean) {
    if (value) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
