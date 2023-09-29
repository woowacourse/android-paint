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
                customView.setColor(getColor(R.color.red))
            }
            orange.setOnClickListener {
                customView.setColor(getColor(R.color.orange))
            }
            yellow.setOnClickListener {
                customView.setColor(getColor(R.color.yellow))
            }
            green.setOnClickListener {
                customView.setColor(getColor(R.color.green))
            }
            blue.setOnClickListener {
                customView.setColor(getColor(R.color.blue))
            }
        }
    }
}
