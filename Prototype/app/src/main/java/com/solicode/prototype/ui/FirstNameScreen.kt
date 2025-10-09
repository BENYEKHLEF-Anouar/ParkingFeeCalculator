package com.solicode.prototype.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val persons = mutableStateListOf<Person>()

@Composable
fun FirstNameScreen() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter first name") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (text.isNotBlank()) {
                    persons.add(Person(text))
                    text = ""
                }
            }) {
                Text("Add")
            }
        }

        persons.forEach { p ->
            Text(
                "👤 ${p.name}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}