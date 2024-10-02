package woowacourse.paint

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat

data class ColorUiModel(
    val color: Int,
) {
    companion object {
        val DEFAULT_COLOR = ColorUiModel(Color.RED)

        fun palletColors(context: Context): List<ColorUiModel> =
            context.resources.obtainTypedArray(R.array.pallet_colors).run {
                val colors = (0 until length()).map { compatColorOf(context, getResourceId(it, 0)) }
                recycle()
                colors
            }.map { ColorUiModel(it) }

        private fun compatColorOf(context: Context, color: Int): Int = ContextCompat.getColor(context, color)

    }
}
