package com.u3f.ethereumsign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.u3f.ethereumsign.R
import com.u3f.ethereumsign.base.delegate.viewBinding
import com.u3f.ethereumsign.base.extension.navigateSafe
import com.u3f.ethereumsign.databinding.ActivityMainBinding
import com.u3f.ethereumsign.ui.navigation.NavManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    private val navController get() = Navigation.findNavController(this, R.id.nav_host_fragment)


    private val navManager: NavManager by lazy { NavManager() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        System.loadLibrary("TrustWalletCore")
        setContentView(binding.root)
        initNavManager()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }
    }


}