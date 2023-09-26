package woowacourse.paint

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class Paint(
    context: Context,
    attributeSet: AttributeSet,
) : View(context, attributeSet) {
    var color: Int = Color.BLACK
        private set

    fun setColor(color: Int) {
        this.color = color
        setBackgroundColor(color)
    }
}
