package woowacourse.paint

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.drawing.Circle
import woowacourse.paint.drawing.Eraser
import woowacourse.paint.drawing.Pen
import woowacourse.paint.drawing.Rectangle

class MainActivity : AppCompatActivity() {
    private val drawingBoard: DrawingBoardView by lazy { findViewById(R.id.drawing_board) }
    private val brushThicknessSelector: RangeSlider by lazy { findViewById(R.id.range_slider) }
    private val drawingTypes: RadioGroup by lazy { findViewById(R.id.rg_drawing_types) }
    private val colorsPallet: RecyclerView by lazy { findViewById(R.id.rv_colors) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBrushThickness()
        initDrawingTypes()
        initColorsPallet()
    }

    private fun initBrushThickness() {
        brushThicknessSelector.addOnChangeListener { _, value, _ ->
            drawingBoard.setBrushThickness(value)
        }
    }

    private fun initDrawingTypes() {
        drawingTypes.setOnCheckedChangeListener { _, checkedId ->
            val drawingType = when (checkedId) {
                R.id.rb_drawing_pen -> Pen.default()
                R.id.rb_drawing_rectangle -> Rectangle.default()
                R.id.rb_drawing_circle -> Circle.default()
                R.id.rb_drawing_eraser -> Eraser()
                else -> null
            }

            drawingType?.let { drawingBoard.setDrawingType(it) }
        }
    }

    private fun initColorsPallet() {
        colorsPallet.adapter =
            ColorsAdapter(
                colors = ColorUiModel.palletColors(this),
                listener = { colorUiModel ->
                    drawingBoard.setBrushColor(colorUiModel.color)
                },
            )
    }
}
