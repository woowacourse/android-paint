package woowacourse.paint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _penToolVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    val penToolVisibility: LiveData<Boolean> = _penToolVisibility

    fun changePenToolVisibility() {
        _penToolVisibility.value = _penToolVisibility.value!!.not()
    }
}
