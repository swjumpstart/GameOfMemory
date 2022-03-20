package com.example.gameofmemory

import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.ui.AppBarConfiguration
import com.example.gameofmemory.databinding.ActivityMainBinding
import java.util.*
import java.util.logging.Level.INFO

class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        //use mutable list to shuffle images for each game
        val images: MutableList<Int> = mutableListOf(R.drawable.bird, R.drawable.butterfly, R.drawable.fish, R.drawable.flask, R.drawable.microscope, R.drawable.molecule,R.drawable.bird, R.drawable.butterfly, R.drawable.fish, R.drawable.flask, R.drawable.microscope, R.drawable.molecule)

        //create array of buttons
        val buttons = listOf<ImageButton>(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9),
            findViewById(R.id.button10),
            findViewById(R.id.button11),
            findViewById(R.id.button12)
        )

        //randomize the images in the array of each of the images
        images.shuffle()

        var clicked = 0
        var turnOver = false
        var compareCardIndex = -1
        var matched = 0

        for (i in 0..11 ) {
            buttons[i].setOnClickListener {

                if (buttons[i].tag.toString() == "OK" && clicked < 2 && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    //Log.i("******BUTTON img: ", images[i].toString())
                    buttons[i].tag = "FaceUp"
                    //if this is the first card, save index to compare
                    if (clicked == 0) {
                        compareCardIndex = i
                    }
                    clicked++
                } else if (buttons[i].tag.toString() == "FaceUp") {
                    buttons[i].setBackgroundResource(R.drawable.heart)
                    buttons[i].tag = "OK"
                    if (compareCardIndex.toString() == i.toString()) {
                        compareCardIndex = -1
                    }
                    clicked--
                }

                if (clicked == 2 && buttons[i].tag == "FaceUp" && buttons[compareCardIndex].tag == "FaceUp") {
                    turnOver = true

                    if (images[i].toString() == images[compareCardIndex].toString()) {
                        Toast.makeText(this@MainActivity, "Congrats! Matched!", Toast.LENGTH_SHORT).show()
                        buttons[i].isClickable = false
                        buttons[compareCardIndex].isClickable = false
                        turnOver = false
                        clicked = 0
                        matched++
                        if (matched > 5) {
                            Toast.makeText(this@MainActivity, "Everything is MATCHED!", Toast.LENGTH_SHORT).show()
                        }

                    } else if (images[i].toString() != images[compareCardIndex].toString()) {
                        turnOver = false
                    }
                }
            }
        }
    }
}
