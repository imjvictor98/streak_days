package com.cvj.app.streakdays.core.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GoalWithRelapses(
    @Embedded val goal: Goal,
    @Relation(
        parentColumn = "id",
        entityColumn = "goalId"
    )
    val relapses: List<Relapse>
)
