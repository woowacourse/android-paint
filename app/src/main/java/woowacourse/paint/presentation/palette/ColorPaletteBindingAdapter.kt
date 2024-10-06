package woowacourse.paint.presentation.palette

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("onClickReverseVisibility")
fun View.setOnClickReverseVisibilityListener(targetView: View) {
    setOnClickListener {
        targetView.visibility = if (targetView.visibility == VISIBLE) GONE else VISIBLE
    }
}
