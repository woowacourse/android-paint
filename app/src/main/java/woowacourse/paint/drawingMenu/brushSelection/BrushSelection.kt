package woowacourse.paint.drawingMenu.brushSelection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.paint.databinding.ViewBrushSelectionBinding
import woowacourse.paint.model.BrushType

class BrushSelection(
    context: Context,
    attrs: AttributeSet? = null,
    onClickBrushListener: (BrushType) -> Unit,
) : ConstraintLayout(context, attrs) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, {})

    private val binding: ViewBrushSelectionBinding =
        ViewBrushSelectionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.rvBrushType.adapter = BrushSelectionAdapter(onClickBrushListener)
    }
}
