package woowacourse.paint.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.domain.BrushColor
import woowacourse.paint.view.palette.PaletteAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initPaintView()
        initColorButtons()
        initWidthRangeSlider()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun initPaintView() {
        binding.paintViewModel = ViewModelProvider(this)[PaintViewModel::class.java]
    }

    private fun initColorButtons() {
        binding.mainPalettes.adapter = PaletteAdapter(BrushColor.paintColors) {
            binding.paintViewModel?.updateColor(it)
        }
    }

    private fun initWidthRangeSlider() {
        binding.mainWidthSlider.addOnChangeListener { _, value, _ ->
            binding.paintViewModel?.updateStrokeWidth(value)
        }
    }
}
