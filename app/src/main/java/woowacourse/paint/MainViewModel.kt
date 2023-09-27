package woowacourse.paint

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var penStroke: Float = 50f
        private set

    val onChange = { value: Float ->
        Log.d("mendel!", "!! $value")
        penStroke = value
    }
}
