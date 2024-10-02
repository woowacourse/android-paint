package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val colorMap =
        mapOf(
            R.id.redChip to R.color.red,
            R.id.orangeChip to R.color.orange,
            R.id.yellowChip to R.color.yellow,
            R.id.greenChip to R.color.green,
            R.id.blueChip to R.color.blue,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeRangeSlider()
        initializeColorChips()
    }

    private fun initializeRangeSlider() {
        binding.rangeSlider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                binding.paintBoard.setStrokeWidth(value)
            }
        }
    }

    private fun initializeColorChips() {
        colorMap.forEach { (chipId, colorId) ->
            findViewById<View>(chipId).setOnClickListener {
                binding.paintBoard.setColor(colorId)
            }
        }
    }
}
