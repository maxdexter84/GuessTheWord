package com.maxdexter.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxdexter.guesstheword.R
import com.maxdexter.guesstheword.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    lateinit var binding: FragmentScoreBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        val finalScore = arguments?.let { ScoreFragmentArgs.fromBundle(it).score }
        viewModelFactory = ScoreViewModelFactory(finalScore ?: 0)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.viewModelScore = viewModel
        binding.setLifecycleOwner(this)
  
        playAgain()

        return binding.root
    }

    private fun playAgain() {
        viewModel.playAgain.observe(viewLifecycleOwner,{play ->
            if (play) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragment2ToGameFragment())
                viewModel.onPlayAgainComplete()
            }
        })

    }


}