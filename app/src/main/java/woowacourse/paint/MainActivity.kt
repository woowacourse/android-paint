package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            rangeSlider.setValues(10f)

            rangeSlider.addOnChangeListener { _, value, _ ->
                customView.setStrokeWidth(value)
            }

            red.setOnClickListener {
                customView.setColor(Color.RED)
            }
            orange.setOnClickListener {
                customView.setColor(Color.ORANGE)
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
}
