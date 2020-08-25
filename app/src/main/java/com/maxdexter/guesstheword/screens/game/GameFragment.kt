package com.maxdexter.guesstheword.screens.game

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxdexter.guesstheword.R
import com.maxdexter.guesstheword.databinding.FragmentGameBinding

class GameFragment: Fragment() {
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        Log.i("TAG", "Called ViewModelProviders")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.setLifecycleOwner(this)

        gameFinished()
        return binding.root
    }


     fun gameFinished() {
         viewModel.eventGameFinish.observe(viewLifecycleOwner, {newEvent ->
             if (newEvent) {
                 findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment2(viewModel.score.value ?: 0))
                 Toast.makeText(this.activity,"Game, has finished", Toast.LENGTH_SHORT).show()
             }
         })
    }
}