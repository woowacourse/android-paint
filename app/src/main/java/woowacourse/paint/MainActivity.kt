package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val paintView by lazy { binding.pvPaper }
    private val adapter = ColorsAdapter { model ->
        viewModel.pickColor(model)
        paintView.setupColor(model.color.colorCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
        setupColors()
        setupWidthSlider()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        viewModel.colors.observe(this) { colors ->
            adapter.submitList(colors)
        }
    }

    private fun setupColors() {
        binding.rvColors.adapter = adapter
        binding.rvColors.setHasFixedSize(true)
    }

    private fun setupWidthSlider() {
        binding.rsThicknessChanger.valueFrom = 0f
        binding.rsThicknessChanger.valueTo = 100f
        binding.rsThicknessChanger.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                paintView.setupWidth(value)
            },
        )
    }
}
