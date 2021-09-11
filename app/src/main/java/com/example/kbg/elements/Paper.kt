package com.example.kbg.elements

import com.example.kbg.suit.ResultSuit
import com.example.kbg.suit.Suit

class Paper(element: String): Suit(element) {
    override fun loseWith(): String {
        return "scissors"
    }

    override fun winFrom(): String {
        return "rock"
    }

    override fun drawFrom(): String {
        return this.element
    }
}