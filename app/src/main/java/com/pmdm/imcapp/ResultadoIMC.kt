package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoIMC : AppCompatActivity() {
    private   lateinit var tvInforme: TextView
    private   lateinit var tvResultado: TextView
    private  lateinit var tvExplicacion:TextView
    private  lateinit var btnReCalcular: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)

        initComponents()
        initListeners()
        Informe()




    }

    fun initComponents(){
        tvInforme =findViewById(R.id.tvInforme)
        tvResultado = findViewById(R.id.tvInforme)
        tvExplicacion = findViewById(R.id.tvExplicacion)
        btnReCalcular = findViewById(R.id.btnReCalcular)
    }

    fun Informe(){

        val resultado = intent.extras?.getDouble("Resultado")

         when(resultado!!){
             in 0.0..18.4 ->
                 tvInforme.text = "BAJO PESO"
             in 18.5 ..24.9 ->
                 tvInforme.text = "Peso normal"
             in 25.0 .. 29.9 ->
                 tvInforme.text = "Peso superior al normal"
             in 30.00..Double.MAX_VALUE->
                 tvInforme.text = "Obesidad"

         }
        when(resultado!!){
            in 0.0..18.4 ->
                tvExplicacion.text = "Tienes que comer mucho mas"
            in 18.5 ..24.9 ->
                tvExplicacion.text = "Estas perfecto nene"
            in 25.0 .. 29.9 ->
                tvExplicacion.text = "Come un poco  menos tu cuerpo te lo agaradecera "
            in 30.00..Double.MAX_VALUE->
                tvExplicacion.text = "Baja de peso que estas falete"

        }





    }

    fun initListeners(){


        btnReCalcular.setOnClickListener {

           navigate1result()
        }


    }
    fun navigate1result(){


        val intent2 = Intent(this,ImcCalculator::class.java)
        startActivity(intent2)


    }


}