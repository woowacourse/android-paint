package woowacourse.paint.ui.glocanvas

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import woowacourse.paint.model.PaintColor
import woowacourse.paint.repository.DrawingKitRepository
import woowacourse.paint.ui.model.DrawingToolModel
import woowacourse.paint.ui.model.PaintColorModel
import woowacourse.paint.ui.model.SelectableDrawingToolModel
import woowacourse.paint.ui.model.mapper.toDrawingTool
import woowacourse.paint.ui.model.mapper.toDrawingToolModel
import woowacourse.paint.ui.model.mapper.toPaintColorModel
import woowacourse.paint.ui.model.mapper.toSelectableDrawingToolModel

class GloCanvasViewModel(
    private val drawingToolRepository: DrawingKitRepository,
) {
    private var _drawingTool: MutableLiveData<DrawingToolModel> = MutableLiveData()
    val drawingTool: LiveData<DrawingToolModel>
        get() = _drawingTool
    private var _drawingTools: MutableLiveData<List<SelectableDrawingToolModel>> = MutableLiveData()
    val drawingTools: LiveData<List<SelectableDrawingToolModel>>
        get() = _drawingTools
    private var _thickness: MutableLiveData<Float> = MutableLiveData()
    val thickness: LiveData<Float>
        get() = _thickness
    private var _paintColor: MutableLiveData<Int> = MutableLiveData()
    val paintColor: LiveData<Int>
        get() = _paintColor
    private var _paintColors: MutableLiveData<List<PaintColorModel>> = MutableLiveData()
    val paintColors: LiveData<List<PaintColorModel>>
        get() = _paintColors

    init {
        setupDrawingTool()
        setupDrawingTools()
        setupThickness()
        setupPaintColor()
        setupPaintColors()
    }

    private fun setupDrawingTool() {
        _drawingTool.value = drawingToolRepository.getDrawingTool().toDrawingToolModel()
    }

    private fun setupDrawingTools() {
        _drawingTool.value?.let { selectedDrawingTool ->
            _drawingTools.value = drawingToolRepository.getAllDrawingTools()
                .map {
                    if (it.toDrawingToolModel() == selectedDrawingTool) {
                        it.toSelectableDrawingToolModel(true)
                    } else {
                        it.toSelectableDrawingToolModel(false)
                    }
                }
        }
    }

    private fun setupThickness() {
        _thickness.value = DEFAULT_THICKNESS
    }

    private fun setupPaintColor() {
        _paintColor.value = Color.parseColor(drawingToolRepository.getPaintColor().color)
    }

    private fun setupPaintColors() {
        _paintColor.value?.let { selectedPaintColor ->
            _paintColors.value = drawingToolRepository.getAllPaintColors()
                .map {
                    if (Color.parseColor(it.color) == selectedPaintColor) {
                        it.toPaintColorModel(true)
                    } else {
                        it.toPaintColorModel(false)
                    }
                }
        }
    }

    fun selectDrawingTool(drawingTool: DrawingToolModel) {
        drawingToolRepository.setDrawingTool(drawingTool.toDrawingTool())
        _drawingTool.value = drawingTool
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
        drawingToolRepository.setPaintColor(PaintColor(Integer.toHexString(color)))
        _paintColor.value = color
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
