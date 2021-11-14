package com.example.theoldlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.theoldlist.core.RootUiAdapter
import com.example.theoldlist.core.UiModelMapper
import com.example.theoldlist.taskdatasource.TasksViewModel
import com.example.theoldlist.taskpage.TasksPageUiAdapter
import com.example.theoldlist.taskpage.TasksPageUiModelMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TasksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tasksListViewModel: TasksViewModel by viewModels()
        val frameLayout = layoutInflater.inflate(R.layout.tasks_fragment, null)
        val composeView:ComposeView = frameLayout.findViewById(R.id.compose_view)
        // Add a Render Bridge here.
        RenderBridge(composeView, TasksPageUiAdapter(tasksListViewModel), TasksPageUiModelMapper(),
            viewLifecycleOwner.lifecycleScope)

        return frameLayout
    }
}

class RenderBridge(
    composeView: ComposeView,
    rootUiAdapter: RootUiAdapter,
    uiModelMapper: UiModelMapper,
    scope: CoroutineScope
) {

    val uiModel = mutableStateOf(rootUiAdapter.loadingUiModel)

    init {
        composeView.setContent {
            uiModelMapper.mapUiModel(uiModel.value)
        }
        scope.launch { uiModel.value = rootUiAdapter.createUiModel() }
    }

}