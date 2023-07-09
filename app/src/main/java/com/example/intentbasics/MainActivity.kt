package com.example.intentbasics

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.intentbasics.ui.theme.IntentBasicsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ImageViewModel by viewModels()
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
                    Image to be received from Chrome when long clicked in application
                     */
                    viewModel.uriReceivedImage?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = "Image received from Chrome"
                        )
                    }

                    /*
                    Now we will show how we can declare EXPLICIT intents in various ways..
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

                    /*
                    Now we will show how we can declare IMPLICIT intents...
                     */

                    /*
                    Here we will show how we can send an email from out application by specifying
                    the type, and using intent extras to send parameters that can be used to pass
                    information with intents. We also pass in queries using resolve
                    Activity with this intent.
                     */
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_EMAIL, arrayOf(
                                    "stefanbayne@gmail.com",
                                    "sfcbayne@yahoo.com",
                                    "stb9395@yahoo.com",
                                    "vincebaynejr@gmail.com"
                                )
                            )
                            putExtra(Intent.EXTRA_SUBJECT, "Sending Email from My Application!")
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Hey! Let me know if you guys receive this email! " +
                                        "I'm sending this from an application on my computer " +
                                        "that I am using to practice sharing information!"
                            )
                        }
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                    }) {
                        Text(text = "Send Email!")
                    }
                }
            }
        }
    }

    /*
    Set the launchMode as SingleTop so when we receive the new image from chrome, the application
    won't launch another task(rerun the application), and instead will just receive the image.

    onNewIntent is called when receiving a new intent from another application.
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // check build version to see if on the required API
        val imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.onReceivedImage(imageUri)
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