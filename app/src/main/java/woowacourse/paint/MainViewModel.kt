package woowacourse.paint

import androidx.lifecycle.ViewModel
import woowacourse.paint.model.Brush
import woowacourse.paint.model.PaletteColor

class MainViewModel : ViewModel() {
    val paletteColor: List<Int> = PaletteColor.values().map { it.color }
    var strokeSize: Float = 0.0f
    var paintColor: Int = 0
    var brush: Brush = Brush.PEN
}
