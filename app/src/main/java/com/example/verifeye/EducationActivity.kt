package com.example.verifeye

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.verifeye.databinding.ActivityEducationBinding // Corrected binding class

class EducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(FactsFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.facts -> replaceFragment(FactsFragment())
                R.id.videos -> replaceFragment(VideoFragment())
                R.id.quizzes -> replaceFragment(QuizFragment())
                R.id.resource_list -> replaceFragment(ResourceFragment())
                R.id.ai_questions -> replaceFragment(AiFragment())
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
