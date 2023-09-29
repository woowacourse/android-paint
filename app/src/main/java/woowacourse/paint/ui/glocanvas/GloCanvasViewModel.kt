package woowacourse.paint.ui.glocanvas

import woowacourse.paint.repository.DrawingToolRepository
import woowacourse.paint.repository.PaintColorRepository
import woowacourse.paint.ui.model.DrawingToolModel
import woowacourse.paint.ui.model.PaintColorModel
import woowacourse.paint.ui.model.SelectableDrawingToolModel
import woowacourse.paint.ui.model.mapper.toPaintColorModel
import woowacourse.paint.ui.model.mapper.toSelectableDrawingToolModel

class GloCanvasViewModel(
    private val paintColorRepository: PaintColorRepository,
    private val drawingToolRepository: DrawingToolRepository,
) {
    private lateinit var paintColors: List<PaintColorModel>
    private lateinit var drawingTools: List<SelectableDrawingToolModel>

    fun getAllPaintColors(): List<PaintColorModel> {
        paintColors = paintColorRepository.getAllPaintColors()
            .mapIndexed { index, paintColor ->
                if (index == 0) {
                    paintColor.toPaintColorModel(true)
                } else {
                    paintColor.toPaintColorModel(false)
                }
            }
        return paintColors
    }

    fun selectPaintColor(color: Int): List<PaintColorModel> {
        return paintColors.map { paintColor ->
            if (paintColor.color == color) {
                paintColor.copy(isSelected = true)
            } else {
                paintColor.copy(isSelected = false)
            }
        }
    }

    fun getAllDrawingTools(): List<SelectableDrawingToolModel> {
        drawingTools = drawingToolRepository.getAllDrawingTools()
            .mapIndexed { index, drawingTool ->
                if (index == 0) {
                    drawingTool.toSelectableDrawingToolModel(true)
                } else {
                    drawingTool.toSelectableDrawingToolModel(false)
                }
            }
        return drawingTools
    }

    fun selectDrawingTool(drawingTool: DrawingToolModel): List<SelectableDrawingToolModel> {
        return drawingTools.map { drawingToolModel ->
            if (drawingToolModel.drawingTool == drawingTool) {
                drawingToolModel.copy(isSelected = true)
            } else {
                drawingToolModel.copy(isSelected = false)
            }
        }
    }
}
