package com.maxdexter.guesstheword.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maxdexter.guesstheword.R
import com.maxdexter.guesstheword.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    lateinit var binding: FragmentTitleBinding
    val score = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title,container,false)
        val words = mutableListOf<String>("")
        binding.btnPlay.setOnClickListener { findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()) }
        return binding.root
    }
}