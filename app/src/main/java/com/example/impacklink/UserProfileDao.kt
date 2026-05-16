package com.example.impacklink

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserProfileDao {

    // කලින් දත්ත තිබුණොත් ඒක අලුත් දත්ත වලින් replace (update) කරනවා
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfile)

    // සේව් කරපු දත්ත ටික ආපහු ගන්න පාවිච්චි කරන query එක
    @Query("SELECT * FROM user_profile_table WHERE id = 1")
    suspend fun getUserProfile(): UserProfile?
}