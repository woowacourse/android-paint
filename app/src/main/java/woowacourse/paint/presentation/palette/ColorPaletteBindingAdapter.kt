package woowacourse.paint.presentation.palette

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("backgroundColorUiModel")
fun ImageView.backgroundColorUiModel(colorUiModel: ColorUiModel) {
    val color = ContextCompat.getColor(context, colorUiModel.resId)
    setBackgroundColor(color)
}

@BindingAdapter("onClickReverseVisibility")
fun View.setOnClickReverseVisibilityListener(targetView: View) {
    setOnClickListener {
        targetView.visibility = if (targetView.visibility == VISIBLE) GONE else VISIBLE
    }
}
