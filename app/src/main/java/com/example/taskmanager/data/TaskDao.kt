package com.example.taskmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertAll( vararg taskEntity: TaskEntity)

    @Update
    suspend fun update (taskEntity: TaskEntity)

    @Delete
    suspend fun delete (taskEntity: TaskEntity)

    @Query("SELECT * FROM taskEntity")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskEntity")
    suspend fun getAllSuspend(): List<TaskEntity>

    @Query("SELECT * FROM taskEntity WHERE id = :id")
    suspend fun getTaskById(id: Long): TaskEntity?

}