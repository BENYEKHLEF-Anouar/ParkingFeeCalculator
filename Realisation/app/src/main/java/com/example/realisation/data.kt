package com.example.realisation.data

import androidx.compose.runtime.mutableStateListOf


data class TimeSlot(val label: String, val startMin: Int, val endMin: Int, val rate: Double)

enum class RoundingMode {
    Proportional,
    FullHour
}

data class SlotUsage(val slotLabel: String, val minutes: Int, val cost: Double)

data class CalculationResult(val usages: List<SlotUsage>, val totalMinutes: Int, val totalCost: Double)

data class HistoryItem(val startTime: String, val endTime: String, val mode: RoundingMode, val total: Double)

val slots = listOf(
    TimeSlot("Nuit", 0, 480, 4.0),
    TimeSlot("Jour", 480, 1140, 8.0),
    TimeSlot("Soir", 1140, 1440, 6.0)
)

val history = mutableStateListOf<HistoryItem>()
fun parseTime(timeStr: String): Int? {
    return try {
        val parts = timeStr.split(":")
        if (parts.size != 2) return null
        val h = parts[0].toIntOrNull() ?: return null
        val m = parts[1].toIntOrNull() ?: return null
        if (h < 0 || h > 23 || m < 0 || m > 59) return null
        h * 60 + m
    } catch (e: Exception) {
        null
    }
}

fun calculate(startStr: String, endStr: String, mode: RoundingMode): CalculationResult? {
    val startMin = parseTime(startStr) ?: return null
    val endMin = parseTime(endStr) ?: return null
    val isWrapping = endMin <= startMin
    val periods = if (!isWrapping) listOf(startMin to endMin) else listOf(startMin to 1440, 0 to endMin)
    val allUsages = mutableListOf<SlotUsage>()
    var totalMin = 0
    for ((pStart, pEnd) in periods) {
        for (slot in slots) {
            val overlap = maxOf(0, minOf(pEnd, slot.endMin) - maxOf(pStart, slot.startMin))
            if (overlap > 0) {
                totalMin += overlap
                val hours = if (mode == RoundingMode.Proportional) overlap / 60.0 else 1.0
                val cost = hours * slot.rate
                allUsages.add(SlotUsage(slot.label, overlap.toInt(), cost))
            }
        }
    }
    val totalCost = allUsages.sumOf { it.cost }
    return CalculationResult(allUsages, totalMin, totalCost)
}