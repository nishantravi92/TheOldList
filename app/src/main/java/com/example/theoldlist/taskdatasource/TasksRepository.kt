package com.example.theoldlist.taskdatasource

class TasksRepository(private val tasksDao: TasksDao) {


    suspend fun getTaskAt(index: Int): TaskPage {
        val list1ToReturn = mutableListOf(Task("Test1", "Do laundry"),
            Task("Test2", "Do laundry"),
            Task("Test3", "Groceries"),
            Task("Tes4", "Watch movies")
        )
            list1ToReturn.add(Task("Tes45", "Go away task. You will be deleted"))
        return if (index == 0) {
            TaskPage(
                list1ToReturn.toList(), 1, null)
        } else if (index == 1) {
            TaskPage(
                listOf(
                    Task("Test5", "Watch movies")
                ), 2, 1)
        } else {
            TaskPage(listOf(), null, null)
        }
    }

    fun markTaskAsCompleted(task: Task) {

    }
}

class TaskPage(val taskList: List<Task>, val nextPageNumber: Int?, val prevPageNumber: Int?)