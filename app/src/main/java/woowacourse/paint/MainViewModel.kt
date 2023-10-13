package woowacourse.paint

import androidx.lifecycle.ViewModel
import woowacourse.paint.model.History

class MainViewModel : ViewModel() {
    private lateinit var _history: History
    val history
        get() = _history

    fun saveHistory(history: History) {
        _history = history
    }
}
