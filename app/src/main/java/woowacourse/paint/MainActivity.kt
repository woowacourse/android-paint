package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.adapter.ColorAdapter
import woowacourse.paint.adapter.ToolAdapter
import woowacourse.paint.customview.PaintBoard
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.Tools

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels()
    private val colorAdapter: ColorAdapter = ColorAdapter(PaintBoard.COLORS, ::onColorClicked)
    private val toolAdapter: ToolAdapter =
        ToolAdapter(Tools.values().map { it.stringRes }, ::onToolClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSizeSelector()
        setupToolSelector()
        setUpColorSelector()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        viewModel.saveHistory(binding.pbPaintBoard.history)
        viewModel.savePainting(binding.pbPaintBoard.painting)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        binding.pbPaintBoard.restoreHistory(viewModel.history)
        binding.pbPaintBoard.restorePainting(viewModel.painting)
        colorAdapter.restoreSelectedColor(viewModel.colorIdx)
        toolAdapter.restoreSelectedTool(viewModel.toolIdx)
    }

    private fun setupSizeSelector() {
        binding.rvSize.apply {
            setValues(PaintBoard.DEFAULT_SIZE)
            setupSizeChangeListener()
        }
    }

    private fun RangeSlider.setupSizeChangeListener() {
        addOnChangeListener { _, value, _ ->
            binding.pbPaintBoard.changeSize(value)
        }
    }

    private fun setupToolSelector() {
        binding.rvTools.adapter = toolAdapter
    }

    private fun onToolClicked(idx: Int) {
        binding.pbPaintBoard.changeTool(Tools.values()[idx])
        viewModel.saveToolIdx(idx)
    }

    private fun setUpColorSelector() {
        binding.rvColors.adapter = colorAdapter
    }

    private fun onColorClicked(idx: Int) {
        binding.pbPaintBoard.changeColor(PaintBoard.COLORS[idx])
        viewModel.saveColorIdx(idx)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_clear -> binding.pbPaintBoard.clear()
            R.id.menu_undo -> binding.pbPaintBoard.undo()
            R.id.menu_redo -> binding.pbPaintBoard.redo()
        }

        return super.onOptionsItemSelected(item)
    }
}
