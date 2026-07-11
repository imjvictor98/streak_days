package com.cvj.app.streakdays.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cvj.app.streakdays.data.local.entity.Goal
import com.cvj.app.streakdays.data.local.entity.Relapse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class DaoTest {
    private lateinit var database: StreakDatabase
    private lateinit var goalDao: GoalDao
    private lateinit var relapseDao: RelapseDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StreakDatabase::class.java
        ).allowMainThreadQueries().build()
        goalDao = database.goalDao()
        relapseDao = database.relapseDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetGoal() = runBlocking {
        val goal = Goal(name = "No Sugar", targetDurationDays = 30, startDate = LocalDate.of(2023, 1, 1))
        val id = goalDao.insertGoal(goal)
        
        val retrievedGoal = goalDao.getGoalById(id).first()
        assertEquals("No Sugar", retrievedGoal?.name)
        assertEquals(30, retrievedGoal?.targetDurationDays)
        assertEquals(LocalDate.of(2023, 1, 1), retrievedGoal?.startDate)
    }

    @Test
    fun insertAndGetRelapses() = runBlocking {
        val goal = Goal(name = "No Sugar", targetDurationDays = 30, startDate = LocalDate.of(2023, 1, 1))
        val goalId = goalDao.insertGoal(goal)

        val relapse1 = Relapse(goalId = goalId, timestamp = Instant.ofEpochMilli(1000))
        val relapse2 = Relapse(goalId = goalId, timestamp = Instant.ofEpochMilli(2000))

        relapseDao.insertRelapse(relapse1)
        relapseDao.insertRelapse(relapse2)

        val relapses = relapseDao.getRelapsesForGoal(goalId).first()
        assertEquals(2, relapses.size)
        // Ordered by timestamp DESC
        assertEquals(2000L, relapses[0].timestamp.toEpochMilli())
        assertEquals(1000L, relapses[1].timestamp.toEpochMilli())
    }
}
