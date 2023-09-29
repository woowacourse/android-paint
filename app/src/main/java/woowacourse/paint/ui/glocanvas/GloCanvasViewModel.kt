package woowacourse.paint.ui.glocanvas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private var _drawingTools: MutableLiveData<List<SelectableDrawingToolModel>> = MutableLiveData()
    val drawingTools: LiveData<List<SelectableDrawingToolModel>>
        get() = _drawingTools
    private var _thickness: MutableLiveData<Float> = MutableLiveData()
    val thickness: LiveData<Float>
        get() = _thickness
    private var _paintColors: MutableLiveData<List<PaintColorModel>> = MutableLiveData()
    val paintColors: LiveData<List<PaintColorModel>>
        get() = _paintColors

    init {
        setupDrawingTools()
        setupThickness()
        setupPaintColors()
    }

    private fun setupDrawingTools() {
        _drawingTools.value = drawingToolRepository.getAllDrawingTools()
            .mapIndexed { index, drawingTool ->
                if (index == 0) {
                    drawingTool.toSelectableDrawingToolModel(true)
                } else {
                    drawingTool.toSelectableDrawingToolModel(false)
                }
            }
    }

    private fun setupThickness() {
        _thickness.value = DEFAULT_THICKNESS
    }

    private fun setupPaintColors() {
        _paintColors.value = paintColorRepository.getAllPaintColors()
            .mapIndexed { index, paintColor ->
                if (index == 0) {
                    paintColor.toPaintColorModel(true)
                } else {
                    paintColor.toPaintColorModel(false)
                }
            }
    }

    fun selectDrawingTool(drawingTool: DrawingToolModel) {
        _drawingTools.value?.let {
            _drawingTools.value = it.map { drawingToolModel ->
                if (drawingToolModel.drawingTool == drawingTool) {
                    drawingToolModel.copy(isSelected = true)
                } else {
                    drawingToolModel.copy(isSelected = false)
                }
            }
        }
    }

    fun setThickness(thickness: Float) {
        _thickness.value = thickness
    }

    fun selectPaintColor(color: Int) {
        _paintColors.value?.let {
            _paintColors.value = it.map { paintColor ->
                if (paintColor.color == color) {
                    paintColor.copy(isSelected = true)
                } else {
                    paintColor.copy(isSelected = false)
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_THICKNESS = 0f
    }
}
