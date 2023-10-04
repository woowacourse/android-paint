package woowacourse.paint.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.data.model.Color
import woowacourse.paint.data.model.SettingMode
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

    fun finishSetting() {
        _uiState.value = _uiState.value!!.copy(settingMode = SettingMode.NONE)
    }

    fun setColor(color: Color) {
        _uiState.value = _uiState.value!!.copy(color = color)
    }
}
