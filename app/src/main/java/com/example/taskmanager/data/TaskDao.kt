package com.example.taskmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert
    suspend fun insertAll( vararg taskEntity: TaskEntity)

    @Update
    fun update (taskEntity: TaskEntity)

    @Delete
    suspend fun delete (taskEntity: TaskEntity)

    @Query("SELECT * FROM taskEntity")
    suspend fun getAll(): List<TaskEntity>

}