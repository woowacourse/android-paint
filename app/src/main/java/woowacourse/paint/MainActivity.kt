package woowacourse.paint

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.canvas.CustomColor
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val canvasView by lazy { binding.cvCanvas }
    private val adapter = ColorsAdapter { model ->
        viewModel.pickColor(model)
        canvasView.setupColor(model.color.colorCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupViewModel()
        setupCanvas()
        setupColors()
        setupWidthSlider()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        binding.viewModel = viewModel
        viewModel.colors.observe(this) { colors ->
            adapter.submitList(colors)
        }
    }

    private fun setupCanvas() {
        binding.cvCanvas.initPaint(minWidth, CustomColor.RED.colorCode)
    }

    private fun setupColors() {
        binding.rvColors.adapter = adapter
        binding.rvColors.setHasFixedSize(true)
        binding.rvColors.addItemDecoration(SpaceItemDecoration(getSpace()))
    }

    private fun getSpace(): Int {
        val colorWidth = resources.getDimensionPixelSize(R.dimen.color_item_size)
        val display = this.applicationContext?.resources?.displayMetrics
        val deviceWidth = display?.widthPixels

        deviceWidth?.let {
            return (deviceWidth - (colorWidth * 5)) / 4
        }
        return 10
    }

    private fun setupWidthSlider() {
        binding.rsThicknessChanger.valueFrom = minWidth
        binding.rsThicknessChanger.valueTo = maxWidth
        binding.rsThicknessChanger.addOnChangeListener(
            RangeSlider.OnChangeListener { _, value, _ ->
                canvasView.setupWidth(value)
            },
        )
    }

    companion object {
        private const val minWidth = 0f
        private const val maxWidth = 100f
    }
}
