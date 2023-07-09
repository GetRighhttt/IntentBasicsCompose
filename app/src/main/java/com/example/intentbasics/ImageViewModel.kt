package com.example.intentbasics

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

    var uriReceivedImage: Uri? by mutableStateOf(null)
        private set

    fun onReceivedImage(uri: Uri?) {
        this.uriReceivedImage = uri
    }
}