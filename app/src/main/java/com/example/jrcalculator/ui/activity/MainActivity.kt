package com.example.jrcalculator.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import com.example.dopecalculator.viewmodel.HomeViewModel
import com.example.jrcalculator.R
import com.example.jrcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    var canAddDot=true
    var canAddOperation=true
    private var screenValue=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        kotlin.runCatching {
            //let's bind
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)
        }.onFailure {
            it.printStackTrace()
        }

        //saving shared prefs
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val darkMode=sharedPreferences.getBoolean("darkMode", false)

        if(darkMode){
            binding.chipNight.isChecked=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            binding.chipDay.isChecked=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        //add logica com os chips
        binding.chipDay.setOnClickListener {

            sharedPreferences.edit(true){
                putBoolean("darkMode", false)
            }

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding.chipNight.setOnClickListener {

            sharedPreferences.edit(true){
                putBoolean("darkMode", true)
            }

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }


        //toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
            toolbar.title = "Jr Calculator"



        binding.buttonDEL.setOnClickListener {
            if (screenValue.length>0 && screenValue.length!=1){
                screenValue=screenValue.subSequence(0,screenValue.length-1).toString()
                Log.d("msg","aaaah ${screenValue}")
            }else{
                if(screenValue.length==1){
                    screenValue=""
                    binding.display.text=screenValue
                    canAddDot=true
                    canAddOperation=true
                }
                return@setOnClickListener
            }
            binding.display.text=screenValue
            when (screenValue[screenValue.length-1]){
                '+'->{
                    canAddOperation=false
                }
                '-'->{
                    canAddOperation=false
                }
                '*'->{
                    canAddOperation=false
                }
                '/'->{
                    canAddOperation=false
                }
                else->{
                    var j=screenValue.length-1
                    while(screenValue[j].code>=48 && screenValue[j].code<=57 || screenValue[j]=='.'){
                        if(screenValue[j]=='.') {
                            canAddDot=false
                            break
                        }else{
                            canAddDot=true
                        }
                        if (j-1<0) break
                        j-=1
                    }
                    canAddOperation=true
                }
            }
        }

        binding.buttonAC.setOnClickListener {
            screenValue=""
            binding.display.text=screenValue
            canAddDot=true
            canAddOperation=true
        }

        binding.buttonMult.setOnClickListener {

            if(canAddOperation){
                screenValue=screenValue.plus("*")
                binding.display.text=screenValue
                canAddDot=true
            }

        }

        binding.buttonDiv.setOnClickListener {
            if(canAddOperation){
                screenValue=screenValue.plus("/")
                binding.display.text=screenValue
                canAddDot=true
            }
        }

        binding.buttonPlus.setOnClickListener {
            if(canAddOperation){
                screenValue=screenValue.plus("+")
                binding.display.text=screenValue
                canAddDot=true
            }

        }

        binding.buttonMinus.setOnClickListener {
            if(canAddOperation){
                screenValue=screenValue.plus("-")
                binding.display.text=screenValue
                canAddDot=true
            }

        }

        binding.buttonANS.setOnClickListener {
            screenValue += viewModel.getAns().toString()
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.buttonEqual.setOnClickListener {
            if(!viewModel.salvaElementos(screenValue)){
                Toast.makeText(this,"Operação Inválida", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.realizaOperacaoMatematica()
            binding.lastOperation.text=screenValue
            screenValue=viewModel.getAns().toString()
            binding.display.text=screenValue
            canAddDot=true
            canAddOperation=true
        }

        binding.buttonDot.setOnClickListener {
            if(canAddDot){
                screenValue=screenValue.plus(binding.buttonDot.text.toString())
                binding.display.text=screenValue
                canAddDot=false
            }
        }


        binding.button0.setOnClickListener {
            screenValue=screenValue.plus(binding.button0.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button1.setOnClickListener {
            screenValue=screenValue.plus(binding.button1.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button2.setOnClickListener {
            screenValue=screenValue.plus(binding.button2.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button3.setOnClickListener {
            screenValue=screenValue.plus(binding.button3.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button4.setOnClickListener {
            screenValue=screenValue.plus(binding.button4.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button5.setOnClickListener {
            screenValue=screenValue.plus(binding.button5.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button6.setOnClickListener {
            screenValue=screenValue.plus(binding.button6.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button7.setOnClickListener {
            screenValue=screenValue.plus(binding.button7.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button8.setOnClickListener {
            screenValue=screenValue.plus(binding.button8.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

        binding.button9.setOnClickListener {
            screenValue=screenValue.plus(binding.button9.text.toString())
            binding.display.text=screenValue
            canAddOperation=true
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Log.d("Main Activity","Mudo configuracao UI para ${newConfig.uiMode} <---------------------------")

    }
}