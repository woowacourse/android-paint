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

        val btnRed: Button = findViewById(R.id.btn_red)
        val btnOrange: Button = findViewById(R.id.btn_orange)
        val btnYellow: Button = findViewById(R.id.btn_yellow)
        val btnGreen: Button = findViewById(R.id.btn_green)
        val btnBlue: Button = findViewById(R.id.btn_blue)

        btnRed.setOnClickListener {
            drawingView.setBrushColor(Color.RED)
        }

        btnOrange.setOnClickListener {
            drawingView.setBrushColor(Color.parseColor("#FFA500")) // 주황색
        }

        btnYellow.setOnClickListener {
            drawingView.setBrushColor(Color.YELLOW)
        }

        btnGreen.setOnClickListener {
            drawingView.setBrushColor(Color.GREEN)
        }

        btnBlue.setOnClickListener {
            drawingView.setBrushColor(Color.BLUE)
        }

        val sliderBrushSize: Slider = findViewById(R.id.slider_brush_size)
        sliderBrushSize.addOnChangeListener { _, value, _ ->
            drawingView.setBrushSize(value)
        }
    }
}
