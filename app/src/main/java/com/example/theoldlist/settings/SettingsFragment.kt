package com.example.theoldlist.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.theoldlist.core.*

class SettingsFragment() : BaseFragment() {


    override fun getRootUiAdapter(): RootUiAdapter<out UiModel> {
        val dummyViewModel: DummyViewModel by viewModelsFactory { DummyViewModel() }
        return SettingsPageRootUiAdapter(findNavController(), dummyViewModel.viewModelScope)
    }

    override fun getPageUiModelMapper(): UiModelMapper {
        return SettingsPageUiModelMapper()
    }
}

class DummyViewModel : ViewModel()