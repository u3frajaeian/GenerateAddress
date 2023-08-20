package com.u3f.ethereumsign.base.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.u3f.ethereumsign.R
import timber.log.Timber


fun Fragment.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
    if (canNavigate()) findNavController().navigate(directions, navOptions)
}

/**
 * Returns true if the navigation controller is still pointing at 'this' fragment, or false
 * if it already navigated away.
 */
fun Fragment.canNavigate(): Boolean {
    val navController = findNavController()
    val destinationIdInNavController = navController.currentDestination?.id

    // add tag_navigation_destination_id to your res\values\ids.xml so that it's unique:
    val destinationIdOfThisFragment =
        view?.getTag(R.id.tag_navigation_destination_id) ?: destinationIdInNavController

    // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
    return if (destinationIdInNavController == destinationIdOfThisFragment) {
        view?.setTag(R.id.tag_navigation_destination_id, destinationIdOfThisFragment)
        true
    } else {
        Timber.d("May not navigate: current destination is not the current fragment.")
        false
    }
}

