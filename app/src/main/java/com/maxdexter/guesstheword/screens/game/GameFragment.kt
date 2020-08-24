package com.maxdexter.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        binding.btnGotIt.setOnClickListener {
            viewModel.onCorrect()
            updateScoreText()
            updateWordText()
        }
        binding.btnSkip.setOnClickListener {
            viewModel.onSkip()
            updateScoreText()
            updateWordText()
        }

        updateScoreText()
        updateWordText()
        return binding.root
    }

     fun updateWordText() {
         viewModel.word.observe(viewLifecycleOwner, Observer { newWord -> binding.tvGuessWord.text = newWord })
    }

     fun updateScoreText() {
         viewModel.score.observe(viewLifecycleOwner, {newScore -> binding.tvScore.text = newScore.toString()})
    }





     fun gameFinished() {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment2(viewModel.score.value ?: 0))
         Toast.makeText(this.activity,"Game, has finished", Toast.LENGTH_SHORT).show()

    }


}