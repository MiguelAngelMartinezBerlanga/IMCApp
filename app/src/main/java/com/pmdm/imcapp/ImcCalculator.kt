package com.pmdm.imcapp

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
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
        }

        btnSubtractPesoMas.setOnClickListener{
            pesoActual +=1
            setPeso()
        }
        btnSubtractPesoMenos.setOnClickListener {
           pesoActual -=1
            setPeso()
        }
        btnSubtractEdadMas.setOnClickListener {
            edadActual +=1
            setEdad()
        }

        btnSubtractEdadMenos.setOnClickListener {
            edadActual -=1
            setEdad()
        }

    }


    fun setPeso( ){
//        val textoPeso = tvPeso.text.toString().toInt()+ num
//        pesoActual=textoPeso
//        tvPeso.text = pesoActual.toString()

        tvPeso.setText(pesoActual.toString() + " kg")

    }

    fun setEdad(){
        tvEdad.setText(edadActual.toString() + " años")
    }



    fun getBackgroundColor(isComponentSelected:Boolean):Int{
        val colorReference = if(isComponentSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
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