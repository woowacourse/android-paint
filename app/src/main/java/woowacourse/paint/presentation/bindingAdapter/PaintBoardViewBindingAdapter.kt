package woowacourse.paint.presentation.bindingAdapter

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import woowacourse.paint.R
import woowacourse.paint.presentation.curstomView.PaintBoardView
import woowacourse.paint.presentation.ui.model.Brush
import woowacourse.paint.presentation.ui.model.Color

@BindingAdapter("app:onTouch")
fun PaintBoardView.setOnTouchListener(listener: PaintBoardView.TouchEventListener) {
    addTouchEventListener(listener)
}

@BindingAdapter("app:brushColor")
fun PaintBoardView.changeBrushColor(color: Color) {
    when (color) {
        Color.RED -> setBrushColor(ContextCompat.getColor(context, R.color.red))
        Color.ORANGE -> setBrushColor(ContextCompat.getColor(context, R.color.orange))
        Color.YELLOW -> setBrushColor(ContextCompat.getColor(context, R.color.yellow))
        Color.GREEN -> setBrushColor(ContextCompat.getColor(context, R.color.green))
        Color.BLUE -> setBrushColor(ContextCompat.getColor(context, R.color.blue))
    }
}

@BindingAdapter("app:strokeWidth")
fun PaintBoardView.changeStrokeWidth(width: Float) {
    setStrokeWidth(width)
}

@BindingAdapter("app:brush")
fun PaintBoardView.changeBrush(brush: Brush) {
    setBrush(brush)
}
