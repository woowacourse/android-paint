package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)

        val btnBlack: Button = findViewById(R.id.btn_black)
        val btnRed: Button = findViewById(R.id.btn_red)
        val btnOrange: Button = findViewById(R.id.btn_orange)
        val btnYellow: Button = findViewById(R.id.btn_yellow)
        val btnGreen: Button = findViewById(R.id.btn_green)
        val btnBlue: Button = findViewById(R.id.btn_blue)

        btnBlack.setOnClickListener {
            drawingView.updateBrushColor(Color.BLACK)
        }

        btnRed.setOnClickListener {
            drawingView.updateBrushColor(Color.RED)
        }

        btnOrange.setOnClickListener {
            drawingView.updateBrushColor(Color.parseColor(ORANGE_COLOR))
        }

        btnYellow.setOnClickListener {
            drawingView.updateBrushColor(Color.YELLOW)
        }

        btnGreen.setOnClickListener {
            drawingView.updateBrushColor(Color.GREEN)
        }

        btnBlue.setOnClickListener {
            drawingView.updateBrushColor(Color.BLUE)
        }

        val sliderBrushSize: Slider = findViewById(R.id.slider_brush_size)
        sliderBrushSize.addOnChangeListener { _, value, _ ->
            drawingView.updateBrushSize(value)
        }
    }

    companion object {
        private const val ORANGE_COLOR = "#FFA500"
    }
}
