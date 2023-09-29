package woowacourse.paint

import android.widget.SeekBar
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setColorPalette")
fun BoardView.setColorPalette(colorPalette: ColorPalette?) {
    if (colorPalette == null) return
    changeColor(colorPalette)
}

@BindingAdapter("app:setWidth")
fun BoardView.setWidth(width: Float?) {
    if (width == null) return
    setWidth(width)
}

@BindingAdapter("app:eraseAll")
fun BoardView.eraseAll(isErasing: Boolean?) {
    if (isErasing == null) return
    if (isErasing) erase()
}

@BindingAdapter("app:setupSeekbar")
fun SeekBar.setupSeekbar(viewModel: MainViewModel) {
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            viewModel.changeWidth(progress.toFloat())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })
}
