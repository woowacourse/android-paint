package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.Brush

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBinding()
        initPalette()
        setupRangeSliderListener()
        setupChangePaintColorListener()
        setupChangeStrokeSizeListener()
        setupChangeBrushListener()
        setupChangeBrush()
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
            R.id.menu_main_clear -> {
                binding.canvasMain.clear()
            }

            R.id.menu_main_undo -> {
                binding.canvasMain.undo()
            }
        }

        return true
    }

    private fun initBinding() {
        binding.vm = mainViewModel
    }

    private fun initPalette() {
        binding.rvMain.adapter = PaletteAdapter(mainViewModel.paletteColor) { color ->
            mainViewModel.paintColor = color
            binding.canvasMain.setPaintColor(color)
        }
        binding.rvMain.setHasFixedSize(true)
    }

    private fun setupRangeSliderListener() {
        binding.rsMain.addOnChangeListener { _, value, _ ->
            mainViewModel.strokeSize = value
            binding.canvasMain.setStrokeSize(value)
        }
    }

    private fun setupChangePaintColorListener() {
        binding.btnMainChangeColor.setOnClickListener {
            binding.rvMain.changeVisibility()
        }
    }

    private fun setupChangeStrokeSizeListener() {
        binding.btnMainChangeStrokeSize.setOnClickListener {
            binding.rsMain.changeVisibility()
        }
    }

    private fun setupChangeBrushListener() {
        binding.btnMainChangeBrush.setOnClickListener {
            binding.llMainBrush.changeVisibility()
        }
    }

    private fun setupChangeBrush() {
        binding.btnMainChangeBrushPen.setOnClickListener {
            mainViewModel.brush = Brush.PEN
            binding.canvasMain.setChangeBrush(Brush.PEN)
        }

        binding.btnMainChangeBrushRectangle.setOnClickListener {
            mainViewModel.brush = Brush.RECT
            binding.canvasMain.setChangeBrush(Brush.RECT)
        }

        binding.btnMainChangeBrushCircle.setOnClickListener {
            mainViewModel.brush = Brush.CIRCLE
            binding.canvasMain.setChangeBrush(Brush.CIRCLE)
        }

        binding.btnMainChangeBrushEraser.setOnClickListener {
            mainViewModel.brush = Brush.ERASER
            binding.canvasMain.setChangeBrush(Brush.ERASER)
        }
    }

    private fun View.changeVisibility() {
        visibility = when (visibility) {
            View.VISIBLE -> View.GONE
            else -> View.VISIBLE
        }
    }
}
