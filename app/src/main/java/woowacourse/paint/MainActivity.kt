package woowacourse.paint

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSizeSelector()
        setupToolSelector()
        setUpColorSelector()
    }

    private fun setupSizeSelector() {
        binding.rvSize.apply {
            setValues(PaintBoard.DEFAULT_SIZE)
            setupSizeChangeListener()
        }
    }

    private fun RangeSlider.setupSizeChangeListener() {
        addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                binding.pbPaintBoard.changeSize(value)
            },
        )
    }

    private fun setupToolSelector() {
        binding.rvTools.adapter = ToolAdapter(Tools.values(), ::onToolClicked)
    }

    private fun onToolClicked(idx: Int) {
        binding.pbPaintBoard.changeTool(Tools.values()[idx])
    }

    private fun setUpColorSelector() {
        binding.rvColors.adapter = ColorAdapter(PaintBoard.COLORS, ::onColorClicked)
    }

    private fun onColorClicked(idx: Int) {
        binding.pbPaintBoard.changeColor(PaintBoard.COLORS[idx])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_clear -> {
                binding.pbPaintBoard.clear()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
