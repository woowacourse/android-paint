package woowacourse.paint.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.launch
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initCollect()
        initView()
    }

    private fun initCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.brush.collect { brush ->
                    binding.cvCanvas.changeBrush(brush)
                }
            }
        }
    }

    private fun initView() {
        initRangeWidth()
        binding.rsWidth.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                viewModel.changeLineWidth(value)
            },
        )
    }

    private fun initRangeWidth() {
        val brush = viewModel.brush.value
        binding.rsWidth.valueFrom = brush.minWidth
        binding.rsWidth.valueTo = brush.maxWidth
        binding.rsWidth.setValues(brush.width)
    }
}
