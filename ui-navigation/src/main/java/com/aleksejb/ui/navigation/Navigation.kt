package com.aleksejb.ui.navigation

import java.lang.ClassCastException

fun <T : NavGraph> List<Navigator<NavGraph>>.navigate(destination: T): Boolean {
    forEach { graph ->
        try {
            if (graph.navigate(destination)) return true
        } catch (ignored: ClassCastException) { }
    }

    error("Unhandled navigator destination: $destination")
}