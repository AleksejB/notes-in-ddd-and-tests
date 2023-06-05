package com.aleksejb.core.ui.appyx

import androidx.lifecycle.*
import com.bumble.appyx.core.plugin.NodeLifecycleAware

class ViewModelOwnerPlugin : NodeLifecycleAware, ViewModelStoreOwner, LifecycleEventObserver {

    private val _viewModelStore: ViewModelStore by lazy { ViewModelStore() }

    override val viewModelStore: ViewModelStore
        get() = _viewModelStore

    override fun onCreate(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            viewModelStore.clear()
        }
    }
}