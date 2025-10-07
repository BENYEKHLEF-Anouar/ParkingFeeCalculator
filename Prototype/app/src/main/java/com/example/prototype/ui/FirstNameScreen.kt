package com.example.prototype.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color // Import the Color class


val persons = mutableStateListOf<Person>()

@Composable
fun FirstNameScreen () {
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
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF009688),
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = Color(0xFF009688),
                    cursorColor = Color(0xFF009688)
                ),
                shape = RoundedCornerShape(8.dp)
            )
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        persons.add(Person(text))
                        text = ""
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF009688),
                    contentColor = Color.White
                )
            ) {
//                Text("Add")
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Person" // Important for accessibility
                )
            }
        }

        persons.forEach { p ->
            Text(
                "👤 ${p.name}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
