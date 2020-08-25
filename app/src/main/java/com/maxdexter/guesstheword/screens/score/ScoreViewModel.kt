package com.maxdexter.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
        init {
            Log.i("TAG", "ScoreViewModel final score $finalScore")
        }
}