package woowacourse.paint.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import woowacourse.paint.customView.content.BrushType
import woowacourse.paint.customView.content.Content
import woowacourse.paint.model.BrushColor
import woowacourse.paint.model.BrushColorItem
import woowacourse.paint.model.BrushTypeItem

class MainViewModel : ViewModel() {
    val minStrokeWidth: Float = MIN_STROKE_WIDTH
    val maxStrokeWidth: Float = MAX_STROKE_WIDTH

    private val _isVisibleColorList = MutableLiveData(false)
    val isVisibleColorList: LiveData<Boolean>
        get() = _isVisibleColorList

    private val _isVisibleStrokeWidth = MutableLiveData(false)
    val isVisibleStrokeWidth: LiveData<Boolean>
        get() = _isVisibleStrokeWidth

    private val _isVisibleBrushTypeList = MutableLiveData(false)
    val isVisibleBrushTypeList: LiveData<Boolean>
        get() = _isVisibleBrushTypeList

    // 색상
    private val _colors = MutableLiveData(getBoardColorItems(BrushColor.values().first()))
    val colors: LiveData<List<BrushColorItem>>
        get() = _colors

    val appliedColor: LiveData<BrushColor> = colors.map {
        it.first { brushColorItem -> brushColorItem.isSelected }.color
    }

    // 굵기
    private val _appliedStroke = MutableLiveData((minStrokeWidth + maxStrokeWidth) / 2)
    val appliedStroke: LiveData<Float>
        get() = _appliedStroke

    // 브러시 타입
    private val usingBrushes =
        listOf(BrushType.Stroke, BrushType.Rectangle, BrushType.Circle, BrushType.Eraser)
    private val _brushTypes = MutableLiveData(getBrushTypeItems(BrushType.Stroke))
    val brushTypes: LiveData<List<BrushTypeItem>>
        get() = _brushTypes

    val appliedBrushType: LiveData<BrushType> = brushTypes.map {
        it.first { brushTypeItem -> brushTypeItem.isSelected }.brushType
    }

    // 임시 저장 컨텐트
    private val _drawnPaths = mutableListOf<Content>()
    val drawnPaths: List<Content>
        get() = _drawnPaths.map { it.deepCopy() }

    private fun getBoardColorItems(selectedColor: BrushColor): List<BrushColorItem> =
        BrushColor.values().map {
            if (it == selectedColor) return@map BrushColorItem(it, true)
            BrushColorItem(it, false)
        }

    private fun getBrushTypeItems(selectedBrushType: BrushType): List<BrushTypeItem> =
        usingBrushes.map {
            if (it == selectedBrushType) return@map BrushTypeItem(it, true)
            BrushTypeItem(it, false)
        }

    fun onChangeSelectedColor(brushColorItem: BrushColorItem) {
        _colors.value = getBoardColorItems(brushColorItem.color)
        _isVisibleColorList.value = false
    }

    fun onChangeSelectedBrushType(brushTypeItem: BrushTypeItem) {
        _brushTypes.value = getBrushTypeItems(brushTypeItem.brushType)
        _isVisibleBrushTypeList.value = false
    }

    val onSelectedStrokeChange = { strokeWidth: Float ->
        _appliedStroke.value = strokeWidth
        _isVisibleStrokeWidth.value = false
    }

    fun onColorVisibleChange() {
        setVisibleState(_isVisibleColorList)
    }

    fun onStrokeVisibleChange() {
        setVisibleState(_isVisibleStrokeWidth)
    }

    fun onBrushTypeVisibleChange() {
        setVisibleState(_isVisibleBrushTypeList)
    }

    private fun setVisibleState(isVisible: MutableLiveData<Boolean>) {
        isVisible.value = isVisible.value != true
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
