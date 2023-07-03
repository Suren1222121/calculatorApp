package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btn0.setOnClickListener { setText("0") }
        binding.btn1.setOnClickListener { setText("1") }
        binding.btn2.setOnClickListener { setText("2") }
        binding.btn3.setOnClickListener { setText("3") }
        binding.btn4.setOnClickListener { setText("4") }
        binding.btn5.setOnClickListener { setText("5") }
        binding.btn6.setOnClickListener { setText("6") }
        binding.btn7.setOnClickListener { setText("7") }
        binding.btn8.setOnClickListener { setText("8") }
        binding.btn9.setOnClickListener { setText("9") }
        binding.btnDecrement.setOnClickListener{setText("-")}
        binding.btnIncrement.setOnClickListener{setText("+")}
        binding.btnDivide.setOnClickListener{setText("/")}
        binding.btnMultiply.setOnClickListener{setText("*")}
        binding.btnBracketOpen.setOnClickListener{setText("(")}
        binding.btnBracketClose.setOnClickListener{setText(")")}
        binding.btnPoint.setOnClickListener { setText(".") }
        binding.btnClear.setOnClickListener {
            binding.tvProcess.text = " "
            binding.tvResult.text = " "
        }
        binding.btnBack.setOnClickListener {
            val str = binding.tvProcess.text.toString()
            if(str.isNotEmpty())
            {
                binding.tvProcess.text = str.substring(0,str.length - 1)
                binding.tvResult.text = " "
            }
        }
        binding.btnEquals.setOnClickListener {
            try {
                val ex  = ExpressionBuilder(binding.tvProcess.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if(result == longRes.toDouble())
                {
                    binding.tvResult.text = longRes.toString()

                }
                else
                    binding.tvResult.text = result.toString()
            }   catch (e:Exception)
            {
                binding.tvResult.text = "${e.message}"
            }
        }
    }

    fun setText(str:String)
    {
        if (binding.tvResult.text!="")
        {
            binding.tvProcess.text = binding.tvResult.text
            binding.tvResult.text = ""
        }
        binding.tvProcess.append(str)
    }
}