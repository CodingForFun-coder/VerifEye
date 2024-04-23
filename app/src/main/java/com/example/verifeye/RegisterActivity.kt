package com.example.verifeye

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupBirthDateEditText()
        setupPhoneNumberEditText()
        setupSignUpButton()
        setupLoginButton()
    }

    private fun setupBirthDateEditText() {
        val birthDateEditText: TextInputEditText = findViewById(R.id.birthDate)
        birthDateEditText.addTextChangedListener(object : TextWatcher {
            private var current = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val clean = s.toString().filter { it.isDigit() }

                if (clean == current) {
                    return
                }

                val ddmmyyyy = StringBuilder(clean)

                // Handle day
                if (ddmmyyyy.length > 2) {
                    ddmmyyyy.insert(2, '/')
                }

                // Handle month
                if (ddmmyyyy.length > 5) {
                    ddmmyyyy.insert(5, '/')
                }

                // Handle year
                if (ddmmyyyy.length > 10) {
                    // Ensures only 2 digits for day, 2 digits for month, and 4 digits for year are taken.
                    ddmmyyyy.delete(10, ddmmyyyy.length)
                }

                current = ddmmyyyy.toString()
                birthDateEditText.removeTextChangedListener(this)
                birthDateEditText.setText(current)
                birthDateEditText.setSelection(current.length)
                birthDateEditText.addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupPhoneNumberEditText() {
        val phoneNumberEditText: TextInputEditText = findViewById(R.id.phoneNumber)
        phoneNumberEditText.addTextChangedListener(object : TextWatcher {
            private var current = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val clean = s.toString().filter { it.isDigit() }

                if (clean == current) {
                    return
                }

                val formatted = when {
                    clean.length <= 3 -> "(${clean}"
                    clean.length <= 6 -> "(${clean.substring(0, 3)}) ${clean.substring(3)}"
                    else -> "(${clean.substring(0, 3)}) ${clean.substring(3, 6)}-${clean.substring(6, clean.length.coerceAtMost(10))}"
                }

                current = formatted
                phoneNumberEditText.removeTextChangedListener(this)
                phoneNumberEditText.setText(formatted)
                phoneNumberEditText.setSelection(formatted.length)
                phoneNumberEditText.addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupSignUpButton() {
        val signUpButton: Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            // Intent to navigate to the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

    private fun setupLoginButton() {
        val loginButton: TextView = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}


