package woowacourse.paint.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import woowacourse.paint.model.BrushHistory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val brushHistory: BrushHistory = BrushHistory()
}
