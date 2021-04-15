package com.example.ac2cachorros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TelaResposta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_resposta)

        val racaPrimeiroCachorro = intent.getStringExtra("racaPrimeiroCachorro")
        val racaSegundoCachorro = intent.getStringExtra("racaSegundoCachorro")
        val precoTotal = intent.getIntExtra("precoTotal", 0)

        val tvPrimeiroCachorro:TextView = findViewById(R.id.tv_primeiro_cachorro)
        val tvSegundoCachorro:TextView = findViewById(R.id.tv_segundo_cachorro)
        val tvPrecoTotal:TextView = findViewById(R.id.tv_preco_total)

        tvPrimeiroCachorro.text = getString(R.string.raca_primeiro, racaPrimeiroCachorro)
        tvSegundoCachorro.text = getString(R.string.raca_segundo, racaSegundoCachorro)
        tvPrecoTotal.text = getString(R.string.preco_total, precoTotal)
    }
}