package woowacourse.paint

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var strokeSize: Float = 0.0f
        private set

    fun setStrokeSize(size: Float) {
        strokeSize = size
    }
}
