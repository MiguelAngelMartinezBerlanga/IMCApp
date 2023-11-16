package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

class ImcCalculator : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvHeight: TextView
    private lateinit var tvPeso: TextView
    private lateinit var btnSubtractPesoMenos: FloatingActionButton
    private lateinit var btnSubtractPesoMas: FloatingActionButton
    private lateinit var tvEdad: TextView
    private lateinit var btnSubtractEdadMenos : FloatingActionButton
    private lateinit var btnSubtractEdadMas : FloatingActionButton
    private lateinit var btnCalcular : Button

    private var isMaleSelected: Boolean = false
    private var isFemaleSelected: Boolean = false
    private var pesoActual:Int = 50
    private var edadActual: Int = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }


    fun initComponents(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        rsHeight =  findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
        btnSubtractPesoMenos = findViewById(R.id.btnSubtractPesoMenos)
        btnSubtractPesoMas = findViewById(R.id.btnSubtractPesoMas)
        tvPeso =findViewById(R.id.tvPeso)
        tvEdad = findViewById(R.id.tvEdad)
        btnSubtractEdadMenos = findViewById(R.id.btnSubtractEdadMenos)
        btnSubtractEdadMas = findViewById(R.id.btnSubtractEdadMas)
        btnCalcular = findViewById(R.id.btnCalcular)

    }

    fun initListeners(){
        viewMale.setOnClickListener {
            isMaleSelected = true
            isFemaleSelected = false
            setGenderColor()
        }

        viewFemale.setOnClickListener {
            isFemaleSelected = true
            isMaleSelected = false
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            //tvHeight.text = value.toString()
            tvHeight.text = DecimalFormat("#.##").format(value)
        }

        btnSubtractPesoMas.setOnClickListener{

            setPeso(+1)
        }
        btnSubtractPesoMenos.setOnClickListener {

            setPeso(-1)
        }
        btnSubtractEdadMas.setOnClickListener {
            edadActual +=1
            setEdad()
        }

        btnSubtractEdadMenos.setOnClickListener {
            edadActual -=1
            setEdad()
        }

        btnCalcular.setOnClickListener {
            CalculateIMC()
            navigate2result()


        }

    }

    fun navigate2result(){
        var num= CalculateIMC()
        val intent = Intent(this,ResultadoIMC::class.java)
        intent.putExtra("Resultado", num)
        startActivity(intent)


    }

    fun CalculateIMC():Double{
        var altura :Double = tvHeight.text.toString().toDouble()
        altura /= 100
       var peso : Double = tvPeso.text.toString().toDouble()

       var resultado:Double = peso/(altura*altura)
//        val df =DecimalFormat("#.##")




        return resultado


    }
    fun setPeso( num :Int){
       val textoPeso = tvPeso.text.toString().toInt()+ num
//        pesoActual=textoPeso
//        tvPeso.text = pesoActual.toString()

        tvPeso.text = textoPeso.toString()
//        tvPeso.setText(pesoActual.toString() + " kg")

    }

    fun setEdad(){
        tvEdad.setText(edadActual.toString() + " años")
    }



    fun getBackgroundColor(isComponentSelected:Boolean):Int{
        val colorReference = if(isComponentSelected) {
            R.color.bg_comp_sel // true
        } else {
            R.color.bg_comp // false
        }
        return ContextCompat.getColor(this,colorReference)
    }
    private fun setGenderColor() {

        val maleColor = getBackgroundColor(isMaleSelected)
        val femaleColor = getBackgroundColor(isFemaleSelected)

        viewMale.setCardBackgroundColor(maleColor)
        viewFemale.setCardBackgroundColor(femaleColor)
    }

    fun initUI(){
        setGenderColor()


    }

}