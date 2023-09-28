package woowacourse.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val colors = listOf(
        PaintColorModel(R.color.red, true),
        PaintColorModel(R.color.orange, false),
        PaintColorModel(R.color.yellow, false),
        PaintColorModel(R.color.green, false),
        PaintColorModel(R.color.blue, false),
    )
    private val brushes = listOf(
        BrushModel(Brush.PEN, true),
        BrushModel(Brush.HIGHLIGHTER, false),
    )
    private lateinit var paintColorPaletteAdapter: PaintColorPaletteAdapter
    private lateinit var brushSettingToolAdapter: BrushSettingToolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBrushSettingTool()
        setupBrushThicknessSettingTool()
        setupPaintColorPalette()
    }

    private fun setupBrushSettingTool() {
        brushSettingToolAdapter = BrushSettingToolAdapter()
        brushSettingToolAdapter.updateBrushes(brushes)
        binding.rvBrushSettingTool.adapter = brushSettingToolAdapter
        binding.rvBrushSettingTool.itemAnimator = null
    }

    private fun setupBrushThicknessSettingTool() {
        binding.rsBrushThicknessSettingTool.isTickVisible = false
        binding.rsBrushThicknessSettingTool.addOnChangeListener { _, value, _ ->
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
        binding.rvPaintColorPalette.adapter = paintColorPaletteAdapter
        binding.rvPaintColorPalette.itemAnimator = null
    }
}
