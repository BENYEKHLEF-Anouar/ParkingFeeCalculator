package com.example.realisation.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.realisation.data.*

@Composable
fun ParkingFeeScreen() {
    var startText by remember { mutableStateOf("") }
    var endText by remember { mutableStateOf("") }
    var selectedMode by remember { mutableStateOf(RoundingMode.Proportional) }
    var currentResult by remember { mutableStateOf<CalculationResult?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Input for start time
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Début : ", style = MaterialTheme.typography.bodyLarge)
            TextField(
                value = startText,
                onValueChange = { startText = it },
                label = { Text("HH:MM") },
                modifier = Modifier.weight(1f)
            )
        }

        // Input for end time
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Fin : ", style = MaterialTheme.typography.bodyLarge)
            TextField(
                value = endText,
                onValueChange = { endText = it },
                label = { Text("HH:MM") },
                modifier = Modifier.weight(1f)
            )
        }

        // Mode selection
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mode : ", style = MaterialTheme.typography.bodyLarge)
            RoundingMode.values().forEach { mode ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedMode == mode,
                        onClick = { selectedMode = mode }
                    )
                    Text(mode.name)
                }
            }
        }

        // Calculate button
        Button(
            onClick = {
                val result = calculate(startText, endText, selectedMode)
                if (result != null) {
                    currentResult = result
                    val item = HistoryItem(startText, endText, selectedMode, result.totalCost)
                    history.add(item)
                    if (history.size > 3) {
                        history.removeAt(0)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculer")
        }

        // Current result
        currentResult?.let { result ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Durée totale: ${result.totalMinutes / 60} h ${result.totalMinutes % 60} min",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    result.usages.forEach { usage ->
                        Text(
                            "${usage.slotLabel}: ${usage.minutes} min → ${"%.2f".format(usage.cost)} MAD"
                        )
                    }
                    Text(
                        "Total: ${"%.2f".format(result.totalCost)} MAD",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // History
        if (history.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Derniers calculs:",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    history.reversed().forEachIndexed { index, item ->
                        Text(
                            "${index + 1}. ${item.startTime} - ${item.endTime} (${item.mode.name}) : ${"%.2f".format(item.total)} MAD"
                        )
                    }
                }
            }
        }
    }
}