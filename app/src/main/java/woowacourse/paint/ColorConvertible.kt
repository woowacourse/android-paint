package woowacourse.paint

import android.content.Context

interface ColorConvertible {
    fun convertAllColor(context: Context): List<Int>
}