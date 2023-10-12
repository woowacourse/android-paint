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
    private val _colors =
        MutableLiveData(BrushColorItem.getBoardColorItems(BrushColor.values().first()))
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
    private val _brushTypes = MutableLiveData(BrushTypeItem.getBrushTypeItems(BrushType.Stroke))
    val brushTypes: LiveData<List<BrushTypeItem>>
        get() = _brushTypes

    val appliedBrushType: LiveData<BrushType> = brushTypes.map {
        it.first { brushTypeItem -> brushTypeItem.isSelected }.brushType
    }

    // 양방향 데이터바인딩으로 뷰모델에서 유지하는 경로 정보
    val drawnContents = MutableLiveData<List<Content>>(listOf())
    val redoAbleContents = MutableLiveData<List<Content>>(listOf())

    fun onChangeSelectedColor(brushColorItem: BrushColorItem) {
        _colors.value = BrushColorItem.getBoardColorItems(brushColorItem.color)
        _isVisibleColorList.value = false
    }

    fun onChangeSelectedBrushType(brushTypeItem: BrushTypeItem) {
        _brushTypes.value = BrushTypeItem.getBrushTypeItems(brushTypeItem.brushType)
        _isVisibleBrushTypeList.value = false
    }

    val onSelectedStrokeChange = { strokeWidth: Float ->
        _appliedStroke.value = strokeWidth
        _isVisibleStrokeWidth.value = false
    }

    fun onColorVisibleChange() = setVisibleState(_isVisibleColorList)
    fun onStrokeVisibleChange() = setVisibleState(_isVisibleStrokeWidth)
    fun onBrushTypeVisibleChange() = setVisibleState(_isVisibleBrushTypeList)

    private fun setVisibleState(isVisible: MutableLiveData<Boolean>) {
        isVisible.value = isVisible.value != true
    }

    companion object {
        private const val MIN_STROKE_WIDTH = 0f
        private const val MAX_STROKE_WIDTH = 100F
    }
}
