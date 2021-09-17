package com.example.kbg


import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import com.example.kbg.elements.Paper
import com.example.kbg.elements.Rock
import com.example.kbg.elements.Scissors
import com.example.kbg.suit.Suit
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val GAME_SUIT = "GAME_SUIT"
        open fun createLog(msg: String) {
            Log.i(GAME_SUIT, msg)
        }
    }

    private lateinit var p1Move: Suit

    //com
    private val rock = Rock("rock")
    private val paper = Paper("paper")
    private val scissors = Scissors("scissors")
    private val listElement = arrayOf(rock, paper, scissors)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintLayout.setOnClickListener {
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
            flBatuCom.setBackgroundResource(R.drawable.custom_ripple)
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        if (random == listElement.get(1)) {
            createLog("Computer choose paper..")
            result = p1Move.actionVersus(paper).status
            flKertasCom.setBackgroundResource(R.drawable.custom_ripple)
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        if (random == listElement.get(2)) {
            createLog("Computer choose scissors..")
            result = p1Move.actionVersus(scissors).status
            flGuntingCom.setBackgroundResource(R.drawable.custom_ripple)
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        when (result) {
            "win" -> playerMenang()
            "lose" -> playerKalah()
            "draw" -> gameDraw()
        }

    }

    private fun gameDraw() {
        center_img.setImageResource(R.drawable.draw)
    }

    private fun playerKalah() {
        center_img.setImageResource(R.drawable.pemain2_menang)
    }

    private fun playerMenang() {
        center_img.setImageResource(R.drawable.pemain1_menang)
    }

    private fun mulaiSuit() {
        createLog("MULAI SUIT..")
    }

    fun playPaperSound() {
        MediaPlayer.create(baseContext, R.raw.paper_sound).start()
    }

    fun playRockSound() {
        MediaPlayer.create(baseContext, R.raw.rock_sound).start()
    }

    fun playScissorSound() {
        MediaPlayer.create(baseContext, R.raw.scissors_sound).start()
    }
    fun playButtonSound() {
            MediaPlayer.create(baseContext, R.raw.button_sound).start()
    }

    fun resetGame(v: View) {
        playButtonSound()
        flBatuPlayer.setBackgroundResource(0)
        flKertasPlayer.setBackgroundResource(0)
        flGuntingPlayer.setBackgroundResource(0)
        flBatuCom.setBackgroundResource(0)
        flKertasCom.setBackgroundResource(0)
        flGuntingCom.setBackgroundResource(0)
        center_img.setImageResource(R.drawable.vs)
        kertasPlayer.isEnabled = true
        guntingPlayer.isEnabled = true
        batuPlayer.isEnabled = true
    }


    fun rockButtonTapped(v: View) {
        playRockSound()
        mulaiSuit()
        p1Move = rock
        createLog("Player 1 choose rock..")
        startSuitWithCom(p1Move)
        flBatuPlayer.setBackgroundResource(R.drawable.custom_ripple)
        kertasPlayer.isEnabled = false
        guntingPlayer.isEnabled = false
        batuPlayer.isEnabled = false
    }

    fun paperButtonTapped(v: View) {
        playPaperSound()
        mulaiSuit()
        p1Move = paper
        createLog("Player 1 choose paper..")
        startSuitWithCom(p1Move)
        flKertasPlayer.setBackgroundResource(R.drawable.custom_ripple)
        batuPlayer.isEnabled = false
        guntingPlayer.isEnabled = false
        kertasPlayer.isEnabled = false
    }

    fun scissorButtonTapped(v: View) {
        playScissorSound()
        mulaiSuit()
        p1Move = scissors
        createLog("Player 1 choose scissors..")
        startSuitWithCom(p1Move)
        flGuntingPlayer.setBackgroundResource(R.drawable.custom_ripple)
        kertasPlayer.isEnabled = false
        batuPlayer.isEnabled = false
        guntingPlayer.isEnabled = false
    }
}