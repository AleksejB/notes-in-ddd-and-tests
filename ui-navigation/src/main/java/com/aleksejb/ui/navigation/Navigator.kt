package com.aleksejb.ui.navigation

interface Navigator<T : NavGraph> {
    fun navigate(destination: T): Boolean
    fun navigateBack() {}

    companion object : Navigator<NavGraph> {
        override fun navigate(destination: NavGraph): Boolean = false
        override fun navigateBack() = Unit
    }
}