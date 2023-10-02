package woowacourse.paint.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.paint.domain.Line
import woowacourse.paint.domain.Lines

class PaintViewModel : ViewModel() {
    private val _lines: MutableLiveData<Lines> = MutableLiveData(Lines())
    val lines: LiveData<Lines>
        get() = _lines

    fun addLine(line: Line) {
        _lines.value = _lines.value?.add(line)
    }
}
