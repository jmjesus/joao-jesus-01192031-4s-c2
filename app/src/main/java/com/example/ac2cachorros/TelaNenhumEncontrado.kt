package com.example.ac2cachorros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TelaNenhumEncontrado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_nenhum_encontrado)

        val mensagemNenhumEncontrado = intent.getStringExtra("mensagemNenhumEncontrado")
        val tvResposta:TextView = findViewById(R.id.tv_resposta)
        tvResposta.text = mensagemNenhumEncontrado
    }
}