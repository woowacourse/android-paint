package woowacourse.paint.ui.glocanvas

import android.graphics.Paint
import woowacourse.paint.ui.model.DrawingToolModel

class Palette(var drawingTool: DrawingToolModel, var thickness: Float, var paintColor: Int) {

    fun getPaint(): Paint {
        return Paint(drawingTool.paint).apply {
            strokeWidth = thickness
            color = paintColor
            if (drawingTool == DrawingToolModel.HIGHLIGHTER) alpha = HIGHLIGHTER_OPACITY
        }
    }

    companion object {
        private const val HIGHLIGHTER_OPACITY = 80
    }
}
