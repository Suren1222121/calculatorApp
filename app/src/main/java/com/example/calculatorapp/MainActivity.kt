package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var tvResult:TextView by Delegates.notNull()
    private var tvProcess:TextView by Delegates.notNull()
    private var tvHistory:TextView by Delegates.notNull()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvResult = findViewById(R.id.tv_result)
        tvProcess = findViewById(R.id.tv_process)
        tvHistory = findViewById(R.id.tv_history)
        savedInstanceState?.takeIf {
            it.containsKey(KEY_TEXT_RESULT)
            it.containsKey(KEY_TEXT_PROCESS)
            it.containsKey(KEY_TEXT_HISTORY)
        }?.let {
            tvResult.text = it.getString(KEY_TEXT_RESULT)
            tvProcess.text = it.getString(KEY_TEXT_PROCESS)
            tvHistory.text = it.getString(KEY_TEXT_HISTORY)
        }
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
                tvHistory.text = "Last action: ${tvProcess.text} = ${tvResult.text}"

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_TEXT_RESULT, tvResult.text.toString())
        outState.putString(KEY_TEXT_PROCESS, tvProcess.text.toString())
        outState.putString(KEY_TEXT_HISTORY, tvHistory.text.toString())
    }
    companion object{
        private const val KEY_TEXT_RESULT ="KEY_TEXT_VALUE"
        private const val KEY_TEXT_PROCESS ="KEY_TEXT_PROCESS"
        private const val KEY_TEXT_HISTORY ="KEY_TEXT_HISTORY"

    }

    fun setText(str:String)
    {
        if (tvResult.text!="")
        {
            tvProcess.text = tvResult.text
            tvResult.text = ""
        }
            tvProcess.append(str)
    }
}