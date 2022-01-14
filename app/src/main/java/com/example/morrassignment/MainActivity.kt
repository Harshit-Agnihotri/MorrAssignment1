package com.example.morrassignment

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.morrassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cardNum: String
    private lateinit var month: String
    private lateinit var secCode: String
    private lateinit var fName: String
    private lateinit var lName: String
    private val number: CardID = CardID()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Date(binding).dateFormat()
        binding.Enter.setOnClickListener {
            if (!emptyValues()){
                if (enter()){
                    warnings()
                }
            }else{
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadInput() {
        cardNum = binding.cardEntry.text.toString()
        month = binding.dateEntryLayout.text.toString()
        secCode = binding.cvvEntry.text.toString()
        fName = binding.firstEntry.text.toString()
        lName = binding.lastEntryLayout.text.toString()
    }

    private fun enter(): Boolean{
        var submit = true
        if (!number.validateCard(cardNum)){
            binding.cardFields.error = "Invalid Card Number"
            return false
        }
        if (!CardOwner().checkChar(fName)) {
            binding.first.error = "Invalid First Name"
            submit = false
        }
        if (!CardOwner().checkChar(lName)){
            binding.lastEntry.error = "Invalid Last Name "
            submit = false
        }

        if (!SCode().securityCode(secCode, number.getRegex())){
            binding.cvv.error = "Invalid Security Code"
            submit = false
        }

        if (!Expire().expiryDate(getExpiryYear(), getExpiryMonth())){
            binding.dateEntry.error = "Invalid Expiry Date"
            submit = false
        }
        return submit
    }

    private fun emptyValues(): Boolean {
        loadInput()
        var empty = false
        if (cardNum.trim().equals("")) {
            empty = true
            binding.cardFields.error = "Check Card Number"
        }
        if (month.trim().equals("")) {
            empty = true
            binding.dateEntry.error = "Check Date Month"
        }
        if (secCode.trim().equals("")) {
            empty = true
            binding.cvv.error = "Check Security Code"
        }
        if (fName.trim().equals("")) {
            empty = true
            binding.first.error = "Check First Name"
        }

        if (lName.trim().equals("")) {
            empty = true
            binding.lastEntry.error = "Check Last Name"
        }

        return empty
    }

    private fun warnings() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Payment Successful")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("OK") { dialogInterface, which ->
            clearValues()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun clearValues(){
        with(binding) {
            cardEntry.setText("")
            dateEntryLayout.setText("")
            cvvEntry.setText("")
            firstEntry.setText("")
            lastEntryLayout.setText("")
            cardFields.error = null
            dateEntry.error = null
            cvv.error = null
            first.error = null
            lastEntry.error = null
        }
    }

    private fun getExpiryMonth():String = month.split("/")[0]
    private fun getExpiryYear(): String = month.split("/")[1]
}