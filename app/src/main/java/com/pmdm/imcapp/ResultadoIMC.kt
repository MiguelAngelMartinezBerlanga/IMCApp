package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

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
        tvResultado = findViewById(R.id.tvResultado)
        tvExplicacion = findViewById(R.id.tvExplicacion)
        btnReCalcular = findViewById(R.id.btnReCalcular)
    }

    fun Informe(){

        val resultado = intent.extras?.getDouble("Resultado")





        when(resultado!!){
             in 0.0..18.4 -> {
                 tvInforme.text = "BAJO PESO"
                 tvExplicacion.text = "Tienes que comer mucho mas"
                }
            in 18.5..24.9 ->{

                 tvInforme.text = "Peso normal"
                tvExplicacion.text = "Estas perfecto nene"
                }
             in 25.0 .. 29.9 ->{
                 tvInforme.text = "Peso superior al normal"
                 tvExplicacion.text = "Come un poco  menos tu cuerpo te lo agaradecera "
                 }
             in 30.00..Double.MAX_VALUE->{
                 tvInforme.text = "Obesidad"
                tvExplicacion.text = "Baja de peso que estas falete"}

         }




//        // Formatear el resultado a dos decimales
        val resultadoFormateado = String.format("%.2f", resultado)
//
//        // Mostrar el resultado formateado en tvResultado
        tvResultado.text = resultadoFormateado



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