package com.maxdexter.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.maxdexter.guesstheword.R
import com.maxdexter.guesstheword.databinding.FragmentGameBinding

class GameFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        return binding.root
    }
}