package com.example.theoldlist.edittask

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.theoldlist.core.*
import com.example.theoldlist.core.application.TheOldListApplication
import com.example.theoldlist.taskdatasource.TasksViewModel

class EditTaskBottomSheetFragment : BaseBottomSheetFragment(), PageFragmentHost {

    override val fManager: FragmentManager
        get() = activity?.supportFragmentManager!!

    override fun getRootUiAdapter(): RootUiAdapter<out UiModel> {
        val args: EditTaskBottomSheetFragmentArgs by navArgs()
        val tasksViewModel: TasksViewModel by
        viewModelsFactory { TasksViewModel((activity?.application as TheOldListApplication).tasksDao) }
        return EditTaskPageUiAdapter(
            args,
            tasksViewModel,
            tasksViewModel.viewModelScope,
            this as PageFragmentHost
        )
    }

    override fun getPageUiModelMapper(): UiModelMapper {
        return EditTaskPageUiModelMapper()
    }
}