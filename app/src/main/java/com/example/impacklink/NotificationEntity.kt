package com.example.impacklink

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val role: String, // NGO, VOLUNTEER, DONOR
    val statusText: String,
    val iconType: String, // TICK, WARNING, MESSAGE
    val timestamp: Long = System.currentTimeMillis()
)

@Dao
interface NotificationDao {
    @Insert
    suspend fun insert(notification: NotificationEntity)

    @Query("SELECT * FROM notifications WHERE role = :roleType ORDER BY timestamp DESC")
    suspend fun getNotificationsByRole(roleType: String): List<NotificationEntity>
    
    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    suspend fun getAllNotifications(): List<NotificationEntity>
}