package woowacourse.paint.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.ui.PaintMode

class MainViewModel : ViewModel() {
    private val _paintWidthControllerVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val paintWidthControllerVisibility: LiveData<Boolean> get() = _paintWidthControllerVisibility

    private val _paintColorControllerVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val paintColorControllerVisibility: LiveData<Boolean> get() = _paintColorControllerVisibility

    private val _paintModeControllerVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val paintModeControllerVisibility: LiveData<Boolean> get() = _paintModeControllerVisibility

    private val _paintMode: MutableLiveData<PaintMode> = MutableLiveData(PaintMode.PEN)
    val paintMode: LiveData<PaintMode> get() = _paintMode

    fun togglePaintWidthControllerVisibility() {
        _paintWidthControllerVisibility.value =
            _paintWidthControllerVisibility.value?.not() ?: false
    }

    fun togglePaintColorControllerVisibility() {
        _paintColorControllerVisibility.value =
            _paintColorControllerVisibility.value?.not() ?: false
    }

    fun togglePaintModeControllerVisibility() {
        _paintModeControllerVisibility.value = _paintModeControllerVisibility.value?.not() ?: false
    }

    fun updatePaintMode(paintMode: PaintMode) {
        _paintMode.value = paintMode
    }
}
