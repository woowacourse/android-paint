package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val colorAdapter by lazy { ColorAdapter(::selectColor, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        attachColorAdapter()

        binding.btnMainColor.setOnClickListener {
            binding.rvMainColor.visibility = View.VISIBLE

        }

        binding.btnMainWidth.setOnClickListener {
            binding.rsMainWidth.visibility = View.VISIBLE
        }



        binding.rsMainWidth.valueFrom = MIN_VALUE
        binding.rsMainWidth.valueTo = MAX_VALUE
        binding.rsMainWidth.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            binding.dpMain.setWidth(value)
        })
    }

    private fun selectColor(color: Color) {
        //
    }

    private fun attachColorAdapter() {
        binding.rvMainColor.adapter = colorAdapter
        binding.rvMainColor.setHasFixedSize(true)
    }

    companion object {
        private const val MIN_VALUE = 0.0f
        private const val MAX_VALUE = 100.0f
    }
}

