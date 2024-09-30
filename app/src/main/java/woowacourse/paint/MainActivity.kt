package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private lateinit var paintCanvas: PaintCanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        paintCanvas = findViewById(R.id.paint_canvas)
        initColorButtons()
        initWidthSeekBar()
    }

    private fun initColorButtons() {
        val colorButtons = mapOf(
            R.id.iv_red to PaintColor.RED,
            R.id.iv_orange to PaintColor.ORANGE,
            R.id.iv_yellow to PaintColor.YELLOW,
            R.id.iv_green to PaintColor.GREEN,
            R.id.iv_blue to PaintColor.BLUE,
            R.id.iv_black to PaintColor.BLACK
        )

        colorButtons.forEach { (buttonId, color) ->
            findViewById<ImageView>(buttonId).apply {
                setSelectedColor(color)
            }
        }
    }

    private fun initWidthSeekBar() {
        findViewById<RangeSlider>(R.id.stroke_width_slider).apply {
            valueFrom = 0.0f
            valueTo = 100.0f

            addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
                paintCanvas.selectStrokeWidth(value)
            })

        }
    }

    private fun ImageView.setSelectedColor(paintColor: PaintColor) {
        setOnClickListener {
            paintCanvas.selectColor(Color.parseColor(paintColor.value))
        }
    }
}
