package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaintViewModel : ViewModel(), PaintActionHandler {
    private val _colorResId: MutableLiveData<Int> = MutableLiveData(DEFAULT_COLOR_RES_ID)
    val colorResId: LiveData<Int> get() = _colorResId

    private val _thickness: MutableLiveData<Int> = MutableLiveData()
    val thickness: LiveData<Int> get() = _thickness

    override fun changeColor(colorResId: Int) {
        _colorResId.value = colorResId
    }

    fun changeThickness(thickness: Int) {
        _thickness.value = thickness
    }

    companion object {
        private val DEFAULT_COLOR_RES_ID = -65536
    }
}
