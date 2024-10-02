package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private val drawingBoard: DrawingBoardView by lazy { findViewById(R.id.drawing_board) }
    private val rangeSlider: RangeSlider by lazy { findViewById(R.id.range_slider) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.rv_colors) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rangeSlider.addOnChangeListener { _, value, _ ->
            drawingBoard.setBrushThickness(value)
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
