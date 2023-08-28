package com.example.check

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.check.databinding.ActivityMainBinding
import java.lang.ArithmeticException
import java.util.Random

class MainActivity : AppCompatActivity() {
    private var counter=0
    private var multiplyer=0
    private var update_counter=0
    lateinit var bindinginst : ActivityMainBinding
    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
    fun halve_coins(cur:Int):Int{
        val after=(cur/2).toInt()
        return after
    }

    var required_coins = rand(0,100)

    fun fi_update(){
        bindinginst.buttonmulty.visibility=View.VISIBLE
        bindinginst.halveButton.visibility=View.VISIBLE
        counter-=required_coins
        bindinginst.begtxt.text="You have $counter coins"
        update_counter+=1
        required_coins=rand(counter,counter+250)
        bindinginst.buttonUpgrade.setText("$required_coins coins required")
    }

    fun sec_update(){
        bindinginst.buttonsquare.visibility=View.VISIBLE
        bindinginst.buttonsqrt.visibility=View.VISIBLE
        counter-=required_coins
        bindinginst.begtxt.text="You have $counter coins"
        update_counter+=1
        required_coins=rand(counter,counter+10000)
        bindinginst.buttonUpgrade.setText("$required_coins coins required")
    }

    fun last_update(){
        bindinginst.buutonexp.visibility=View.VISIBLE
        bindinginst.buttonln.visibility=View.VISIBLE
        counter-=required_coins
        bindinginst.begtxt.text="You have $counter coins"
        update_counter+=1
        required_coins=rand(counter,counter+10000000)
        bindinginst.buttonUpgrade.setText("$required_coins coins required")
    }

    fun new_game(){
        bindinginst.begtxt.text="You won, start new game"
        update_counter=0
        counter=0
        multiplyer+=1
        bindinginst.textMulty.setText("You won $multiplyer times")
        required_coins=rand(0,100)

        bindinginst.buttonmulty.visibility=View.INVISIBLE
        bindinginst.halveButton.visibility=View.INVISIBLE
        bindinginst.buttonsquare.visibility=View.INVISIBLE
        bindinginst.buttonsqrt.visibility=View.INVISIBLE
        bindinginst.buutonexp.visibility=View.INVISIBLE
        bindinginst.buttonln.visibility=View.INVISIBLE
    }

    fun end_game(){
        bindinginst.begtxt.text="You won, now restart"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinginst= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindinginst.root)

        bindinginst.buttonstart.setText("+1 coin")
        bindinginst.buttonminus.setText("-1 coin")
        bindinginst.buttonmulty.setText("2x coins")
        bindinginst.halveButton.setText("0.5x coins")
        bindinginst.buttonnewgame.setText("Reset progress")
        bindinginst.buttonsquare.setText("x^2 coins")
        bindinginst.buttonsqrt.setText("sqrt x coins")
        bindinginst.buutonexp.setText("e^x coins")
        bindinginst.buttonln.setText("lnx coins")
        bindinginst.textMulty.setText("You won $multiplyer"+" times")



        bindinginst.buttonUpgrade.setText("$required_coins coins required")

        bindinginst.buttonstart.setOnClickListener{
            try{
                counter+=1
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }

        bindinginst.buttonminus.setOnClickListener {
            try{
                counter-=1
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }

        bindinginst.buttonUpgrade.setOnClickListener {
            if(counter==required_coins){

                when(update_counter){
                    0 -> fi_update()
                    1 -> sec_update()
                    2 -> last_update()
                }

            }
        }

        bindinginst.buttonmulty.setOnClickListener{
            try{
                counter*=2
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }

        bindinginst.halveButton.setOnClickListener {
            try{
                counter=halve_coins(counter)
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }

        bindinginst.buttonsquare.setOnClickListener {
            try{
                counter*=counter
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }

        bindinginst.buttonsqrt.setOnClickListener {
            try{
                counter=Math.sqrt(counter.toDouble()).toInt()
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }

        bindinginst.buttonnewgame.setOnClickListener{
            counter=0
            bindinginst.begtxt.text="You have $counter coins"
        }

        bindinginst.buutonexp.setOnClickListener {
            try{
                counter=Math.pow(Math.E,counter.toDouble()).toInt()
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"
            }
        }
        bindinginst.buttonln.setOnClickListener {
            try{
                counter=Math.log(counter.toDouble()).toInt()
                bindinginst.begtxt.text="You have $counter coins"
            } catch (exception:ArithmeticException){
                counter=0
                bindinginst.begtxt.text="You have $counter coins"

            }
        }

        Log.d("LogMainActivity","onCreate_success")
    }



    override fun onStart(){
        super.onStart()
        Log.d("LogMainActivity","onStart_success")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LogMainActivity","onResume_success")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LogMainActivity","onPause_success")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LogMainActivity","onDestroy_success")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LogMainActivity","onStop_success")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LogMainActivity","onRestart_success")
    }
}