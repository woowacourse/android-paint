package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.paint.adapter.ColorPaletteAdapter
import woowacourse.paint.brush.ColorPalette
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityViewBinding.root)
        initPalettes()
        initListener()
    }

    private fun initPalettes() {
        activityViewBinding.rvColor.adapter =
            ColorPaletteAdapter(
                colorPalettes = ColorPalette.entries,
            ) { color ->
                activityViewBinding.customCanvas.changeColor(color)
            }
    }

    private fun initListener() {
        activityViewBinding.rangeSlider.addOnChangeListener { _, width, _ ->
            activityViewBinding.customCanvas.changeLineWidth(width)
        }
    }
}
