package com.tasty.recipesapp.activities

import android.R
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.providers.RepositoryProvider


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.tasty.recipesapp.R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(com.tasty.recipesapp.R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(com.tasty.recipesapp.R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)

        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darker_gray)

        RepositoryProvider.initialize(this)

    }

}

//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        replaceFragment(HomeFragment())
//
//        binding.bottomNavigationView.setOnItemSelectedListener{
//            when(it.itemId){
//                R.id.homeFragment -> replaceFragment(HomeFragment())
//                R.id.recipesFragment -> replaceFragment(RecipesFragment())
//                R.id.profileFragment -> replaceFragment(ProfileFragment())
//
//                else ->{
//
//                }
//            }
//            true
//        }
//    }
//
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
//        fragmentTransaction.commit()
//    }
//}

