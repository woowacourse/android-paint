package woowacourse.paint

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class ColorView private constructor(
    context: Context,
    attr: AttributeSet? = null,
) : View(context, attr) {
    private var size: Int = -1
    private lateinit var color: Color

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setBackgroundColor(context.getColor(color.id))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            size - HORIZONTAL_PADDING * 2,
            size - HORIZONTAL_PADDING * 2,
        )
    }

    companion object {
        private const val HORIZONTAL_PADDING = 12

        fun create(
            context: Context,
            size: Int,
            color: Color,
            onColorSelected: (Color) -> Unit,
        ): ColorView {
            val layoutParams = LinearLayout.LayoutParams(size, size)
            layoutParams.setMargins(HORIZONTAL_PADDING, 0, HORIZONTAL_PADDING, 0)

            return ColorView(context).also {
                it.size = size
                it.color = color
                it.layoutParams = layoutParams
                it.setOnClickListener { onColorSelected.invoke(color) }
            }
        }
    }
}
