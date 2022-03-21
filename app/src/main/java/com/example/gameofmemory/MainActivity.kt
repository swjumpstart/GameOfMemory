package com.example.gameofmemory

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gameofmemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val mButton = findViewById<Button>(R.id.RestartButton)

        mButton.setOnClickListener {
            val mIntent = intent
                    finish()
            startActivity(mIntent)
        }

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
        var lastCardIndex = -1
        var matchedPairs = 0

        for (i in 0..11 ) {
            buttons[i].setOnClickListener {

                if (buttons[i].tag.toString() == "OK" && clicked < 2 && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].tag = "FaceUp"
                    //if this is the first card, save index to compare
                    if (clicked == 0) {
                        compareCardIndex = i
                    }else if (clicked == 1){
                        lastCardIndex = i
                    }
                    clicked++
                } else if (buttons[i].tag.toString() == "FaceUp") {
                    //Log.i("******cardUpFlip: ", i.toString())
                    buttons[i].setBackgroundResource(R.drawable.heart)
                    buttons[i].tag = "OK"
                    clicked--
                    //if last card is only card face up, this becomes the compare card
                    if (buttons[lastCardIndex].tag.toString() == "FaceUp") {
                        compareCardIndex = lastCardIndex
                    }

                }
                //If 2 cards face up, begin compare
                if (clicked == 2 && buttons[i].tag == "FaceUp" && buttons[compareCardIndex].tag == "FaceUp") {
                    turnOver = true

                    if (images[i].toString() == images[compareCardIndex].toString()) {
                        Toast.makeText(this@MainActivity, "Congrats! Matched!", Toast.LENGTH_SHORT).show()
                        //matching cards stay face up
                        buttons[i].isClickable = false
                        buttons[compareCardIndex].isClickable = false
                        turnOver = false
                        clicked = 0
                        matchedPairs++

                        if (matchedPairs > 5) {
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
