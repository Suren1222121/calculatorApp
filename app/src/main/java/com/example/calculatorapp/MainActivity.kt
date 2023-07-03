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
        with(binding) {
            btn0.setOnClickListener { setText("0") }
            btn1.setOnClickListener { setText("1") }
            btn2.setOnClickListener { setText("2") }
            btn3.setOnClickListener { setText("3") }
            btn4.setOnClickListener { setText("4") }
            btn5.setOnClickListener { setText("5") }
            btn6.setOnClickListener { setText("6") }
            btn7.setOnClickListener { setText("7") }
            btn8.setOnClickListener { setText("8") }
            btn9.setOnClickListener { setText("9") }
            btnDecrement.setOnClickListener{setText("-")}
            btnIncrement.setOnClickListener{setText("+")}
            btnDivide.setOnClickListener{setText("/")}
            btnMultiply.setOnClickListener{setText("*")}
            btnBracketOpen.setOnClickListener{setText("(")}
            btnBracketClose.setOnClickListener{setText(")")}
            btnPoint.setOnClickListener { setText(".") }
            btnClear.setOnClickListener {
                tvProcess.text = " "
                tvResult.text = " "
            }

            btnBack.setOnClickListener {
                val str = tvProcess.text.toString()
                if(str.isNotEmpty())
                {
                    tvProcess.text = str.substring(0,str.length - 1)
                    tvResult.text = " "
                }
            }

            btnEquals.setOnClickListener {
                try {
                    val ex  = ExpressionBuilder(tvProcess.text.toString()).build()
                    val result = ex.evaluate()
                    val longRes = result.toLong()
                    if(result == longRes.toDouble())
                    {
                        tvResult.text = longRes.toString()

                    }
                    else
                        tvResult.text = result.toString()
                }   catch (e:Exception)
                {
                    tvResult.text = "${e.message}"
                }
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