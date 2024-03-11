package ru.stankin.labs.bekmurzaev1

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FeedbackActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextSubject: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        editTextName = findViewById(R.id.editTextName)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextSubject = findViewById(R.id.editTextSubject)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        textViewError = findViewById(R.id.textViewError)

        buttonSubmit.setOnClickListener {
            if (validateForm()) {
                // Действия при успешной валидации формы
                textViewError.visibility = TextView.GONE // Hide the warning text
                showToast("Form submitted successfully")
            }
        }
    }

    private fun validateForm(): Boolean {
        val name = editTextName.text.toString().trim()
        val address = editTextAddress.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val subject = editTextSubject.text.toString().trim()
        val message = editTextMessage.text.toString().trim()

        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            textViewError.text = "Please fill out all fields"
            textViewError.visibility = TextView.VISIBLE
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textViewError.text = "Please enter a valid email address"
            textViewError.visibility = TextView.VISIBLE
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

