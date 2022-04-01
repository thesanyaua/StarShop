package com.example.starshop.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartImageViewModel:ViewModel() {
    val timeEnd = MutableLiveData<Boolean>()

    init {
        runTimer()
    }

    private fun runTimer() {
        val timer = object :CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                timeEnd.postValue(true)
            }
        }
        timer.start()
    }
}