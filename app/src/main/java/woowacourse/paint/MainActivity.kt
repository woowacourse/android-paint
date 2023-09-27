package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val colors = listOf(
        R.color.red,
        R.color.orange,
        R.color.yellow,
        R.color.green,
        R.color.blue,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupPaintColorPalette()
    }

    private fun setupPaintColorPalette() {
        binding.paintColorPalette.adapter = PaintColorPaletteAdapter(colors)
    }
}
