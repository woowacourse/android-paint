package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.brush.BrushAdapter
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.palette.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initPalette()
        initBrush()
        setupRangeSliderListener()
        setupChangePaintColorListener()
        setupChangeStrokeSizeListener()
        setupChangeBrushListener()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.canvasMain.setPaintColor(mainViewModel.paintColor)
        binding.canvasMain.setStrokeSize(mainViewModel.strokeSize)
        binding.canvasMain.setChangeBrush(mainViewModel.brush)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_clear -> binding.canvasMain.clear()
            R.id.menu_main_undo -> binding.canvasMain.undo()
            R.id.menu_main_redo -> binding.canvasMain.redo()
        }

        return true
    }

    private fun initPalette() {
        binding.rvMainPalette.adapter = PaletteAdapter(mainViewModel.paletteColor) { color ->
            mainViewModel.paintColor = color
            binding.canvasMain.setPaintColor(color)
        }
        binding.rvMainPalette.setHasFixedSize(true)
    }

    private fun initBrush() {
        binding.rvMainBrush.adapter = BrushAdapter(mainViewModel.brushes) { brush ->
            mainViewModel.brush = brush
            binding.canvasMain.setChangeBrush(brush)
        }
        binding.rvMainBrush.setHasFixedSize(true)
    }

    private fun setupRangeSliderListener() {
        binding.rsMain.addOnChangeListener { _, value, _ ->
            mainViewModel.strokeSize = value
            binding.canvasMain.setStrokeSize(value)
        }
    }

    private fun setupChangePaintColorListener() {
        binding.btnMainChangeColor.setOnClickListener {
            binding.rvMainPalette.changeVisibility()
        }
    }

    private fun setupChangeStrokeSizeListener() {
        binding.btnMainChangeStrokeSize.setOnClickListener {
            binding.rsMain.changeVisibility()
        }
    }

    private fun setupChangeBrushListener() {
        binding.btnMainChangeBrush.setOnClickListener {
            binding.rvMainBrush.changeVisibility()
        }
    }

    private fun View.changeVisibility() {
        visibility = when (visibility) {
            View.VISIBLE -> View.GONE
            else -> View.VISIBLE
        }
    }
}
