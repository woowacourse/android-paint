package woowacourse.paint.ui.bindingadapter

import androidx.databinding.BindingAdapter
import woowacourse.paint.ui.PaintMode
import woowacourse.paint.ui.custom.DrawingCanvas

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:paintMode")
    fun updatePaintMode(drawingCanvas: DrawingCanvas, paintMode: PaintMode) {
        drawingCanvas.changePaintMode(paintMode)
    }
}
