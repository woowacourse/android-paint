package woowacourse.paint.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.ui.main.brush.BrushTypeAdapter
import woowacourse.paint.ui.main.color.BrushColorAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    private val colorAdapter: BrushColorAdapter by lazy {
        BrushColorAdapter(viewModel::onChangeSelectedColor)
    }

    private val brushTypeAdapter: BrushTypeAdapter by lazy {
        BrushTypeAdapter(viewModel::onChangeSelectedBrushType)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter() {
        binding.rvColors.adapter = colorAdapter
        binding.rvBrushTypes.adapter = brushTypeAdapter
    }

    private fun setupViewModel() {
        viewModel.colors.observe(this) {
            colorAdapter.changeColorList(it)
        }
        viewModel.brushTypes.observe(this) {
            brushTypeAdapter.changeTypeList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
