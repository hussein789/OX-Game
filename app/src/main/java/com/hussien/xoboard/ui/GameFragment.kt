package com.hussien.xoboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.hussien.xoboard.R
import com.hussien.xoboard.databinding.FragmentGameBinding
import com.hussien.xoboard.ui.viewmodel.SharedViewModel


class GameFragment : Fragment() {

    lateinit var binding:FragmentGameBinding
    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.newGameBtn.setOnClickListener {
            viewModel.onNewGameClicked()
        }

        binding.view1.setOnClickListener {
            onCellClicked(it as ImageView, 0, 0)
        }
        binding.view2.setOnClickListener {
            onCellClicked(it as ImageView, 0, 1)
        }
        binding.view3.setOnClickListener {
            onCellClicked(it as ImageView, 0, 2)
        }
        binding.view4.setOnClickListener {
            onCellClicked(it as ImageView, 1, 0)
        }
        binding.view5.setOnClickListener {
            onCellClicked(it as ImageView, 1, 1)
        }
        binding.view6.setOnClickListener {
            onCellClicked(it as ImageView, 1, 2)
        }
        binding.view7.setOnClickListener {
            onCellClicked(it as ImageView, 2, 0)
        }
        binding.view8.setOnClickListener {
            onCellClicked(it as ImageView, 2, 1)
        }
        binding.view9.setOnClickListener {
            onCellClicked(it as ImageView, 2, 2)
        }
    }

    private fun observeViewModel() {
        viewModel.player1Name.observe(viewLifecycleOwner){ name ->
            binding.player1Name.text = name
        }

        viewModel.player2Name.observe(viewLifecycleOwner){ name ->
            binding.player2Name.text = name
        }

        viewModel.player1Score.observe(viewLifecycleOwner){ score ->
            binding.player1Score.text = score.toString()
        }

        viewModel.player2Score.observe(viewLifecycleOwner){
            score -> binding.player2Score.text = score.toString()
        }

        viewModel.clearBoard.observe(viewLifecycleOwner){
            clearBoard -> if(clearBoard) clearBoard()
        }

        viewModel.playerWinGame.observe(viewLifecycleOwner){
            winName -> Toast.makeText(requireContext(),getString(R.string.win_player,winName),Toast.LENGTH_LONG).show()
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
    }


    private fun onCellClicked(it: ImageView,row:Int,col:Int) {
        viewModel.onCellMarked(it,row,col)
    }


}