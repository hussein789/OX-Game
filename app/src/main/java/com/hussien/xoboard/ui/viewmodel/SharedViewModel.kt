package com.hussien.xoboard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {


    private val _navigateToGameScreen = MutableLiveData<Boolean>()
    val navigateToGameScreen:LiveData<Boolean> get() = _navigateToGameScreen

    var player1Name = ""
    var player2Name = ""

    fun isValidCred(player1Name: String, player2Name: String): Boolean {
        return player1Name.isNotEmpty() && player2Name.isNotEmpty()
    }

    fun onStartGameClicked(player1Name: String, player2Name: String) {
        if(isValidCred(player1Name,player2Name)){
            this.player1Name = player1Name
            this.player2Name = player2Name

            _navigateToGameScreen.value = true
        }
    }

}