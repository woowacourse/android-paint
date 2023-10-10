package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.adapter.color.ColorAdapter
import woowacourse.paint.adapter.tool.ToolAdapter
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.Tool

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val colorAdapter by lazy { ColorAdapter(::selectColor) }
    private val toolAdapter by lazy { ToolAdapter(::selectTool) }

    private fun selectColor(color: Int) {
        viewModel.updateColor(this.getColor(color))
    }

    private fun selectTool(tool: Tool) {
        viewModel.updateToolState(tool)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
        setupButton()
        setupRangeSlider()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.tools.observe(this) { tool ->
            binding.dpMain.setTool(tool)
        }

        viewModel.painting.observe(this) { lines ->
            binding.dpMain.setPainting(lines)
        }
    }

    private fun setupView() {
        with(binding) {
            rvMainColor.adapter = colorAdapter
            rvMainTools.adapter = toolAdapter
            rvMainColor.setHasFixedSize(true)
            rvMainTools.setHasFixedSize(true)
        }
    }

    private fun setupButton() {
        binding.btnMainColor.setOnClickListener { setVisibility(binding.rvMainColor) }
        binding.btnMainWidth.setOnClickListener { setVisibility(binding.rsMainWidth) }
        binding.btnMainTools.setOnClickListener { setVisibility(binding.rvMainTools) }
        binding.btnMainReset.setOnClickListener { viewModel.resetPaintings() }
        binding.btnMainUndo.setOnClickListener { viewModel.undoPaintings() }
        binding.btnMainRedo.setOnClickListener { viewModel.redoPaintings() }
    }

    private fun setVisibility(view: View) {
        when (view.visibility == View.GONE) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }

    private fun setupRangeSlider() {
        binding.rsMainWidth.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            viewModel.updateWidth(value)
        })
    }
}
