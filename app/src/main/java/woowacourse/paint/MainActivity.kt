package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        initClickListener()
    }

    private fun initAdapter() {
        binding.rvPalette.adapter = PaletteAdapter(
            backgroundColors = PaletteColor.values().map {
                it.convertToArgb(this)
            },
            onClick = ::paletteClickEvent,
        )
    }

    private fun paletteClickEvent(@ColorInt color: Int) {
        binding.rvPalette.visibility = View.GONE
        binding.fdvBoard.updateColor(color)
    }

    private fun initClickListener() {
        binding.btChangeColor.setOnClickListener {
            binding.rvPalette.visibility = View.VISIBLE
        }
        binding.btChangeThickness.setOnClickListener {
            val thickness = binding.rsThickness.values.last()
            binding.fdvBoard.updateThickness(thickness)
        }
    }
}
