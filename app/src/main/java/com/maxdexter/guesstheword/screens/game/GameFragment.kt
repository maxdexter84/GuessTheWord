package com.maxdexter.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maxdexter.guesstheword.R
import com.maxdexter.guesstheword.databinding.FragmentGameBinding

class GameFragment: Fragment() {
    private var word = "" //Текущее слово
    private var score = 0 //Текущие очки
    private lateinit var wordList: MutableList<String>
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        resetList()
        nextWord()
        binding.btnGotIt.setOnClickListener { onCorrect() }
        binding.btnSkip.setOnClickListener { onSkip() }
        updateScoreText()
        updateWordText()
        return binding.root
    }

    private fun updateWordText() {
        binding.tvGuessWord.text = word
    }

    private fun updateScoreText() {
        binding.tvScore.text = score.toString()
    }

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun onCorrect() {
        score++
        nextWord()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            gameFinished()
        }else {
            word = wordList.removeAt(0)
            updateWordText()
            updateScoreText()
        }
    }

    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment2(score)
        findNavController().navigate(action)

    }

    private fun resetList() {
        wordList = mutableListOf("queen", "hospital", "basketball", "cat", "dog", "fish", "ice", "wind", "weather", "guitar", "desk", "railway"
        ,"bag", "desert", "home", "zebra", "crow", "trade")
        wordList.shuffle()
    }
}