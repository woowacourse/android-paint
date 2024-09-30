package woowacourse.paint

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rangeSlider = findViewById<RangeSlider>(R.id.range_slider)
        val customView = findViewById<CustomView>(R.id.painter)
        val red = findViewById<View>(R.id.red)
        val orange = findViewById<View>(R.id.orange)
        val yellow = findViewById<View>(R.id.yellow)
        val green = findViewById<View>(R.id.green)
        val blue = findViewById<View>(R.id.blue)

        rangeSlider.valueFrom = 0.0f
        rangeSlider.valueTo = 100.0f

        rangeSlider.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            customView.setStrokeWidth(value)
            // value.toInt() 활용
        })
        red.setOnClickListener {
            customView.setColor(Color.RED)
        }
        orange.setOnClickListener {
            customView.setColor(Color.parseColor("#FFA500"))
        }
        yellow.setOnClickListener {
            customView.setColor(Color.YELLOW)
        }
        green.setOnClickListener {
            customView.setColor(Color.GREEN)
        }
        blue.setOnClickListener {
            customView.setColor(Color.BLUE)
        }
    }
}
