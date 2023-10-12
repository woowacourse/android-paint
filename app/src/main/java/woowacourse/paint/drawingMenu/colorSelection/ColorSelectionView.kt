package woowacourse.paint.drawingMenu.colorSelection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.paint.databinding.ViewColorSelectionBinding
import woowacourse.paint.model.ColorPalette

class ColorSelectionView(
    context: Context,
    attrs: AttributeSet? = null,
    click: (ColorPalette) -> Unit,
) : ConstraintLayout(context, attrs) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, {})

    private val binding: ViewColorSelectionBinding =
        ViewColorSelectionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.rvColorPalette.adapter = ColorSelectionAdapter(click)
    }
}
