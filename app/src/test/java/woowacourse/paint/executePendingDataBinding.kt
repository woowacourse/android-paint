package woowacourse.paint

import android.app.Activity
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Activity.executePendingDataBinding() {
    val rootView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0) ?: return
    val dataBinding = DataBindingUtil.getBinding<T>(rootView) ?: return
    dataBinding.executePendingBindings()
}
