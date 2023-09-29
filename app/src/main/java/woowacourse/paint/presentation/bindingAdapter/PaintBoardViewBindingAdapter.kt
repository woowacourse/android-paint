package woowacourse.paint.presentation.bindingAdapter

import androidx.databinding.BindingAdapter
import woowacourse.paint.presentation.curstomView.PaintBoardView

@BindingAdapter("app:onTouch")
fun PaintBoardView.setOnTouchListener(listener: PaintBoardView.TouchEventListener) {
    addTouchEventListener(listener)
}
