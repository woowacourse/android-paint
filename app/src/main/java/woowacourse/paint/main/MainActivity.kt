package woowacourse.paint.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.Slider
import woowacourse.paint.R
import woowacourse.paint.customview.paint.BrushColor
import woowacourse.paint.customview.paint.PaintMode
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.main.adapter.BrushColorPaletteAdapter

class MainActivity : AppCompatActivity(), ColorClickListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val brushColorPaletteAdapter: BrushColorPaletteAdapter by lazy {
        BrushColorPaletteAdapter(colorClickListener = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setSupportActionBar(binding.toolbarMain)
        observePaintings()
        observeColorBox()
        observePaintMode()
        setupBrushColorPaletteAdapter()
        setupBrushThicknessRangeSliderChangeListener()
        setupChangeBrushColorPaletteButtonClickListener()
        setupChangeBrushThicknessButtonClickListener()
        setupChangePaintModeButtonClickListener()
        setupPaintModeButtonClickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_paint, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_paint_undo -> binding.pvMain.undo()
            R.id.menu_paint_redo -> binding.pvMain.redo()
            R.id.menu_paint_clear -> binding.pvMain.clear()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.updatePaintings(binding.pvMain.paintings)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupBrushColorPaletteAdapter() {
        binding.rvMainColorPalette.adapter = brushColorPaletteAdapter
    }

    private fun setupBrushThicknessRangeSliderChangeListener() {
        binding.sliderMainBrushThickness.addOnChangeListener(
            Slider.OnChangeListener { _, value, _ ->
                viewModel.updateBrushThickness(value)
            },
        )
    }

    private fun setupChangeBrushColorPaletteButtonClickListener() {
        binding.btnMainChangeBrushColor.setOnClickListener {
            binding.rvMainColorPalette.toggleVisibleOrGone()
        }
    }

    private fun setupChangeBrushThicknessButtonClickListener() {
        binding.btnMainChangeBrushThickness.setOnClickListener {
            binding.sliderMainBrushThickness.toggleVisibleOrGone()
        }
    }

    private fun setupChangePaintModeButtonClickListener() {
        binding.btnMainChangePaintMode.setOnClickListener {
            binding.btnMainPaintModePen.toggleVisibleOrGone()
            binding.btnMainPaintModeRectangle.toggleVisibleOrGone()
            binding.btnMainPaintModeOval.toggleVisibleOrGone()
            binding.btnMainPaintModeEraser.toggleVisibleOrGone()
        }
    }

    private fun setupPaintModeButtonClickListener() {
        binding.btnMainPaintModePen.setOnClickListener {
            viewModel.updatePaintMode(PaintMode.PEN)
        }
        binding.btnMainPaintModeRectangle.setOnClickListener {
            viewModel.updatePaintMode(PaintMode.RECTANGLE)
        }
        binding.btnMainPaintModeOval.setOnClickListener {
            viewModel.updatePaintMode(PaintMode.OVAL)
        }
        binding.btnMainPaintModeEraser.setOnClickListener {
            viewModel.updatePaintMode(PaintMode.ERASER)
        }
    }

    private fun observePaintings() {
        viewModel.paintings.observe(this) {
            binding.pvMain.setPaintings(it)
        }
    }

    private fun observeColorBox() {
        viewModel.brushColorBoxes.observe(this) {
            brushColorPaletteAdapter.submitList(it)
        }
    }

    private fun observePaintMode() {
        viewModel.paintMode.observe(this) {
            binding.pvMain.setPaintMode(it)
        }
    }

    override fun onColorClick(brushColor: BrushColor) {
        viewModel.updateBrushColorBoxes(brushColor)
    }

    private fun View.toggleVisibleOrGone() {
        this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}
