package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()
    }

    private fun setAdapter() {
        binding.rvPalette.adapter = PaletteAdapter(
            backgroundColors = PaletteColor.values().map {
                it.convertToArgb(this)
            },
        ) { paletteClickEvent(it) }
    }

    private fun paletteClickEvent(color: Int) {
    }
}
