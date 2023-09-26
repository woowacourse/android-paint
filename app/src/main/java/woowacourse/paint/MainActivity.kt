package woowacourse.paint

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import woowacourse.paint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var viewBinding: ActivityMainBinding
    private var thickness = 0f
    private var color = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setListener()
    }

    private fun setListener() {
        viewBinding.btnColorChange.setOnClickListener {
            viewBinding.customCanvas.changeColor(color)
        }

        viewBinding.btnThicknessChange.setOnClickListener {
            viewBinding.customCanvas.changeThickness(thickness)
        }

        viewBinding.rangebar.addOnChangeListener { _, value, _ ->
            thickness = value
        }
        viewBinding.btnRed.setOnClickListener(this)
        viewBinding.btnOrange.setOnClickListener(this)
        viewBinding.btnYellow.setOnClickListener(this)
        viewBinding.btnGreen.setOnClickListener(this)
        viewBinding.btnBlue.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_red -> color = getColor(R.color.red)
            R.id.btn_orange -> color = getColor(R.color.orange)
            R.id.btn_yellow -> color = getColor(R.color.yellow)
            R.id.btn_green -> color = getColor(R.color.green)
            R.id.btn_blue -> color = getColor(R.color.blue)
        }
    }
}
