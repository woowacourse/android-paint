package woowacourse.paint.presentation.bindingAdapter

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import woowacourse.paint.R
import woowacourse.paint.data.model.Brush
import woowacourse.paint.data.model.Color
import woowacourse.paint.presentation.curstomView.PaintBoardView

@BindingAdapter("app:onTouch")
fun PaintBoardView.setOnTouchListener(listener: PaintBoardView.TouchEventListener) {
    addTouchEventListener(listener)
}

@BindingAdapter("app:strokeColor")
fun PaintBoardView.setStrokeColor(color: Color) {
    when (color) {
        Color.RED -> setBrushColor(ContextCompat.getColor(context, R.color.red))
        Color.ORANGE -> setBrushColor(ContextCompat.getColor(context, R.color.orange))
        Color.YELLOW -> setBrushColor(ContextCompat.getColor(context, R.color.yellow))
        Color.GREEN -> setBrushColor(ContextCompat.getColor(context, R.color.green))
        Color.BLUE -> setBrushColor(ContextCompat.getColor(context, R.color.blue))
    }
}

@BindingAdapter("app:brush")
fun PaintBoardView.changeBrush(brush: Brush) {
    setBrush(brush)
}
