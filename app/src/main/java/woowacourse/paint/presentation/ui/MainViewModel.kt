package woowacourse.paint.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.presentation.ui.model.Brush
import woowacourse.paint.presentation.ui.model.Color
import woowacourse.paint.presentation.ui.model.SettingMode
import woowacourse.paint.presentation.ui.uiState.MainUiState

class MainViewModel : ViewModel() {

    private val _uiState: MutableLiveData<MainUiState> = MutableLiveData(MainUiState.Initial)
    val uiState: LiveData<MainUiState> = _uiState

    fun startColorSelection() {
        _uiState.value = _uiState.value!!.copy(settingMode = SettingMode.COLOR)
    }

    fun startThicknessSelection() {
        _uiState.value = _uiState.value!!.copy(settingMode = SettingMode.THICKNESS)
    }

    fun startBrushSelection() {
        _uiState.value = _uiState.value!!.copy(settingMode = SettingMode.BRUSH)
    }

    fun finishSetting() {
        _uiState.value = _uiState.value!!.copy(settingMode = SettingMode.NONE)
    }

    fun setColor(color: Color) {
        _uiState.value = _uiState.value!!.copy(color = color)
    }

    fun setBrush(brush: Brush) {
        _uiState.value = _uiState.value!!.copy(brush = brush)
    }
}
