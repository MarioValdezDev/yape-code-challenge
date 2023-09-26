package mx.mariovaldez.yapecodechallenge.ktx

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputEditText
import mx.mariovaldez.yapecodechallenge.ui.delegates.FragmentViewBindingDelegate

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.showKeyboard(searchTextInputEditText: TextInputEditText) {
    view?.let { activity?.showKeyboard(searchTextInputEditText) }
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
