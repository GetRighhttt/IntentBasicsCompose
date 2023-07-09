package com.example.intentbasics

import android.content.ActivityNotFoundException
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
                    /*
                    Now we will show how we can declare Explicit intents in various ways..
                     */

                    // regular explicit intent to open another activity using also scope function
                    Button(onClick = {
                        Intent(this@MainActivity, IntentReceivedActivity::class.java)
                            .also {
                                startActivity(it)
                            }
                    }) {
                        Text(text = "Go to next activity!")
                    }

                    /*
                    Intent to open google chrome from our application
                    We are using adb (android debug bridge) to get the package name from the terminal
                    We also wrap this intent in a variable so that we can use it again, and error
                    handle with try-catch
                     */
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_MAIN)
                        intent.`package` = "com.android.chrome"
                        try {
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            e.printStackTrace()
                        }
                    }) {
                        Text(text = "Open Chrome!")
                    }

                    /*
                    Intent to launch youtube with if statement to see if app is installed,
                    and we use resolve Activity to do so.
                     */
                    Button(onClick = {
                        Intent(Intent.ACTION_MAIN).also {
                            it.`package` = "com.google.android.youtube"
                            if (it.resolveActivity(packageManager) != null) {
                                startActivity(it)
                            }
                        }
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