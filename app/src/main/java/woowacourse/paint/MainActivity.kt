package woowacourse.paint

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import woowacourse.paint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val colorAdapter by lazy { ColorAdapter(::selectColor) }

    private fun selectColor(color: Int) {
        binding.dpMain.changeColor(color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        attachColorAdapter()
        setupButton()
        setupRangeSlider()
    }

    private fun attachColorAdapter() {
        binding.rvMainColor.adapter = colorAdapter
        binding.rvMainColor.setHasFixedSize(true)
    }

    private fun setupButton() {
        binding.btnMainColor.setOnClickListener { setVisibility(binding.rvMainColor) }
        binding.btnMainWidth.setOnClickListener { setVisibility(binding.rsMainWidth) }
    }

    private fun setVisibility(view: View) {
        when (view.visibility == View.GONE) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }

    private fun setupRangeSlider() {
        binding.rsMainWidth.valueFrom = MIN_VALUE
        binding.rsMainWidth.valueTo = MAX_VALUE
        selectWidth()
    }

    private fun selectWidth() {
        binding.rsMainWidth.addOnChangeListener(RangeSlider.OnChangeListener { _, value, _ ->
            binding.dpMain.changeWidth(value)
        })
    }

    companion object {
        private const val MIN_VALUE = 0.0f
        private const val MAX_VALUE = 100.0f
    }
}
