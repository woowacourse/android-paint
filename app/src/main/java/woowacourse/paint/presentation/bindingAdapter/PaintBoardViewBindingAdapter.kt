package woowacourse.paint.presentation.bindingAdapter

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import woowacourse.paint.R
import woowacourse.paint.data.model.Color
import woowacourse.paint.presentation.curstomView.PaintBoardView

@BindingAdapter("app:onTouch")
fun PaintBoardView.setOnTouchListener(listener: PaintBoardView.TouchEventListener) {
    addTouchEventListener(listener)
}

@BindingAdapter("app:strokeColor")
fun PaintBoardView.setStrokeColor(color: Color) {
    when (color) {
        Color.RED -> setStrokeColor(ContextCompat.getColor(context, R.color.red))
        Color.ORANGE -> setStrokeColor(ContextCompat.getColor(context, R.color.orange))
        Color.YELLOW -> setStrokeColor(ContextCompat.getColor(context, R.color.yellow))
        Color.GREEN -> setStrokeColor(ContextCompat.getColor(context, R.color.green))
        Color.BLUE -> setStrokeColor(ContextCompat.getColor(context, R.color.blue))
    }
}
