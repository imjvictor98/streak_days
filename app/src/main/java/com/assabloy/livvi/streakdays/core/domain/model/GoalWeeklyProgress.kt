package com.assabloy.livvi.streakdays.core.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

/**
 * Representa o estado de um dia na semana do widget.
 */
enum class DailyProgressState {
    COMPLETED,
    TODAY_PENDING,
    FUTURE_OR_MISSED
}

/**
 * Calcula o estado de cada dia da semana atual (Domingo a Sábado) baseado no streak atual.
 * 
 * Retorna uma lista de 7 elementos, onde o índice 0 é Domingo e o 6 é Sábado.
 */
fun Goal.getWeeklyProgress(today: LocalDate = LocalDate.now()): List<DailyProgressState> {
    val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    
    // Se currentStreakDays = 0, a data de início do streak seria no futuro (inválido), 
    // então consideramos que não há dias completados no streak atual.
    val streakStartDate = if (currentStreakDays > 0) {
        today.minusDays(currentStreakDays - 1L)
    } else {
        today.plusDays(1) // Uma data no futuro para garantir que nada seja "COMPLETED"
    }

    return (0..6L).map { i ->
        val currentDay = startOfWeek.plusDays(i)
        
        when {
            currentDay.isAfter(today) -> DailyProgressState.FUTURE_OR_MISSED
            currentDay == today -> {
                // Hoje sempre completamos se o streak é > 0 e streakStartDate <= hoje.
                // Como StreakCalculator adiciona o dia atual automaticamente no streak,
                // consideraremos TODAY_PENDING apenas como o indicador visual pro usuário,
                // ou COMPLETED se já estiver feito. O StreakDays não tem o conceito 
                // explícito de "feito hoje", ele conta o dia a menos que haja relapse.
                // Para o widget, vamos pintar de COMPLETED.
                if (currentStreakDays > 0) DailyProgressState.COMPLETED else DailyProgressState.FUTURE_OR_MISSED
            }
            currentDay.isBefore(streakStartDate) -> DailyProgressState.FUTURE_OR_MISSED
            else -> DailyProgressState.COMPLETED
        }
    }
}
