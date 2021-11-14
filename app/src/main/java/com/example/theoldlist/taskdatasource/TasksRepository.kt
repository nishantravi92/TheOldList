package com.example.theoldlist.taskdatasource

class TasksRepository {

    suspend fun getAllTasks() {

    }

    suspend fun getTaskAt(index: Int): TaskPage {
        return if (index == 0) {
            TaskPage(
            listOf(
                TaskData("Test1", "Do laundry"),
                TaskData("Test2", "Do laundry"),
                TaskData("Test3", "Groceries"),
                TaskData("Tes4", "Watch movies")
            ), 1)
        } else if (index == 1) {
            TaskPage(
                listOf(
                    TaskData("Test5", "Watch movies")
                ), 2)
        } else {
            TaskPage(listOf(), null)
        }
    }
}

class TaskPage(val taskDataList: List<TaskData>, val nextPageNumber: Int?)