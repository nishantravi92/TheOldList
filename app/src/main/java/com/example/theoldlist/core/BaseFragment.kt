package com.example.theoldlist.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.theoldlist.R

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frameLayout = layoutInflater.inflate(R.layout.base_fragment, null)
        val composeView: ComposeView = frameLayout.findViewById(R.id.compose_view)
        RenderBridge(
            composeView, getRootUiAdapter(), getPageUiModelMapper(),
            viewLifecycleOwner.lifecycleScope
        )
        return frameLayout
    }

    abstract fun getRootUiAdapter(): RootUiAdapter<out UiModel>

    abstract fun getPageUiModelMapper(): UiModelMapper
}


inline fun <reified T : ViewModel> Fragment.viewModelsFactory(crossinline viewModelInitialization: () -> T): Lazy<T> {
    return viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModelInitialization() as T
            }
        }
    }
}