package woowacourse.paint

import androidx.lifecycle.ViewModel
import woowacourse.paint.model.History
import woowacourse.paint.model.Painting

class MainViewModel : ViewModel() {
    private lateinit var _painting: Painting
    val painting: Painting
        get() = _painting
    private lateinit var _history: History
    val history
        get() = _history
    private var _toolIdx: Int = 0
    val toolIdx
        get() = _toolIdx
    private var _colorIdx: Int = 0
    val colorIdx
        get() = _colorIdx

    fun saveHistory(history: History) {
        _history = history
    }

    fun savePainting(painting: Painting) {
        _painting = painting
        this.painting.paint
    }

    fun saveToolIdx(idx: Int) {
        _toolIdx = idx
    }

    fun saveColorIdx(idx: Int) {
        _colorIdx = idx
    }
}
