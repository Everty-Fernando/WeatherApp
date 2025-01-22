package br.com.everty.shared.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Fragment.runAfter(
    delayTimeMillis: Long = 1000L,
    runAfter: () -> Unit = {},
) {
    this.lifecycleScope.launch {
        delay(delayTimeMillis)
        runAfter()
    }
}