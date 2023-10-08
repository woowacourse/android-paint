package woowacourse.paint.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.main.adapter.ColorAdapter
import woowacourse.paint.model.DrawMode

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewModel()

        setColorsRecyclerview()
        setBrushSizeListener()
        setDrawModeListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_paint_board, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_undo -> binding.ctvPaintBoard.undo()
            R.id.action_redo -> binding.ctvPaintBoard.redo()
            R.id.action_delete_all -> binding.ctvPaintBoard.deleteAll()
        }
        return true
    }

    private fun setViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setColorsRecyclerview() {
        binding.rvColors.apply {
            adapter = ColorAdapter {
                viewModel.setBrushColor(it)
            }
            setHasFixedSize(true)
        }
    }

    private fun setBrushSizeListener() {
        binding.sliderBrushSize.addOnChangeListener { _, value, _ ->
            viewModel.setBrushSize(value)
        }
    }

    private fun setDrawModeListener() {
        binding.rgDrawMode.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbBrush.id -> viewModel.setDrawMode(DrawMode.BRUSH)
                binding.rbSquare.id -> viewModel.setDrawMode(DrawMode.SQUARE)
                binding.rbCircle.id -> viewModel.setDrawMode(DrawMode.CIRCLE)
                binding.rbEraser.id -> viewModel.setDrawMode(DrawMode.ERASER)
            }
        }
    }
}
