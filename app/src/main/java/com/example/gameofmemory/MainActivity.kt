package com.example.gameofmemory

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.gameofmemory.R.id.*
import com.example.gameofmemory.R.drawable.*
import com.example.gameofmemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        //use mutable list to shuffle images for each game
        val images: MutableList<Int> = mutableListOf(bird, butterfly, fish, flask, microscope, molecule, testtube)

        //create array of buttons
        val buttons: Array<Int> = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12)
        //val buttons: Array<ImageButton> = arrayOf<ImageButton>(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12)

        //randomize the images on each button
        images.shuffle()
        for(i:Int in 0..11) {
            buttons[i].setBackgroundResource(bird)
        }

    }
}