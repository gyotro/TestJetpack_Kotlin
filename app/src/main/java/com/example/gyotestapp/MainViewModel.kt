package com.example.gyotestapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val textField = MutableLiveData("")

    val mutableLiveDataList = MutableLiveData<MutableList<String>>(mutableListOf("Gyo", "torogi", "sbouete"))

    fun onTextChanged(newText: String) {
        textField.value = newText
    }

    fun addValueToList(valueToad: String) {
        mutableLiveDataList.value?.add(valueToad)
    }

}