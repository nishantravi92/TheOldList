package com.example.theoldlist.core

// Later on use this for animating items in and out.
class ListState(initialContent: List<UiModel?>) {

    val items = initialContent.toMutableList()

    fun updateList(index: Int, uiModel: UiModel) {
        if (index >= items.size) {
            items.add(uiModel)
        } else {
            items[index] = uiModel
        }
    }
}