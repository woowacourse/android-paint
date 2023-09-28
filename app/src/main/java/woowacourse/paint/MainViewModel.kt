package woowacourse.paint

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _colors: MutableList<Int> = mutableListOf()
    val colors: List<Int> get() = _colors.toList()
    var strokeSize: Float = 0.0f
    var paintColor: Int = 0

    fun setColors(colors: List<Int>) {
        _colors = colors.toMutableList()
    }
}
