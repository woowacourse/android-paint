package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        observeViewModel()
    }

    private fun initViewModel() {
        binding.vm = viewModel
    }

    private fun initBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewModel() {
        viewModel.selectedColor.observe(
            this,
            Observer { color ->
                binding.drawingView.setPaintColor(color = color.toColorInt())
            },
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
