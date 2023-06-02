package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)

       binding.calculateButton.setOnClickListener { calculateTip() }
       binding.costOfServiceEditText.setOnKeyListener { v, keyCode, event -> handleKeyEvent(v, keyCode)  }

   }
    // Calculating tip function that does the math
    private fun calculateTip(){
        // grabs the text from the EditTextView as a string
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        // converts the string of text into a Double
        var cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            binding.totalAmount.text = ""
            binding.totalCost.text = ""
            return
        }

        // Grabs the boolean value of the radio button into selectedId

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId){
            R.id.option_20_percent -> 0.20
            R.id.option_18_percent -> 0.18
            R.id.option_15_percent -> 0.15
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
            cost = kotlin.math.ceil(cost)
        }

        val totalAmount = cost + tip

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        val formattedCost = NumberFormat.getCurrencyInstance().format(cost)
        val formattedTotal = NumberFormat.getCurrencyInstance().format(totalAmount)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        binding.totalCost.text = getString(R.string.total_cost,formattedCost)
        binding.totalAmount.text = getString(R.string.total_amount, formattedTotal)
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}