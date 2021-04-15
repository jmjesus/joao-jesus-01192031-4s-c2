package com.example.ac2cachorros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun comprar(view: View) {
        val apiCachorros = ConexaoApiCachorros.criar()

        val etIdPrimeiroCachorro: EditText = findViewById(R.id.et_primeiro_cachorro)
        val idPrimeiro = etIdPrimeiroCachorro.text.toString().toInt()
        var statusCodePrimeiroCachorro = 0

        val etIdSegundoCachorro: EditText = findViewById(R.id.et_segundo_cachorro)
        val idSegundo = etIdSegundoCachorro.text.toString().toInt()
        var statusCodeSegundoCachorro = 0

        val telaNenhumEncontrado = Intent(this, TelaNenhumEncontrado::class.java)
        val telaResposta = Intent(this, TelaResposta::class.java)

        var racaPrimeiro = ""
        var racaSegundo = ""
        var precoPrimeiro = 0
        var precoSegundo = 0

        apiCachorros.get(idPrimeiro).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 404) {
                    statusCodePrimeiroCachorro = 404
                    racaPrimeiro = getString(R.string.nao_encontrado)
                } else {
                    val primeiroCachorro = response.body()
                    racaPrimeiro = primeiroCachorro!!.raca
                    precoPrimeiro = primeiroCachorro.precoMedio
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    getString(R.string.mensagem_erro, t.message!!),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        apiCachorros.get(idSegundo).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 404) {
                    statusCodePrimeiroCachorro = 404
                    racaSegundo = getString(R.string.nao_encontrado)
                } else {
                    val segundoCachorro = response.body()
                    racaSegundo = segundoCachorro!!.raca
                    precoSegundo = segundoCachorro.precoMedio
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    getString(R.string.mensagem_erro, t.message!!),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        if (statusCodePrimeiroCachorro == 404 && statusCodeSegundoCachorro == 404) {
            telaNenhumEncontrado.putExtra("mensagemNenhumEncontrado", getString(R.string.nenhum_encontrado, idPrimeiro, idSegundo))
            startActivity(telaNenhumEncontrado)
        } else {
            telaResposta.putExtra("racaPrimeiroCachorro", racaPrimeiro)
            telaResposta.putExtra("racaSegundoCachorro", racaSegundo)
            telaResposta.putExtra("precoTotal", precoPrimeiro + precoSegundo)

            startActivity(telaResposta)
        }
    }

}