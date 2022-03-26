package com.demolition.beginningcompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _textListState = MutableLiveData<String>("")
    val textListState: LiveData<String> = _textListState

    val btnTextState = MutableLiveData<String>("")

    fun onTextChanged(newText: String){
        _textListState.value = newText
    }

    fun onButtonClicked(newText: String){
       btnTextState.value = newText
    }
}