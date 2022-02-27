package com.hussien.xoboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hussien.xoboard.R
import com.hussien.xoboard.databinding.FragmentPlayerBinding
import com.hussien.xoboard.ui.viewmodel.SharedViewModel

class PlayerFragment : Fragment() {

    lateinit var binding:FragmentPlayerBinding

    lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.navigateToGameScreen.observe(viewLifecycleOwner){navigate ->
            if(navigate) navigateToGameScreen()
        }
    }

    private fun navigateToGameScreen() {
        this.findNavController().navigate(R.id.action_playerFragment_to_gameFragment)
    }

    private fun initClickListeners() {
        binding.gameStartBtn.setOnClickListener {
            onStartGameClicked()
        }
    }

    private fun onStartGameClicked() {
        val player1Name = binding.player1Et.text.toString()
        val player2Name = binding.player2Et.text.toString()

        viewModel.onStartGameClicked(player1Name,player2Name)
    }

}