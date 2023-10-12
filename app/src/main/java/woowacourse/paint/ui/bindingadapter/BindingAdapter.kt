package woowacourse.paint.ui.bindingadapter

import androidx.databinding.BindingAdapter
import woowacourse.paint.model.BrushTools
import woowacourse.paint.ui.custom.DrawingCanvas

@BindingAdapter("app:paintMode")
fun DrawingCanvas.updatePaintMode(brushTools: BrushTools) {
    changePaintMode(brushTools)
}
