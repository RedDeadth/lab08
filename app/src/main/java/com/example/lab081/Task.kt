package com.example.lab081

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var description: String,
    var isCompleted: Boolean = false,

    var priority: Priority = Priority.MEDIUM,
    var category: String = "",

    var recurrenceInterval: RecurrenceInterval? = null
)

enum class Priority { LOW, MEDIUM, HIGH }

enum class RecurrenceInterval { DAILY, WEEKLY, MONTHLY }