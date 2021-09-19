package com.example.kbg


import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import com.example.kbg.databinding.ActivityMainBinding
import com.example.kbg.elements.Paper
import com.example.kbg.elements.Rock
import com.example.kbg.elements.Scissors
import com.example.kbg.suit.Suit


class MainActivity : AppCompatActivity() {
    companion object {
        val GAME_SUIT = "GAME_SUIT"
        open fun createLog(msg: String) {
            Log.i(GAME_SUIT, msg)
        }
    }
    private lateinit var p1Move: Suit
    var mp = MediaPlayer()

    private val rock = Rock("rock")
    private val paper = Paper("paper")
    private val scissors = Scissors("scissors")
    private val listElement = arrayOf(rock, paper, scissors)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.constraintLayout.setOnClickListener {
            createLog("==========")
            createLog("GAME SUIT")
            createLog("==========")
        }
        }

    override fun onResume() {
        super.onResume()
        gameSuit()
    }
    private fun gameSuit(){
        createLog("==========")
        createLog("GAME SUIT")
        createLog("==========")
    }


    open fun startSuitWithCom(p1Move: Suit) {

        val random = listElement.random()
        var result = ""
        if (random == listElement.get(0)) {
            createLog("Computer choose rock..")
            result = p1Move.actionVersus(rock).status
            binding.flBatuCom.setBackgroundResource(R.drawable.custom_ripple)
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        if (random == listElement.get(1)) {
            createLog("Computer choose paper..")
            result = p1Move.actionVersus(paper).status
            binding.flKertasCom.setBackgroundResource(R.drawable.custom_ripple)
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        if (random == listElement.get(2)) {
            createLog("Computer choose scissors..")
            result = p1Move.actionVersus(scissors).status
            binding.flGuntingCom.setBackgroundResource(R.drawable.custom_ripple)
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        when (result) {
            "win" -> playerMenang()
            "lose" -> playerKalah()
            "draw" -> gameDraw()
        }

    }

    private fun gameDraw() {
        binding.centerImg.setImageResource(R.drawable.draw)
    }

    private fun playerKalah() {
        binding.centerImg.setImageResource(R.drawable.pemain2_menang)
    }

    private fun playerMenang() {
        binding.centerImg.setImageResource(R.drawable.pemain1_menang)
    }

    private fun mulaiSuit() {
        createLog("MULAI SUIT..")
    }

    fun playPaperSound() {
        mp.release()
        mp = MediaPlayer.create(this@MainActivity, R.raw.paper_sound)
        mp.start()
    }

    fun playRockSound() {
        mp.release()
        mp = MediaPlayer.create(this@MainActivity, R.raw.rock_sound)
        mp.start()
    }

    fun playScissorSound() {
        mp.release()
        mp = MediaPlayer.create(this@MainActivity, R.raw.scissors_sound)
        mp.start()
    }
    fun playButtonSound() {
        mp.release()
        mp = MediaPlayer.create(this@MainActivity, R.raw.button_sound)
        mp.start()
    }


    fun resetGame(v: View) {
        playButtonSound()
        binding.flBatuPlayer.setBackgroundResource(0)
        binding.flKertasPlayer.setBackgroundResource(0)
        binding.flGuntingPlayer.setBackgroundResource(0)
        binding.flBatuCom.setBackgroundResource(0)
        binding.flKertasCom.setBackgroundResource(0)
        binding.flGuntingCom.setBackgroundResource(0)
        binding.centerImg.setImageResource(R.drawable.vs)
        binding.kertasPlayer.isEnabled = true
        binding.guntingPlayer.isEnabled = true
        binding.batuPlayer.isEnabled = true
    }


    fun rockButtonTapped(v: View) {
        playRockSound()
        mulaiSuit()
        p1Move = rock
        createLog("Player 1 choose rock..")
        startSuitWithCom(p1Move)
        binding.flBatuPlayer.setBackgroundResource(R.drawable.custom_ripple)
        binding.kertasPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
        binding.batuPlayer.isEnabled = false
    }

    fun paperButtonTapped(v: View) {
        playPaperSound()
        mulaiSuit()
        p1Move = paper
        createLog("Player 1 choose paper..")
        startSuitWithCom(p1Move)
        binding.flKertasPlayer.setBackgroundResource(R.drawable.custom_ripple)
        binding.batuPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
        binding.kertasPlayer.isEnabled = false
    }

    fun scissorButtonTapped(v: View) {
        playScissorSound()
        mulaiSuit()
        p1Move = scissors
        createLog("Player 1 choose scissors..")
        startSuitWithCom(p1Move)
        binding.flGuntingPlayer.setBackgroundResource(R.drawable.custom_ripple)
        binding.kertasPlayer.isEnabled = false
        binding.batuPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
    }
}