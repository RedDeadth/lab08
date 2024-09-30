package com.example.lab081

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {


    // Obtener todas las tareas
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>


    // Insertar una nueva tarea
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
    // Marcar una tarea como completada o no completada
    @Update
    suspend fun updateTask(task: Task)


    // Eliminar todas las tareas
    @Query("DELETE FROM tasks")
    fun deleteAllTasks()

    @Query("SELECT * FROM tasks WHERE description LIKE :searchQuery")
    fun searchTasks(searchQuery: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = :isCompleted")
    fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE category = :category")
    fun getTasksByCategory(category: String): Flow<List<Task>>
}