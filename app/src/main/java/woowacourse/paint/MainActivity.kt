package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val colors = listOf(
        PaintColor(R.color.red, true),
        PaintColor(R.color.orange, false),
        PaintColor(R.color.yellow, false),
        PaintColor(R.color.green, false),
        PaintColor(R.color.blue, false),
    )
    private lateinit var paintColorPaletteAdapter: PaintColorPaletteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBrushThicknessSettingTool()
        setupPaintColorPalette()
    }

    private fun setupBrushThicknessSettingTool() {
        binding.brushThicknessSettingTool.isTickVisible = false
        binding.brushThicknessSettingTool.addOnChangeListener { _, value, _ ->
            binding.paintBoard.setBrushThickness(value)
        }
    }

    private fun setupPaintColorPalette() {
        paintColorPaletteAdapter = PaintColorPaletteAdapter {
            paintColorPaletteAdapter.updateColors(
                colors.map { paintColor ->
                    if (paintColor.color == it) {
                        paintColor.copy(
                            isSelected = true,
                        )
                    } else {
                        paintColor.copy(isSelected = false)
                    }
                },
            )
            binding.paintBoard.setPaintColor(getColor(it))
        }
        paintColorPaletteAdapter.updateColors(colors)
        binding.paintColorPalette.adapter = paintColorPaletteAdapter
        binding.paintColorPalette.itemAnimator = null
    }
}
