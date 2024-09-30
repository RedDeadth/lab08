package com.example.lab081
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks = taskDao.getAllTasks().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _filterType = MutableStateFlow(FilterType.ALL)
    val filterType: StateFlow<FilterType> = _filterType

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _sortType = MutableStateFlow(SortType.DATE)
    val sortType: StateFlow<SortType> = _sortType

    fun addTask(description: String) {
        viewModelScope.launch {
            taskDao.insertTask(Task(description = description))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            taskDao.deleteAllTasks()
        }
    }

    fun setFilter(filterType: FilterType) {
        _filterType.value = filterType
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSortType(sortType: SortType) {
        _sortType.value = sortType
    }
}

enum class FilterType { ALL, COMPLETED, PENDING }
enum class SortType { NAME, DATE, STATUS }