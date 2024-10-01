package woowacourse.paint

import android.graphics.Color
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
                colors = colors,
                listener = { colorUiModel ->
                    drawingBoard.setBrushColor(colorUiModel.color)
                },
            )
    }

    companion object {
        private val colors =
            listOf(
                ColorUiModel(0, Color.BLACK),
                ColorUiModel(1, Color.RED),
                ColorUiModel(3, Color.YELLOW),
                ColorUiModel(4, Color.GREEN),
                ColorUiModel(5, Color.BLUE),
                ColorUiModel(6, Color.MAGENTA),
            )
    }
}
