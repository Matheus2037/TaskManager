package com.example.taskmanager.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface TaskDao {

    @Insert
    fun insertAll( vararg taskEntity: TaskEntity)

    @Update
    fun update (taskEntity: TaskEntity)

    @Delete
    fun delete (taskEntity: TaskEntity)

    @Query("SELECT * FROM taskEntity")
    fun getAll(): List<TaskEntity>

}