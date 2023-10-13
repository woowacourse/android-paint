package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.brush.Circle
import woowacourse.paint.model.brush.Pen
import woowacourse.paint.model.brush.Rectangle
import woowacourse.paint.model.palettecolor.PaletteColor

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        initClickListener()
        initSlideTouchListener()
    }

    private fun initSlideTouchListener() {
        binding.rsThickness.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) = Unit
            override fun onStopTrackingTouch(slider: RangeSlider) {
                updateThickness()
            }
        })
    }

    private fun updateThickness() {
        binding.rsThickness.visibility = View.GONE
        val thickness = binding.rsThickness.values.last()
        binding.fdvBoard.updateThickness(thickness)
    }

    private fun initAdapter() {
        binding.rvPalette.adapter = PaletteAdapter(
            backgroundColors = PaletteColor.getAll(),
            onClick = ::onPaletteClick,
        )
    }

    private fun onPaletteClick(color: PaletteColor) {
        binding.rvPalette.visibility = View.GONE
        binding.fdvBoard.updateColor(color)
    }

    private fun initClickListener() {
        binding.btChangeColor.setOnClickListener {
            binding.rvPalette.visibility = View.VISIBLE
        }

        binding.btChangeThickness.setOnClickListener {
            binding.rsThickness.visibility = View.VISIBLE
        }
        binding.btChangeBrush.setOnClickListener {
            setBrushTypeVisibility(true)
        }
        binding.btPen.setOnClickListener {
            setBrushTypeVisibility(false)
            binding.fdvBoard.setBrushType(Pen())
        }
        binding.btRectangle.setOnClickListener {
            setBrushTypeVisibility(false)
            binding.fdvBoard.setBrushType(Rectangle())
        }
        binding.btCircle.setOnClickListener {
            setBrushTypeVisibility(false)
            binding.fdvBoard.setBrushType(Circle())
        }
        binding.btEraser.setOnClickListener {
            setBrushTypeVisibility(false)
            binding.fdvBoard.setEraseMode()
        }
    }

    private fun setBrushTypeVisibility(isVisible: Boolean) {
        with(binding) {
            btPen.isVisible = isVisible
            btRectangle.isVisible = isVisible
            btCircle.isVisible = isVisible
            btEraser.isVisible = isVisible
        }
    }
}
