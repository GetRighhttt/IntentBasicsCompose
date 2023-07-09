package com.example.intentbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.intentbasics.ui.theme.IntentBasicsTheme

class IntentReceivedActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentBasicsTheme() {
                Text(text = "Received Activity")
            }
        }
    }
}