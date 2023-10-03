package woowacourse.paint

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class Hue(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    var hue: Int = Color.BLACK
        set(value) {
            field = value
            this.setBackgroundColor(value)
        }
}
