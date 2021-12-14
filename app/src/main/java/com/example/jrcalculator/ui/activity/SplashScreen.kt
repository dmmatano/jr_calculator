package com.example.jrcalculator.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieDrawable
import com.example.jrcalculator.R
import com.example.jrcalculator.databinding.ActivityMainBinding
import com.example.jrcalculator.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        kotlin.runCatching {
            //let's bind
            binding = ActivitySplashScreenBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
        }.onFailure {
            it.printStackTrace()
        }

        //binding.logoImage.animate().translationY(-700f).setDuration(1000).setStartDelay(0)

        lifecycleScope.launch {
            delay(2000)

            val intent= Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        Handler().postDelayed({
//            val intent= Intent(this@SplashScreen,MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        },2000)

    }
}