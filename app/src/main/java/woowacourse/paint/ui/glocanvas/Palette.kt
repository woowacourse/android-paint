package woowacourse.paint.ui.glocanvas

import android.graphics.Paint
import woowacourse.paint.ui.model.DrawingToolModel

class Palette(drawingTool: DrawingToolModel, thickness: Float, paintColor: Int) {
    var drawingTool = drawingTool
        private set
    var thickness = thickness
        private set
    var paintColor = paintColor
        private set

    fun setDrawingTool(drawingTool: DrawingToolModel) {
        this.drawingTool = drawingTool
    }

    fun setThickness(thickness: Float) {
        this.thickness = thickness
    }

    fun setPaintColor(color: Int) {
        paintColor = color
    }

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
