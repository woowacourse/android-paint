package woowacourse.paint.presentation.paint.model

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import woowacourse.paint.R

enum class ColorUiModel(
    @ColorRes val resId: Int,
) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    GREEN(R.color.green),
    BLUE(R.color.blue),
    ;

    fun getColor(context: Context): Int {
        return ContextCompat.getColor(context, resId)
    }
}
