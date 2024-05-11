package com.example.verifeye

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.verifeye.databinding.ActivityStatsAndRecommendationBinding

class StatsAndRecommendationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsAndRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsAndRecommendationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(RightFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.right_leaning -> replaceFragment(RightFragment())
                R.id.center_leaning-> replaceFragment(CenterFragment())
                R.id.left_leaning -> replaceFragment(LeftFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }
}
