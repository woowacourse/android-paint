package woowacourse.paint.customView.widthSelection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.paint.databinding.ViewWidthSelectionBinding

class WidthSelection(
    context: Context,
    attrs: AttributeSet? = null,
    onClickWidthListener: (Float) -> Unit,
    initialValue: Float = 0f,
) : ConstraintLayout(context, attrs) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, {})

    private val binding: ViewWidthSelectionBinding =
        ViewWidthSelectionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.onClickWidthListener = onClickWidthListener
        binding.initialValue = initialValue
    }
}
