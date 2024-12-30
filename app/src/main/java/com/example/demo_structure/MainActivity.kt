package com.example.demo_structure

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demo_structure.app.manager.LanguageManager
import com.example.demo_structure.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var languageManager = LanguageManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeLanguageVi.setOnClickListener {
            languageManager.setLocale(this, "vi")
            recreate()
            Log.d("QQQ", "đổi sang Tiếng Việt")
        }

        binding.changeLanguageEn.setOnClickListener {
            languageManager.setLocale(this, "en")
            recreate()
            Log.d("QQQ", "đổi sang Tiếng Anh")
        }
        val navView: BottomNavigationView = binding.navView

//        val navController = fiupportFragmndNavController(R.id.nav_host_fragment_activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}