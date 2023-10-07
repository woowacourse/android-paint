package woowacourse.paint.view

import android.content.Context
import android.widget.HorizontalScrollView
import android.widget.LinearLayout

class ShapeScrollView private constructor(
    context: Context,
) : HorizontalScrollView(context) {
    private var onShapeSelected: (PaletteShape) -> Unit = {}
        set(value) {
            field = value
            setupPaletteShapeViews()
        }

    private val paletteShapeViews = LinearLayout(context).apply {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        orientation = LinearLayout.HORIZONTAL
    }

    init {
        addView(paletteShapeViews)
    }

    private fun setupPaletteShapeViews() {
        PaletteShape.values().forEach { paletteShape ->
            paletteShapeViews.addView(ShapeView.create(context, paletteShape, onShapeSelected))
        }
    }

    companion object {
        fun create(
            context: Context,
            onShapeSelected: (PaletteShape) -> Unit,
        ): ShapeScrollView = ShapeScrollView(context).also { view ->
            view.onShapeSelected = onShapeSelected
        }
    }
}
