package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rangeSlider = findViewById<RangeSlider>(R.id.range_slider)
        val painterView = findViewById<PainterView>(R.id.painter)
        val red = findViewById<View>(R.id.red)
        val orange = findViewById<View>(R.id.orange)
        val yellow = findViewById<View>(R.id.yellow)
        val green = findViewById<View>(R.id.green)
        val blue = findViewById<View>(R.id.blue)
        val eraser = findViewById<View>(R.id.eraser)
        val brush = findViewById<View>(R.id.brush)
        val rectangle = findViewById<View>(R.id.rectangle)
        val circle = findViewById<View>(R.id.circle)
        val triangle = findViewById<View>(R.id.triangle)

        rangeSlider.valueFrom = 0.0f
        rangeSlider.valueTo = 100.0f

        rangeSlider.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                painterView.setStrokeWidth(value)
            },
        )
        red.setOnClickListener {
            painterView.setColor(Color.RED)
        }
        orange.setOnClickListener {
            painterView.setColor(Color.parseColor("#FFA500"))
        }
        yellow.setOnClickListener {
            painterView.setColor(Color.YELLOW)
        }
        green.setOnClickListener {
            painterView.setColor(Color.GREEN)
        }
        blue.setOnClickListener {
            painterView.setColor(Color.BLUE)
        }
        brush.setOnClickListener {
            painterView.setBrush()
        }
        rectangle.setOnClickListener {
            painterView.setSquare()
        }
        circle.setOnClickListener {
            painterView.setCircle()
        }
        triangle.setOnClickListener {
            painterView.setTriangle()
        }
        eraser.setOnClickListener {
            painterView.setEraser()
        }
    }
}
