package com.example.prototype // Ensure this package matches your file's folder structure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prototype.ui.FirstNameScreen // Import your screen
import com.example.prototype.ui.theme.PrototypeTheme // Import your app's theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrototypeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    FirstNameScreen()
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewApp () {
    FirstNameScreen()
}