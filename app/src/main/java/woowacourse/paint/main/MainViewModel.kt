package woowacourse.paint.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import woowacourse.paint.customview.paint.BrushColor
import woowacourse.paint.customview.paint.PaintMode
import woowacourse.paint.customview.paint.Painting
import woowacourse.paint.main.model.BrushColorBox

class MainViewModel : ViewModel() {

    private val _brushThickness: MutableLiveData<Float> = MutableLiveData(DEFAULT_BRUSH_THICKNESS)
    val brushThickness: LiveData<Float> get() = _brushThickness

    private val _paintings: MutableLiveData<List<Painting>> = MutableLiveData()
    val paintings: LiveData<List<Painting>> get() = _paintings

    private val _paintMode: MutableLiveData<PaintMode> = MutableLiveData(PaintMode.PEN)
    val paintMode: LiveData<PaintMode> get() = _paintMode

    private val _brushColorBoxes: MutableLiveData<List<BrushColorBox>> =
        MutableLiveData(BrushColorBox.getColorBoxes(BrushColor.RED))
    val brushColorBoxes: LiveData<List<BrushColorBox>> get() = _brushColorBoxes

    val brushColor: LiveData<BrushColor> =
        brushColorBoxes.map {
            it.first { brushColorBox ->
                brushColorBox.isSelected
            }.brushColor
        }

    private val _paintingHistory: MutableLiveData<List<Painting>> = MutableLiveData()
    val paintingHistory: LiveData<List<Painting>> get() = _paintingHistory

    fun updateBrushThickness(value: Float) {
        _brushThickness.value = value
    }

    fun updatePaintings(newPaintings: List<Painting>) {
        _paintings.value = newPaintings
    }

    fun updateBrushColorBoxes(clickedColor: BrushColor) {
        val currentColor: BrushColor? =
            _brushColorBoxes.value?.firstOrNull { it.isSelected }?.brushColor

        if (clickedColor == currentColor) return

//        _brushColor.value = clickedColor
        _brushColorBoxes.value = BrushColorBox.getColorBoxes(clickedColor)
    }

    fun updatePaintMode(newPaintMode: PaintMode) {
        _paintMode.value = newPaintMode
    }

    fun updatePaintHistory(newPaintingHistory: List<Painting>) {
        _paintingHistory.value = newPaintingHistory
    }

    companion object {
        private const val DEFAULT_BRUSH_THICKNESS = 30f
        private const val THE_BRUSH_COLOR_HAS_NOT_BEEN_SELECTED_YET = "브러시의 색이 지정되지 않았습니다."
    }
}
