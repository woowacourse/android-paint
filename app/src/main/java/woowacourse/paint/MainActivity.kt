package woowacourse.paint

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.drawing.Circle
import woowacourse.paint.drawing.Pen
import woowacourse.paint.drawing.Rectangle

class MainActivity : AppCompatActivity() {
    private val drawingBoard: DrawingBoardView by lazy { findViewById(R.id.drawing_board) }
    private val rangeSlider: RangeSlider by lazy { findViewById(R.id.range_slider) }
    private val drawingTypes: RadioGroup by lazy { findViewById(R.id.rg_drawing_types) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.rv_colors) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rangeSlider.addOnChangeListener { _, value, _ ->
            drawingBoard.setBrushThickness(value)
        }

        drawingTypes.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_drawing_pen ->
                    drawingBoard.setDrawingType(
                        Pen.default(),
                    )

                R.id.rb_drawing_rectangle ->
                    drawingBoard.setDrawingType(
                        Rectangle.default(),
                    )

                R.id.rb_drawing_circle ->
                    drawingBoard.setDrawingType(
                        Circle.default(),
                    )
            }
        }

        recyclerView.adapter =
            ColorsAdapter(
                colors = ColorUiModel.palletColors(this),
                listener = { colorUiModel ->
                    drawingBoard.setBrushColor(colorUiModel.color)
                },
            )
    }
}
