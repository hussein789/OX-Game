package com.hussien.xoboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.hussien.xoboard.R
import com.hussien.xoboard.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    lateinit var binding:FragmentGameBinding
    var turn = 1
    private var endGame = false
    var board = arrayOf(arrayOf(0,0,0),arrayOf(0,0,0),arrayOf(0,0,0))
    var map = mutableMapOf<Pair<Int,Int>,Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.newGameBtn.setOnClickListener {
            clearBoard()
        }

        binding.view1.setOnClickListener {
            onCellClicked(it as ImageView,0,0)
        }
        binding.view2.setOnClickListener {
            onCellClicked(it as ImageView,0,1)
        }
        binding.view3.setOnClickListener {
            onCellClicked(it as ImageView,0,2)
        }
        binding.view4.setOnClickListener {
            onCellClicked(it as ImageView,1,0)
        }
        binding.view5.setOnClickListener {
            onCellClicked(it as ImageView,1,1)
        }
        binding.view6.setOnClickListener {
            onCellClicked(it as ImageView,1,2)
        }
        binding.view7.setOnClickListener {
            onCellClicked(it as ImageView,2,0)
        }
        binding.view8.setOnClickListener {
            onCellClicked(it as ImageView,2,1)
        }
        binding.view9.setOnClickListener {
            onCellClicked(it as ImageView,2,2)
        }
    }

    private fun clearBoard() {
        binding.view1.setImageDrawable(null)
        binding.view2.setImageDrawable(null)
        binding.view3.setImageDrawable(null)
        binding.view4.setImageDrawable(null)
        binding.view5.setImageDrawable(null)
        binding.view6.setImageDrawable(null)
        binding.view7.setImageDrawable(null)
        binding.view8.setImageDrawable(null)
        binding.view9.setImageDrawable(null)
        endGame = false
        board = arrayOf(arrayOf(0,0,0),arrayOf(0,0,0),arrayOf(0,0,0))
        turn = 1
    }

    private fun onCellClicked(it: ImageView,row:Int,col:Int) {
        if (it.drawable == null && !endGame){
            if(turn == 1){
                it.setImageResource(R.drawable.blue_x)
                val win = checkIfWin(row,col)
                if(win){
                    Toast.makeText(requireContext(),"Player1 has won the game",Toast.LENGTH_LONG).show()
                    endGame = true
                }
                turn = 2
            } else {
                it.setImageResource(R.drawable.red_o)
                val win = checkIfWin(row, col)
                if(win) {
                    Toast.makeText(requireContext(),"Player2 has won the game",Toast.LENGTH_LONG).show()
                    endGame = true
                }
                turn = 1
            }
        }
    }

    private fun checkIfWin(row: Int, col: Int): Boolean {
        board[row][col] = turn
        if((board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1) ||
            (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1) ||
            (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1) ||
            (board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1) ||
            (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1) ||
            (board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1) ||
            (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) ||
            (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1)){
            return true
        } else return (board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2) ||
                (board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2) ||
                (board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2) ||
                (board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 2) ||
                (board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2) ||
                (board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 2) ||
                (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) ||
                (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2)
    }


}