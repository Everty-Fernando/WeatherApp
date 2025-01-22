package br.estudo.everty.app_weather.utils.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

open class BaseActivity : AppCompatActivity {

    constructor() : super()
    constructor(@LayoutRes res: Int) : super(res)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}