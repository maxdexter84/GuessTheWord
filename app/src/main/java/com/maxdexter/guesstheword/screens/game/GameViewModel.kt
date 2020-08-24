package com.maxdexter.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
   private var _word = MutableLiveData<String>()//Текущее слово
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    lateinit var wordList: MutableList<String>
    init {
        Log.i("TAG", "GameViewModel created")
        _score.value = 0
        _word.value = ""
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TAG", "GameViewModel destroyed!")
    }

    fun resetList() {
        wordList = mutableListOf("queen", "hospital", "basketball", "cat", "dog", "fish", "ice", "wind", "weather", "guitar", "desk", "railway"
            ,"bag", "desert", "home", "zebra", "crow", "trade")
        wordList.shuffle()
    }

   fun nextWord() {
        if (wordList.isEmpty()) {
           // gameFinished()
        }else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }
}