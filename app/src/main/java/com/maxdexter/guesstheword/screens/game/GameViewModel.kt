package com.maxdexter.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 100000L
    }
    private lateinit var timer: CountDownTimer

   private var _word = MutableLiveData<String>()//Текущее слово
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val __eventGameFinish = MutableLiveData<Boolean>()
        val eventGameFinish: LiveData<Boolean>
                get() = __eventGameFinish

    private val _timeSec = MutableLiveData<String>()
        val timeSec: LiveData<String>
            get() = _timeSec
    lateinit var wordList: MutableList<String>
    init {
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(p0: Long) {
                _timeSec.value = onTimeCreate(p0)
                Log.i("TAG", " " + p0)
            }
            override fun onFinish() {
                __eventGameFinish.value = true
            }
        }.start()

        __eventGameFinish.value = false
        _score.value = 0
        _word.value = ""
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()

    }

    fun resetList() {
        wordList = mutableListOf("queen", "hospital", "basketball", "cat", "dog", "fish", "ice", "wind", "weather", "guitar", "desk", "railway"
            ,"bag", "desert", "home", "zebra", "crow", "trade")
        wordList.shuffle()
    }

   fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
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

    fun onGameFinishComplit() {
        __eventGameFinish.value = false
    }

    fun onTimeCreate(time: Long): String {
        val sec = time / 1000
        val strTime = DateUtils.formatElapsedTime(sec)
        return strTime
    }


}