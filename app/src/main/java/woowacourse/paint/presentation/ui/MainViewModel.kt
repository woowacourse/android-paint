package woowacourse.paint.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.presentation.ui.uiState.MainUiState

class MainViewModel : ViewModel() {

    private val _uiState: MutableLiveData<MainUiState> = MutableLiveData(MainUiState.Initial)
    val uiState: LiveData<MainUiState> = _uiState
}
