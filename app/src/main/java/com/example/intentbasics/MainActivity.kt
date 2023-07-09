package com.example.intentbasics

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.intentbasics.ui.theme.IntentBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentBasicsTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // here we will demonstrate various forms of intents that we can use
                    // regular explicit intent to open another activity
                    Button(onClick = {
                        Intent(this@MainActivity, IntentReceivedActivity::class.java)
                            .also {
                                startActivity(it)
                            }
                    }) {
                        Text(text = "Go to next activity!")
                    }

                    // implicit intent to open another application from your application in try-catch
                    Button(onClick = {

                    }) {
                        Text(text = "Open Chrome!")
                    }

                    // implicit intent to open app using if for null check and resolve
                    Button(onClick = {

                    }) {
                        Text(text = "Open Youtube")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentBasicsTheme {
        Greeting("Android")
    }
}