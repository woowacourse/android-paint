package woowacourse.paint.ui.glocanvas

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import woowacourse.paint.model.PaintColor
import woowacourse.paint.repository.DrawingKitRepository
import woowacourse.paint.ui.model.DrawingToolModel
import woowacourse.paint.ui.model.PaintColorModel
import woowacourse.paint.ui.model.SelectableDrawingToolModel
import woowacourse.paint.ui.model.mapper.toDrawingTool
import woowacourse.paint.ui.model.mapper.toDrawingToolModel
import woowacourse.paint.ui.model.mapper.toPaintColorModel
import woowacourse.paint.ui.model.mapper.toSelectableDrawingToolModel
import javax.inject.Inject

@HiltViewModel
class GloCanvasViewModel @Inject constructor(
    private val drawingKitRepository: DrawingKitRepository,
) : ViewModel() {
    lateinit var palette: Palette
        private set
    private var _drawingTools: MutableLiveData<List<SelectableDrawingToolModel>> = MutableLiveData()
    val drawingTools: LiveData<List<SelectableDrawingToolModel>>
        get() = _drawingTools
    private var _paintColors: MutableLiveData<List<PaintColorModel>> = MutableLiveData()
    val paintColors: LiveData<List<PaintColorModel>>
        get() = _paintColors

    init {
        setupPalette()
        setupDrawingTools()
        setupPaintColors()
    }

    private fun setupPalette() {
        val drawingTool = drawingKitRepository.getDrawingTool().toDrawingToolModel()
        val thickness = drawingKitRepository.getThickness()
        val paintColor = Color.parseColor(drawingKitRepository.getPaintColor().color)
        palette = Palette(drawingTool, thickness, paintColor)
    }

    private fun setupDrawingTools() {
        _drawingTools.value = drawingKitRepository.getAllDrawingTools()
            .map {
                val isSelected = it.toDrawingToolModel() == palette.drawingTool
                it.toSelectableDrawingToolModel(isSelected)
            }
    }

    private fun setupPaintColors() {
        _paintColors.value = drawingKitRepository.getAllPaintColors()
            .map {
                val isSelected = Color.parseColor(it.color) == palette.paintColor
                it.toPaintColorModel(isSelected)
            }
    }

    fun selectDrawingTool(drawingTool: DrawingToolModel) {
        drawingKitRepository.changeDrawingTool(drawingTool.toDrawingTool())
        palette.drawingTool = drawingTool
        _drawingTools.value?.let {
            _drawingTools.value = it.map { drawingToolModel ->
                val isSelected = drawingToolModel.drawingTool == drawingTool
                drawingToolModel.copy(isSelected = isSelected)
            }
        }
    }

    fun setThickness(thickness: Float) {
        drawingKitRepository.changeThickness(thickness)
        palette.thickness = thickness
    }

    fun selectPaintColor(color: Int) {
        drawingKitRepository.changePaintColor(PaintColor.of(color))
        palette.paintColor = color
        _paintColors.value?.let {
            _paintColors.value = it.map { paintColor ->
                val isSelected = paintColor.color == color
                paintColor.copy(isSelected = isSelected)
            }
        }
    }
}
