package com.example.theoldlist.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import com.example.theoldlist.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frameLayout = layoutInflater.inflate(R.layout.base_bottom_sheet_dialog_fragment, null)
        val composeView: ComposeView = frameLayout.findViewById(R.id.compose_view)
        RenderBridge(
            composeView, getRootUiAdapter(), getPageUiModelMapper(),
            viewLifecycleOwner.lifecycleScope
        )
        return frameLayout
    }

    abstract fun getRootUiAdapter(): RootUiAdapter<out UiModel>

    abstract fun getPageUiModelMapper() : UiModelMapper
}