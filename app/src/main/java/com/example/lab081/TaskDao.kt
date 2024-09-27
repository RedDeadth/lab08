package com.example.lab081

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM tasks WHERE description LIKE :searchQuery")
    fun searchTasks(searchQuery: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = :isCompleted")
    fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM tasks ORDER BY
            CASE WHEN :orderBy = 'name' THEN description END ASC,
    CASE WHEN :orderBy = 'date' THEN createdAt END DESC,
    CASE WHEN :orderBy = 'status' THEN isCompleted END ASC")
    fun getTasksOrdered(orderBy: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE category = :category")
    fun getTasksByCategory(category: String): Flow<List<Task>>
}