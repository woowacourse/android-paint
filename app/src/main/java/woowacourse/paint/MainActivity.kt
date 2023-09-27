package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.adapter.BoardColorAdapter
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
}
