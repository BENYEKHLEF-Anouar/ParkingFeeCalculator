package com.solicode.prototypelivecoding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

var Persons = mutableStateListOf<Person>()

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
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter first name") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color(0xFF009688),
                    cursorColor = Color(0xFF009688),
                    focusedLabelColor = Color.Black
                ),
                shape = RoundedCornerShape(9.dp)

            )
            Button(
                onClick = {
                    val cleaned = text.trim().replaceFirstChar { it.uppercase() }
                    if (cleaned.isNotBlank()) {

                        var newName = cleaned
                        while (Persons.any { it.name == newName }) {
                            newName += "+"
                        }

                        Persons.add(Person(newName))
                        text = ""
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF009688),
                    contentColor = Color.White
                )

            ) {
                // Text("Add")
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )

            }

        }

        Persons.forEach { p ->
            Text(
                "👤 ${p.name}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )

        }

//        for (p in Persons) {
//            if (Persons.contains(p))
//                Text(
//                    text = "👤 ${p.name} 2",
//                    style = MaterialTheme.typography.bodyLarge,
//                    modifier = Modifier.padding(8.dp)
//                )
//        }


    }


}