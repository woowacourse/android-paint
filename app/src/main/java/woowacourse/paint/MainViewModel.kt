package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _changingState = MutableLiveData<ChangingState>(ChangingState.Nothing)
    val changingState: LiveData<ChangingState>
        get() = _changingState

    fun setChangeColor() {
        if (_changingState.value == ChangingState.ColorChanging) {
            _changingState.value = ChangingState.Nothing
            return
        }
        _changingState.value = ChangingState.ColorChanging
    }

    fun setChangeThickness() {
        if (_changingState.value == ChangingState.ThicknessChanging) {
            _changingState.value = ChangingState.Nothing
            return
        }
        _changingState.value = ChangingState.ThicknessChanging
    }
}
