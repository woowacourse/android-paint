package woowacourse.paint

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaintViewModel : ViewModel(), PaintActionHandler {
    private val _color: MutableLiveData<Int> = MutableLiveData(Color.RED)
    val color: LiveData<Int> get() = _color

    override fun changeColor(color: Int) {
        _color.value = color
    }
}
