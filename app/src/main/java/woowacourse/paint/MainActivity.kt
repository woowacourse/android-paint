package woowacourse.paint

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushMode
import woowacourse.paint.model.PaintingColor

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val paintingColorList = PaintingColor.entries
    private val paintingColorAdapter by lazy {
        PaintingColorAdapter(paintingColorList) { color ->
            binding.customView.changeColor(
                ContextCompat.getColor(
                    this,
                    color.colorRes,
                ),
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setBackgroundColorByDarkModeCondition()

        binding.rvColors.adapter = paintingColorAdapter
        binding.rangeSlider
            .apply {
                values = listOf(DEFAULT_RANGE_SLIDER_VALUE)
                valueFrom = MIN_RANGE_SLIDER_VALUE
                valueTo = MAX_RANGE_SLIDER_VALUE
            }.addOnChangeListener { _, value, _ ->
                binding.customView.changeStrokeWidth(value)
            }

        binding.btnPen.setOnClickListener {
            binding.customView.changeBrushMode(BrushMode.PEN)
        }

        binding.btnRectangler.setOnClickListener {
            binding.customView.changeBrushMode(BrushMode.RECT)
        }

        binding.btnCircle.setOnClickListener {
            binding.customView.changeBrushMode(BrushMode.CIRCLE)
        }

        binding.btnEraser.setOnClickListener {
            binding.customView.changeBrushMode(BrushMode.ERASER)
        }

        binding.btnUndo.setOnClickListener {
            binding.customView.undo()
        }
        binding.btnRedo.setOnClickListener {
            binding.customView.redo()
        }
        binding.btnClear.setOnClickListener {
            binding.customView.clear()
        }
    }

    private fun setBackgroundColorByDarkModeCondition() {
        val typedValue = TypedValue()
        theme.resolveAttribute(R.attr.colorOnPrimary, typedValue, true)
        val colorOnPrimary = typedValue.data
        binding.customView.setBackgroundColor(colorOnPrimary)
    }

    companion object {
        const val DEFAULT_RANGE_SLIDER_VALUE = 10f
        const val MIN_RANGE_SLIDER_VALUE = 1f
        const val MAX_RANGE_SLIDER_VALUE = 50f
    }
}
