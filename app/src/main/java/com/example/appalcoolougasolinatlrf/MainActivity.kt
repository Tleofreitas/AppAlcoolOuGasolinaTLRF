package com.example.appalcoolougasolinatlrf

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool : TextInputLayout
    private lateinit var editAlcool : TextInputEditText

    private lateinit var textInputGasolina : TextInputLayout
    private lateinit var editGasolina : TextInputEditText

    private lateinit var btnCalcular : Button
    private lateinit var textResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar variáveis
        inicializarComponentesInterface()

        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }
    }

    private fun calcularMelhorPreco() {
        val precoAlcool   = editAlcool.text.toString()
        val precoGasolina = editGasolina.text.toString()
        
        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)

        if(resultadoValidacao){
            val resultado = precoAlcool.toDouble() / precoGasolina.toDouble()

            // Se valorAlcool / ValorGasolina >= 0,7 , é melhor usar Gasolina
            if(resultado >= 0.7){
                textResultado.text = "Melhor utilizar Gasilina"
            } else {
                textResultado.text = "Melhor utilizar Álcool"
            }
        }
    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {

        // Inicialmente sem nenhuma mensagem de erro
        textInputAlcool.error = null
        textInputGasolina.error = null

        // Se tiver vazio, da mensagem de erro
        if(pAlcool.isEmpty()){
            textInputAlcool.error = "Digite o preço do Álcool"
            return false
        } else if(pGasolina.isEmpty()) {
            return false
            textInputGasolina.error = "Digite o preço da Gasolina"
        }

        // Se tiver OK, sem nenhuma mensagem de erro
        return true
    }

    private fun inicializarComponentesInterface() {

        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)

        textResultado = findViewById(R.id.text_resultado)
    }
}