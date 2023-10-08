package woowacourse.paint.model.drawable.path

import woowacourse.paint.model.BrushSize
import woowacourse.paint.model.drawable.DrawableElement

interface DrawablePath : DrawableElement {
    fun changeBrushSize(brushSize: BrushSize): DrawablePath
}
