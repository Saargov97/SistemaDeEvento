package com.example.easycheckin.shared

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.*
import androidx.navigation.fragment.findNavController

fun SavedStateHandle.toBundle(): Bundle = bundleOf(*keys().map { it to get<Any?>(it) }.toTypedArray())

@MainThread
inline fun <reified T : NavArgs> SavedStateHandle.navArgs(): NavArgsLazy<T> = NavArgsLazy(T::class, ::toBundle)

@MainThread
inline fun <reified VM : ViewModel> Fragment.navGraphViewModels(
    @IdRes navGraphId: Int,
    @IdRes navHostId: Int? = null
): Lazy<VM> = viewModels(ownerProducer = {
    val navController = navHostId?.let(requireActivity()::findNavController) ?: findNavController()
    navController.getBackStackEntry(navGraphId)
})

fun NavController.navigateSafe(
    directions: NavDirections,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
): Unit = navigateSafe(directions.actionId, directions.arguments, navOptions, navigatorExtras)

fun NavController.navigateSafe(
    @IdRes id: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (canNavigate(id))
        navigate(id, args, navOptions, navigatorExtras)
}

private fun NavController.canNavigate(@IdRes id: Int): Boolean {
    val action = currentDestination?.getAction(id) ?: graph.getAction(id)
    return action != null && currentDestination?.id != action.destinationId
}