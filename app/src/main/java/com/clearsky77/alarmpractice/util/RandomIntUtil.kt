package com.clearsky77.alarmpractice.util

import android.util.Log
import java.util.concurrent.atomic.AtomicInteger

class RandomIntUtil {
    private val seed = AtomicInteger()

//    fun getRandomInt() : Int = seed.getAndIncrement()+ System.currentTimeMillis().toInt()
    fun getRandomInt() : Int{
        var result = seed.getAndIncrement()+ System.currentTimeMillis().toInt()
        // .getAndIncrement() 현재 값을 원자적으로 1씩 증가시킵니다.
        Log.d("RandomIntUtil 내부","result 값: "+result.toString())
        return result
    }


}