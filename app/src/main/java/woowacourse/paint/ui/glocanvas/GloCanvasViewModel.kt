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
    private val drawingKitRepository: DrawingKitRepository,
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
        _drawingTool.value = drawingKitRepository.getDrawingTool().toDrawingToolModel()
    }

    private fun setupDrawingTools() {
        _drawingTool.value?.let { selectedDrawingTool ->
            _drawingTools.value = drawingKitRepository.getAllDrawingTools()
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
        _thickness.value = drawingKitRepository.getThickness()
    }

    private fun setupPaintColor() {
        _paintColor.value = Color.parseColor(drawingKitRepository.getPaintColor().color)
    }

    private fun setupPaintColors() {
        _paintColor.value?.let { selectedPaintColor ->
            _paintColors.value = drawingKitRepository.getAllPaintColors()
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
        drawingKitRepository.changeDrawingTool(drawingTool.toDrawingTool())
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
        drawingKitRepository.changeThickness(thickness)
        _thickness.value = thickness
    }

    fun selectPaintColor(color: Int) {
        drawingKitRepository.changePaintColor(PaintColor(Integer.toHexString(color)))
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
}
