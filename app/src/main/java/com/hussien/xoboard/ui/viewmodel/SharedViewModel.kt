package com.hussien.xoboard.ui.viewmodel

import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hussien.xoboard.R
import com.hussien.xoboard.utils.SingleLiveEvent

class SharedViewModel : ViewModel() {


    private val _navigateToGameScreen = SingleLiveEvent<Boolean>()
    val navigateToGameScreen: SingleLiveEvent<Boolean> get() = _navigateToGameScreen

    private val _player1Name = MutableLiveData<String>()
    val player1Name: LiveData<String> get() = _player1Name

    private val _player2Name = MutableLiveData<String>()
    val player2Name: LiveData<String> get() = _player2Name

    private val _player1Score = MutableLiveData<Int>(0)
    val player1Score: LiveData<Int> get() = _player1Score

    private val _player2Score = MutableLiveData<Int>(0)
    val player2Score: LiveData<Int> get() = _player2Score

    private val _clearBoard = MutableLiveData<Boolean>()
    val clearBoard:LiveData<Boolean> get() = _clearBoard

    private val _playerWinGame = MutableLiveData<String>()
    val playerWinGame: LiveData<String> get() = _playerWinGame

    var turn = 1
    private var endGame = false
    var board = arrayOf(arrayOf(0,0,0),arrayOf(0,0,0),arrayOf(0,0,0))


    fun isValidCred(player1Name: String, player2Name: String): Boolean {
        return player1Name.isNotEmpty() && player2Name.isNotEmpty()
    }

    fun onStartGameClicked(player1Name: String, player2Name: String) {
        if (isValidCred(player1Name, player2Name)) {
            this._player1Name.value = player1Name
            this._player2Name.value = player2Name
            onNewGameClicked()
            _navigateToGameScreen.value = true
        }
    }

    fun onNewGameClicked() {
        _clearBoard.value = true
        endGame = false
        board = arrayOf(arrayOf(0,0,0),arrayOf(0,0,0),arrayOf(0,0,0))
        turn = 1
    }

    fun onCellMarked(it: ImageView, row: Int, col: Int) {
        if (it.drawable == null && !endGame) {
            if(turn == 1){
                onPlayer1Play(it, row, col)
            } else {
                onPlayer2Play(it, row, col)
            }
        }
    }

    private fun onPlayer2Play(it: ImageView, row: Int, col: Int) {
        it.setImageResource(R.drawable.red_o)
        val player2Win = checkIfWin(row, col,2)
        if (player2Win) {
            _player2Score.value = _player2Score.value?.inc()
            _playerWinGame.value = _player2Name.value
            endGame = true
        } else {
            turn = 1
        }

    }

    private fun onPlayer1Play(it: ImageView, row: Int, col: Int) {
        it.setImageResource(R.drawable.blue_x)
        val player1Win = checkIfWin(row, col,1)
        if (player1Win) {
            _player1Score.value = _player1Score.value?.inc()
            _playerWinGame.value = _player1Name.value
            endGame = true
        } else {
            turn = 2
        }
    }

    private fun checkIfWin(row: Int, col: Int,playerTurn:Int): Boolean {
        board[row][col] = turn
        return ((board[0][0] == playerTurn && board[0][1] == playerTurn && board[0][2] == playerTurn) ||
            (board[1][0] == playerTurn && board[1][1] == playerTurn && board[1][2] == playerTurn) ||
            (board[2][0] == playerTurn && board[2][1] == playerTurn && board[2][2] == playerTurn) ||
            (board[0][0] == playerTurn && board[1][0] == playerTurn && board[2][0] == playerTurn) ||
            (board[0][1] == playerTurn && board[1][1] == playerTurn && board[2][1] == playerTurn) ||
            (board[0][2] == playerTurn && board[1][2] == playerTurn && board[2][2] == playerTurn) ||
            (board[0][0] == playerTurn && board[1][1] == playerTurn && board[2][2] == playerTurn) ||
            (board[0][2] == playerTurn && board[1][1] == playerTurn && board[2][0] == playerTurn))

    }



}