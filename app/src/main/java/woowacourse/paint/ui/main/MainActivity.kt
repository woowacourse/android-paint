package woowacourse.paint.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    private val colorAdapter: BoardColorAdapter by lazy {
        BoardColorAdapter(viewModel::onChangeSelectedColor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        if (viewModel.drawnPaths.isNotEmpty()) binding.pbBoard.changeDrawnPaths(viewModel.drawnPaths)
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter() {
        binding.rvColors.adapter = colorAdapter
    }

    private fun setupViewModel() {
        viewModel.colors.observe(this) {
            colorAdapter.changeColorList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing.not()) viewModel.saveDrawnPaths(binding.pbBoard.drawnPaths)
    }
}
