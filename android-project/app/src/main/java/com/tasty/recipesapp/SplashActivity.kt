package com.tasty.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    companion object{
        const val TAG = "SplashActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.nameInput
        binding.StartButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("message", "Hello from Splash")
            startActivity(intent)
        }

        Log.d(TAG, "onCreate: SplashActivity created.")

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
    }


}