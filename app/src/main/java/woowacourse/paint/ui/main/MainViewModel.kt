package woowacourse.paint.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content
import woowacourse.paint.model.BrushColor
import woowacourse.paint.model.BrushColorItem
import woowacourse.paint.model.BrushTypeItem

class MainViewModel : ViewModel() {
    val minStrokeWidth: Float = MIN_STROKE_WIDTH
    val maxStrokeWidth: Float = MAX_STROKE_WIDTH
    var selectedStroke: Float = (minStrokeWidth + maxStrokeWidth) / 2
        private set
    var selectedColor: BrushColor = BrushColor.values().first()
        private set
    var selectedBrushType: BrushType = BrushType.Stroke
        private set

    private val _appliedColor: MutableLiveData<BrushColor> = MutableLiveData(selectedColor)
    val appliedColor: LiveData<BrushColor>
        get() = _appliedColor

    private val _appliedStroke: MutableLiveData<Float> = MutableLiveData(selectedStroke)
    val appliedStroke: LiveData<Float>
        get() = _appliedStroke

    private val _appliedBrushType: MutableLiveData<BrushType> = MutableLiveData(selectedBrushType)
    val appliedBrushType: LiveData<BrushType>
        get() = _appliedBrushType

    private val _colors: MutableLiveData<List<BrushColorItem>> =
        MutableLiveData(getBoardColorItems(selectedColor))
    val colors: LiveData<List<BrushColorItem>>
        get() = _colors

    private val brushes =
        listOf(BrushType.Stroke, BrushType.Rectangle, BrushType.Circle, BrushType.Eraser)
    private val _brushTypes: MutableLiveData<List<BrushTypeItem>> =
        MutableLiveData(getBrushTypeItems(selectedBrushType))
    val brushTypes: LiveData<List<BrushTypeItem>>
        get() = _brushTypes

    private val _drawnPaths = mutableListOf<Content>()
    val drawnPaths: List<Content>
        get() = _drawnPaths.map { it.deepCopy() }

    private fun getBoardColorItems(selectedColor: BrushColor): List<BrushColorItem> =
        BrushColor.values().map {
            if (it == selectedColor) return@map BrushColorItem(it, true)
            BrushColorItem(it, false)
        }

    private fun getBrushTypeItems(selectedBrushType: BrushType): List<BrushTypeItem> =
        brushes.map {
            if (it == selectedBrushType) return@map BrushTypeItem(it, true)
            BrushTypeItem(it, false)
        }

    fun onChangeSelectedColor(brushColorItem: BrushColorItem) {
        selectedColor = brushColorItem.color
    }

    fun onChangeSelectedBrushType(brushTypeItem: BrushTypeItem) {
        selectedBrushType = brushTypeItem.brushType
    }

    val onSelectedStrokeChange = { strokeWidth: Float ->
        selectedStroke = strokeWidth
    }

    fun onAppliedColorChange() {
        _appliedColor.value = selectedColor
        _colors.value = getBoardColorItems(selectedColor)
    }

    fun onAppliedStrokeChange() {
        _appliedStroke.value = selectedStroke
    }

    fun onAppliedBrushTypeChange() {
        _appliedBrushType.value = selectedBrushType
    }

    fun saveDrawnPaths(paths: List<Content>) {
        _drawnPaths.clear()
        _drawnPaths.addAll(paths)
    }

    companion object {
        private const val MIN_STROKE_WIDTH = 0f
        private const val MAX_STROKE_WIDTH = 100F
    }
}
